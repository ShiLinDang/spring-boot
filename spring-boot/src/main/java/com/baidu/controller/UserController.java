package com.baidu.controller;

import cn.hutool.json.JSONUtil;
import com.baidu.Excell.Demo;
import com.baidu.Excell.ExcelFormat;
import com.baidu.Excell.ExcelType;
import com.baidu.Excell.ExcellCallable;
import com.baidu.entity.User;
import com.baidu.service.UserService;
import com.google.common.util.concurrent.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Mypc on 2018/3/26 0026.
 */
@RequestMapping("user")
@RestController
public class UserController {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "testUser",method = RequestMethod.GET)
    public String getNickName(){
        List<User> userList = userService.getUserList();
        String nickName = userList.get(0).getNickName();
        System.out.println(nickName);
        return nickName;
    }

    @RequestMapping(value = "getUserName",method = RequestMethod.GET)
    public String getUser(String id){
        User user = userService.getUser(id);
        return "欢迎我们可爱的:"+user.getName();
    }

    @GetMapping("showName")
    public String showName(String id){
        User user = userService.getUser(id);
        executor.schedule(()->printName(user),2,TimeUnit.MINUTES);
        return "哈哈,你好呀:"+user.getName();
    }

    @GetMapping("showExcell")
    public void showExcell(){
        LinkedHashMap<String,String> headers = new LinkedHashMap<>();
        headers.put("username","用户名");
        headers.put("passWord", "密码");
        List<Demo> content = new ArrayList<>();
        Demo demo1 = new Demo("1","1");
        Demo demo2 = new Demo("2","2");
        Demo demo3 = new Demo("3","3");
        Demo demo4 = new Demo("4","4");
        content.add(demo1);
        content.add(demo2);
        content.add(demo3);
        content.add(demo4);

        //写入
        ExcelFormat.from(headers,content)
                .excelType(ExcelType.XLS)
                .build("niuli")
                .write("文件.xls");
        System.out.println("写入成功");
    }

     public void printName(User user){
        System.out.println("***********************************************************************");
        System.out.println(JSONUtil.toJsonStr(user));
        System.out.println("***********************************************************************");
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Scheduled(fixedRate = 100000)
    public void printTime(){
        logger.info("定时任务，展示现在时间："+System.currentTimeMillis());
    }

    @RequestMapping(value = "readExcell",method = RequestMethod.GET)
    public void readExcell(){
        // 获取文件流
        try {
            InputStream inputStream = new FileInputStream("C:/Users/54505/Desktop/原系统数据/用户升级报表.xlsx");
            // 获取Excell工作薄对象
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            // 获取所有的sheet页数量
            int numberOfSheets = workbook.getNumberOfSheets();
            List<List<String>> excellRows = new ArrayList<>();
            for (int i = 0; i <numberOfSheets ; i++) {
                // 遍历获取sheet页
                XSSFSheet sheet = workbook.getSheetAt(i);
                for (int j = 0; j < sheet.getLastRowNum()+1; j++) {
                    XSSFRow row = sheet.getRow(j);
                    List<String> oneRow = new ArrayList<>();
                    for (Cell cell : row) {
                        String stringCellValue = cell.getStringCellValue();
                        oneRow.add(stringCellValue);
                        System.out.println(stringCellValue);
                    }
                    excellRows.add(oneRow);
                }
            }
            // 处理excellRows中的数据
            List<String> colums = null;
            List<User> clazzList = new ArrayList<>();
            for (int i = 0; i < excellRows.size(); i++) {
                // 获取第一行数据(表头,字段)
                List<String> temp = excellRows.get(i);
                if (i==0){
                    // 表头,字段列,长度和每一行的长度相同
                    colums = new ArrayList<>(temp.size());
                    for (String s : temp) {
                        colums.add(s);
                    }
                    continue;
                }
                Class<User> clazz = User.class;
                User user = clazz.newInstance();
                for (int j = 0; j < temp.size(); j++) {
                    // 获取属性名
                    String propertyName = colums.get(j);
                    // 根据属性名称获取属性
                    Field field = clazz.getDeclaredField(propertyName);
                    if (null != field){
                        // 设置属性
                        field.set(user,temp.get(j));
                    }
                }
                clazzList.add(user);
            }

            // 处理集合数据
            clazzList.stream().forEach(user -> {
                user.setId(UUID.randomUUID().toString());
            });
            // 批量插入数据
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程解析Excel
     */
    @RequestMapping(value = "excel",method = RequestMethod.GET)
    public void threadReadExcell(){
        ExecutorService executorService = null;
        try {
            final List<User> arrayList = new ArrayList<>();
            // 创建一个长度为10的定长线程池
            executorService = Executors.newFixedThreadPool(10);
            ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
            // 创建线程任务
            ExcellCallable excellCallable = new ExcellCallable();
            // 线程池执行线程任务
            ListenableFuture listenableFuture = listeningExecutorService.submit(excellCallable);
            // 获取回调,处理逻辑
            Futures.addCallback(listenableFuture, new FutureCallback<List<User>>() {

                @Override
                public void onSuccess(List<User> result) {
                    // 成功(处理后续逻辑)
                    arrayList.addAll(result);
                    System.out.println("====================="+result.size());
                    System.out.println("====================="+arrayList.size());
                }

                @Override
                public void onFailure(Throwable t) {
                    // 失败
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭资源
            executorService.shutdown();
        }
    }
//
//    public static void main(String[] args) {
//        List<User> list = new ArrayList<>();
//
//        User user = new User();
//        user.setName("李白");
//
//        User user2 = new User();
//        user2.setScore(700);
//
//        User user3 = new User();
//        user3.setName("李四");
//
//        list.add(user);
//        list.add(user2);
//        list.add(user3);
//
//        Integer collect = list.stream().filter(e->e.getScore() != null).collect(Collectors.summingInt(User::getScore));
//        System.out.println(collect);
//
//        List<String> collect1 = list.stream().filter(e->e.getName() != null).map(User::getName).collect(Collectors.toList());
//        System.out.println(collect1);
//
//    }

    public static void main(String[] args) {
        try {
//            User user = null;
//            if (null == user){
//                System.out.println("******************为空");
//            }
//            Assert.notNull(user,"对象不能为空");
            List<User> users = null;
            if (CollectionUtils.isEmpty(users)){
                System.out.println("******************为空");
            }
            Assert.notEmpty(users,"集合不能为空");
        } catch (Exception e) {
            System.out.println("*****************************"+e.getMessage());
        }
    }
}

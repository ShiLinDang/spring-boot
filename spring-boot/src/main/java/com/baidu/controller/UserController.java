package com.baidu.controller;

import com.baidu.Excell.ExcellCallable;
import com.baidu.entity.User;
import com.baidu.service.UserService;
import com.google.common.util.concurrent.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mypc on 2018/3/26 0026.
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试用类
     * @return
     */
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
}

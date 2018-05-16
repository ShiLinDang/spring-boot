package com.baidu.Excell;

import cn.hutool.core.util.ObjectUtil;
import com.baidu.entity.User;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author dangsl
 * @description:解析Excell的线程任务
 * @date ${date} ${time}.
 * ${tags}.
 */
public class ExcellCallable implements Callable{

    @Override
    public Object call() throws Exception {
        // 获取文件流对象
        InputStream stream = new FileInputStream("C:\\Users\\54505\\Desktop\\原系统数据\\古真健康-用户.xls");
        // 获取Excel工作薄对象
        Workbook workbookFactory = WorkbookFactory.create(stream);
        // 获取所有的sheet页数量
        int numberOfSheets = workbookFactory.getNumberOfSheets();
        List<User> userList = new ArrayList<>();
        for (int i = 0;i<numberOfSheets;i++){
            Sheet sheet = workbookFactory.getSheetAt(i);
            // 除去表头
            for (int j=1;j<sheet.getLastRowNum()+1;j++){
                // 获取sheet的每一行
                Row row = sheet.getRow(j);
                User user = new User();
                for (int k=0;k<row.getLastCellNum()+1;k++){
                    Cell cell = row.getCell(k);
                    if (ObjectUtil.isNotNull(cell)){
                        switch (k){
                            case 0:
                                System.out.println("==========="+cell.getStringCellValue());
                                user.setId(cell.getStringCellValue());
                                break;
                            case 2:
                                System.out.println("==========="+cell.getStringCellValue());
                                user.setNickName(cell.getStringCellValue());
                                break;
                            case 3:
                                System.out.println("==========="+cell.getStringCellValue());
                                user.setMobile(cell.getStringCellValue());
                                break;
                            case 10:
                                System.out.println("==========="+cell.getStringCellValue());
                                user.setName(cell.getStringCellValue());
                                break;
                        }
                    }
                }
                userList.add(user);
            }
        }
        return userList;
    }
}

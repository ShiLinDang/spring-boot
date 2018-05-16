package com.baidu.Excell;

import com.baidu.entity.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dangsl
 * @description
 * @date ${date} ${time}
 * ${tags}
 */
public class AnalyzeExcell {

    public static void main(String[] args) throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        // 获取文件流
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
        System.out.println(excellRows.size());
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
    }
}

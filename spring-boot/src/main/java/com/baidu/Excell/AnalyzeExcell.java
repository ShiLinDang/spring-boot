package com.baidu.Excell;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author dangsl
 * @description
 * @date ${date} ${time}
 * ${tags}
 */
public class AnalyzeExcell {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        // 获取文件流
        InputStream inputStream = new FileInputStream("C:/Users/54505/Desktop/原系统数据/用户升级报表.xlsx");
        // 获取Excell工作薄对象
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        // 获取所有的sheet页数量
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i <numberOfSheets ; i++) {
            // 遍历获取sheet页
            XSSFSheet sheet = workbook.getSheetAt(i);
            for (int j = 1; j < sheet.getLastRowNum()+1; j++) {
                XSSFRow row = sheet.getRow(j);
                for (Cell cell : row) {
                    String stringCellValue = cell.getStringCellValue();
                    System.out.println(stringCellValue);
                }
            }
        }
    }
}

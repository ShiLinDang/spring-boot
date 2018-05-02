package com.baidu.Excell;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Mypc on 2018/5/2 0002.
 */
public class MakeExcell {
    public static void main(String[] args) throws IOException {
        // 创建一个工作薄对象
        Workbook workbook = new XSSFWorkbook();

        // 创建一个sheet页并命名
        Sheet sheet = workbook.createSheet("sheet1");

        // 创建字体对象
        Font font = workbook.createFont();
        // 设置字体高度
        font.setFontHeightInPoints((short) 20);
        // 设置字体颜色
        font.setColor(Font.COLOR_RED);
        // 设置字体
        font.setFontName("黑体");
        // 设置宽度
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        // 是否使用斜体
        font.setItalic(true);
        //是否使用划线
        font.setStrikeout(true);

        // 设置单元格类型
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        // 水平布局：居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setWrapText(true);

        CellStyle cellStyle2 = workbook.createCellStyle();
        // 用于格式化单元格的数据
        DataFormat format = workbook.createDataFormat();
        cellStyle2.setDataFormat(format.getFormat("＃, ## 0.0"));

        CellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));

        // 定义几行
        for (int rownum = 0; rownum < 10; rownum++) {
            // 创建行
            Row row = sheet.createRow(rownum);
            // 创建单元格
            Cell cell = row.createCell((short)1);
            cell.setCellValue("姓名");// 设置单元格内容
            cell.setCellStyle(cellStyle);// 设置单元格样式
            cell.setCellType(Cell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串

            // 格式化数据
            Cell cell2 = row.createCell((short) 2);
            cell2.setCellValue("电话");
            cell2.setCellStyle(cellStyle2);

            Cell cell3 = row.createCell((short) 3);
            cell3.setCellValue("日期");
            cell3.setCellStyle(cellStyle3);

            sheet.autoSizeColumn((short) 0); // 调整第一列宽度
            sheet.autoSizeColumn((short) 1); // 调整第二列宽度
            sheet.autoSizeColumn((short) 2); // 调整第三列宽度
            sheet.autoSizeColumn((short) 3); // 调整第四列宽度

        }

         // 保存
         String filename = "C:/Users/Mypc/Desktop/workbook.xlsx";
         FileOutputStream out = new FileOutputStream(filename);
         workbook.write(out);
         out.close();
    }
}

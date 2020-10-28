package com.itheima.service.store;

import com.itheima.domain.store.Question;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxq
 * @create 2020-08-29 10:51
 */
public class PoiTest {
    @Test
    public void testWriteByPoi() throws IOException {
        //1.获取到对应的Excel文件，工作簿文件
        Workbook wb = new XSSFWorkbook();

        //2.创建工作表
        Sheet sheet = wb.createSheet();

        //3.创建工作表中的行对象
        Row row = sheet.createRow(1);
        //4.创建工作表中行的列对象
        Cell cell = row.createCell(1);
        //5.在单元格中写数据
        cell.setCellValue("测试");


        //创建一个文件对象，作为excel文件内容的输出文件
        File file = new File("test.xlsx");
        //输出时通过流的形式对外输出，包装对应的文件
        OutputStream os = new FileOutputStream(file);
        //将内存中的workbook数据写入流中
        wb.write(os);

        wb.close();
    }

    @Test
    public void testReadPoi() throws IOException {
        //1.获取要读取的文件工作簿对象
        Workbook wb = new XSSFWorkbook("test.xlsx");

        //2.获取工作表
        Sheet sheet = wb.getSheetAt(0);

        //3.获取行
        Row row = sheet.getRow(1);
        //4.获取列
        Cell cell = row.getCell(1);
        //5.获取单元格的值
        String stringCellValue = cell.getStringCellValue();

        System.out.println(stringCellValue);

        wb.close();
    }

    @Test
    public void testProjectPoi() throws IOException {
        //1.获取到对应的Excel文件，工作簿文件
        Workbook wb = new XSSFWorkbook();

        //2.创建工作表
        Sheet sheet = wb.createSheet("数据文件");

        //设置通用配置
        CellStyle cellStyle_field = wb.createCellStyle();
        cellStyle_field.setAlignment(HorizontalAlignment.CENTER);
        cellStyle_field.setBorderTop(BorderStyle.THIN);
        cellStyle_field.setBorderRight(BorderStyle.THIN);
        cellStyle_field.setBorderBottom(BorderStyle.THIN);
        cellStyle_field.setBorderLeft(BorderStyle.THIN);

        //3.制作标题
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 12));

        //单元格填值
        Row row_1 = sheet.createRow(1);
        Cell cell_1_1 = row_1.createCell(1);
        cell_1_1.setCellValue("在线试题导出信息");

        //水平垂直居中
        CellStyle cellStyle_title = wb.createCellStyle();
        cellStyle_title.setAlignment(HorizontalAlignment.CENTER);
        cellStyle_title.setVerticalAlignment(VerticalAlignment.CENTER);
        cell_1_1.setCellStyle(cellStyle_title);


        //4.制作表头
        String[] fields = {"题目ID", "所属公司ID", "所属目录ID", "题目简介", "题干描述", "题干配图",
                "题目分析", "题目类型", "题目难度", "是否经典题", "题目状态", "审核状态"};


        //单元格填值
        Row row_2 = sheet.createRow(2);

        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_temp = row_2.createCell(1 + i);
            cell_2_temp.setCellValue(fields[i]);

            //水平居中
            cell_2_temp.setCellStyle(cellStyle_field);
        }


        //5.制作数据区

        List<Question> questionList = new ArrayList<>();

        //单元格填值
        int row_index = 0;
        for (Question q : questionList) {
            Row row_temp = sheet.createRow(3+row_index++);
            int cell_index = 0;

            Cell cell_data_1 = row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());
            cell_data_1.setCellStyle(cellStyle_field);

            Cell cell_data_2 = row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getCompanyId());
            cell_data_2.setCellStyle(cellStyle_field);

            Cell cell_data_3 = row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getCatalogId());
            cell_data_3.setCellStyle(cellStyle_field);

            Cell cell_data_4 = row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getRemark());
            cell_data_4.setCellStyle(cellStyle_field);

            Cell cell_data_5 = row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getSubject());
            cell_data_5.setCellStyle(cellStyle_field);

            Cell cell_data_6 = row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getPicture());
            cell_data_6.setCellStyle(cellStyle_field);

            Cell cell_data_7 = row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getAnalysis());
            cell_data_7.setCellStyle(cellStyle_field);

            Cell cell_data_8 = row_temp.createCell(1 + cell_index++);
            cell_data_8.setCellValue(q.getType());
            cell_data_8.setCellStyle(cellStyle_field);

            Cell cell_data_9 = row_temp.createCell(1 + cell_index++);
            cell_data_9.setCellValue(q.getDifficulty());
            cell_data_9.setCellStyle(cellStyle_field);

            Cell cell_data_10 = row_temp.createCell(1 + cell_index++);
            cell_data_10.setCellValue(q.getIsClassic());
            cell_data_10.setCellStyle(cellStyle_field);

            Cell cell_data_11 = row_temp.createCell(1 + cell_index++);
            cell_data_11.setCellValue(q.getState());
            cell_data_11.setCellStyle(cellStyle_field);

            Cell cell_data_12 = row_temp.createCell(1 + cell_index++);
            cell_data_12.setCellValue(q.getReviewStatus());
            cell_data_12.setCellStyle(cellStyle_field);
        }


        //创建一个文件对象，作为excel文件内容的输出文件
        File file = new File("test.xlsx");
        //输出时通过流的形式对外输出，包装对应的文件
        OutputStream os = new FileOutputStream(file);
        //将内存中的workbook数据写入流中
        wb.write(os);

        wb.close();

    }
}

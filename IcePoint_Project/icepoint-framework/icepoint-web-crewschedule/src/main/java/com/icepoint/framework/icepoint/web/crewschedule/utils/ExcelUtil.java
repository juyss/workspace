package com.icepoint.framework.icepoint.web.crewschedule.utils;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Juyss (Copy from park)
 * @version 1.0
 * @ClassName ExcelUtils
 * @description
 * @since 2021-07-30 15:25
 */
public class ExcelUtil {

    /**
     * 编码导出的中文名，解决中文文件名乱码问题
     * @param fileName 文件名
     * @return String
     * @author zhangzhong
     * date: 2020/2/28 15:44
     */
    public static String encodingFileName(String fileName, HttpServletRequest request) {
        String returnFileName = "";
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && agent.contains("MSIE") || null != agent
                    && agent.contains("Trident") || null != agent && agent.contains("Edge")) { // ie浏览器及Edge浏览器
                returnFileName = URLEncoder.encode(fileName, "UTF-8");
            } else { // 火狐,Chrome等浏览器
                returnFileName = URLEncoder.encode(fileName, "UTF-8");
                returnFileName = StringUtil.replace(
                        returnFileName, "+", "%20");
                if ((!StringUtil.isInvalid(returnFileName))
                        && returnFileName.contains("%")) {
                    returnFileName = new String(fileName.getBytes("GB2312"),
                            "ISO8859-1");
                    returnFileName = StringUtil.replace(
                            returnFileName, " ", "%20");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }

    /**
     * 通过easyExcel导出文件
     * @param response
     * @param request
     * @param fileName
     * @param dataList
     * @param clazz
     * @param headList
     * @param sheetNo
     * @param headRowIndex
     */
    public static void exportBeanByEasyExcel(HttpServletResponse response, HttpServletRequest request, String fileName, List<?> dataList, Class<?> clazz, List<List<String>> headList, int sheetNo, int headRowIndex) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriterBuilder()
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();

            WriteSheet sheet = new WriteSheet();
            sheet.setSheetNo(sheetNo);
            sheet.setClazz(clazz);
            sheet.setRelativeHeadRowIndex(headRowIndex);
            sheet.setSheetName(fileName);
            WriteTable writeTable = EasyExcel.writerTable().head(headList).build();
            writer.write(dataList, sheet, writeTable);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + encodingFileName(fileName+ ".xlsx", request));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

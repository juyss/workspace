package util;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * excel文件处理类
 * Copyright (C), 2020-2020, IoT数据采集测试
 * @author zhangzhong
 * @version 1.0
 * date: 2020/2/27 16:38
 * history:
 */
public class ExcelUtil {

    /**
     * 拼接get方法名
     * @param variablesName 实体类属性名
     * @return String
     * @author zhangzhong
     * date: 2020/2/28 18:06
     */
    public static String appendGetMethod(String variablesName) {
        StringBuffer methodName = new StringBuffer();
        methodName.append("get")
                .append(Character.toUpperCase(variablesName.charAt(0)))
                .append(variablesName.substring(1)).toString();
        return methodName.toString();
    }

    /**
     * 拼接is方法名
     * @param variablesName 实体类属性名
     * @return String
     * @author zhangzhong
     * 2020/7/19 17:52
     */
    public static String appendIsMethod(String variablesName) {
        if (variablesName.startsWith("is")) {
            return variablesName;
        } else {
            StringBuffer methodName = new StringBuffer();
            methodName.append("is")
                    .append(Character.toUpperCase(variablesName.charAt(0)))
                    .append(variablesName.substring(1)).toString();
            return methodName.toString();
        }
    }

    /**
     * 拼接set方法名
     * @param variablesName 实体类属性名
     * @return String
     * @author zhangzhong
     * 2020/7/21 14:08
     */
    public static String appendSetMethod(String variablesName) {
        StringBuffer methodName = new StringBuffer();
        methodName.append("set")
                .append(Character.toUpperCase(variablesName.charAt(0)))
                .append(variablesName.substring(1)).toString();
        return methodName.toString();
    }

    /**
     * 居中合并单元格
     * @param sheet 目标表表格
     * @param row_s 起始行
     * @param row_e 结尾行
     * @param col_s 其实列
     * @param col_e 结尾列
     * @author zhangzhong
     * date: 2020/2/28 15:19
     */
    public static void mergeAndCenter(HSSFSheet sheet, int row_s, int row_e, int col_s, int col_e) {
        sheet.addMergedRegion(new CellRangeAddress(row_s, row_e, col_s, col_e));
		/*if(WORKBOOK == null){
			WORKBOOK = sheet.getWorkbook();
		}*/
        HSSFCellStyle centerStyle = (HSSFCellStyle) sheet.getWorkbook().createCellStyle();
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        addSheetCell(sheet, row_s, col_s, centerStyle, null);

    }

    /**
     * 新增单位细胞
     * @param sheet  目标表格
     * @param rowNum 行数
     * @param colNum 列数
     * @param style  格式
     * @param font   字体
     * @author zhangzhong
     * date: 2020/2/28 15:21
     */
    public static void addSheetCell(HSSFSheet sheet, int rowNum, int colNum, HSSFCellStyle style, HSSFFont font) {
        HSSFCell cell = null;
        if (sheet.getRow(rowNum) == null) {
            cell = sheet.createRow(rowNum).createCell(colNum);
        } else {
            HSSFRow row = sheet.getRow(rowNum);
            if (row.getCell(colNum) == null) {
                cell = row.createCell(colNum);
            }
        }
        if (style != null) {
            if (font != null) {
                style.setFont(font);
            }
            cell.setCellStyle(style);
        }
    }

    /**
     * 设置列宽
     * @param sheet         目标表格
     * @param columnWithMap 各列列宽
     * @return HSSFSheet
     * @author zhangzhong
     * date: 2020/2/27 17:12
     */
    public static HSSFSheet setColumnsWith(HSSFSheet sheet, Map<Integer, Integer> columnWithMap) {
        if (!columnWithMap.isEmpty()) {
            for (Map.Entry<Integer, Integer> entry : columnWithMap.entrySet()) {
                sheet.setColumnWidth(entry.getKey(), entry.getValue());
            }
        }
        return sheet;
    }

    /**
     * 设置列宽
     * @param sheet       目标表格
     * @param columnWiths 各列列宽
     * @return HSSFSheet
     * @author zhangzhong
     * date: 2020/2/28 15:26
     */
    public static HSSFSheet setColumnsWith(HSSFSheet sheet, List<Integer> columnWiths) {
        return setColumnsWith(sheet, columnWiths, null);
    }

    /**
     * 设置列宽
     * @param sheet       目标表格
     * @param columnWiths 各列列宽
     * @param start       起始列
     * @return HSSFSheet
     * date: 2020/2/27 17:13
     * @author zhangzhong
     */
    public static HSSFSheet setColumnsWith(HSSFSheet sheet, List<Integer> columnWiths, Integer start) {
        if (columnWiths != null && columnWiths.size() > 0) {
            int i = 0;
            if (start != null) {
                i = start;
            }
            for (Integer columnWith : columnWiths) {
                sheet.setColumnWidth(i++, columnWith * 256);
            }
        }
        return sheet;
    }


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
     * @param name               表格名称
     * @param title              表格标题
     * @param attribute_name_map 属性、名称对应图
     * @param columnsWidth       各列列宽
     * @param dataArray          装填数据
     * @return Workbook
     * @author zhangzhong
     * date: 2020/3/13 18:53
     */
    public static Workbook builtExportWorkbook(String name, String title,
                                               Map<String, String> attribute_name_map, List<Integer> columnsWidth, List<?> dataArray) {

        //验证参数，参数无效时抛出异常
        ExcelUtil.ValidParam.validName(name);
        ExcelUtil.ValidParam.validTitle(title);
        ExcelUtil.ValidParam.validMap(attribute_name_map);
        ExcelUtil.ValidParam.validArray(columnsWidth);
//        ExcelUtil.ValidParam.validArray(dataArray);

        Class<?> clazz = null;
        if (dataArray != null && dataArray.size() > 0) {
            clazz = dataArray.get(0).getClass();
        } else {
            clazz = new Object().getClass();
        }
        ExcelDocument document = ExcelUtil.parseExcelDocument(attribute_name_map, clazz);
        Sheet sheet = new ExcelUtil().new SheetOperator(name)
                .withTitle(title, attribute_name_map.size())
                .withHead(document.getColumnNames())
                .setColumnsWidth(columnsWidth)
                .addData(document.getAssignmentMethodMap(), document.getNodeMethondMap(), dataArray)
                .withDefaultTemplate()
                .end();
        return sheet.getWorkbook();

    }

    /**
     * 表格元素内部类
     */
    public class ExcelDocument {
        private List<String> columnNames;
        private Map<String, Method> assignmentMethodMap;
        private Map<String, Method> nodeMethondMap;

        public List<String> getColumnNames() {
            return columnNames;
        }

        public void setColumnNames(List<String> columnNames) {
            this.columnNames = columnNames;
        }

        public Map<String, Method> getAssignmentMethodMap() {
            return assignmentMethodMap;
        }

        public void setAssignmentMethodMap(Map<String, Method> assignmentMethodMap) {
            this.assignmentMethodMap = assignmentMethodMap;
        }

        public Map<String, Method> getNodeMethondMap() {
            return nodeMethondMap;
        }

        public void setNodeMethondMap(Map<String, Method> nodeMethondMap) {
            this.nodeMethondMap = nodeMethondMap;
        }
    }

    /**
     * 解析属性、名称对应图
     * @param attribute_name_map 属性、名称对应图
     * @param clazz              实体类类型
     * @return ExcelDocument
     * @author zhangzhong
     * date: 2020/3/13 18:55
     */
    public static ExcelDocument parseExcelDocument(Map<String, String> attribute_name_map, Class<?> clazz) {
        ExcelDocument document = new ExcelUtil().new ExcelDocument(); //解析结果容器
        List<String> columnsName = new ArrayList<>(); //各列列名结果集
        Map<String, Method> assignmentMethodMap = new LinkedHashMap<String, Method>(); //各列取值函数（名称、get方法）图
        Set<String> nodeMethodsName = new HashSet<String>(); //节点方法集，用于二级实体获取方法去重
        Map<String, Method> nodeMethodMap = new HashMap<String, Method>(); //二级实体获取方法（名称、get方法）图

        /**
         * 遍历属性、名称图，解析出各列名称结果集、取值方法图
         */
        for (Map.Entry<String, String> attributeName : attribute_name_map.entrySet()) {
            columnsName.add(attributeName.getValue());
            String variablesName = attributeName.getKey();
            try {
                if (!clazz.getName().equals("java.lang.Object")) {
                    if (variablesName.contains(".")) {
                        String[] nodeNames = variablesName.split("\\.");
                        String nodeMethodName = ExcelUtil.appendGetMethod(nodeNames[0]);
                        String assignmentMethodName = ExcelUtil.appendGetMethod(nodeNames[1]);
                        Method nodeMethod = null;
                        if (nodeMethodsName.add(nodeMethodName)) {
                            nodeMethod = clazz.getMethod(nodeMethodName);
                            nodeMethodMap.put(nodeMethodName, nodeMethod);
                        } else {
                            nodeMethod = nodeMethodMap.get(nodeMethodName);
                        }
                        Class<?> nodeClazz = nodeMethod.getReturnType();
                        Method method = nodeClazz.getMethod(assignmentMethodName);
                        assignmentMethodMap.put(nodeMethodName + "," + assignmentMethodName, method);
                    } else {
                        String assignmentMethodName = ExcelUtil.appendGetMethod(variablesName);
                        Method method = clazz.getMethod(assignmentMethodName);
                        assignmentMethodMap.put(assignmentMethodName, method);
                    }
                }
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        document.setAssignmentMethodMap(assignmentMethodMap);
        document.setNodeMethondMap(nodeMethodMap);
        document.setColumnNames(columnsName);
        return document;
    }

    /**
     * 表格操作内部类
     * Copyright (C), 2020-2020, IoT数据采集测试
     * @author zhangzhong
     * @version 1.0
     * date: 2020/3/13 18:58
     * history:
     */
    public class SheetOperator {

        private HSSFWorkbook workbook;
        private String sheetName;

        public SheetOperator(String sheetName) {
            this.workbook = new HSSFWorkbook();
            this.sheetName = sheetName;
            workbook.createSheet(sheetName);
        }

        public HSSFSheet getSheet() {
            return this.workbook.getSheet(sheetName);
        }

        /**
         * 添加标题
         * @param title  表格标题
         * @param colNum 列数
         * @return SheetOperator
         * @author zhangzhong
         * date: 2020/3/13 18:59
         */
        public SheetOperator withTitle(String title, int colNum) {
            ExcelUtil.mergeAndCenter(getSheet(), 0, 0, 0, colNum - 1);
            HSSFRow rowTitle = getSheet().getRow(0);
            rowTitle.setHeightInPoints(40);
            HSSFCell cellTitle = rowTitle.getCell(0);
            cellTitle.setCellValue(title);
            return this;
        }


        /**
         * 添加各列列名
         * @param columnsName 各列列名
         * @return SheetOperator
         * @author zhangzhong
         * date: 2020/3/13 19:00
         */
        public SheetOperator withHead(List<String> columnsName) {
            HSSFRow rowHead = null;
            if (getSheet().getRow(0) == null) {
                rowHead = getSheet().createRow(0);
            } else {
                rowHead = getSheet().createRow(1);
            }
            int i = 0;
            for (String columnName : columnsName) {
                rowHead.createCell(i++).setCellValue(columnName);
            }
            return this;
        }

        /**
         * 设置列宽
         * @param columnsWidth 各列列宽
         * @return SheetOperator
         * @author zhangzhong
         * date: 2020/3/13 19:01
         */
        public SheetOperator setColumnsWidth(List<Integer> columnsWidth) {
            if (columnsWidth != null && columnsWidth.size() > 0) {
                int i = 0;
                for (Integer columnWith : columnsWidth) {
                    getSheet().setColumnWidth(i++, columnWith * 256);
                }
            }
            return this;
        }

        /**
         * 添加数据
         * @param <T>                 泛型类
         * @param assignmentMethodMap 取值方法图
         * @param nodeMethodMap       节点方法图
         * @param list                装填数据
         * @return SheetOperator
         * @author zhangzhong
         * date: 2020年3月13日下午7:02:19
         */
        public <T> SheetOperator addData(Map<String, Method> assignmentMethodMap,
                                         Map<String, Method> nodeMethodMap, List<T> list) {
            int dataRowStart = 0;
            if (getSheet().getRow(0) != null) {
                dataRowStart = 1;
                if (getSheet().getRow(1) != null) {
                    dataRowStart = 2;
                }
            }
            for (T bean : list) {
                HSSFRow row = getSheet().createRow(dataRowStart++);
                int i = 0;
                for (Map.Entry<String, Method> method : assignmentMethodMap.entrySet()) {
                    HSSFCell cell = row.createCell(i++);
                    try {
                        String type = method.getValue().getGenericReturnType().getTypeName();
                        Object value = null;
                        if (!method.getKey().contains(",")) {
                            value = method.getValue().invoke(bean);
                        } else {
                            Method nodeMethod = nodeMethodMap.get(method.getKey().split(",")[0]);
                            Object o = nodeMethod.invoke(bean);
                            if (o != null) {
                                value = method.getValue().invoke(o);
                            }
                        }
                        switch (type) {
                            default:
                                cell.setCellValue((String) value);
                                break;
                            case "int":
                                cell.setCellValue(value == null ? 0 : (int) value);
                                break;
                            case "java.lang.Integer":
                                cell.setCellValue(value == null ? 0 : (Integer) value);
                                break;
                            case "long":
                                cell.setCellValue(value == null ? 0 : (long) value);
                                break;
                            case "java.lang.Long":
                                cell.setCellValue(value == null ? 0 : (Long) value);
                                break;
                            case "double":
                                cell.setCellValue(value == null ? 0 : (double) value);
                                break;
                            case "java.lang.Double":
                                cell.setCellValue(value == null ? 0 : (Double) value);
                                break;
                            case "java.util.Date":
                                cell.setCellValue(value == null ? "" : TimeUtil.formatDateyyyyMMdd((Date) value));
                                break;
                            case "java.sql.Timestamp":
                                cell.setCellValue(value == null ? "" : TimeUtil.formatDateyyyyMMddHHmmss((Timestamp) value));
                                break;
                            case "java.math.BigDecimal":
                                cell.setCellValue(value == null ? "" : value.toString());
                                break;
                        }
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return this;
        }

        /**
         * 使用默认模板
         * @return SheetOperator
         * @author zhangzhong
         * date: 2020/3/14 11:02
         */
        public SheetOperator withDefaultTemplate() {

            //设置标题单元格格式、字体
            HSSFCell titleCell = getSheet().getRow(0).getCell(0);
            HSSFCellStyle style = titleCell.getCellStyle();
            HSSFCellStyle titleStyle = (HSSFCellStyle) workbook.createCellStyle();
            if (style != null) {
                titleStyle.cloneStyleFrom(style);
            }
            HSSFFont titleFont = (HSSFFont) workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontName("宋体");
            titleFont.setFontHeightInPoints((short) 18);
            titleStyle.setFont(titleFont);
    		/*style_1.setBorderBottom(BorderStyle.MEDIUM);
    		style_1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    		style_1.setBorderLeft(BorderStyle.MEDIUM);
    		style_1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    		style_1.setBorderRight(BorderStyle.MEDIUM);
    		style_1.setRightBorderColor(IndexedColors.BLACK.getIndex());
    		style_1.setBorderTop(BorderStyle.MEDIUM);
    		style_1.setTopBorderColor(IndexedColors.BLACK.getIndex());*/
            titleCell.setCellStyle(titleStyle);

            //设置列表头部单元格格式、字体
            HSSFRow headRow = getSheet().getRow(1);
            HSSFCellStyle headStyle = (HSSFCellStyle) workbook.createCellStyle();
            HSSFFont headFont = (HSSFFont) workbook.createFont();
            headFont.setFontName("黑体");
            headFont.setFontHeightInPoints((short) 10);
            headStyle.setFont(headFont);
            headStyle.setAlignment(HorizontalAlignment.CENTER);
            int i = 0;
            HSSFCell cell = null;
            while ((cell = headRow.getCell(i++)) != null) {
                cell.setCellStyle(headStyle);
            }
            return this;
        }

        /**
         * 获取处理后的表格
         * @return HSSFSheet
         * @author zhangzhong
         * date: 2020/3/13 19:04
         */
        public HSSFSheet end() {
            return getSheet();
        }
    }

    /**
     * 参数验证内部类
     * Copyright (C), 2020-2020, IoT数据采集测试
     * @author zhangzhong
     * @version 1.0
     * date: 2020/3/14 13:14
     * history:
     */
    public static class ValidParam {

        /**
         * 验证名称
         * @param name 名称
         * @author zhangzhong
         * date: 2020年3月14日下午1:14:56
         */
        public static void validName(String name) {
            if (name == null || name.length() < 2) {
                throw new RuntimeException("表格名不少于两个字符");
            }
        }

        /**
         * 验证标题
         * @param title 标题
         * @author zhangzhong
         * date: 2020/3/14 13:15
         */
        public static void validTitle(String title) {
            if (title == null || title.length() < 2) {
                throw new RuntimeException("标题名不少于两个字符");
            }
        }

        /**
         * 验证图
         * @param map 图
         * @author zhangzhong
         * date: 2020/3/14 13:15
         */
        public static void validMap(Map<?, ?> map) {
            if (map == null || map.size() < 1) {
                throw new RuntimeException("无意义图，图至少装填一个数据");
            }
        }

        /**
         * 验证列表
         * @param list 列表
         * @author zhangzhong
         * date: 2020/3/14 13:16
         */
        public static void validArray(List<?> list) {
            validArray(list, null);
        }

        public static void validArray(List<?> list, String msg) {
            if (list == null || list.size() < 1) {
                if (msg == null) {
                    throw new RuntimeException(msg);
                } else {
                    throw new RuntimeException("无意义列表，列表至少装填一个数据");
                }
            }
        }
    }

    /**
     * 通过easyExcel导出文件
     * @param response
     * @param fileName
     * @param list
     * @param clazz
     * @param sheetNo
     * @param headLineMun
     */
    public static void exportBeanByEasyExcel(HttpServletResponse response, HttpServletRequest request, String fileName, List<?> list, Class<?> clazz, int sheetNo, int headLineMun) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriterBuilder()
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();

            WriteSheet sheet = new WriteSheet();
            sheet.setSheetNo(sheetNo);
            sheet.setClazz(clazz);
            sheet.setRelativeHeadRowIndex(headLineMun);
            sheet.setSheetName(fileName);
            writer.write(list, sheet);
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

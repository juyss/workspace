package com.icepoint.framework.workorder.work.service.impl;

import com.icepoint.framework.web.system.dao.AssetMapper;
import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.service.ResourceService;
import com.icepoint.framework.workorder.work.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private ResourceService resourceService;

    private AssetMapper assetMapper;

    @Override
    public List<Map<String, Object>> uploadFile(File file, Integer startLine, List<String> fields) {
        try (FileInputStream inputStream = new FileInputStream(file)){
            //获取工作薄
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            //获取标题类容
            XSSFRow rowTitle = sheet.getRow(0);
            //标题集合
            List<String> titleList = new ArrayList<>();
            if (rowTitle != null) {
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    XSSFCell cell = rowTitle.getCell(cellNum);
                    if (cell != null) {
                        String cellValue = cell.getStringCellValue();
                        titleList.add(cellValue);
                    }
                }
            }
            //需要判断标题和字段是否匹配
            List<Map<String, Object>> allList = null;
            titleList.retainAll(fields);
            if (titleList.size() != fields.size()) {
                throw new IllegalArgumentException("字段和excel文件标题不匹配");
            }
            allList = new ArrayList<>();
            //获取内容
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int rowNum = 1; rowNum < rowCount; rowNum++) {
                Map<String, Object> map = new HashMap<>(rowNum);
                Row rowDate = sheet.getRow(rowNum);
                if (rowDate != null) {
                    //读取列
                    assert rowTitle != null;
                    int cellCount = rowTitle.getPhysicalNumberOfCells();
                    for (int cellNum = 0; cellNum < cellCount; cellNum++) {

                        Cell cell = rowDate.getCell(cellNum);
                        //匹配列的类型
                        if (cell != null) {
                            switch (cell.getCellTypeEnum()) {
                                case NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        //日期格式
                                        Date date = cell.getDateCellValue();
                                        DateFormat formater = new SimpleDateFormat("yyyy-MM");
                                        String s = formater.format(date);
                                        map.put(titleList.get(cellNum), s);
                                    } else {
                                        map.put(titleList.get(cellNum), String.valueOf(cell.getNumericCellValue()));
                                    }
                                    break;
                                case STRING:
                                    map.put(titleList.get(cellNum), String.valueOf(cell.getNumericCellValue()));
                                    break;
                                case FORMULA:
                                    break;
                                case BLANK:
                                    map.put(titleList.get(cellNum), null);
                                    break;
                                case BOOLEAN:
                                    map.put(titleList.get(cellNum), String.valueOf(cell.getBooleanCellValue()));
                                    break;
                                case ERROR:
                                    log.error("错误");
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
                allList.add(map);
            }
            return allList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer uploadProcess(List<Map<String, Object>> list, Long assetsId, Integer mode) {
        // 根据资产ID查询出来字段信息
        AssetDefine assetDefine = assetMapper.findById(assetsId).orElseThrow(() -> new IllegalArgumentException("资产为空"));
        // FIXME
        TableMetadata tableMetadata = null;
//                resourceService.getInfoByResourceCode(assetsDefine.getResourceCode());
        //获取所有的字段
        List<FieldMetadata> fields = tableMetadata.getFieldMetadatas();
        //根据mode确定是添加还是更新 mode 1添加 2跟新
        if (mode.equals(1)) {
            for (Map<String, Object> map : list) {
                for (FieldMetadata field : fields) {
                    if (map.containsKey(field.getNameEn())) {
                        //获取每个值
                        map.get(field);
                        //TODO
                    }
                }


            }
        }
        //循环数据，根据检查标识checkMark确定是否导入，正确和警告类型会处理，错误类型忽略不处理
        return null;
    }


}

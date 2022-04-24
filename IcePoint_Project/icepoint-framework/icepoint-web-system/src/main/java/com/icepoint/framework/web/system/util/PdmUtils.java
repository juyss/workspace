package com.icepoint.framework.web.system.util;


import com.icepoint.framework.web.system.model.Column;
import com.icepoint.framework.web.system.model.Table;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 解析pdm TODO 这只是基础的解析   更复杂的pdm文件
 *
 * @author ck
 * @version 1.0
 * @date 2021/5/19 13:54
 */
@Slf4j
public class PdmUtils {
    public static Table[] parsePDM_VO(String filePath) {
        Table[] tabs = new Table[]{};
        List<Table> voS = new ArrayList<Table>();
        Table vo = null;
        Column[] cols = null;
        File f = new File(filePath);
        SAXReader sr = new SAXReader();
        Document doc = null;
        try {
            doc = sr.read(f);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Iterator itr = doc.selectNodes("//c:Tables//o:Table").iterator();
        while (itr.hasNext()) {
            vo = new Table();
            cols = new Column[]{};
            List<Column> list = new ArrayList<Column>();
            Column col = null;
            Element eTable = (Element) itr.next();

            vo.setName(eTable.elementTextTrim("Name"));
            vo.setNameEn(eTable.elementTextTrim("Code"));
            vo.setDescription(eTable.elementTextTrim("Comment"));
            Iterator itr1 = eTable.element("Columns").elements("Column").iterator();
            while (itr1.hasNext()) {
                try {
                    col = new Column();
                    Element eCol = (Element) itr1.next();
                    //id
                    String pkID = eCol.attributeValue("Id");
                    //默认值
                    col.setDefaultValue(eCol.elementTextTrim("DefaultValue"));
                    //名称
                    col.setName(eCol.elementTextTrim("Name"));
                    if (eCol.elementTextTrim("DataType").indexOf("(") > 0) {
                        col.setType(eCol.elementTextTrim("DataType")
                                .substring(0, eCol.elementTextTrim("DataType").indexOf("(")));
                    } else {
                        col.setType(eCol.elementTextTrim("DataType"));
                    }
                    //字段类型
                    col.setNativeType(eCol.elementTextTrim("DataType"));
                    //字段
                    col.setNameEn(eCol.elementTextTrim("Code"));
                    //字段长度
                    col.setMaxlen(eCol.elementTextTrim("Length") == null ? null : Integer.parseInt(eCol.elementTextTrim("Length")));
                    //字段精度
                    col.setFractional(eCol.elementTextTrim("Precision"));
                    if (eTable.element("Keys") != null) {
                        String keyID = eTable.element("Keys").element("Key").attributeValue("Id");
                        String keys_column_ref = eTable.element("Keys").element("Key").element("Key.Columns")
                                .element("Column").attributeValue("Ref");
                        String keys_primarykey_ref_id = eTable.element("PrimaryKey")
                                .element("Key")
                                .attributeValue("Ref");

                        if (keys_primarykey_ref_id.equals(keyID) && keys_column_ref.equals(pkID)) {
                            col.setPrimaryKey(true);
                            vo.setPkField(col.getNameEn());
                        }
                    }
                    col.setUniqueidx(eCol.elementTextTrim("Column.Mandatory"));
                    //最大值
                    col.setMaxVal(eCol.elementTextTrim("HighValue"));
                    //最小值
                    col.setMinVal(eCol.elementTextTrim("LowValue"));
                    list.add(col);
                    log.info("字段名{}", col);
                } catch (Exception ex) {
                    log.error("解析错误,错误消息:{}", ex.getMessage());

                    ex.printStackTrace();
                }
            }
            vo.setCols(list.toArray(cols));
            voS.add(vo);
            log.info("{解析的表为}", vo);
            log.info("======================");
        }
        return voS.toArray(tabs);
    }

    public static void initTable(Table[] tabs) {
        List<String> list = new ArrayList();
        for (Table tab : tabs) {
            list.add(tab.getName());
            log.info("表名为{}", tab.getName());
        }

    }


}

package com.icepoint.framework.web.system.util;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.model.Attr;
import com.icepoint.framework.web.system.model.Code;
import com.icepoint.framework.web.system.service.FieldMetadataService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ck
 * @version 1.0
 * @date 2021/5/24 14:12
 */
@Component
public class CreateCode {

    // 数据库类型转属性类型
    public static Map<String, String> dataTypeMap;

    @Autowired
    private FieldMetadataService fieldMetadataService;

    @Autowired
    private Configuration freeMarker;


    static {
        dataTypeMap = new HashMap<String, String>();
        dataTypeMap.put("bigint", "Long");
        dataTypeMap.put("tinyint", "Integer");
        dataTypeMap.put("varchar", "String");
        dataTypeMap.put("int", "Integer");
        dataTypeMap.put("number", "Integer");
        dataTypeMap.put("varchar2", "String");
        dataTypeMap.put("timestamp", "Date");
        dataTypeMap.put("timestamp(6)", "Date");
        dataTypeMap.put("character varying", "String");
        dataTypeMap.put("integer", "Integer");
        dataTypeMap.put("smallint", "Integer");
        dataTypeMap.put("date", "Date");
        dataTypeMap.put("timestamp", "Date");
        dataTypeMap.put("timestamp without time zone", "Date");
        dataTypeMap.put("boolean", "Boolean");
        dataTypeMap.put("numeric", "Double");
    }


    /**
     * 判断包路径是否存在
     *
     */
    private void pathJudgeExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 输出到文件
     */
    public void printFile(Map<String, Object> root, Template template, String filePath, String fileName) throws Exception {
        pathJudgeExist(filePath);
        File file = new File(filePath, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        template.process(root, out);
        out.close();
    }

    /**
     * 首字母大写
     */
    public String capFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * 下划线命名转为驼峰命名
     */
    public String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] str = para.split("_");
        for (String s : str) {
            if (result.length() == 0) {
                result.append(s);
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 获取类名
     */
    public String getEntityName(String tableName) {
        return underlineToHump(capFirst(tableName.toLowerCase()));
    }

    /**
     * 获取首字母小写类名
     */
    public String getEntityNameLower(String tableName) {
        return underlineToHump(tableName.toLowerCase());
    }



    /**
     * 匹配字符串中的英文字符
     */
    public String matchResult(String str) {
        String regEx2 = "[a-z||A-Z]";
        Pattern pattern = Pattern.compile(regEx2);
        StringBuilder sb = new StringBuilder();
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                sb.append(m.group());
            }
        }
        return sb.toString();
    }

    /**
     * 获取表信息
     */
    public Map<String, Object> getDataInfo(TableMetadata table,String packagePath,String uri) {
        Map<String, Object> codeMap = new HashMap(16);
        if (ObjectUtils.isEmpty(table)) {
            throw new IllegalArgumentException("table not found");
        }
        //查询表对应的所有字段
        LambdaQueryWrapper<FieldMetadata> queryWrapper
                = new LambdaQueryWrapper<>();
        queryWrapper.eq(FieldMetadata::getTableId,table.getId());
        List<FieldMetadata> list = fieldMetadataService.list(queryWrapper);
        if (ObjectUtils.isEmpty(list)) {
            throw new IllegalArgumentException("Table has no fields");
        }
        Code code = Code
                .builder()
                .entityName(getEntityName(table.getNameEn()))
                .dataTable(table.getNameEn())
                .packageName(packagePath)
                .uri(uri)
                .build();
        List<Attr> attrs = new ArrayList();
        for (FieldMetadata field : list) {
            Attr attr = new Attr();
            //字段名称
            String nameEn = field.getNameEn();
            //字段类型
            String type = field.getJavaType();
            BeanUtils.copyProperties(field,attr);
            attr.setDes("asc");
            attr.setJavaName(underlineToHump(nameEn));
            attr.setJdbcName(nameEn);
            attr.setMaxlen(field.getMaxLength());
            attr.setOptional(field.getOptional() ? 1: 0);
            attr.setTitle(field.getName());
            attr.setJavaType(type);
         // attr.setQueryField(field.getQueryable() ? 1 : 0);
            attr.setMaxVal(field.getMax());
            attr.setMinVal(field.getMin());
            attrs.add(attr);

        }
        code.setAttrs(attrs);
        codeMap.put("code", code);
        return codeMap;

    }

    /**
     * 生成代码
     */
    public void generate(Map<String, Object> root, String templateName, String saveUrl, String entityName) throws Exception {
        //获取模板
        Template template = freeMarker.getTemplate(templateName);
        //输出文件
        printFile(root, template, saveUrl, entityName);
    }




}

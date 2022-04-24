package com.icepoint.framework.code.utils.create;

import com.icepoint.framework.code.utils.CodeUtil;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.service.FieldMetadataService;
import com.icepoint.framework.web.system.util.CreateCode;
import com.power.common.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ck
 * @version 1.0
 * @date 2021/5/28 14:33
 */
@Component
@Slf4j
public class CreateRobotFramework extends TestCaseTemplate {

    protected static final String CONST_BLANK_1 = " ";

    @Resource
    private FieldMetadataService fieldMetadataService;


    @Override
    public boolean createSuiteAdd() {
        try {
            String absolutePath = super.addFile.getAbsolutePath();
            log.info("..............{}", absolutePath);
            FileWriter fw = new FileWriter(addFile);
            fw.write("*** Settings ***\r\n");
            fw.write("Library           RequestsLibrary\r\n");
            fw.write("Library           Collections\r\n");
            fw.write("Library           DatabaseLibrary\r\n");
            fw.write("Resource          ../UserKeyword.txt\r\n");
            fw.write("\r\n");
            fw.write("*** Test Cases ***\r\n");
            String str = "";

            Map<String, String> map1 = doMain(fieldList);
            Collection<String> values = map1.values();
            fw.write("add" + values.stream().map(dm -> "_" + dm).reduce((cnt, itm) -> cnt + itm).get() + "\r\n");
            fw.write("    [Tags]    pass\r\n");
            fw.write("    #\r\n");
            fw.write("    ${dict}    Create Dictionary    Content-Type=application/json    User-Agent=Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36    Connection=keep-alive\r\n");
            fw.write("    Create Session    service    ${httpurl}    ${dict}\r\n");
            fw.write(getPostParaConvert(fieldList, map1));
            fw.write("    ${data}    Create Dictionary" + getParaVar(fieldList) + "\r\n");
            fw.write("    ${sign}    Encrypt Para    ${data}" + "\r\n");
            fw.write("    ${pstr}    Dict Para2Str    ${sign}" + "\r\n");
            //请求路径  先写死 TODO
            str = "${resp}    Post Request    service    /api/prod/attr/add    data=${pstr}    headers=${dict}";
            fw.write(str);
            fw.write("    Should Be Equal As Strings    ${resp.status_code}    200\r\n");
            fw.write("    ${responsedata}    To Json    ${resp.content}\r\n");
            fw.write("    ${code}    Get From Dictionary    ${responsedata}    code\r\n");
            fw.write("    Should Be Equal As Strings    ${code}    2000\r\n");
            fw.write("    ${data}    Get From Dictionary    ${responsedata}    data\r\n");
            //获取主键
            FieldMetadata sysField = getPkField(fieldList);
            String pkName = sysField.getNameEn();
            fw.write("    ${" + pkName + "}    Get From Dictionary    ${data}    " + pkName + "\r\n");
            fw.write("    连接数据库\r\n");
            fw.write("    @{list}    Query    " + getSelectByPk(fieldList, pkName, super.tableMetadata.getNameEn()) + "\r\n");
            fw.write("    ${length}    Get Length    ${list}\r\n");
            fw.write("    Should Be Equal As Integers    ${length}    1\r\n");
            fw.write(getEqualFields(fieldList));
            fw.write("    #删除测试数据\r\n");
            fw.write("    Execute Sql String    " + getDeleteByPk(super.tableMetadata.getNameEn(), pkName) + "\r\n");
            fw.write("    Disconnect From Database\r\n");
            fw.close();
            return true;
        } catch (IOException e) {
            log.error("新增测试文件创建失败");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createSuiteUpdate() {

        try {
            FileWriter fw = new FileWriter(super.updateFile);
            fw.write("*** Settings ***\r\n");
            fw.write("Library           RequestsLibrary\r\n");
            fw.write("Library           Collections\r\n");
            fw.write("Library           DatabaseLibrary\r\n");
            fw.write("Resource          ../UserKeyword.txt\r\n");
            fw.write("\r\n");
            fw.write("*** Test Cases ***\r\n");
            fw.write("update______");
            fw.write("    [Tags]    pass\r\n");
            fw.write("    #\r\n");
            FieldMetadata pkField = getPkField(fieldList);
            String pkName = pkField.getNameEn();
            Map<String, String> map1 = doMain(fieldList);
            fw.write(getPkParam(pkField));
            fw.write("    连接数据库\r\n");
            fw.write("    @{list}    Query    " + getSelectByPk(fieldList, super.tableMetadata.getNameEn(), pkName) + "\r\n");
            fw.write(getOriginalVals(fieldList));
            fw.write("    ${dict}    Create Dictionary    Content-Type=application/json    User-Agent=Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36    Connection=keep-alive\r\n");
            fw.write("    Create Session    service    ${httpurl}    ${dict}\r\n");
            fw.write(getPostParaConvert(fieldList, map1));
            fw.write("    ${data}    Create Dictionary" + getParaVar(fieldList) + "\r\n");
            fw.write("    ${sign}    Encrypt Para    ${data}\r\n");
            fw.write("    ${pstr}    Dict Para2Str    ${sign}\r\n");
            //TODO 请求路径
            fw.write("    ${resp}    Post Request    service    " + project.getNameEn() + "/" + super.tableMetadata.getNameEn() + "/" + "update" + "    data=${pstr}    headers=${dict}\r\n");
            fw.write("    Should Be Equal As Strings    ${resp.status_code}    200\r\n");
            fw.write("    Log    ${resp.content}\r\n");
            fw.write("    ${responsedata}    To Json    ${resp.content}\r\n");
            fw.write("    ${code}    Get From Dictionary    ${responsedata}    code\r\n");
            fw.write("    Should Be Equal As Strings    ${code}    2000\r\n");
            fw.write("    ${data}    Get From Dictionary    ${responsedata}    data\r\n");
            fw.write("    ${r" + pkName + "}    Get From Dictionary    ${data}    " + pkName + "\r\n");
            fw.write("    Should Be Equal As Integers    ${r" + pkName + "}    ${" + pkName + "}\r\n");
            fw.write("    @{list}    Query    " + getSelectByPk(fieldList, pkName, super.tableMetadata.getNameEn()) + "\r\n");
            fw.write("    ${length}    Get Length    ${list}\r\n");
            fw.write("    Should Be Equal As Integers    ${length}    1\r\n");
            fw.write(getEqualFields(fieldList));
            fw.write("    #恢复数据\r\n");
            fw.write(getUpdOriginalVals(super.tableMetadata.getNameEn(), fieldList));
            fw.write("    Disconnect From Database\r\n");
            fw.write("\r\n");
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean createSuiteGet() {

        try {

            FileWriter fw = new FileWriter(getFile);
            fw.write("*** Settings ***\r\n");
            fw.write("Library           RequestsLibrary\r\n");
            fw.write("Library           Collections\r\n");
            fw.write("Library           DatabaseLibrary\r\n");
            fw.write("Resource          ../UserKeyword.txt\r\n");
            fw.write("\r\n");
            fw.write("*** Test Cases ***\r\n");
            Map<String, String> map1 = doMain(fieldList);
            fw.write("get_" + map1.get("id"));
            fw.write("    [Tags]    pass\r\n");
            fw.write("    #\r\n");
            fw.write("    Create Session    service    ${httpurl}\r\n");
            fw.write("    #\r\n");
            fw.write(getPksParam(fieldList, map1));
            fw.write("    ${params}    Create Dictionary" + getParaVar(fieldList) + "\r\n");
            fw.write("    ${sign}    Encrypt Para    ${params}\r\n");
            fw.write("    ${pstr}    Dict Para2Str    ${sign}\r\n");
            fw.write("    ${resp}    Get Request    service    " + project.getNameEn() + "/" + super.tableMetadata.getNameEn() + "/get" + "?${pstr}    params=\r\n");
            fw.write("    Should Be Equal As Strings    ${resp.status_code}    200\r\n");
            fw.write("    Log    ${resp.content}\r\n");
            fw.write("    ${responsedata}    To Json    ${resp.content}\r\n");
            fw.write("    ${code}    Get From Dictionary    ${responsedata}    code\r\n");
            fw.write("    Should Be Equal As Strings    ${code}    2000\r\n");
            fw.write("    ${data}    Get From Dictionary    ${responsedata}    data\r\n");
            for (FieldMetadata sysField : fieldList) {
                fw.write("    ${" + sysField.getNameEn() + "}    Get From Dictionary    ${data}    " + sysField.getNameEn() + "\r\n");
            }
            fw.write("    连接数据库\r\n");
            fw.write("    @{list}    Query    " + getSelectByPks(fieldList, super.tableMetadata.getNameEn()) + "\r\n");
            fw.write("    Disconnect From Database\r\n");
            fw.write("    ${length}    Get Length    ${list}\r\n");
            fw.write("    Should Be Equal As Integers    ${length}    1\r\n");
            fw.write(getEqualFields(fieldList));
            fw.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createSuiteDelete() {

        try {


            FileWriter fw = new FileWriter(deleteFile);
            fw.write("*** Settings ***\r\n");
            fw.write("Library           RequestsLibrary\r\n");
            fw.write("Library           Collections\r\n");
            fw.write("Library           DatabaseLibrary\r\n");
            fw.write("Resource          ../UserKeyword.txt\r\n");
            fw.write("\r\n");
            fw.write("*** Test Cases ***\r\n");
            Map<String, String> map1 = doMain(fieldList);
            fw.write("delete_" + map1.get("id"));
            fw.write("    [Tags]    pass\r\n");
            fw.write("    #\r\n");
            fw.write("    ${dict}    Create Dictionary    Content-Type=application/json    User-Agent=Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36    Connection=keep-alive\r\n");
            fw.write("    Create Session    service    ${httpurl}    ${dict}\r\n");
            fw.write(getPostParaConvert(fieldList, map1));
            fw.write("    ${data}    Create Dictionary" + getParaVar(fieldList) + "\r\n");
            fw.write("    ${sign}    Encrypt Para    ${data}" + "\r\n");
            fw.write("    ${pstr}    Dict Para2Str    ${sign}" + "\r\n");
            //TODO 请求路径
            fw.write("    ${resp}    Post Request    service    " + project.getNameEn() + "/" + super.tableMetadata.getNameEn() + "/" + "update" + "    data=${pstr}    headers=${dict}\r\n");
            fw.write("    Should Be Equal As Strings    ${resp.status_code}    200\r\n");
            fw.write("    ${responsedata}    To Json    ${resp.content}\r\n");
            fw.write("    ${code}    Get From Dictionary    ${responsedata}    code\r\n");
            fw.write("    Should Be Equal As Strings    ${code}    2000\r\n");
            fw.write("    ${data}    Get From Dictionary    ${responsedata}    data\r\n");
            FieldMetadata pkField = getPkField(fieldList);
            fw.write("    ${r" + pkField.getNameEn() + "}    Get From Dictionary    ${data}    " + pkField.getNameEn() + "\r\n");
            fw.write("    连接数据库\r\n");
            fw.write("    #检查是否存在添加数据\r\n");
            fw.write("    Check If Exists In Database    " + getSelectByPk(fieldList, pkField.getNameEn(), super.tableMetadata
                    .getNameEn()) + "\r\n");
            fw.write("    #删除数据\r\n");
            fw.write("    ${data}    Create Dictionary" + getPostParaData(fieldList, map1) + "\r\n");
            fw.write("    ${sign}    Encrypt Para    ${data}\r\n");
            fw.write("    ${pstr}    Dict Para2Str    ${sign}\r\n");
            //TODO 请求路径
            fw.write("    ${resp}    Post Request    service    " + project.getNameEn() + "/" + super.tableMetadata.getNameEn() + "/" + "update" + "    data=${pstr}    headers=${dict}\r\n");
            fw.write("    Should Be Equal As Strings    ${resp.status_code}    200\r\n");
            fw.write("    Log    ${resp.content}\r\n");
            fw.write("    ${responsedata}    To Json    ${resp.content}\r\n");
            fw.write("    ${code}    Get From Dictionary    ${responsedata}    code\r\n");
            fw.write("    Should Be Equal As Strings    ${code}    2000\r\n");
            fw.write("    ${data}    Get From Dictionary    ${responsedata}    data\r\n");
            fw.write("    ${" + pkField.getNameEn() + "}    Get From Dictionary    ${data}    " + pkField.getNameEn() + "\r\n");
            fw.write("    Should Be Equal As Strings    ${" + pkField.getNameEn() + "}    ${r" + pkField.getNameEn() + "}\r\n");
            fw.write("    #检查是否删除成功\r\n");
            fw.write("    Check If Not Exists In Database    " + getSelectByPk(fieldList, pkField.getNameEn(), super.tableMetadata
                    .getNameEn()) + "\r\n");
            fw.write("    Disconnect From Database");
            fw.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private String getPkParam(FieldMetadata pkField) {
        String nameEn = pkField.getNameEn();
        String type = pkField.getNativeType();
        String dt = CreateCode.dataTypeMap.get(type);
        if ("Integer".equalsIgnoreCase(dt) || "Int".equalsIgnoreCase(dt) || "Long".equalsIgnoreCase(dt) || "Boolean".equalsIgnoreCase(dt)) {
            return "    ${" + nameEn + "}    Convert To Integer\r\n";
        } else if ("Double".equalsIgnoreCase(dt) || "Float".equalsIgnoreCase(dt)) {
            return "    ${" + nameEn + "}    Convert To Number\r\n";
        } else {
            return "    ${" + nameEn + "}    Convert To String\r\n";
        }
    }


    /**
     * 获取随机的测试用例
     */
    public Map<String, String> doMain(List<FieldMetadata> fldList) {
        Map<String, String> map1 = new HashMap<>(16);
        for (FieldMetadata sysField : fldList) {
            String nameEn = sysField.getNameEn();
            String domain = sysField.getDomain();
            //设置测试用例
            if (!ObjectUtils.isEmpty(domain)) {
                List<String> collect = Arrays.asList(domain.split("\\|")).stream()
                        .map(itm -> itm.split(":")[0])
                        .collect(Collectors.toList());
                map1.put(sysField.getNameEn(), collect.get(0));
                continue;
            }
            if (!ObjectUtils.isEmpty(sysField.getMax())) {
                map1.put(nameEn, sysField.getMax());
                continue;
            }
            if (!ObjectUtils.isEmpty(sysField.getMin())) {
                map1.put(nameEn, sysField.getMin());
                continue;
            }
            String dt = CreateCode.dataTypeMap.get(sysField.getNativeType());
            Integer maxLen = sysField.getMaxLength();
            //设置Integer 的测试用例
            if ("Integer".equalsIgnoreCase(dt)) {
                if (!ObjectUtils.isEmpty(maxLen)) {
                    Integer i = RandomUtil.randomInt(maxLen);
                    map1.put(nameEn, String.valueOf(i));
                    continue;
                }
                map1.put(nameEn, String.valueOf(Integer.MAX_VALUE));
                continue;
            }
            //设置 Long 类型的测试用例
            if ("Long".equalsIgnoreCase(dt)) {
                if (!ObjectUtils.isEmpty(maxLen)) {
                    long l = RandomUtil.randomLong(0, maxLen);
                    map1.put(nameEn, String.valueOf(l));
                    continue;
                }
                map1.put(nameEn, String.valueOf(Long.MAX_VALUE));
                continue;
            }

            //设置double 的默认测试用例
            if ("Double".equalsIgnoreCase(dt)) {
                if (!ObjectUtils.isEmpty(maxLen)) {
                    double v = RandomUtil.randomDouble(0, maxLen);
                    map1.put(nameEn, String.valueOf(v));
                    continue;
                }
                map1.put(nameEn, String.valueOf(Double.MAX_VALUE));
                continue;
            }
            //设置字符串
            if ("String".equalsIgnoreCase(dt)) {
                if (!ObjectUtils.isEmpty(maxLen)) {
                    String s = CodeUtil.generateRandomString(maxLen);
                    map1.put(nameEn, s);
                    continue;
                }
                map1.put(nameEn, CodeUtil.generateRandomString(255));
                continue;
            }
        }
        return map1;
    }


    private String getPostParaConvert(List<FieldMetadata> fieldList, Map<String, String> map) {
        String str = "";
        for (FieldMetadata sysField : fieldList) {
            String dt = CreateCode.dataTypeMap.get(sysField.getNativeType());
            String nameEn = sysField.getNameEn();

            if ("Integer".equalsIgnoreCase(dt) || "Int".equalsIgnoreCase(dt) || "Long".equalsIgnoreCase(dt)) {
                str += "    ${" + nameEn + "}    Convert To Integer    " + map.get(nameEn) + "\r\n";
            } else if ("Double".equalsIgnoreCase(dt) || "Float".equalsIgnoreCase(dt)) {
                str += "    ${" + nameEn + "}    Convert To Number    " + map.get(nameEn) + "\r\n";
            } else if ("Boolean".equalsIgnoreCase(dt)) {
                str += "    ${" + nameEn + "}    Convert To Boolean    " + map.get(nameEn) + "\r\n";
            } else {
                str += "    ${" + nameEn + "}    Convert To String    " + map.get(nameEn) + "\r\n";
            }
        }
        return str;
    }


    private String getParaVar(List<FieldMetadata> fldList) {
        String str = "";
        for (FieldMetadata sysField : fldList) {
            str += "    " + sysField.getNameEn() + "=${" + sysField.getNameEn() + "}";
        }
        return str;
    }

    private String getSelectByPk(List<FieldMetadata> fieldList, String pkName, String tabName) {
        String str = fieldList.stream().map(item -> item.getNameEn() + ",").reduce((cnt, item) -> cnt + item).get();
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return "SELECT" + CONST_BLANK_1 + str + CONST_BLANK_1 + "FROM" + CONST_BLANK_1 + tabName + CONST_BLANK_1 + "WHERE" + CONST_BLANK_1 + pkName + CONST_BLANK_1 + "=" + CONST_BLANK_1 + "${" + pkName + "}";
    }

    /**
     * list只有一个元素，判断字段是否相同
     */
    private String getEqualFields(List<FieldMetadata> fldList) {
        String str = "";
        for (int i = 0; i < fldList.size(); i++) {
            FieldMetadata sysField = fldList.get(i);
            String type = sysField.getNativeType();
            String dt = CreateCode.dataTypeMap.get(type);
            String nameEn = sysField.getNameEn();

            if ("Integer".equalsIgnoreCase(dt) || "Int".equalsIgnoreCase(dt) || "Long".equalsIgnoreCase(dt) || "Boolean"
                    .equalsIgnoreCase(dt)) {
                str += "    Should Be Equal As Integers    ${" + nameEn + "}    @{list[0]}[" + i + "]\r\n";
            } else if ("Double".equalsIgnoreCase(dt) || "Float".equalsIgnoreCase(dt)) {
                str += "    Should Be Equal As Numbers    ${" + nameEn + "}    @{list[0]}[" + i + "]\r\n";
            } else {
                str += "    ${db_" + nameEn + "}    decode    @{list[0]}[" + i + "]    UTF-8\r\n"
                        + "    Should Be Equal As Strings    ${db_" + nameEn + "}    ${" + nameEn + "}\r\n";
            }
        }
        return str;
    }

    private String getDeleteByPk(String tabName, String pkName) {
        return "DELETE" + CONST_BLANK_1 + "FROM" + CONST_BLANK_1 + tabName + CONST_BLANK_1 + "WHERE" + CONST_BLANK_1 + pkName + CONST_BLANK_1 + "=" + CONST_BLANK_1 + "${" + pkName + "}";
    }

    /**
     * 获取主键
     */
    public FieldMetadata getPkField(List<FieldMetadata> sysFields) {
        List<FieldMetadata> collect = sysFields.stream()
                .filter(pk -> Boolean.TRUE.equals(pk.getPrimaryKey()))
                .collect(Collectors.toList());
        if (collect.size() != 1) {
            throw new IllegalStateException("主键出现问题");
        }
        return collect.get(0);
    }

    private String getOriginalVals(List<FieldMetadata> fldList) {
        String str = "";
        for (int i = 0; i < fldList.size(); i++) {
            FieldMetadata sysField = fldList.get(i);
            String type = sysField.getNativeType();
            String dt = CreateCode.dataTypeMap.get(type);
            if (Boolean.TRUE.equals(sysField.getPrimaryKey())) {
                str += "";
            }
            if ("Integer".equalsIgnoreCase(dt) || "Int".equalsIgnoreCase(dt) || "Long".equalsIgnoreCase(dt) || "Boolean"
                    .equalsIgnoreCase(dt) || "Double".equalsIgnoreCase(dt) || "Float".equalsIgnoreCase(dt)) {
                str += "    ${original_" + sysField.getNameEn() + "}    Set Variable    @{list[0]}[" + i + "]\r\n";
            } else {
                str += "    ${original_" + sysField.getNameEn() + "}    decode    @{list[0]}[" + i + "]    UTF-8\r\n";
            }
        }
        return str;
    }


    private String getUpdOriginalVals(String tabName, List<FieldMetadata> fldList) {
        String str = "";
        for (int i = 0; i < fldList.size(); i++) {
            FieldMetadata sysField = fldList.get(i);
            String type = sysField.getNativeType();
            String dt = CreateCode.dataTypeMap.get(type);
            String nameEn = sysField.getNameEn();
            if (Boolean.TRUE.equals(sysField.getPrimaryKey())) {
                str += "";
            }
            if ("Integer".equalsIgnoreCase(dt) || "Int".equalsIgnoreCase(dt) || "Long".equalsIgnoreCase(dt) || "Boolean"
                    .equalsIgnoreCase(dt) || "Double".equalsIgnoreCase(dt) || "Float".equalsIgnoreCase(dt)) {
                str += nameEn + "=${original_" + nameEn + "},";
            } else {
                str += nameEn + "='${original_" + nameEn + "}',";
            }
        }

        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        String strWhere = "";
        for (int i = 0; i < fldList.size(); i++) {
            FieldMetadata sysField = fldList.get(i);
            String type = sysField.getNativeType();
            String dt = CreateCode.dataTypeMap.get(type);
            String nameEn = sysField.getNameEn();
            if (Boolean.TRUE.equals(sysField.getPrimaryKey())) {
                strWhere += "";
            }
            if ("Integer".equalsIgnoreCase(dt) || "Int".equalsIgnoreCase(dt) || "Long".equalsIgnoreCase(dt) || "Boolean"
                    .equalsIgnoreCase(dt) || "Double".equalsIgnoreCase(dt) || "Float".equalsIgnoreCase(dt)) {
                strWhere += nameEn + "=${" + nameEn + "} AND";
            } else {
                strWhere += nameEn + "='${" + nameEn + "}' AND";
            }
        }
        if (strWhere.length() > 3 && strWhere.endsWith("AND")) {
            strWhere = strWhere.substring(0, strWhere.length() - 3);
        }
        return "    Execute Sql String    UPDATE " + tabName + CONST_BLANK_1 + "SET" + CONST_BLANK_1 + str + CONST_BLANK_1 + "WHERE" + CONST_BLANK_1 + strWhere + "\r\n";

    }

    public String getPksParam(List<FieldMetadata> sysFields, Map<String, String> map) {
        String type = "";
        String nameEn = "";
        for (FieldMetadata sysField : sysFields) {
            if (Boolean.TRUE.equals(sysField.getPrimaryKey())) {
                nameEn = sysField.getNameEn();
                type = sysField.getNativeType();
            }
        }

        type = CreateCode.dataTypeMap.get(type);
        if ("Integer".equalsIgnoreCase(type) || "Int".equalsIgnoreCase(type) || "Long".equalsIgnoreCase(type) || "Boolean"
                .equalsIgnoreCase(type)) {
            return "    ${" + nameEn + "}    Convert To Integer " + map.get(nameEn) + "\r\n";
        } else if ("Double".equalsIgnoreCase(type) || "Float".equalsIgnoreCase(type)) {
            return "    ${" + nameEn + "}    Convert To Number " + map.get(nameEn) + "\r\n";
        } else {
            return "    ${" + nameEn + "}    Convert To String  " + map.get(nameEn) + "\r\n";
        }
    }

    private String getSelectByPks(List<FieldMetadata> sysFields, String tabName) {
        FieldMetadata pkField = getPkField(sysFields);
        String pkName = pkField.getNameEn();
        String str = sysFields.stream().map(item -> item.getNameEn() + ",").reduce((cnt, item) -> cnt + item).get();
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        String strWhere = CONST_BLANK_1 + pkName + "=" + "${" + pkName + "}" + CONST_BLANK_1 + "AND";
        if (strWhere.endsWith("AND")) {
            strWhere = strWhere.substring(0, strWhere.length() - 3);
        }
        return "SELECT" + CONST_BLANK_1 + str + CONST_BLANK_1 + "FROM" + CONST_BLANK_1 + tabName + CONST_BLANK_1 + "WHERE" + CONST_BLANK_1 + strWhere;
    }

    private String getPostParaData(List<FieldMetadata> sysFields, Map<String, String> map) {
        String str = "";
        for (FieldMetadata sysField : sysFields) {
            String type = sysField.getNativeType();
            String dt = CreateCode.dataTypeMap.get(type);
            String nameEn = sysField.getNameEn();
            if ("Integer".equalsIgnoreCase(dt) || "Int".equalsIgnoreCase(dt) || "Long".equalsIgnoreCase(dt) || "Double".equalsIgnoreCase(dt) || "Float"
                    .equalsIgnoreCase(dt) || "Boolean".equalsIgnoreCase(dt)) {
                str += "    " + nameEn + "=" + "${" + nameEn + "}";
            } else {
                str += "    " + nameEn + "=" + map.get(nameEn);
            }
        }
        return str;
    }


}

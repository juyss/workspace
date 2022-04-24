package com.github.tangyi.user.service.impl;

import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.util.ExcelUtils;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.example.ContactsExample;
import com.github.tangyi.model.Contacts;
import com.github.tangyi.user.api.module.Dept;
import com.github.tangyi.user.service.ContactsService;
import com.github.tangyi.user.service.DeptService;
import lombok.Data;
import my.convert.Map2Bean;
import my.xh.validate.CustomException;
import my.xh.validate.ValidateField;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private CommonDaoService commonDaoService;
    @Autowired
    private DeptService deptService;

    @Override
    @ValidateField(index = 4, strVals = {"yuanqu", "qiye", "shequ"}, notNull = true)
    public PageResult list(Integer pageNum, Integer pageSize, String sort, String order, String type, Long deptId, BaseReq baseReq) {
        List<Dept> depts = deptService.depts(null, type);
        Map<Long, Dept> collect = depts.stream().collect(Collectors.toMap(Dept::getId, item -> item));
        List<Long> deptIds = getDeptIds(deptId, depts);

        ContactsExample contactsExample = new ContactsExample();
        ContactsExample.Criteria c = contactsExample.and().andValid().andDeptIdIn(deptIds).andTypeEqualTo(type);
        if (StringUtils.isNotBlank(baseReq.getKw())) {
            c.andNameLike(String.format("%%%s%%", baseReq.getKw()));
        }
        contactsExample.setOrderByClause(String.format("%s %s", sort, order));
        PageResult result = PageUtils.query(pageNum, pageSize, 10, () -> commonDaoService.selectByExample(contactsExample));
        List<Contacts> rows = (List<Contacts>) result.getRows();
        List<ContactsVo> row2 = rows.stream().map(item -> new ContactsVo(item, collect)).collect(Collectors.toList());
        result.setRows(row2);
        return result;
    }

    private List<ContactsVo> listByIds(String type,List<Long> ids,String sort,String order) {
        List<Dept> depts = deptService.depts(null, type);
        Map<Long, Dept> collect = depts.stream().collect(Collectors.toMap(Dept::getId, item -> item));
        ContactsExample contactsExample = new ContactsExample();
        ContactsExample.Criteria c = contactsExample.and().andValid().andIdIn(ids);

        contactsExample.setOrderByClause(String.format("%s %s", sort, order));
        List<Contacts> contacts = commonDaoService.selectByExample(contactsExample);

        List<ContactsVo> row2 = contacts.stream().map(item -> new ContactsVo(item, collect)).collect(Collectors.toList());
        return row2;
    }

    private List<Long> getDeptIds(Long deptId, List<Dept> depts) {
        List<Long> deptIds = new ArrayList<Long>();
        for (Dept dept : depts) {
            if (dept.getId().equals(deptId)) {
                deptIds.add(dept.getId());
            }
            if (dept.getParentId().equals(deptId)) {
                deptIds.addAll(getDeptIds(dept.getId(), depts));
            }
        }
        return deptIds;
    }

    @CustomException(excptionClazz = CommonException.class)
    @ValidateField(index = 0, filedName = "type", strVals = {"yuanqu", "qiye", "shequ"}, notNull = true)
    @ValidateField(index = 0, filedName = "phone", minLen = 10, notNull = true)
    @ValidateField(index = 0, filedName = "tel", minLen = 1)
    @ValidateField(index = 0, filedName = "name", minLen = 1, notNull = true)
    @ValidateField(index = 0, filedName = "position", minLen = 1, notNull = true)
    @ValidateField(index = 0, filedName = "email", minLen = 1, notNull = true)
    @Override
    public int save(Contacts contacts) {
        if (contacts.isNewRecord()) {
            contacts.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
            return commonDaoService.insert(contacts);
        } else {
            return commonDaoService.update(contacts);
        }
    }

    @Override
    public int del(Long id) {
        return commonDaoService.delete(Contacts.class, id);
    }

    @Override
    public void export(String type, Long deptId, HttpServletResponse response, String sort, String order, String ids, BaseReq baseReq) {
        Map<String, Object> data = new HashMap<String, Object>();
        PageResult list = null;

        String[] split = StringUtils.isBlank(ids) ? (new String[0]) : ids.split(",");
        if (split.length > 0) {
            List<Long> collect = Arrays.asList(split).stream().map(Long::valueOf).collect(Collectors.toList());
            data.put("dataList",listByIds(type,collect,sort,order));
        } else {
            list = list(1, Integer.MAX_VALUE, sort, order, type, deptId, baseReq);
            data.put("dataList", list.getRows());
        }

        data.put("dataFormat", new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"));
        String filename = "通讯录-" + type + new SimpleDateFormat("-yyyyMMddHHmmss").format(new Date());
        String template = type + "_contacts_template";
        ExcelUtils.export(data, filename, template, response);
    }

    @Data
    public static class ContactsVo extends Contacts {
        private String companyName;
        private Long companyId;
        private String deptName;

        public ContactsVo() {
            super();
        }

        public ContactsVo(Contacts contacts, Map<Long, Dept> collect) {
            try {
                Map2Bean.getInstance().getBeanFromBean(Contacts.class, this, contacts);

                if (collect == null || collect.size() == 0) return;
                Dept dept = collect.get(this.getDeptId());
                this.setDeptName(dept.getDeptName());
                if (dept.getParentId() != -1L) {
                    Dept dept1 = collect.get(dept.getParentId());
                    if (dept1 == null) return;
                    this.setCompanyName(dept1.getDeptName());
                    this.setCompanyId(dept1.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

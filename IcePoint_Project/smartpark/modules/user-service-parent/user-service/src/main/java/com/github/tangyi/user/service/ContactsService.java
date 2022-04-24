package com.github.tangyi.user.service;

import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.Contacts;

import javax.servlet.http.HttpServletResponse;

public interface ContactsService {
    PageResult list(Integer pageNum, Integer pageSize, String sort, String order, String type, Long deptId, BaseReq baseReq);

    int save(Contacts contacts);

    int del(Long id);

    void export(String type, Long deptId, HttpServletResponse response, String sort, String order, String ids, BaseReq baseReq);
}

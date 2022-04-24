package com.github.tangyi.user.service;

import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.PageClickLog;

import java.util.List;
import java.util.Map;

public interface PageClickLogService {

    List<Map<String, Object>> list(BaseReq baseReq);

    int save(PageClickLog pageClickLog);

    PageResult detailList(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq);
}

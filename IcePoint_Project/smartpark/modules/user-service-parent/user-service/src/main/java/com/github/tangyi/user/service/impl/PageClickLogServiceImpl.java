package com.github.tangyi.user.service.impl;

import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.model.PageClickLog;
import com.github.tangyi.user.mapper.PageClickLogMapper;
import com.github.tangyi.user.service.PageClickLogService;
import my.xh.validate.CustomException;
import my.xh.validate.ValidateField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageClickLogServiceImpl implements PageClickLogService {

    @Autowired
    private CommonDaoService commonDaoService;

    @Autowired(required = false)
    private PageClickLogMapper pageClickLogMapper;

    @Override
    @ValidateField(index = 0, filedName = "startTime",notNull = true)
    @ValidateField(index = 0, filedName = "endTime",notNull = true)
    public List<Map<String, Object>> list(BaseReq baseReq) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("startTime", baseReq.getStartTime());
        param.put("endTime", baseReq.getEndTime());
//        param.put("type", type);
        List<Map<String, Object>> statistics = pageClickLogMapper.statistics(param);
        return statistics;
    }

    @CustomException(excptionClazz = CommonException.class)
    @ValidateField(index = 0, filedName = "clickId", minLen = 1, notNull = true)
    @ValidateField(index = 0, filedName = "clickType", minLen = 1, notNull = true)
    @Override
    public int save(PageClickLog pageClickLog) {
        if (pageClickLog.isNewRecord()) {
            pageClickLog.setIdentifier(SysUtil.getUser());
            pageClickLog.setCommonValue("admin", "EXAM", "gitee");
            return commonDaoService.insert(pageClickLog);
        }
        return 0;
    }

    @Override
    @ValidateField(index = 4,filedName = "startTime", notNull = true)
    @ValidateField(index = 4,filedName = "endTime", notNull = true)
    @ValidateField(index = 4,filedName = "kw", notNull = true,minLen = 1)
    public PageResult detailList(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        return PageUtils.query(pageNum,pageSize,10,()-> pageClickLogMapper.getList(baseReq,sort,order));
    }
}

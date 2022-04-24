package com.github.tangyi.user.synchrodata.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.MainRole;
import com.github.tangyi.user.synchrodata.config.SynchDeptProperties;
import com.github.tangyi.user.synchrodata.model.MainDept;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MainDeptApiService extends MainApiService {

    private SynchDeptProperties synchDeptProperties;

    public List<MainDept> getMainDeptTree(String token) {

        String exchange = this.exchange(synchDeptProperties.getGetTree(), null, token, HttpMethod.GET);
        JsonNode jsonNode = JsonUtils.toJsonNode(exchange);
        if (jsonNode.get("code").asInt() == 200) {
            ArrayList<MainDept> data = JsonUtils.toList(jsonNode.get("data").toString(), MainDept.class);
            return data;
        } else {
            throw new CommonException("获取部门数据列表失败: " + jsonNode.get("msg").toString());
        }
    }


    public List<MainDept> getMainDeptByPage(String token, Integer page, Integer size, Integer parentId) {
        String exchange = this.exchange(String.format(synchDeptProperties.getGetPage(), page, size, parentId), null, token, HttpMethod.GET);
        JsonNode jsonNode = JsonUtils.toJsonNode(exchange);
        if (jsonNode.get("code").asInt() == 200) {
            ArrayList<MainDept> data = JsonUtils.toList(jsonNode.get("rows").toString(), MainDept.class);

            int total = jsonNode.get("total").asInt();
            if (page * size < total){
                List<MainDept> nextPage = getMainDeptByPage(token, page + 1, size, parentId);
                data.addAll(nextPage);
            }
            return data;
        } else {
            throw new CommonException("获取部门数据列表失败: " + jsonNode.get("msg").toString());
        }
    }
}

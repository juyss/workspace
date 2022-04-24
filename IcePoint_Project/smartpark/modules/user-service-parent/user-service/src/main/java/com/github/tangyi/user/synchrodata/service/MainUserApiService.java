package com.github.tangyi.user.synchrodata.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.security.ty.MainUserWithRole;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.MainUser;
import com.github.tangyi.user.synchrodata.config.SynchMainUserProperties;
import com.github.tangyi.user.synchrodata.model.MainUserWithRoleAndDept;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @Author xh
 * @Description 主系统用户api 封装
 * @Date 10:48 2020/11/5
 * @Param
 * @return
 **/
@Service
@AllArgsConstructor
public class MainUserApiService extends MainApiService {
    private static Logger logger = LoggerFactory.getLogger(MainUserApiService.class);

    private SynchMainUserProperties synchMainUserProperties;

    /**
     * 获取用户列表，分页
     *
     * @return
     */
    public PageResult getListByPage(int page, int size, String token) {
        String result = this.get(String.format(synchMainUserProperties.getGetListByPage(), page, size), token);
        logger.debug("分页获取用户数据列表：{}", result);
        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            PageResult pageResult = new PageResult(page, size, 10);
            ArrayList<MainUserWithRoleAndDept> data = JsonUtils.toList(jsonNode.get("rows").toString(), MainUserWithRoleAndDept.class);
            data.forEach(item -> {
                MainUserWithRoleAndDept itemById = getItemById(item.getUserId(), token);
                item.setRoles(itemById.getRoles());
                item.setOrgs(itemById.getOrgs());
            });
            pageResult.setRows(data);
            pageResult.setTotal(jsonNode.get("total").asInt());
            return pageResult;
        } else {
            throw new CommonException("获取用户数据列表失败: " + jsonNode.get("msg").toString());
        }
    }


    /**
     * 通过id获取用户
     *
     * @return
     */
    public MainUserWithRoleAndDept getItemById(Integer userId, String token) {
        if (userId == null) throw new CommonException("userId 不能为空");
        String result = this.get(String.format(synchMainUserProperties.getGetItemById(), userId), token);
        logger.debug("通过id获取用户：{}", result);
        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            MainUserWithRoleAndDept data = JsonUtils.toObject(jsonNode.get("data").toString(), MainUserWithRoleAndDept.class);
            return data;
        } else {
            throw new CommonException("通过id获取用户: " + jsonNode.get("msg").toString());
        }
    }

    private String get(String uri, String token) {
        return this.exchange(uri, null, token, HttpMethod.GET);
    }
}

package com.github.tangyi.user.synchrodata.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.MainRole;
import com.github.tangyi.model.MainUser;
import com.github.tangyi.user.synchrodata.config.SynchMainRoleProperties;
import com.github.tangyi.user.synchrodata.config.SynchMainUserProperties;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MainRoleApiService extends MainApiService {

    private static Logger logger = LoggerFactory.getLogger(MainRoleApiService.class);

    private SynchMainRoleProperties synchMainRoleProperties;

    /**
     * 获取用户列表，分页
     *
     * @return
     */
    public PageResult getListByPage(int page, int size, String token) {
        String result = this.exchange(String.format(synchMainRoleProperties.getGetListByPage(), page, size), null, token, HttpMethod.GET);
        logger.debug("分页获取角色数据列表：{}", result);
        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            PageResult pageResult = new PageResult(page, size, 10);
            ArrayList<MainRole> data = JsonUtils.toList(jsonNode.get("rows").toString(), MainRole.class);
            pageResult.setRows(data);
            pageResult.setTotal(jsonNode.get("total").asInt());
            return pageResult;
        } else {
            throw new CommonException("获取角色数据列表失败: " + jsonNode.get("msg").toString());
        }
    }


    /**
     * 添加角色
     *
     * @return
     */
    public int add(MainRole mainRole, String token) {

        String result = this.exchange(String.format(synchMainRoleProperties.getAdd()), JsonUtils.toString(mainRole), token, HttpMethod.POST);
        logger.debug("添加主系统角色返回：{}", result);
        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            int roleId = jsonNode.get("data").asInt();
            return roleId;
        } else {
            throw new CommonException("添加主系统角色失败: " + jsonNode.get("msg").toString());
        }
    }

    public void update(MainRole mainRole, String token) {

        String result = this.exchange(String.format(synchMainRoleProperties.getUpdate(), mainRole.getRoleId()), JsonUtils.toString(mainRole), token, HttpMethod.PUT);
        logger.debug("修改主系统角色返回：{}", result);
        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {

        } else {
            throw new CommonException("修改主系统角色失败: " + jsonNode.get("msg").toString());
        }
    }

    public void del(MainRole mainRole, String token) {

        String result = this.exchange(String.format(synchMainRoleProperties.getDel(), mainRole.getRoleId()), null, token, HttpMethod.DELETE);
        logger.debug("删除主系统角色返回：{}", result);
        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {

        } else {
            throw new CommonException("删除主系统角色失败: " + jsonNode.get("msg").toString());
        }
    }

}

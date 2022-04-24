package com.github.tangyi.tools.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.github.tangyi.core.common.util.StringUtils;
import com.github.tangyi.tools.Constants;
import com.github.tangyi.tools.dto.resp.WeiXinUserListResp;
import com.google.gson.JsonSyntaxException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author gaokx
 * @Description 微信获取用户相关
 * @create 2021-03-25 10:03
 **/
@Slf4j
@Service
public class WxUserService {

  public static final Integer limit = 10000;

  /**
   * 获得关注用户openId
   * @param openidList
   * @param accessToken
   * @param nextOpenid
   * @return
   */
  public List<String> findWxUserOpenIdList(List<String> openidList, String accessToken,
      String nextOpenid) {
    WeiXinUserListResp userListResp = null;
    Map<String, Object> map = new TreeMap<>();
    map.put("access_token", accessToken);
    if (StringUtils.isNotBlank(nextOpenid)) {
      map.put("next_openid", nextOpenid);
    }
    String result = HttpUtil.get(Constants.WECHAT_USER_GET_URL, map);
    if (StringUtils.isNotEmpty(result)) {
      try {
        userListResp = WeiXinUserListResp.fromJson(result);
        if (userListResp != null && userListResp.getErrcode() != null) {
          log.info("获取关注用户列表失败，结果为{}", JSONUtil.toJsonStr(userListResp));
          return openidList;
        }
        if (userListResp.getCount() <= limit && userListResp.getCount() > 0) {
          openidList.addAll(userListResp.getData().getOpenid());
        } else {
          //如果大于1000的,继续查询
          findWxUserOpenIdList(openidList, accessToken, userListResp.getNextOpenid());
        }
      } catch (JsonSyntaxException e) {
        openidList = null;
      }
    }
    return openidList;
  }

}

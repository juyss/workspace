package com.github.tangyi.tools.dto.resp;

import com.github.tangyi.tools.utils.wx.WxMpGsonBuilder;
import com.github.tangyi.tools.utils.wx.enums.EnumWxMpStatus;
import java.util.List;
import lombok.Data;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-25 9:59
 * 关注的用户列表
 **/
@Data
public class WeiXinUserListResp {

  /**
   * 总关注用户数
   */
  private int total;
  /**
   * 获取的OpenId个数
   */
  private int count;
  /**
   * OpenId列表
   */
  private WxOpenIdResp data;
  /**
   * 最后一个用户的openid
   */
  private String nextOpenid;

  /**
   * 错误码
   */
  private Integer errcode;

  /**
   * 错误信息
   */
  private String errmsg;

  public static WeiXinUserListResp fromJson(String json) {
    WeiXinUserListResp accessTokenResp = WxMpGsonBuilder.create().fromJson(json, WeiXinUserListResp.class);
    if (accessTokenResp.getErrcode() != null) {
      EnumWxMpStatus wxMpStatus = EnumWxMpStatus.getByCode(accessTokenResp.getErrcode());
      if (wxMpStatus != null) {
        accessTokenResp.setErrmsg(wxMpStatus.getLabel());
      }
    }
    return accessTokenResp;
  }
}

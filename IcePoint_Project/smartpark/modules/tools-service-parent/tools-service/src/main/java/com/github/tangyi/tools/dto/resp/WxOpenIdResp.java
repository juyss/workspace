package com.github.tangyi.tools.dto.resp;

import java.util.List;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-25 12:56
 **/
public class WxOpenIdResp {

  private List<String> openid;

  public List<String> getOpenid() {
    return openid;
  }
  public void setOpenid(List<String> openid) {
    this.openid = openid;
  }
}

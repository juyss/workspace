package com.github.tangyi.tools.service;

import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.req.ParkNewsReq;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.common.util.DateUtils;
import com.github.tangyi.tools.Constants;
import com.github.tangyi.tools.dto.req.WxMpTemplateMessageReq;
import com.github.tangyi.tools.dto.resp.WxMpAccessTokenResp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gaokx
 * @Description 推送消息
 * @create 2021-03-24 22:48
 **/
@Slf4j
@Service
public class WxPushMessageService {

  @Autowired
  WxMpService wxMpService;

  @Autowired
  WxAccessTokenService wxAccessTokenService;

  @Autowired
  WxUserService  wxUserService ;

  /**
   * 给所有关注折推送园区新闻
   */
  public boolean pushNews(ParkNewsReq newsReq) {
    WxMpAccessTokenResp accessToken = wxAccessTokenService.getAccessToken();
    //1.获取到所有关注的用户列表
    List<String> openIds = new ArrayList<>();
    wxUserService.findWxUserOpenIdList(openIds, accessToken.getAccessToken(), "");
    //2.给关注用户发消息
    if (CollectionUtils.isNotEmpty(openIds)) {
      for (String openId : openIds) {
        WxMpTemplateMessageReq wxMpTemplateMessageReq = new WxMpTemplateMessageReq();
        wxMpTemplateMessageReq.setAccessToken(accessToken.getAccessToken());
        wxMpTemplateMessageReq.setToUser(openId);
        wxMpTemplateMessageReq.setTemplateId(Constants.NEWS_TEMPLATE_ID);
        wxMpTemplateMessageReq.setUrl(newsReq.getUrl());
        // data
          List<WxMpTemplateMessageReq.WxMpTemplateData> templateDatas = new ArrayList<>();
          for (WxTemplateMessageExtData extData : templateMessageExtData) {
            WxMpTemplateMessageReq.WxMpTemplateData templateData = new WxMpTemplateMessageReq.WxMpTemplateData();
            templateData.setName(extData.getDataName());
            templateData.setColor(extData.getDataColor());
            templateData.setValue(genNewsValueByName(extData.getDataName(), newsReq, extData));
            templateDatas.add(templateData);
          }
          wxMpTemplateMessageReq.setData(templateDatas);
          log.debug("咨询添加通知 请求参数为 {}", wxMpTemplateMessageReq.toJson());
          wxMpService.sendTemplateMessage(wxMpTemplateMessageReq);
      }
    }
    return true;
  }

  /**
   * 咨询模板工具
   *
   * @param name                   字段名
   * @return
   */
  private String genNewsValueByName(String name,ParkNewsReq newsReq,WxTemplateMessageExtData extData) throws CommonException {
    String value;
    switch (name) {
      case "first":
        // 首行
        value = extData.getDataValue() + "\n";
        break;
      case "remark":
        // 尾行
        value = extData.getDataValue() + "\n";
        break;
      case "keyword1":
        // 新闻栏目：
        value = newsReq.getProgram();
        break;
      case "keyword2":
        // 新闻标题：
        value = newsReq.getTitle();
        break;
      case "keyword3":
        //添加时间：
        value = DateUtils.formatDateTime(new Date());
        break;
      default:
        throw new CommonException("信息模板配置错误");
    }
    return value;
  }

  /**
   * 模板数据初始化
   */
  static  List<WxTemplateMessageExtData> templateMessageExtData = new ArrayList<>();

  static  {
    WxTemplateMessageExtData first = new WxTemplateMessageExtData();
    first.setDataName("first");
    first.setDataValue("新闻资讯发布");
    WxTemplateMessageExtData remark = new WxTemplateMessageExtData();
    remark.setDataName("remark");
    remark.setDataValue("");
    WxTemplateMessageExtData keyword1 = new WxTemplateMessageExtData();
    keyword1.setDataName("keyword1");
    keyword1.setDataValue("");
    WxTemplateMessageExtData keyword2 = new WxTemplateMessageExtData();
    keyword2.setDataName("keyword2");
    keyword2.setDataValue("");
    WxTemplateMessageExtData keyword3 = new WxTemplateMessageExtData();
    keyword3.setDataName("keyword3");
    keyword3.setDataValue("");
    templateMessageExtData.add(first);
    templateMessageExtData.add(remark);
    templateMessageExtData.add(keyword1);
    templateMessageExtData.add(keyword2);
    templateMessageExtData.add(keyword3);
  }


  @Data
  public static class WxTemplateMessageExtData {

    private String dataName;

    private String dataValue;

    private String dataColor;
  }

}

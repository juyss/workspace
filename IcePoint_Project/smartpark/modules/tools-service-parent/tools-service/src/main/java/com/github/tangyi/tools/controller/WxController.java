package com.github.tangyi.tools.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.ParkNewsReq;
import com.github.tangyi.tools.service.WxPushMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信工具接口
 *
 * @author gaokx
 * @date 2020/03/22 12:59
 */
@Slf4j
@AllArgsConstructor
@Api("微信工具接口")
@RestController
@RequestMapping(value = "/v1/tools/wx")
public class WxController {

  @Autowired
  WxPushMessageService wxPushMessageService;

  @ApiOperation(value = "新闻推送", notes = "新闻推送")
  @ApiImplicitParam(name = "answer", value = "新闻实体", required = true, dataType = "Answer")
  @PostMapping("pushNews")
  public ResponseBean<Boolean> pushNews(@RequestBody @Validated ParkNewsReq parkNewsReq) {
    Boolean result = wxPushMessageService.pushNews(parkNewsReq);
    return new ResponseBean<>(result);
  }

}

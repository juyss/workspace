package com.github.tangyi.auth.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tangyi.auth.model.CustomUserDetails;
import com.github.tangyi.common.basic.enums.LoginTypeEnum;
import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.common.security.event.CustomAuthenticationSuccessEvent;
import com.github.tangyi.common.security.ty.MainAccessToken;
import com.github.tangyi.common.security.ty.MainUserWithRole;
import com.github.tangyi.common.security.ty.TyAuthProperties;
import com.github.tangyi.common.security.ty.TyAuthService;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.user.api.feign.UserServiceClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xh
 * @Description 对接 主系统 统一认证 api
 * @Date 15:40 2020/11/5
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/v1/authentication/ty")
public class TyAuthenticationController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TyAuthProperties tyAuthProperties;
    @Autowired
    private TyAuthService tyAuthService;
    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired(required = false)
    private HttpServletResponse response;

    @GetMapping("/auth_url")
    public ResponseBean getAuthorizeUrl(HttpServletRequest request) {
        String feUrl = request.getHeader("Referer");//前端页面url
//        if (StringUtils.isNotBlank(feUrl) && !StringUtils.contains(feUrl, "backstage")) {
//            feUrl += "backstage";// backstage 正式环境后台布署的目录
//        }
        feUrl = getIP(feUrl) + "/park/#/gateway";
        StringBuilder authUrl = new StringBuilder();
        authUrl.append(tyAuthProperties.getAuthorizeUrl()).append("?")
                .append("clientId=").append(tyAuthProperties.getClientId()).append("&")
                .append("responseType=code&")
                .append("redirectUri=").append(tyAuthProperties.getRedirectUri()).append("&")
                .append("state=").append(feUrl);
        Map<String, String> map = new HashMap<String, String>();
        map.put("authUrl", authUrl.toString());

        StringBuilder logoutUrl = new StringBuilder();
        logoutUrl.append(tyAuthProperties.getLogoutUrl()).append("?")
                .append("redirectUri=").append(tyAuthProperties.getRedirectUri());
        map.put("logoutUrl", logoutUrl.toString());
        return new ResponseBean(map);
    }

    private URI getIP(String url) {
        URI effectiveURI = null;
        try {
            URI uri = new URI(url);
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveURI = null;
        }
        return effectiveURI;
    }
    /**
     * 登陆的回调
     *
     * @param code
     * @throws Exception
     */
    @GetMapping("/callback")
    public void callback(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "state", required = false) String state,
                         HttpServletRequest request) throws Exception {

        logger.info("回调参数:  query: {}, json: {}", JsonUtils.toString(request.getParameterMap()), JsonUtils.toString(getjsonParamByRequest(request)));

        String redirectUrl = tyAuthProperties.getDefaultRedirect();
        if (StringUtils.isBlank(state)) {
            //throw new CommonException("state 不符合规则");
        } else {
            redirectUrl = state;
            MainAccessToken accessToken = tyAuthService.getAccessToken(code);

            redirectUrl = redirectUrl + "#/?token=" + accessToken.getAccessToken();

            try {
                MainUserWithRole userInfo = tyAuthService.getUserInfo(accessToken.getAccessToken());
                // 构造数据 发布一个登陆成功的事件 以记录登陆信息
                UserVo user = userServiceClient.findUserByIdentifier(userInfo.getLoginName(), "gitee").getData();
                UserDetails userDetails = new CustomUserDetails(userInfo.getLoginName(),"123456",true,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("role_admin"),"gitee",user.getId(),new Date().getTime(), LoginTypeEnum.PWD);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo.getLoginName(),"123456");
                SpringContextHolder.publishEvent(new CustomAuthenticationSuccessEvent(authentication , userDetails));
            } catch (Exception e) {
                logger.error(" {}",e.toString());
            }
        }


        response.sendRedirect(redirectUrl);
    }

    private JsonNode getjsonParamByRequest(HttpServletRequest request) throws IOException, IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        JsonNode jsonNode = JsonUtils.toJsonNode(responseStrBuilder.toString());
        return jsonNode;
    }

    /**
     * 退出登陆的回调
     *
     * @param param
     * @throws Exception
     */
    @PostMapping("/callback")
    public void callback2(@RequestBody Map<String, Object> param) throws Exception {
        logger.info("退出登陆： param:{}", JsonUtils.toString(param));
        tyAuthService.removeAccessTokenByTgc(param.get("tgc").toString());
    }
}

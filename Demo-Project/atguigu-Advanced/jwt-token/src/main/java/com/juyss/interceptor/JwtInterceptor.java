package com.juyss.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juyss.util.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: JwtInterceptor
 * @Desc: JWT验证拦截器
 * @package com.juyss.interceptor
 * @project atguigu-Advanced
 * @date 2020/12/6 20:45
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(token)){
            response.sendRedirect("/login");
            return false;
        }
        HashMap<String, String> map = new HashMap<>();
        try {
            JwtUtils.verify(token);//验证令牌
            return true;//放行请求
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "无效签名!");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token过期!");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "token算法不一致!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效!!");
        }
        map.put("code", "403");//设置状态
        //将 map 转为json  jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}

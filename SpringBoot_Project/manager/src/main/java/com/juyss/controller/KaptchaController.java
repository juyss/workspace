package com.juyss.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: KaptchaController
 * @Desc:
 * @package com.juyss.controller
 * @project manager
 * @date 2021/1/21 14:35
 */
@Api(tags = "验证码相关")
@RestController
@Slf4j
@RequestMapping("/sys")
public class KaptchaController {
    /**
     * 获取验证码图片
     * Gets captcha code.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    @GetMapping("/getVerify")
    public void getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(2);
        CaptchaUtil.out(captcha, request, response);
    }

}

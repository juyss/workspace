package com.icepoint.framework.web.security;

import com.icepoint.framework.web.core.AbstractApplicationInitializer;
import com.icepoint.framework.web.core.dao.ResponseMessageRepository;
import com.icepoint.framework.web.security.util.SecurityMessage;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Component
public class WebSecurityInitializer extends AbstractApplicationInitializer<SecurityMessage> {

    public WebSecurityInitializer(ResponseMessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    public void initialize() {
        initMessages(SecurityMessage.values());
    }

    @Override
    protected String getModuleName() {
        return "WebSecurity";
    }

    @Override
    protected String getMissingMessage(SecurityMessage message) {
        switch (message) {
            case LOGIN_FAILED_WRONG:
                return "登录失败, 用户名或密码错误";
            case NOT_LOGIN:
                return "未登录";
            case NOT_AUTHORIZED:
                return "没有访问权限";
            case USERNAME_NOT_EXISTS:
                return "该用户名不存在";
            case USER_LOCKED:
                return "该账户已被锁定";
            case USER_EXPIRED:
                return "该账户已过期";
            case USER_DISABLED:
                return "该账户已被禁用";
            case CERTIFICATION_EXPIRED:
                return "该账户的认证权已过期";
            case OPERATE_NOT_AUTHORIZED:
                return "没有该操作的权限";
            case CSRF_TOKEN_INVALID:
                return "CSRF Token验证失败";
            case USERNAME_ALREADY_EXISTED:
                return "该用户名已存在";
            case ILLEGAL_PASSWORD:
                return "密码不合法";
            default:
                throw new IllegalStateException("Unexpected value: " + message);
        }
    }
}

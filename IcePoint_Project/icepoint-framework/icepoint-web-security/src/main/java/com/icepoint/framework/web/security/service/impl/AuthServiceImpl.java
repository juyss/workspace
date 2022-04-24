package com.icepoint.framework.web.security.service.impl;

import com.icepoint.framework.data.annotation.EncryptType;
import com.icepoint.framework.data.annotation.WriteTransaction;
import com.icepoint.framework.web.core.util.MessageAssert;
import com.icepoint.framework.web.security.dao.UserRepository;
import com.icepoint.framework.web.security.entity.Password;
import com.icepoint.framework.web.security.entity.QUser;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.AuthService;
import com.icepoint.framework.web.security.util.SecurityMessage;
import com.icepoint.framework.web.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @WriteTransaction
    @Override
    public void register(User user) {
        MessageAssert.isTrue(SecurityUtils.isLegalPassword(user.getPassword()), SecurityMessage.ILLEGAL_PASSWORD);

        QUser quser = QUser.user;

        MessageAssert.isTrue(!userRepository.exists(quser.username.eq(user.getUsername())),
                SecurityMessage.USERNAME_ALREADY_EXISTED);

        Password password = user.getPasswords().iterator().next();
        password.setPassword(passwordEncoder.encode(password.getPassword()));
        password.setAlgorithm(EncryptType.BCRYPT.id());
        password.setType("1");

        user.setState("1");
        userRepository.save(user);
    }
}

package com.icepoint.framework.web.security;

import com.icepoint.framework.web.security.dao.UserRepository;
import com.icepoint.framework.web.security.entity.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.hasText(username, "用户名不能为空");

        return repository.findOne(QUser.user.username.eq(username), false)
                .orElseThrow(() -> new UsernameNotFoundException("没有找到用户名为: " + username + "的用户"));
    }

}

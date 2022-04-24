package com.juyss.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.juyss.entity.User;
import com.juyss.service.UserService;
import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserDetailsServiceImpl
 * @package com.juyss.config
 * @project Spring-Security
 * @date 2021/12/16 0:59
 */
@Service
@Data
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class);
        wrapper.eq(User::getUsername, username);
        User user = userService.getOne(wrapper);
        String name = user.getUsername();
        String pwd = user.getPassword();

        return new org.springframework.security.core.userdetails.User(name, pwd,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal"));
    }
}

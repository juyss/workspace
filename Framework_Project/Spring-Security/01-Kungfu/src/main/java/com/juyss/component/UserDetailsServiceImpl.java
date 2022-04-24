package com.juyss.component;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDetailsServiceImpl
 * @Desc: 授权实现类
 * @package com.juyss.component
 * @project Spring-Security
 * @date 2020/10/20 21:10
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String queryUser = "SELECT * FROM `t_admin` WHERE loginacct=?";

        //1、查询指定用户的信息
        Map<String, Object> map = jdbcTemplate.queryForMap(queryUser, username);

        log.debug("查询到用户信息："+map);

        //2、将查询到的用户封装到框架使用的UserDetails里面
        return new User(map.get("loginacct").toString(), map.get("userpswd").toString(),
                AuthorityUtils.createAuthorityList( "三级"));//暂时写死，过后数据库中查
    }


    @Test
    public void BCryptTest(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "102850";
        String encodedPwd = "$2a$10$lXHaUukTM6WpP90nBYLTpOZFkany0DZj/D71UKRvXF7zkP9Uep.a6";

        boolean matches = encoder.matches(rawPassword, encodedPwd);

        System.out.println(matches);
    }

}

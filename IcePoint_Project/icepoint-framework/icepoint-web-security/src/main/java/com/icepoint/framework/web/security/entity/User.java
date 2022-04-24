package com.icepoint.framework.web.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.icepoint.framework.core.util.TimestampUtils;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.web.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@Table(name = "auth_user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@TableName("auth_user")
public class User extends LongStdEntity implements UserDetails {

    @Column(name = "id")
    @TableField("id")
    private Long id;
    /**
     * 是否为管理员
     */
    private Boolean admin;

    /**
     * 昵称（用户名）
     */
    private String nickname;

    /**
     * 用户名（登录用户名）
     */
    private String username;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 用户状态
     */
    private String state;

    /**
     * 过期日期
     */
    private Long expire;

    /**
     * 最后一次登录时间
     */
    private Long lastLoginTime;

    /**
     * 连续登录失败次数
     */
    private Integer loginFailTimes;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIp;
    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;
    /**
     * 已绑定的资产
     */



    /**
     * 关联的密码
     */
    @TableField(exist = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Password> passwords;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "auth_user_role",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @TableField(exist = false)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return CollectionUtils.isEmpty(roles)
                ? Collections.emptyList()
                : roles.stream()
                .map(Role::getCode)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        String password;
        if (CollectionUtils.isEmpty(passwords)) {
            password = null;
        } else if (passwords.size() == 1) {
            password = passwords.iterator().next().getPassword();
        } else {
            password = passwords.stream()
                    .filter(p -> SecurityUtils.defaultPasswordType().equals(p.getType()))
                    .findAny()
                    .map(Password::getPassword)
                    .orElseThrow(() -> new IllegalStateException("该用户没有默认的密码"));
        }
        return password;
    }

    public void setPassword(String password) {
        if (passwords == null) {
            passwords = new LinkedHashSet<>();
        }

        passwords.add(Password.builder()
                .password(password)
                .build());
    }

    @Override
    public boolean isAccountNonExpired() {
        return expire == null || expire >= TimestampUtils.toMills(LocalDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return loginFailTimes == null || loginFailTimes <= 5;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "admin=" + admin +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", expire=" + expire +
                ", lastLoginTime=" + lastLoginTime +
                ", loginFailTimes=" + loginFailTimes +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", passwords=[PROTECTED]" +
                '}';
    }
}

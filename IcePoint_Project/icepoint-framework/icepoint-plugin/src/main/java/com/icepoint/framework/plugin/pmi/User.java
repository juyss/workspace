package com.icepoint.framework.plugin.pmi;

import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

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
public class User extends LongStdEntity {

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

   

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "auth_user_role",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

   
    
}

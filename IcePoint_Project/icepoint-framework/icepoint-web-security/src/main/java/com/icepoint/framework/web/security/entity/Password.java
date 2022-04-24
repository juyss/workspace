package com.icepoint.framework.web.security.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jiawei Zhao
 */
@Table(name = "auth_user_password")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Password extends LongStdEntity {

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    private String type;

    private String algorithm;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}

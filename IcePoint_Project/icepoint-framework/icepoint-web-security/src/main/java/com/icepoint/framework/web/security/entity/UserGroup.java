package com.icepoint.framework.web.security.entity;

import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "pmi_user_group")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class UserGroup extends LongStdEntity {

    @Column(name = "`name`")
    private String name;

    @Column(name = "`code`")
    private String code;

    @Column(name = "`description`")
    private String description;
}

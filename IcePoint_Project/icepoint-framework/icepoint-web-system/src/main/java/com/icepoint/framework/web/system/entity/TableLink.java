package com.icepoint.framework.web.system.entity;

import com.icepoint.framework.data.domain.LongBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jiawei Zhao
 */
@Table(name = "sys_table_link")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class TableLink extends LongBaseEntity {

    private Long tableId;

    private Long fkFieldId;

    private Long linkTableId;

    private String associationType;

    private String name;

    private String nameAlias;
}

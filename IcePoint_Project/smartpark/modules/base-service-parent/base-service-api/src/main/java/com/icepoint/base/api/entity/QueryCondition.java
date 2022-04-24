package com.icepoint.base.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ent_query_condition")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QueryCondition extends BasicEntity<Long> {

    private String name;//

    private String module;//

    private String filed;//

    private String optional;//

    private String value;//

    private String conditional;//

}
package com.icepoint.framework.data.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icepoint.framework.data.dao.EncryptPropertyHandlerListener;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@DynamicUpdate
@EntityListeners(EncryptPropertyHandlerListener.class)
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public abstract class BaseEntity<ID extends Serializable>
        implements Identifiable<ID>, Persistable<ID>, Serializable {

    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "`id`", type = IdType.AUTO)
    @Column(name = "`id`")
    private ID id;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return getId() == null;
    }

}

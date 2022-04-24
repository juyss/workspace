package com.icepoint.base.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Data
public abstract class BasicEntity<ID extends Serializable> implements Identifiable<ID>, Serializable {

    @Id
    private ID id;

}

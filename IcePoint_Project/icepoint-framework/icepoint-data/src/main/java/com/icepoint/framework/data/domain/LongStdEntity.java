package com.icepoint.framework.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

/**
 * @author Jiawei Zhao
 */
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class LongStdEntity extends StdEntity<Long, Long> {
}

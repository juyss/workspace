package com.icepoint.base.web.basic.service;

import com.icepoint.base.api.domain.BasicEntity;

import java.io.Serializable;

/**
 *
 * @param <E> 实体类类型
 * @param <ID> 实体ID的类型
 *
 * @author Jiawei Zhao
 */
public interface Service<E extends BasicEntity<ID>, ID extends Serializable> {
}

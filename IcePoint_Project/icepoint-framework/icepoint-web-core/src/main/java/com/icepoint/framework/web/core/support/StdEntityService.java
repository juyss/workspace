package com.icepoint.framework.web.core.support;

import com.icepoint.framework.data.domain.StdEntity;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 基于{@link StdEntity}实体类的Service接口
 *
 * @author Jiawei Zhao
 */
public interface StdEntityService<T extends StdEntity<ID, ID>, ID extends Serializable>
        extends BaseEntityService<T, ID> {

    Optional<T> findById(ID id, boolean deleted);

    Optional<T> findOne(Example<T> example, boolean deleted) throws IncorrectResultSizeDataAccessException;

    List<T> findAllByIds(Iterable<ID> ids, boolean deleted);

    List<T> findAll(boolean deleted);

    List<T> findAll(Sort sort, boolean deleted);

    Page<T> findAll(Pageable pageable, boolean deleted);

    List<T> findAll(Example<T> example, boolean deleted);

    List<T> findAll(Example<T> example, Sort sort, boolean deleted);

    Page<T> findAll(Example<T> example, Pageable pageable, boolean deleted);

    long count(boolean deleted);

    long count(Example<T> example, boolean deleted);

    boolean exists(Example<T> example, boolean deleted);

    boolean existsById(ID id, boolean deleted);

}

package com.icepoint.framework.web.core.support;

import com.icepoint.framework.data.dao.StdRepository;
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
 * {@link StdEntityService}的基础实现类
 *
 * @author Jiawei Zhao
 */
public abstract class StdEntityServiceImpl<T extends StdEntity<ID, ID>, ID extends Serializable>
        extends BaseEntityServiceImpl<T, ID>
        implements StdEntityService<T, ID> {

    @SuppressWarnings("unchecked")
    StdRepository<T, ID> getStdRepository() {
        return getRepository(StdRepository.class);
    }

    @Override
    public List<T> findAll(boolean deleted) {
        return getStdRepository().findAll(deleted);
    }

    @Override
    public List<T> findAll(Sort sort, boolean deleted) {
        return getStdRepository().findAll(sort, deleted);
    }

    @Override
    public Page<T> findAll(Pageable pageable, boolean deleted) {
        return getStdRepository().findAll(pageable, deleted);
    }


    @Override
    public Optional<T> findById(ID id, boolean deleted) {
        return getStdRepository().findById(id, deleted);
    }

    @Override
    public long count(boolean deleted) {
        return getStdRepository().count(deleted);
    }

    @Override
    public boolean existsById(ID id, boolean deleted) {
        return getStdRepository().existsById(id, deleted);
    }

    @Override
    public Optional<T> findOne(Example<T> example, boolean deleted) throws IncorrectResultSizeDataAccessException {
        return getStdRepository().findOne(example, deleted);
    }

    @Override
    public List<T> findAllByIds(Iterable<ID> ids, boolean deleted) {
        return getStdRepository().findAllById(ids, deleted);
    }

    @Override
    public List<T> findAll(Example<T> example, boolean deleted) {
        return getStdRepository().findAll(example, deleted);
    }

    @Override
    public List<T> findAll(Example<T> example, Sort sort, boolean deleted) {
        return getStdRepository().findAll(example, sort, deleted);
    }

    @Override
    public Page<T> findAll(Example<T> example, Pageable pageable, boolean deleted) {
        return getStdRepository().findAll(example, pageable, deleted);
    }

    @Override
    public long count(Example<T> example, boolean deleted) {
        return getStdRepository().count(example, deleted);
    }

    @Override
    public boolean exists(Example<T> example, boolean deleted) {
        return getStdRepository().exists(example, deleted);
    }
}

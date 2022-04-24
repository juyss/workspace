package com.icepoint.framework.web.core.support;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.dao.BaseRepository;
import com.icepoint.framework.data.domain.BaseEntity;
import com.icepoint.framework.data.mybatis.ExtendedTableInfo;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.util.Lazy;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * {@link BaseEntityService}的基础实现类
 *
 * @author Jiawei Zhao
 */
public abstract class BaseEntityServiceImpl<T extends BaseEntity<ID>, ID extends Serializable>
        extends ServiceSupport
        implements BaseEntityService<T, ID>, InitializingBean, ApplicationContextAware {

    private EntityMetadata<T, ID> metadata;

    private ApplicationContext applicationContext;

    private final Lazy<Repositories> repositories;

    private final Lazy<BaseRepository<T, ID>> repository;

    protected BaseEntityServiceImpl() {
        this.repositories = Lazy.of(() -> getApplicationContext()
                .getBean(Repositories.class));
        this.repository = this.repositories
                .map(rs -> rs.getRepositoryFor(this.metadata.getEntityInformation().getJavaType())
                        .orElseThrow(() -> new IllegalStateException("Repository解析失败")))
                .map(CastUtils::cast);
    }

    @Override
    public void afterPropertiesSet() {

        ResolvableType type = ResolvableType.forInstance(this);
        Class<?> entityType = type
                .as(BaseEntityService.class)
                .resolveGeneric(0);
        Assert.state(entityType != null, "无法解析实体泛型类型");

        EntityInformation<T, ID> info = repositories.get().getEntityInformationFor(entityType);
        Assert.state(info instanceof JpaEntityInformation, "实体信息解析异常");
        JpaEntityInformation<T, ID> information = CastUtils.cast(info);

        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityType);
        if (tableInfo == null) {
            MybatisConfiguration configuration = applicationContext.getBean(MybatisPlusProperties.class)
                    .getConfiguration();
            String resource = entityType.getName().replace('.', '/') + ".java (best guess)";
            MapperBuilderAssistant builderAssistant = new MapperBuilderAssistant(configuration, resource);
            tableInfo = TableInfoHelper.initTableInfo(builderAssistant, entityType);
        }
        Assert.state(tableInfo != null, "找不到TableInfo");

        if (!(tableInfo instanceof ExtendedTableInfo)) {
            tableInfo = new ExtendedTableInfo(tableInfo);
        }

        this.metadata = new EntityMetadata<>(information, tableInfo); // NOSONAR
    }

    @Override
    public EntityMetadata<T, ID> getEntityMetadata() {
        return this.metadata;
    }

    protected ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    BaseRepository<T, ID> getRepository() {
        return this.repository.get();
    }

    <R extends BaseRepository<T, ID>> R getRepository(Class<R> type) {
        return this.repository
                .map(type::cast)
                .get();
    }

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Optional<T> findOne(Example<T> example) throws IncorrectResultSizeDataAccessException {
        return getRepository().findOne(example);
    }

    @Override
    public List<T> findAllByIds(Iterable<ID> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public List<T> findAll(Example<T> example) {
        return getRepository().findAll(example);
    }

    @Override
    public List<T> findAll(Example<T> example, Sort sort) {
        return getRepository().findAll(example, sort);
    }

    @Override
    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return getRepository().findAll(example, pageable);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public long count(Example<T> example) {
        return getRepository().count(example);
    }

    @Override
    public boolean exists(Example<T> example) {
        return getRepository().exists(example);
    }

    @Override
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T save(T entity, SaveType type) throws IllegalArgumentException, EmptyResultDataAccessException {
        ID id = entity.getId();
        switch (type) {
            case FORCE_INSERT:
                Assert.isNull(id, "插入数据的id必须为空");
                break;
            case FORCE_UPDATE_FULLY:
                Assert.notNull(id, "更新数据的id必须不能为空");
                if (!existsById(id)) {
                    throw new EmptyResultDataAccessException("要更新数据不存在, id: " + id, 1);
                }
                break;
            case FORCE_UPDATE_NONNULL:
                Assert.notNull(id, "更新数据的id必须不能为空");
                T db = findById(id).orElseThrow(() -> new EmptyResultDataAccessException("要更新数据不存在, id: " + id, 1));
                BeanUtils.copyProperties(entity, db, true);
                entity = db;
                break;
            case DEFAULT:
            default:
                // do nothing
        }

        return save(entity);
    }

    @Override
    public List<T> saveAll(Iterable<T> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    public void deleteById(ID id) throws EmptyResultDataAccessException {
        if (!existsById(id)) {
            throw new EmptyResultDataAccessException("要删除数据不存在, id: " + id, 1);
        }
        getRepository().deleteById(id);
    }

    @Override
    public void deleteAllInIds(Iterable<ID> ids) {
        getRepository().deleteAllInId(ids);
    }

    @Override
    public void delete(T entity) throws EmptyResultDataAccessException {
        ID id = entity.getId();
        Assert.notNull(id, "要删除的数据id不能为空");
        deleteById(id);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        Set<ID> ids = Streams.streamable(entities)
                .map(BaseEntity::getId)
                .toSet();
        deleteAllInIds(ids);
    }

}

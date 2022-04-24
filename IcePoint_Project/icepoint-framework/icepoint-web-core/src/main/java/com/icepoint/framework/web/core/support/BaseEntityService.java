package com.icepoint.framework.web.core.support;

import com.icepoint.framework.data.domain.BaseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 基于{@link BaseEntity}实体类的Service接口，另外继承了{@link EntityMetadataProvider}，可以获取对应实体的相关信息
 *
 * @author Jiawei Zhao
 */
public interface BaseEntityService<T extends BaseEntity<ID>, ID extends Serializable>
        extends Service, EntityMetadataProvider<T, ID> {

    /**
     * 根据id查询实体数据
     *
     * @param id 实体id
     * @return 查询结果
     */
    Optional<T> findById(ID id);

    /**
     * 根据{@link Example}查询对象查询单个实体数据
     *
     * @param example 查询对象
     * @return 查询结果
     * @throws IncorrectResultSizeDataAccessException 当查询结果超出1个时
     */
    Optional<T> findOne(Example<T> example) throws IncorrectResultSizeDataAccessException;

    /**
     * 根据多个id查询所有对应的实体数据
     *
     * @param ids id集合
     * @return 查询结果列表
     */
    List<T> findAllByIds(Iterable<ID> ids);

    /**
     * 无条件查询所有实体数据
     *
     * @return 查询结果列表
     */
    List<T> findAll();

    /**
     * 无条查询所有实体数据，并根据{@link Sort}对象进行排序
     *
     * @param sort 排序定义对象
     * @return 查询结果列表
     */
    List<T> findAll(Sort sort);

    /**
     * 无条查询所有实体数据，并根据{@link Pageable}对象进行分页
     *
     * @param pageable 分页定义对象
     * @return 查询结果分页列表
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 根据{@link Example}查询对象查询所有符合条件的实体数据
     *
     * @param example 查询对象
     * @return 查询结果列表
     */
    List<T> findAll(Example<T> example);

    /**
     * 根据{@link Example}查询对象查询所有符合条件的实体数据，并根据{@link Sort}对象进行排序
     *
     * @param example 查询对象
     * @param sort    排序定义对象
     * @return 查询结果列表
     */
    List<T> findAll(Example<T> example, Sort sort);

    /**
     * 根据{@link Example}查询对象查询所有符合条件的实体数据，并根据{@link Pageable}对象进行分页
     *
     * @param example  查询对象
     * @param pageable 分页定义对象
     * @return 查询结果分页列表
     */
    Page<T> findAll(Example<T> example, Pageable pageable);

    /**
     * 统计所有实体数据记录的数量
     *
     * @return 实体数据记录的数量
     */
    long count();

    /**
     * 根据{@link Example}对象统计实体数据记录的数量
     *
     * @param example 查询对象
     * @return 实体数据记录的数量
     */
    long count(Example<T> example);

    /**
     * 据{@link Example}对象，判断符合条件的实体数据是否存在
     *
     * @param example 查询对象
     * @return 如果存在
     */
    boolean exists(Example<T> example);

    /**
     * 判断对应id的实体数据是否存在
     *
     * @param id 实体数据id
     * @return 如果存在
     */
    boolean existsById(ID id);

    /**
     * 根据实体数据的id是否为null进行插入或者更新操作
     *
     * @param entity 要保存的实体数据
     * @return 保存后的实体数据，不管是否保存所有对象，都会包含该实体数据的所有字段值
     */
    T save(T entity);

    /**
     * 保存实体数据，并根据{@link SaveType}进行对应的操作和验证
     *
     * @param entity 要保存的实体数据
     * @return 保存后的实体数据，不管是否保存所有对象，都会包含该实体数据的所有字段值
     * @throws IllegalArgumentException       如果对应id与保存类型不匹配
     * @throws EmptyResultDataAccessException 要更新实体数据不存在
     */
    T save(T entity, SaveType type) throws IllegalArgumentException, EmptyResultDataAccessException;

    /**
     * 根据实体数据的id是否为null进行批量插入或者批量更新操作
     *
     * @param entities 要保存的实体数据集合
     * @return 保存后的实体数据，不管是否保存所有对象，都会包含该实体数据的所有字段值
     */
    List<T> saveAll(Iterable<T> entities);

    /**
     * 删除对应id的实体数据
     *
     * @param id 要删除的实体数据id
     * @throws EmptyResultDataAccessException 要更新实体数据不存在
     */
    void deleteById(ID id) throws EmptyResultDataAccessException;

    /**
     * 删除对应id集合的所有实体数据，允许要删除的数据不存在
     *
     * @param ids 要删除的实体数据id集合
     */
    void deleteAllInIds(Iterable<ID> ids);

    /**
     * 删除实体数据，也是根据实体的id进行删除
     *
     * @param entity 要删除的实体数据
     */
    void delete(T entity) throws EmptyResultDataAccessException;

    /**
     * 删除实体数据，也是根据实体的id进行删除，允许要删除的数据不存在
     *
     * @param entities 要删除的实体数据集合
     */
    void deleteAll(Iterable<? extends T> entities);

}

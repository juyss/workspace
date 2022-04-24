package com.icepoint.framework.web.system.resource;

/**
 * @author Jiawei Zhao
 */
public interface ResourceAccessProcessor {

    /**
     * 获取数据之前调用的方法
     *
     * @param lookup Lookup
     */
    default void preLoad(Lookup lookup) {
    }

    /**
     * 新增数据之前调用的方法
     *
     * @param model ResourceModel
     */
    default void prePersist(ResourceModel model) {
    }

    /**
     * 更新数据之前调用的方法
     *
     * @param model ResourceModel
     */
    default void preUpdate(ResourceModel model) {
    }

    /**
     * 根据id删除数据之前嗲用的方法
     *
     * @param lookup Lookup
     * @param id     删除数据的id
     */
    default void preDeleteById(Lookup lookup, Object id) {
    }

}

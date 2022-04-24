package com.icepoint.framework.data.mybatis;

import com.icepoint.framework.data.domain.StdEntity;
import org.springframework.stereotype.Component;

/**
 * 设置审计字段
 *
 * @author Jiawei Zhao
 */
@Component
public class MybatisAuditorListener implements MybatisPersistListener {

    @Override
    public void insertFill(StdEntity<?, ?> entity) {
        entity.setCreateTime(System.currentTimeMillis());
        entity.setUpdateTime(System.currentTimeMillis());
    }

    @Override
    public void updateFill(StdEntity<?, ?> entity) {
        entity.setUpdateTime(System.currentTimeMillis());
    }
}

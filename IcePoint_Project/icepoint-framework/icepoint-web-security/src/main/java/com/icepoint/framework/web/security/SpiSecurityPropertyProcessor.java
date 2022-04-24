package com.icepoint.framework.web.security;

import com.icepoint.framework.data.domain.StdEntity;
import com.icepoint.framework.data.domain.StdEntityEventListener;
import com.icepoint.framework.data.mybatis.MybatisPersistListener;
import com.icepoint.framework.web.security.util.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Component
public class SpiSecurityPropertyProcessor implements StdEntityEventListener, MybatisPersistListener {

    /**
     * JPA的持久化拦截
     *
     * @param entity 实体对象
     */
    @Override
    public void prePersist(StdEntity<?, ?> entity) {
        doProcess(entity);
    }

    /**
     * Mybatis的持久化拦截
     *
     * @param entity 实体对象
     */
    @Override
    public void insertFill(StdEntity<?, ?> entity) {
        doProcess(entity);
    }

    private void doProcess(StdEntity<?, ?> entity) {
        if (entity.getAppId() == null) {
            entity.setAppId(SecurityUtils.getAppId());
        }
        if (entity.getOwnerId() == null) {
            entity.setOwnerId(SecurityUtils.getOwnerId());
        }
        if (entity.getPlatformId() == null) {
            entity.setPlatformId(SecurityUtils.getPlatformId());
        }
    }
}

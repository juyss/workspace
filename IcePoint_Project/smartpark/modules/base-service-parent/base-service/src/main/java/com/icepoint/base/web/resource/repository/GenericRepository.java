package com.icepoint.base.web.resource.repository;

import com.icepoint.base.api.domain.GenericEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 资源数据仓储接口
 *
 * @author Jiawei Zhao
 * @see GenericTableRepository
 * @see DatabaseTableRepository
 * @see ChildCollectionRepository
 * @see ThirdPartyRepository
 */
@NoRepositoryBean
public interface GenericRepository
        extends ResourceRepositoryImplementor<GenericEntity, Object> {

}

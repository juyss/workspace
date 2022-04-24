package com.icepoint.framework.data.dao;

import com.icepoint.framework.data.domain.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jiawei Zhao
 */
@NoRepositoryBean
public interface StringBaseRepository<T extends BaseEntity<String>>
        extends BaseRepository<T, String> {
}

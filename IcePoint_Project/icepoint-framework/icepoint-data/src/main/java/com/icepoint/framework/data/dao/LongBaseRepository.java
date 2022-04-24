package com.icepoint.framework.data.dao;

import com.icepoint.framework.data.domain.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jiawei Zhao
 */
@NoRepositoryBean
public interface LongBaseRepository<T extends BaseEntity<Long>> extends BaseRepository<T, Long> {
}

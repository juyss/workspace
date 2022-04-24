package com.icepoint.framework.data.dao;

import com.icepoint.framework.data.domain.StdEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jiawei Zhao
 */
@NoRepositoryBean
public interface LongStdRepository<T extends StdEntity<Long, Long>>
        extends LongBaseRepository<T>, StdRepository<T, Long> {
}

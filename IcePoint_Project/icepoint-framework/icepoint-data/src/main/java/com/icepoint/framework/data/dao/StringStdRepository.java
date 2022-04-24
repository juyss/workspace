package com.icepoint.framework.data.dao;

import com.icepoint.framework.data.domain.StdEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jiawei Zhao
 */
@NoRepositoryBean
public interface StringStdRepository<T extends StdEntity<String, String>>
        extends StringBaseRepository<T>, StdRepository<T, String> {
}

package com.icepoint.framework.web.security.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.security.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface UserRepository extends LongStdRepository<User> {
}

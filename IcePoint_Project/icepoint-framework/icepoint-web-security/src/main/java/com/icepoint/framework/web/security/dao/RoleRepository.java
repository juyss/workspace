package com.icepoint.framework.web.security.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.security.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface RoleRepository extends LongStdRepository<Role> {
}

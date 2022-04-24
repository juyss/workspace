package com.icepoint.framework.web.security.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.security.entity.UserGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends LongStdRepository<UserGroup> {
}

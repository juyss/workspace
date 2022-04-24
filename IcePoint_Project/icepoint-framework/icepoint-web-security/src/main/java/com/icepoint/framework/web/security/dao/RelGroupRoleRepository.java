package com.icepoint.framework.web.security.dao;

import com.icepoint.framework.data.dao.LongBaseRepository;
import com.icepoint.framework.data.dao.StdRepository;
import com.icepoint.framework.web.security.entity.RelGroupRole;
import org.springframework.stereotype.Repository;

@Repository
public interface RelGroupRoleRepository extends LongBaseRepository<RelGroupRole>, StdRepository<RelGroupRole, Long> {

}

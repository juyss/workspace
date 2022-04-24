package com.icepoint.framework.web.security.dao;

import com.icepoint.framework.data.dao.LongBaseRepository;
import com.icepoint.framework.data.dao.StdRepository;
import com.icepoint.framework.web.security.entity.RelRoleData;
import org.springframework.stereotype.Repository;

@Repository
public interface RelRoleDataRepository extends LongBaseRepository<RelRoleData>, StdRepository<RelRoleData, Long> {

}

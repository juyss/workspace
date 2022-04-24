package com.icepoint.framework.web.security.dao;

import com.icepoint.framework.data.dao.LongBaseRepository;
import com.icepoint.framework.data.dao.StdRepository;
import com.icepoint.framework.web.security.entity.Organization;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends LongBaseRepository<Organization>, StdRepository<Organization, Long> {

}

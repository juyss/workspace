package com.icepoint.framework.sample.module.sample.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.sample.module.sample.entity.Student;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@Repository
@MultiplyPathRepositoryRestResource(path = "/students")
public interface StudentRepository extends LongStdRepository<Student> {
}

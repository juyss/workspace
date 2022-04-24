package com.juyss.mapper;

import com.juyss.pojo.Student;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: StudentMapper
 * @Desc: student表的操作接口
 * @package com.juyss.mapper
 * @project Mybatis
 * @date 2020/8/27 18:30
 */
public interface StudentMapper {

    List<Student> getStudentList();

    Student getStudentById(Integer id);

    int insert(Student student);

    int update(Student student);

    int delete(Integer id);
}

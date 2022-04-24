package com.juyss.mapper;

import com.juyss.pojo.Teacher;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: TeacherMapper
 * @Desc: teacher表的操作接口
 * @package com.juyss.mapper
 * @project Mybatis
 * @date 2020/8/27 18:54
 */
public interface TeacherMapper {

    List<Teacher> getTeacherList();

    Teacher getTeacherById(Integer id);

    int insert(Teacher teacher);

    int update(Teacher teacher);

    int delete(Integer id);
}

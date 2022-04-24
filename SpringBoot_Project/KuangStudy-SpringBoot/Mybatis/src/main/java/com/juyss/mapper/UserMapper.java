package com.juyss.mapper;

import com.juyss.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapper
 * @Desc:
 * @package com.juyss.mapper
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 11:20
 */
@Repository
@Mapper
public interface UserMapper {

    List<Person> getAll();

    int insert(Person person);

    int delete(@Param("id") Integer id);

    int update(Person person);

}

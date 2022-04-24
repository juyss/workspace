package com.juyss.mapper;

import com.juyss.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapper
 * @Desc:
 * @package com.juyss.mapper
 * @project atguigu-Advanced
 * @date 2020/12/6 18:31
 */
@Mapper
public interface UserMapper {

    /**
     * 根据name字段查询
     * @return User
     */
    User selectByName(@Param("name") String name);
}

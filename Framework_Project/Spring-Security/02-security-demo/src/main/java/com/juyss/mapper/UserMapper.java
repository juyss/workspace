package com.juyss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juyss.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserMapper
 * @package com.juyss.mapper
 * @project Spring-Security
 * @date 2021/12/16 1:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

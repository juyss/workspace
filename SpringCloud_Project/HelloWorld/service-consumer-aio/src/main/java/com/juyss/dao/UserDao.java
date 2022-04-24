package com.juyss.dao;

import com.juyss.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDao
 * @Desc:
 * @package com.juyss.dao
 * @project HelloWorld
 * @date 2020/10/29 21:20
 */
@Repository
public class UserDao {

    public User getUser(Integer id){
        return new User(id,"第"+id+"号User");
    }
}

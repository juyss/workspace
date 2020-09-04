package com.DAO;

import com.pojo.Person;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PersonDAO
 * @Desc:  对person表的操作的接口类
 * @package com.DAO
 * @project JDBC_Example
 * @date 2020/7/5 10:49
 */
public interface PersonDAO {
    /**
     * 向数据库插入一条数据
     * @param person
     */
    void insert(Person person);
}

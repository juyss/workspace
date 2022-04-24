package com.DAO;

import com.pojo.Person;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PersonImpl
 * @Desc:  对person表操作的具体实现类
 * @package com.DAO
 * @project JDBC_Example
 * @date 2020/7/5 10:50
 */
public class PersonDAOImpl extends BaseDAO implements PersonDAO{

    /**
     * 向表中插入一条数据
     * @param person
     */
    @Override
    public void insert(Person person) {
        String sql = "insert into `person` values (?,?,?,?,?)";
        try {
            update(sql, null,person.getName(),person.getAge(),person.getGender(),person.getBirthday());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

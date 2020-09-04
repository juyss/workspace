package com.test;

import com.DAO.PersonDAOImpl;
import com.pojo.Person;

import java.sql.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PersonDAOTest
 * @Desc:  测试PersonDAO
 * @package com.test
 * @project JDBC_Example
 * @date 2020/7/5 11:00
 */
public class PersonDAOTest {
    public static void main(String[] args){
        Date date = new Date(1234564789156L);
        Person p = new Person("Jasica", 16, "女", date);
        PersonDAOImpl dao = new PersonDAOImpl();
        dao.insert(p);
    }
}

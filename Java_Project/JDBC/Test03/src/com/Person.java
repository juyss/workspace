package com;

import java.sql.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: com.Person
 * @Desc:  属性对应数据库的字段
 *          每一个类的对象都对应一条数据
 * @package PACKAGE_NAME
 * @project JDBC
 * @date 2020/6/2 17:32
 */
public class Person {
    private int id;
    private String name;
    private int age;
    private String gender;
    private Date birthday;

    public Person() {
    }

    public Person(int id, String name, int age, String gender, Date birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "com.Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

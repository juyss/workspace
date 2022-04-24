package com.pojo;

import java.sql.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Person
 * @Desc:
 * @package com.pojo
 * @project JDBC_Example
 * @date 2020/7/4 19:48
 */
public class Person {

    String name;
    int age;
    String gender;
    Date birthday;

    public Person() {
    }

    public Person(String name, int age, String gender, Date birthday) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

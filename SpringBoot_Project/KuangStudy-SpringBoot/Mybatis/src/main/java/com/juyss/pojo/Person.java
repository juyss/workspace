package com.juyss.pojo;

import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Person
 * @Desc:
 * @package com.juyss.pojo
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 11:14
 */
@Component
public class Person {

    private Integer id;
    private String Name;
    private Integer age;
    private String gender;
    private Date birthday;

    public Person() {
    }

    public Person(Integer id, String name, Integer age, String gender, Date birthday) {
        this.id = id;
        Name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

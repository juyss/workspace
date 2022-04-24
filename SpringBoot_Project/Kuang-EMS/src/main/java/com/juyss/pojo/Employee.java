package com.juyss.pojo;


import java.util.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Employee
 * @Desc: 员工
 * @package com.juyss.pojo
 * @project Kuang-EMS
 * @date 2020/10/23 12:01
 */
public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private Integer gender; //0:女  1:男

    private Department department;
    private Date birthday;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birthday = new Date(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", department=" + department +
                ", birthday=" + birthday +
                '}';
    }
}

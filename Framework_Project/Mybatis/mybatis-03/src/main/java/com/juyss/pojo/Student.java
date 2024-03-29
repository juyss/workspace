package com.juyss.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Student
 * @Desc: student表实体类
 * @package com.juyss.pojo
 * @project Mybatis
 * @date 2020/8/27 17:37
 */
public class Student {

    private int id;
    private String name;

    private Teacher teacher;

    public Student() {
    }

    public Student(int id, String name, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}

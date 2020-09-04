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

    private Integer id;
    private String name;
    private Integer tid;

    public Student() {
    }

    public Student(Integer id, String name, Integer tid) {
        this.id = id;
        this.name = name;
        this.tid = tid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid=" + tid +
                '}';
    }
}

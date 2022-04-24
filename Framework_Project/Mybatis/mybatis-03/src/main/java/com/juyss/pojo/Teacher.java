package com.juyss.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Teacher
 * @Desc: teacher表实体类
 * @package com.juyss.pojo
 * @project Mybatis
 * @date 2020/8/27 17:35
 */
public class Teacher {

    private Integer id;
    private String name;

    public Teacher() {
    }

    public Teacher(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.juyss.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Department
 * @Desc: 部门
 * @package com.juyss.pojo
 * @project Kuang-EMS
 * @date 2020/10/23 11:59
 */
public class Department {

    private Integer id;
    private String name;

    public Department() {
    }

    public Department(Integer id, String name) {
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
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

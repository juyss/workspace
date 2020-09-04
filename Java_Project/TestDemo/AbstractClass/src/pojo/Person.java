package pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: pojo.Person
 * @Desc:
 * @package PACKAGE_NAME
 * @project TestDemo
 * @date 2020/7/20 16:02
 */
public abstract class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getDesc();
}

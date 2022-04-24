package com.shme.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Person
 * @Desc:
 * @package com.shme.pojo
 * @project javaweb-session
 * @date 2020/6/17 22:53
 */
public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

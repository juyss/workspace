package com.springAPI.pojo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Person
 * @Desc:
 * @package com.springAPI.pojo
 * @project KuangStudy-Spring
 * @date 2020/9/3 16:27
 */
public class Person {

    @Autowired
    private Cat cat;

    @Autowired
    private Dog dog;

    private String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}

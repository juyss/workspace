/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc: p命名空间注入:通过set方法注入
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/3 14:34
 */
public class User {

    private int age;
    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

package comjuyss.pojo;

import java.sql.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc:
 * @package comjuyss.pojo
 * @project SpringTest
 * @date 2020/7/4 22:08
 */
public class User {

    Integer id;
    String name;
    Integer age;
    String gender;
    Date birthday;

    public User() {
    }

    public User(Integer id, String name, Integer age, String gender, Date birthday) {
        this.id = id;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

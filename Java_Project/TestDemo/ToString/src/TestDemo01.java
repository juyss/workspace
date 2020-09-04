import java.util.ArrayList;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: TestDemo01
 * @Desc: 重写类的toString方法后,类的集合打印结果测试
 * @package PACKAGE_NAME
 * @project TestDemo
 * @date 2020/7/9 23:45
 */
public class TestDemo01 {
    public static void main(String[] args) {
        User user1 =new User("AA",1);
        User user2 =new User("BB",2);
        User user3 =new User("CC",3);
        ArrayList list = new ArrayList();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        System.out.println(list);
    }
}
class User{
    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
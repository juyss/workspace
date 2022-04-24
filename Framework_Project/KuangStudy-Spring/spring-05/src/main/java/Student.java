import java.util.*;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Student
 * @Desc: 不同类型属性值的注入方式
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/3 13:48
 */
public class Student {

    private int id;
    private String name;
    private Address address;
    private String[] hobbies;
    private List<String> subject;
    private Set<String> games;
    private Map<String,String> score;
    private Properties info;
    private String wife;
    private String children;

    public Student() {
    }

    public Student(int id, String name, Address address, String[] hobbies, List<String> subject, Set<String> games, Map<String, String> score, Properties info, String wife, String children) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hobbies = hobbies;
        this.subject = subject;
        this.games = games;
        this.score = score;
        this.info = info;
        this.wife = wife;
        this.children = children;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public Map<String, String> getScore() {
        return score;
    }

    public void setScore(Map<String, String> score) {
        this.score = score;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", subject=" + subject +
                ", games=" + games +
                ", score=" + score +
                ", info=" + info +
                ", wife='" + wife + '\'' +
                ", children='" + children + '\'' +
                '}';
    }
}

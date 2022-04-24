/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Address
 * @Desc: 被引用类
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/3 13:47
 */
public class Address {

    private String Location;

    public Address() {
    }

    public Address(String location) {
        Location = location;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public String toString() {
        return "Address{" +
                "Location='" + Location + '\'' +
                '}';
    }
}

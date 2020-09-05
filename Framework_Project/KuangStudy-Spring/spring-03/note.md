### User.java
```java
@Component
public class User {


    private String name;

    public String getName() {
        return name;
    }

    @Value("Juyss")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

### SpringConfig.java
```java
@Configuration
@ComponentScan("com.springAPIgAPI.pojo")
public class SpringConfig {

    @Bean
    public User getUser(){
        return new User();
    }

}
```
## 总结

### 情况一:
    配置类中类名添加`@Configuration`注解,同时提供一个方法返回一个Bean实例,且这个方法添加注解`@Bean`.就可以由Spring管理此对象
    如下所示:
#### User.java
```java
public class User {


    private String name;

    public String getName() {
        return name;
    }

    @Value("Juyss") //注入name属性的值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

#### SpringConfig.java
```java
@Configuration //表明此类为Spring配置类
public class SpringConfig {

    @Bean //返回一个实例,注册为一个Bean由Spring管理
    public User getUser(){
        return new User();
    }

}
```
#### ConfTest.java
```java
public class ConfTest {

    @Test
    public void Test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        User user = context.getBean("getUser", User.class); //第一个参数需与方法名一致
        System.out.println(user);
    }
}
```
    测试类运行结果得到:User{name='Juyss'}
### 情况二:
    配置类中类名上添加注解`@Configuration`表明为Spring配置类,添加注解`@ComponentScan("ClassPath")`表明扫描的包路径
    然后在需要被Spring管理的Bean类名添加注解`@Component`
### User.java
```java
@Component //表明这个类注册为Bean组件
public class User {


    private String name;

    public String getName() {
        return name;
    }

    @Value("Juyss") //注入name属性的值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```
### SpringConfig.java
```java
@Configuration //表明为Spring配置类
@ComponentScan("com.springAPIgAPI.pojo") //扫描此包下的所有已注册的Bean
public class SpringConfig {
}
```
#### ConfTest.java
```java
public class ConfTest {

    @Test
    public void Test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        User user = context.getBean("user", User.class); //第一个参数为类名首字母小写
        System.out.println(user);
    }
}
```
    测试类运行结果得到:User{name='Juyss'}
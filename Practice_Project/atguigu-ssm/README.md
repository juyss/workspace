## 尚硅谷ssm-crud整合项目实现步骤

### 1. 整合基本的SSM框架

### 2.建表

#### ssm-emp

```sql
CREATE TABLE IF NOT EXISTS `ssm_emp`(
	`emp_id` INT(11) NOT NULL,
	`emp_name` VARCHAR(20) NOT NULL,
	`emp_gender` VARCHAR(2) NOT NULL,
	`emp_email` VARCHAR(50) NOT NULL,
        `d_id` int(11) 	NOT NULL,
	PRIMARY KEY (`emp_id`)
)ENGINE=INNODB CHARSET=utf8;
```

#### ssm-dept

```sql
CREATE TABLE IF NOT EXISTS `ssm-dept`(
	`dept_id` INT(11) NOT NULL,
	`dept_name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (dept_id)
)ENGINE=INNODB CHARSET=utf8;
```

### 3. 使用Mybatis-generator快捷生成

#### 配置文件:mbg.xml


#### Java类执行生成

```java
public class Mbg {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        File configFile = new File("mbg.xml");
        System.out.println(configFile);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }
}
```

#### 添加连接查询方法
EmployeeMapper.xml
```xml
<!--联合查询-->
  <resultMap id="WithDeptResultMap" type="com.juyss.pojo.Employee">
    <id column="emp_id" jdbcType="INTEGER" property="empId" />
    <result column="emp_name" jdbcType="VARCHAR" property="empName" />
    <result column="emp_gender" jdbcType="VARCHAR" property="empGender" />
    <result column="emp_email" jdbcType="VARCHAR" property="empEmail" />
    <result column="d_id" jdbcType="INTEGER" property="dId" />
    <association property="department" javaType="com.juyss.pojo.Department">
      <id column="dept_id" jdbcType="INTEGER" property="deptId"/>
      <result column="dept_name" jdbcType="VARCHAR" property="deptName"/>
    </association>
  </resultMap>

<!--  带部门信息字段的SQL片段  -->
  <sql id="WithDept_Column_List">
    e.emp_id, e.emp_name, e.emp_gender, e.emp_email, e.d_id, d.dept_id, d.dept_name
  </sql>

<!--  带部门信息的查询语句 -->
  <select id="selectByExampleWithDept" parameterType="com.juyss.pojo.EmployeeExample" resultMap="WithDeptResultMap">
    select
    <if test="distinct">
      distinct
    </if>
    <include refid="WithDept_Column_List" />
    from ssm_emp e
    left join ssm_dept d
    on e.d_id = d.dept_id
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
    <if test="orderByClause != null">
      order by ${orderByClause}
    </if>
  </select>

<!--  带部门信息的单个查询  -->
  <select id="selectByPrimaryKeyWithDept" parameterType="java.lang.Integer" resultMap="WithDeptResultMap">
    select
    <include refid="WithDept_Column_List"/>
    from ssm_emp e
    left join ssm_dept d
    on e.d_id = d.dept_id
    where e.emp_id=#{empId,jdbcType=INTEGER}
  </select>

```

### 4.测试并添加表信息
```java
/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SpringUnitTest
 * @Desc: Spring提供自动注入功能,单元测试
 * @package PACKAGE_NAME
 * @project atguigu-ssm
 * @date 2020/9/27 13:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class SpringUnitTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private Department department;

    @Autowired
    private Employee employee;

    @Test
    public void Test01(){
        department.setDeptId(0);
        department.setDeptName("开发部");
        departmentMapper.insertSelective(department);
        System.out.println("===================开发部插入完成");

        department.setDeptId(1);
        department.setDeptName("产品部");
        departmentMapper.insertSelective(department);
        System.out.println("===================产品部插入完成");
    }

    @Test
    public void Test02(){
        for (int i = 0 ;i<=20 ;i++) {
            employee.setEmpId(i);
            employee.setEmpName("员工"+i);
            if(i % 3 ==0){
                employee.setEmpGender("男");
                employee.setdId(0);
            }else{
                employee.setEmpGender("女");
                employee.setdId(1);
            }
            employee.setEmpEmail("employeeID"+i+"@163.com");

            employeeMapper.insertSelective(employee);
            System.out.println("====================第"+i+"个员工插入完成");
        }
        System.out.println("==========================全部插入完成");
    }

}
```
### 5.编写Service层接口及其实现类

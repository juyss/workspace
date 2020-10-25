import com.juyss.mapper.DepartmentMapper;
import com.juyss.mapper.EmployeeMapper;
import com.juyss.pojo.Department;
import com.juyss.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SpringUnitTest
 * @Desc: Spring提供的单元测试
 * @package PACKAGE_NAME
 * @project atguigu-ssm
 * @date 2020/9/27 13:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
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

        employee.setEmpId(0);
        employee.setEmpName("admin");
        employee.setEmpGender("男");
        employee.setEmpEmail("admin@163.com");
        employee.setdId(1);
        employeeMapper.insertSelective(employee);
        System.out.println("===================admin插入完成");

        employee.setEmpId(1);
        employee.setEmpName("李明");
        employee.setEmpGender("男");
        employee.setEmpEmail("liming@163.com");
        employee.setdId(0);
        employeeMapper.insertSelective(employee);
        System.out.println("====================李明插入完成");
    }

    @Test
    public void Test02(){
        for (int i = 21 ;i<=40 ;i++) {
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

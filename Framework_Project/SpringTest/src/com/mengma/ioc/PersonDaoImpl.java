package com.mengma.ioc;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PersonDaoImpl
 * @Desc:
 * @package com.mengma.ioc
 * @project SpringTest
 * @date 2020/6/5 16:19
 */
public class PersonDaoImpl implements PersonDao{
    @Override
    public void add() {
        System.out.println("add()方法执行了...");
    }
}

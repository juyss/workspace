package test;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Test01
 * @Desc:
 * @package test
 * @project TestDemo
 * @date 2020/7/20 16:26
 */
public class Test01 {
    public static void main(String[] args) {
        Class<Integer> clazz01 = int.class;
        System.out.println(clazz01.getClass());
        Class<Integer> clazz02 = Integer.class;
        System.out.println(clazz02.getClass());
    }
}

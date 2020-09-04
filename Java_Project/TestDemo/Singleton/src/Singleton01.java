/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Singleton01
 * @Desc: 单例模式懒汉式,通过双重判断,保证线程安全
 * @package PACKAGE_NAME
 * @project TestDemo
 * @date 2020/9/2 23:45
 */
public class Singleton01 {
    public static void main(String[] args) {
        Admin admin = new Admin();

        Thread thread01 = new Thread(admin);
        Thread thread02 = new Thread(admin);
        Thread thread03 = new Thread(admin);
        Thread thread04 = new Thread(admin);

            thread01.start();
            thread02.start();
            thread03.start();
            thread04.start();
    }

}

class Admin implements Runnable{

    private static Admin admin;

    public static Admin getInstance() {
        if (admin == null) {
            synchronized (Admin.class) {
                if (admin == null) {
                    admin = new Admin();
                }
            }
        }
        return admin;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+getInstance());
    }
}

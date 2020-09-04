/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Singleton
 * @Desc: 单例模式练习
 * @project TestDemo
 * @date 2020/7/27 8:59
 */
public class Singleton {
    public static void main(String[] args) {
        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();
        Bank bank3 = Bank.getInstance();
        Bank bank4 = Bank.getInstance();
        System.out.println(bank1==bank2);
        System.out.println(bank3==bank2);
        System.out.println(bank4==bank2);
        System.out.println(bank4==bank1);
    }
}

//懒汉式
class Bank{

    private static Bank instance;

    private Bank(){}

    public static Bank getInstance() {
        if (instance==null) {
            synchronized (Bank.class) {
                if (instance==null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
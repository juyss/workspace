import java.util.ArrayList;
import java.util.Scanner;

public class ManagerSystem {


    //定义一个方法，判断账号是否被使用
    public boolean isUsed(ArrayList<Worker> array, String sid) {
        //如果与集合中的某一个账号相同，返回true;如果都不相同，返回false
        boolean flag = false;

        for (int i = 0; i < array.size(); i++) {
            Worker s = array.get(i);
            if (s.getId().equals(sid)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //方法：添加员工信息
    public void addWorkers(ArrayList<Worker> workers) {
        Scanner sc = new Scanner(System.in);
        String id;
        while (true) {
            System.out.println("请输入账号");
            id = sc.nextLine();
            boolean flag = isUsed(workers, id);
            if (flag) {
                System.out.println("你输入的账号已经被占用，请重新输入");
            } else {
                break;
            }
        }
        //创建员工对象，赋值给成员变量
        Worker w = new Worker();
        w.setId(id);
        workers.add(w);
    }

    //方法：列出集合内容
    public void list(ArrayList<Worker> workers) {
        //判断是否有内容
        if (workers.size() == 0) {
            System.out.println("无内容");
        }

        //显示表头信息
        System.out.println("用户名");
        for (int i = 0; i < workers.size(); i++) {
            Worker w = workers.get(i);
            System.out.println(w.getId());
        }
    }

    public void set(ArrayList<Worker> workers) {

    }

    public static void main(String[] args) {
        ArrayList<Worker> workers = new ArrayList<>();
        ManagerSystem ms = new ManagerSystem();
        ms.addWorkers(workers);
        ms.addWorkers(workers);
        ms.addWorkers(workers);
        ms.list(workers);
    }
}

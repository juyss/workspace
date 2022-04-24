import java.util.Scanner;

public class input {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        int result = Integer.reverse(i);
//        //直接调用reverse方法
//        System.out.println();
//
//        //创建对象调用方法
//        solution s = new solution();
////        int result = s.reverse(i);
        System.out.println(result);
    }
}

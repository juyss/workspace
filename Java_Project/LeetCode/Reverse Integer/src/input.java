import java.util.Scanner;

public class input {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        int result = Integer.reverse(i);
//        //ֱ�ӵ���reverse����
//        System.out.println();
//
//        //����������÷���
//        solution s = new solution();
////        int result = s.reverse(i);
        System.out.println(result);
    }
}

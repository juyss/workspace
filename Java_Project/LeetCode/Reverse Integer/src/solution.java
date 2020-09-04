import java.util.Scanner;

public class solution {
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int temp = x % 10;
            x /= 10;
            rev = rev * 10 + temp;
        }
        return rev;
    }
}
class get{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        solution s = new solution();
        int result = s.reverse(i);
        System.out.println(result);
    }
}

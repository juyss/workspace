package test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Test02
 * @Desc:
 * @package test
 * @project TestDemo
 * @date 2020/8/11 12:45
 */
public class Test02 {
    public static void main(String[] args) {
        int a = 10;
        int b;
        if (a>2){
            b = 1;
        }else if(a<=2){
            b = 3;
        }
        int i = 15 & 15641 ;
        System.out.println(i);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("键", "值");
        map.put("键1", "值1");
        map.put("键1", "值2");
        ArrayList<String> strings = new ArrayList<>();
        System.out.println(map);
    }

}

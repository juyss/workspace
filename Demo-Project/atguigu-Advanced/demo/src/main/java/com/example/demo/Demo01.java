package com.example.demo;

public class Demo01 {
    private static final String NUMBER="7";


    public static void test01() {
        test02();
        System.out.println("trst01 end");
    }

    public static void test02() {

        System.out.println("trst02 end");
    }

    public static int add(int x,int y) {
        int result = -1;
        result = x + y;
        return result;
    }


    public static void main(String[] args) {
        Object obj = new Object();
        Demo01 d01 = new Demo01();
        String str = new String("abc");

        test01();

        System.out.println("main end");

    }
}

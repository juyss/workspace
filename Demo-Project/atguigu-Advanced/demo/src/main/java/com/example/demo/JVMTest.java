package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class JVMTest {
    byte[] byteArray = new byte[1*1024*1024];//1MB

    public static void main(String[] args) {
        List<JVMTest> list = new ArrayList<JVMTest>();

        int count = 0;

        try {
            while(true)
            {
                list.add(new JVMTest());
                count = count + 1;
            }

        } catch (Throwable e) {
            System.out.println("*********count="+count);
            e.printStackTrace();
        }


    }
}

package com.icepoint.framework.code.utils;

import java.util.Random;

/**
 * @author ck
 * @version 1.0
 * @date 2021/5/31 16:55
 */
public class CodeUtil {
    private static final String[] CHARS = new String[] {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
            "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    private static final String[] INTS = new String[] {"1", "2", "3", "4", "5",
            "6", "7", "8", "9"};

    /**
     * 生成随机码
     */
    public static String generateRandomString(int bit){
        StringBuilder sb = new StringBuilder(20);
        Random r = new Random();
        for(int i=0; i<bit; i++){
            int num = r.nextInt(CHARS.length);
            sb.append(CHARS[num]);
        }
        return sb.toString();
    }

    public static String generateRandomNumber(int bit){
        StringBuilder sb = new StringBuilder(20);
        Random r = new Random();
        for(int i=0; i<bit; i++){
            int num = r.nextInt(INTS.length);
            sb.append(INTS[num]);
        }
        return sb.toString();
    }


}

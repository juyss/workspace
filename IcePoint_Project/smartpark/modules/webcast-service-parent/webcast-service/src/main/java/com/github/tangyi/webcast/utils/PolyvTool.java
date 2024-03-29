package com.github.tangyi.webcast.utils;

import com.github.tangyi.webcast.service.ChannelAuthService;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.Channel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * polyv工具类
 */
@Component
public class PolyvTool {



    @Autowired
    ChannelAuthService channelAuthService;

    /**
     * 获取加密串
     */
    public static void setLiveSign(Map<String, String> params, String appId, String key) {
        params.put("sign", getSign(params, appId, key));
    }

    /**
     * 获取加密串
     */
    public static String getSign(Map<String, String> params, String appId, String key) {
        params.put("appId", appId);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        // 处理参数，计算MD5哈希值
        String concatStr = concatParams(params);
        String plain = key + concatStr + key;
        System.out.println("request signStr=" + plain);
        String encrypted = md5Hex(plain);
        // 32位大写MD5值
        return encrypted.toUpperCase();
    }

    /**
     * 把数组所有元素排序，并按照“参数参数值”的模式成字符串，用于计算MD5哈希值
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String concatParams(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            if (isBlank(value)) {
                continue;
            }
            sb.append(key).append(value);
        }
        return sb.toString();
    }

    /**
     * 对字符串做MD5加密，返回加密后的字符串。
     *
     * @param text 待加密的字符串。
     * @return 加密后的字符串。
     */
    public static String md5Hex(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = text.getBytes();
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 将字节数组换成成16进制的字符串
     *
     * @param byteArray 字节
     * @return 字符串
     */
    public static String byteArrayToHex(byte[] byteArray) {
        // 初始化一个字符数组用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }


    /**
     * url 参数串连但是不进行参数Encode
     *
     * @param map map
     * @return string
     */
    public static String mapJoinNotEncode(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (isBlank(value)) {
                continue;
            }
            if (0 != i) {
                stringBuilder.append("&");
            }
            stringBuilder.append(key).append("=").append(map.get(key));
            i++;
        }
        return stringBuilder.toString();
    }

    /**
     * url 参数串连
     *
     * @param map            map
     * @param keyLower       keyLower
     * @param valueUrlEncode valueUrlEncode
     * @return string
     */
    public static String mapJoin(Map<String, String> map, boolean keyLower, boolean valueUrlEncode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (isBlank(value)) {
                continue;
            }

            try {
                String temp = (key.endsWith("_") && key.length() > 1) ? key.substring(0, key.length() - 1) : key;
                stringBuilder.append(keyLower ? temp.toLowerCase() : temp)
                        .append("=")
                        .append(valueUrlEncode ? URLEncoder.encode(value, "utf-8").replace("+", "%20") : value)
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.getMessage());
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     * 判断字符是否为空
     *
     * @param cs 字符
     * @return true/false
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

}

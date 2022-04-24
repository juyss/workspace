package com.github.tangyi.core.common.util;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 加密工具
 *
 * @author hedongzhou
 * @date 2018/06/14
 */
public class EncryptUtils {

    /**
     * MD5
     *
     * @param origin
     * @return
     */
    public static String md5(String origin) {
        return md5(origin, null);
    }

    /**
     * MD5
     *
     * @param origin
     * @param charsetName
     * @return
     */
    public static String md5(String origin, String charsetName) {
        try {
            char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] md;
            if (StringUtils.isBlank(charsetName)) {
                md = digest.digest(origin.getBytes());
            } else {
                md = digest.digest(origin.getBytes(charsetName));
            }

            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }

            return new String(str);
        } catch (Exception e) {
            LogUtils.error(e);
        }
        return null;
    }

    /**
     * SHA
     *
     * @param origin
     * @return
     */
    public static String sha(String origin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(origin.getBytes());

            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (Exception e) {
            LogUtils.error(e);
        }
        return null;
    }

    /**
     * SHA256
     *
     * @param origin
     * @return
     */
    public static String sha256(String origin) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(origin.getBytes("UTF-8"));
            return StringUtils.bytesToHexString(messageDigest.digest());
        } catch (Exception e) {
            LogUtils.error(e);
        }
        return null;
    }

    /**
     * 加密
     *
     * @param origin
     * @param key
     * @return
     */
    public static String encrypt(String origin, String key) {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret(dks), sr);
            return Base64Utils.encodeToString(cipher.doFinal(origin.getBytes()));
        } catch (Exception e) {
            LogUtils.error(e);
        }
        return null;
    }

    /**
     * 解密
     *
     * @param source
     * @param key
     * @return
     */
    public static String decrypt(String source, String key) {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret(dks), sr);
            return new String(cipher.doFinal(Base64Utils.decodeFromString(source)));
        } catch (Exception e) {
            LogUtils.error(e);
        }
        return null;
    }

}

package com.github.tangyi.core.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 该类的AES加密，生成的加密字符串无特殊符号。
 * <b><code>AESUtil</code></b>
 * <p>
 * 说明.
 * </p>
 * <b>Creation Time:</b> 2016年4月8日 下午4:18:58
 * @author Administrator
 *
 */
public class AESUtil {

	private static final int KEYSIZE = 128;
	private static final String CHARSET = "UTF-8";
	private static String secretKey="diauiba18620981710";

	private AESUtil(String secretKey) {

	}

	/**
	 * 加密
	 *
	 * @param origin
	 * @return
	 */
	public static String encrypt(String origin) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(secretKey.getBytes());
			kgen.init(KEYSIZE, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return byte2hex(cipher.doFinal(origin.getBytes(CHARSET)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 解密
	 *
	 * @param dest
	 * @return
	 */
	public static String decrypt(String dest) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(secretKey.getBytes());
			kgen.init(KEYSIZE, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(hex2byte(dest.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				hs.append('0');
			}
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException();
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis(); //获取开始时间
		String ssString=encrypt("wangtui&17");
		System.out.println("加密后" + encrypt(ssString));
		System.out.println("加密后" + encrypt("111_222_11"));
		System.out.println("加密后" + decrypt(encrypt("111_222_11")));
		System.out.println("加密后" + encrypt("246"));
		System.out.println("时间开始" + String.valueOf(startTime));
		System.out.println("时间加密后" + encrypt(String.valueOf(startTime)));
		System.out.println("时间加密后" + decrypt(encrypt(String.valueOf(startTime))));
		//System.out.println("程序运行时间1： " + (endTime - startTime) + "ms");
		startTime = System.currentTimeMillis(); //获取开始时间
		System.out.println("解密后" + decrypt(encrypt("2")));
		System.out.println("解密后" + decrypt(encrypt("4")));
		System.out.println("解密后" + decrypt(encrypt("5")));
		System.out.println("解密后" + decrypt(encrypt("5")));
		System.out.println("解密后" + decrypt(encrypt("5")));
		System.out.println("解密后" + decrypt(encrypt("5")));
		System.out.println("解密后" + decrypt(encrypt("511")));
		System.out.println("解密后" + decrypt(encrypt("123123131")));
		System.out.println("加密后" + encrypt("123123131"));
		System.out.println("加密后" + encrypt("999999999"));
		System.out.println("加密后" + encrypt("101010122"));
		System.out.println("加密后" + encrypt("2"));
		long endTime = System.currentTimeMillis(); //获取结束时间
		System.out.println("程序运行时间2： " + (endTime - startTime) + "ms");
	}
}

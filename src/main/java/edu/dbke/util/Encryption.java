package edu.dbke.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类，返字符串通过加密后的base64编码
 * @author huitang
 *
 */
public class Encryption {
	/**
	 * 使用MD5进行加密
	 * 
	 * @param str 待加密的字符串
	 * @return 加密后的字符串
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String encoderByMd5(String str) {
		if (null == str) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // 确定计算方法
			return Base64.encodeBase64String(md.digest(str.getBytes("utf-8"))).trim();// 返回加密后的字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用SHA1进行加密
	 * 
	 * @param str 待加密的字符串
	 * @return 加密后的字符串
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String encoderBySHA1(String str) {
		if (null == str) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1"); // 确定计算方法
			return Base64.encodeBase64String(md.digest(str.getBytes("utf-8"))).trim();// 返回加密后的字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(Encryption.encoderBySHA1("Dbke6413"));

	}
}

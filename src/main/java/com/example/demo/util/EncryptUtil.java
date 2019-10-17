package com.example.demo.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

/**
 * 加密工具类
 * 
 * @author admin
 *
 */
public class EncryptUtil {

	private static final String CHARSET_UTF8 = "UTF-8";
	private final static String ALGORITHM_DES = "DES";
	private final static String ALGORITHM_AES = "AES";
	private final static String ALGORITHM_RSA = "RSA";
	private final static String ALGORITHM_MD5_RSA = "MD5withRSA";
	private final static String ALGORITHM_SHA1_RSA = "SHA1withRSA";

	/***
	 * MD5加密 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		if (StringUtils.isBlank(inStr)) {
			return null;
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] byteArray = null;

		try {
			byteArray = inStr.getBytes(CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] md5Bytes = md5.digest(byteArray);
		
		return bytes2hex(md5Bytes);

	}
	/**
	 * 字节数组转成16进制编码
	 * @param bytes
	 * @return
	 */
	private static String bytes2hex(byte[] bytes){
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = ((int) bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	/**
	 * SHA1加密
	 * 
	 * @param inStr
	 * @return
	 */
	public static String string2SHA1(String inStr) {
		if (StringUtils.isBlank(inStr)) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] bytes = md.digest(inStr.getBytes(CHARSET_UTF8));
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				int val = ((int) bytes[i]) & 0xff;
				if (val < 16) {
					buffer.append("0");
				}
				buffer.append(Integer.toHexString(val));
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Base64编码（Base64并非加密方式，只是一种编码方式，因为它可以很容易的逆向解码）
	 * 
	 * @param inStr
	 * @return
	 */
	public static String string2Base64(String inStr) {
		if (StringUtils.isBlank(inStr)) {
			return null;
		}
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			return encoder.encode(inStr.getBytes(CHARSET_UTF8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Base64解码
	 * 
	 * @param base64Str
	 * @return
	 */
	public static String base64ToString(String base64Str) {
		if (StringUtils.isBlank(base64Str)) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] bytes = decoder.decodeBuffer(base64Str);
			return new String(bytes, CHARSET_UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * DES加密(DES属于对称加密算法，加解密共用一个密钥)，该加密算法比较容易破解，已被AES算法取代。<br/>
	 * 加密后的字节已用Base64转码，而没有直接用new String(byte[] bytes)转字符串，不然解密会出问题。
	 * @param data
	 *            原始数据
	 * @param pwd
	 *            密钥
	 * @return
	 */
	public static String encriptDES(String data, String pwd) {
		try {
			SecretKey secretKey = genDesSecretKey(pwd);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] bytes = cipher.doFinal(data.getBytes(CHARSET_UTF8));
			return new BASE64Encoder().encode(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * DES解密(DES属于对称加密算法，加解密共用一个密钥)
	 * 
	 * @param data
	 *            已加密的Base64编码的数据
	 * @param pwd
	 *            密钥
	 * @return
	 */
	public static String decriptDES(String data, String pwd) {
		try {
			SecretKey secretKey = genDesSecretKey(pwd);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(data));
			return new String(bytes, CHARSET_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成DES算法的密钥对象
	 * 
	 * @param pwd
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	private static SecretKey genDesSecretKey(String pwd) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_DES);
		keyGenerator.init(new SecureRandom(pwd.getBytes(CHARSET_UTF8)));
		return keyGenerator.generateKey();
	}
	
	/**
	 * AES加密(AES属于对称加密算法，加解密共用一个密钥)<br/>
	 * 加密后的字节已用Base64转码，而没有直接用new String(byte[] bytes)转字符串，不然解密会出问题。
	 * @param data
	 *            原始数据
	 * @param pwd
	 *            密钥
	 * @return
	 */
	public static String encriptAES(String data, String pwd) {
		try {
			SecretKey secretKey = genAesSecretKey(pwd);
			Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] bytes = cipher.doFinal(data.getBytes(CHARSET_UTF8));
			return new BASE64Encoder().encode(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES解密(AES属于对称加密算法，加解密共用一个密钥)
	 * 
	 * @param data
	 *            已加密的Base64编码的数据
	 * @param pwd
	 *            密钥
	 * @return
	 */
	public static String decriptAES(String data, String pwd) {
		try {
			SecretKey secretKey = genAesSecretKey(pwd);
			Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(data));
			return new String(bytes, CHARSET_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 生成AES算法的密钥对象
	 * 
	 * @param pwd
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	private static SecretKey genAesSecretKey(String pwd) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_AES);
		keyGenerator.init(new SecureRandom(pwd.getBytes(CHARSET_UTF8)));
		return keyGenerator.generateKey();
	}
	
	/**
	 * 生成RSA算法的公钥和私钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static HashMap<String, String> getRsaKeys() throws NoSuchAlgorithmException{
		HashMap<String, String> map = new HashMap<String, String>();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		map.put("private", new BASE64Encoder().encode(privateKey.getEncoded()));
		map.put("public", new BASE64Encoder().encode(publicKey.getEncoded()));
		return map;
	}
	
	/**
	 * 用公钥对明文进行RSA加密
	 * @param content 明文
	 * @param pubPwd Base64编码的公钥
	 * @return Base64编码的密文
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encriptRSA(String content,String pubPwd) throws Exception{
		PublicKey publicKey = getPublicKey(pubPwd);
		
		Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bytes = cipher.doFinal(content.getBytes(CHARSET_UTF8));
		return new BASE64Encoder().encode(bytes);
	}
	
	/**
	 * 根据公钥字符串获得公钥对象
	 * @param pubPwd Base64编码的公钥
	 * @return
	 * @throws Exception
	 */
	private static PublicKey getPublicKey(String pubPwd) throws Exception{
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(pubPwd));
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}
	
	/**
	 * 用私钥对Base64编码的密文进行RSA解密
	 * @param content Base64编码的密文
	 * @param priPwd Base64编码的私钥
	 * @return 明文
	 * @throws Exception 
	 */
	public static String decriptRSA(String content,String priPwd) throws Exception{
		PrivateKey privateKey = getPrivateKey(priPwd);
		
		Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));
		return new String(bytes, CHARSET_UTF8);
	}
	
	/**
	 * 根据私钥字符串获得私钥对象
	 * @param priPwd Base64编码的私钥
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKey(String priPwd) throws Exception{
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(priPwd));
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	
	/**
	 * 数字签名--MD5withRSA算法
	 * @param content 明文
	 * @param priPwd Base64编码的私钥
	 * @return Base64编码的签名
	 * @throws Exception 
	 */
	public static String signByMD5withRSA(String content,String priPwd) throws Exception{
		PrivateKey privateKey = getPrivateKey(priPwd);
		Signature signature = Signature.getInstance(ALGORITHM_MD5_RSA);
		signature.initSign(privateKey);
		signature.update(content.getBytes(CHARSET_UTF8));
		byte[] bytes = signature.sign();
		return new BASE64Encoder().encode(bytes);
	}
	
	/**
	 * 校验签名信息的完整性--MD5withRSA算法
	 * @param content 明文
	 * @param sign Base64编码的签名
	 * @param pubPwd Base64编码的公钥
	 * @return
	 * @throws Exception 
	 */
	public static boolean verifyByMD5withRSA(String content,String sign,String pubPwd) throws Exception{
		PublicKey publicKey = getPublicKey(pubPwd);
		Signature signature = Signature.getInstance(ALGORITHM_MD5_RSA);
		signature.initVerify(publicKey);
		signature.update(content.getBytes(CHARSET_UTF8));
		return signature.verify(new BASE64Decoder().decodeBuffer(sign));
	}
	
	/**
	 * 数字签名--SHA1withRSA算法
	 * @param content 明文
	 * @param priPwd Base64编码的私钥
	 * @return Base64编码的签名
	 * @throws Exception 
	 */
	public static String signBySHA1withRSA(String content,String priPwd) throws Exception{
		PrivateKey privateKey = getPrivateKey(priPwd);
		Signature signature = Signature.getInstance(ALGORITHM_SHA1_RSA);
		signature.initSign(privateKey);
		signature.update(content.getBytes(CHARSET_UTF8));
		byte[] bytes = signature.sign();
		return new BASE64Encoder().encode(bytes);
	}
	
	/**
	 * 校验签名信息的完整性--SHA1withRSA算法
	 * @param content 明文
	 * @param sign Base64编码的签名
	 * @param pubPwd Base64编码的公钥
	 * @return
	 * @throws Exception 
	 */
	public static boolean verifyBySHA1withRSA(String content,String sign,String pubPwd) throws Exception{
		PublicKey publicKey = getPublicKey(pubPwd);
		Signature signature = Signature.getInstance(ALGORITHM_SHA1_RSA);
		signature.initVerify(publicKey);
		signature.update(content.getBytes(CHARSET_UTF8));
		return signature.verify(new BASE64Decoder().decodeBuffer(sign));
	}
	
	// 测试主函数
	public static void main(String args[]) throws Exception {
		String s = new String("123456");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("spring工具MD5后："+DigestUtils.md5DigestAsHex(s.getBytes(CHARSET_UTF8)));
//		System.out.println("加密的：" + convertMD5(s));
//		System.out.println("解密的：" + convertMD5(convertMD5(s)));
//		System.out.println("SHA1后：" + string2SHA1(s));
//		System.out.println("Base64编码：" + string2Base64(s));
//		System.out.println("Base64解码：" + base64ToString(string2Base64(s)));
//
//		String desData = encriptDES(s, "sj234jwer23iewolfrwpw8q9");
//		System.out.println("DES加密后："+desData);
//		System.out.println("DES解密后："+decriptDES(desData, "sj234jwer23iewolfrwpw8q9"));

//		String aesData = encriptAES(s, "!gprgoz789369!");
//		System.out.println("AES加密后："+aesData);
//		System.out.println("AES解密后："+decriptAES(aesData, "!gprgoz789369!"));

//		HashMap<String, String> map = getRsaKeys();
//		System.out.println("生成公钥："+map.get("public"));
//		System.out.println("生成私钥："+map.get("private"));
//
//		String rsaData = encriptRSA(s, map.get("public"));
//		System.out.println("RSA加密后："+rsaData);
//		System.out.println("RSA解密后："+decriptRSA(rsaData, map.get("private")));
//
//		String signByMD5withRSA = signByMD5withRSA(s, map.get("private"));
//		System.out.println("MD5withRSA签名后："+signByMD5withRSA);
//		boolean verifyByMD5withRSA = verifyByMD5withRSA(s, signByMD5withRSA, map.get("public"));
//		System.out.println("MD5withRSA校验签名结果："+verifyByMD5withRSA);
//
//		String signBySHA1withRSA = signBySHA1withRSA(s, map.get("private"));
//		System.out.println("SHA1withRSA签名后："+signBySHA1withRSA);
//		boolean verifyBySHA1withRSA = verifyBySHA1withRSA(s, signBySHA1withRSA, map.get("public"));
//		System.out.println("SHA1withRSA校验签名结果："+verifyBySHA1withRSA);
//		System.out.println("==========================================================");
//		System.out.println("转成16进制编码后："+bytes2hex(s.getBytes(CHARSET_UTF8)));
//		System.out.println("转成Base64编码后："+new BASE64Encoder().encode(s.getBytes(CHARSET_UTF8)));
	}
}

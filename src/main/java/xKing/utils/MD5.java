package xKing.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5 {

	// MD5 加密
	public static String EncoderByMd5(String str)  {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
			return newstr;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Can't use MD5");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("The Character Encoding is not supported.");
		}
	}

}

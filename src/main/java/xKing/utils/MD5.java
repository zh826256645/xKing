package xKing.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类（32位）
 * 
 * @author 钟浩
 * @date 2016年11月17日
 *
 */
public class MD5 {

	// MD5 加密
	public static String EncoderByMd5(String str)  {
		try {
			if(str == null) {
				str = "";
			}
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte b[] = md5.digest();
			int i;
			StringBuffer buff = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset ++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buff.append("0");
				}
				buff.append(Integer.toHexString(i));
			}
			String newstr = buff.toString();
			return newstr;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Can't use MD5");
		}
	}
}

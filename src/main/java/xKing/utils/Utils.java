package xKing.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils {
	
	// 获得 UUID
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	// 获取格式化日期
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}
	
	// 截取文件名后缀
	public static String getExtensionName(String fileName) {
		int i = fileName.lastIndexOf(".");
		if((i > -1) && i < (fileName.length() - 1)) {
			return fileName.substring(i); 
		}
		return null;
	}
	
	// 格式化时间
	public static String getFormatData(long time) {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		String date = format.format(new Date(time));
		return date;
	}
}

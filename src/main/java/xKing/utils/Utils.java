package xKing.utils;

import java.text.ParseException;
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
	
	// 获取格式化日期
	public static String getFormatDataTime(long time) {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String date = format.format(new Date(time));
		return date;
	}
	
	// 时间字符串转时间戳
	public static long timeStrToLong(String timeStr) {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		try{
			Date date = format.parse(timeStr);
			return date.getTime();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static long getTodayTimeLong() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String timeStr =  format.format(new Date());
		try {
			return format.parse(timeStr).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}
}

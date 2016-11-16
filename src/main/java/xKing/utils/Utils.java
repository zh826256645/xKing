package xKing.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}
	
}

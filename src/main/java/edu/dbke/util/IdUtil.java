package edu.dbke.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 通过当前时间和随机数生成主键
 * 
 */
public class IdUtil {
	public static String getId(){
		String base = "0123456789";
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < 15; i++) {
	        int number = random.nextInt(base.length());
	        sb.append(base.charAt(number));
	    }

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");

		return sdf.format(new Date()).toString()+sb.toString();
	}
//
//	public static  Integer getId(){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		Date date = new Date();
//		return sdf.format(date).compareTo(sdf.format(new Date()));
//	}
}

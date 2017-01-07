package edu.dbke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
	public static long coverTimeFromStringToLong(String src) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
		try {
			return formatter.parse(src).getTime();
		} catch (ParseException e) {
		}
		return 0L;
	}

	public static String formatDate(long date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
		String Time = formatter.format(new Date(date));
		return Time;
	}
	
	@SuppressWarnings("unused")
	public static String getSeriaNo() {
		String year = Integer.toString(Calendar.getInstance().get(1));
		String month = Integer.toString(Calendar.getInstance().get(2));
		int day = Calendar.getInstance().get(5);
		int hh = Calendar.getInstance().get(11);
		int mm = Calendar.getInstance().get(12);
		int ss = Calendar.getInstance().get(13);
		int ms = Calendar.getInstance().get(14);
		return null;
	}

	public static String getTaskStatu4Str(int statu) {
		String value = "";
		switch (statu) {
		case 2:
			value = "任务被取消";
			break;
		case 3:
			value = "任务已完成";
			break;
		case 1:
			value = "任务进行中";
			break;
		case 0:
			value = "任务未开始";
			break;
		default:
			value = "无数据";
		}
		return value;
	}

	public static String converToHtml(String src) {
		if (src == null)
			return "";

		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll("\r", "<br/>");
		src = src.replaceAll(" ", "&nbsp;");
		return src;
	}

	public static void main(String[] args) {
		System.out.println(coverTimeFromStringToLong("2011-11-15"));
	}
}
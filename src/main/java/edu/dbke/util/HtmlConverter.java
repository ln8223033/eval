package edu.dbke.util;

/**
 * html代码转换工具
 * @author huitang
 */
public class HtmlConverter {
	/**
	 *特殊字符转换
	 */
	public static String parseHtml(String str) {
		if (str == null) {
			return null;
		}
		str = str.replace("&", "&amp;");// 检查字符: &
		str = str.replaceAll("\"", "&quot;");// 检查字符: "
		str = str.replace("<", "&lt;");// 检查字符: <
		str = str.replace(">", "&gt;");// 检查字符: >
		str = str.replace("  ", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");// 替换空格
		str = str.replace("'", "’");// 替换单引号
		str = str.replaceAll("(\r)?\n", "&lt;br&gt;");// 采用正则表达式处理Windows/Mac平台和Unix/Linux平台的换行
		return str;
	}

	public static String conver2Html(String str) {
		if (str == null) {
			return null;
		}
		str = str.replace("&amp;", "&");// 检查字符: &
		str = str.replaceAll("&quot;", "\"");// 检查字符: "
		str = str.replace("&lt;", "<");// 检查字符: <
		str = str.replace("&gt;", ">");// 检查字符: >
		str = str.replace("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", "  ");// 替换空格
		str = str.replace("’", "'");// 替换单引号
		str = str.replaceAll("&lt;br&gt;", "(\r)?\n");// 采用正则表达式处理Windows/Mac平台和Unix/Linux平台的换行
		return str;
	}
}

package edu.dbke.web.acl;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 系统配置文件
 * 
 */
public class SystemConf {
	private static Map<String, String> confMap = new HashMap<String, String>();
	static {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(getPath("configuration.properties"));
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}
		Enumeration<?> enumeration = prop.propertyNames();
		while (enumeration.hasMoreElements()) {
			String name = (String) enumeration.nextElement();
			confMap.put(name, prop.getProperty(name).trim());
		}
	}

	/**
	 * @param name
	 * @return
	 */
	private static String getPath(String name) {
		String folderPath = Thread.currentThread().getContextClassLoader().getResource(name).getPath();
		folderPath = folderPath.replaceAll("%20", " ");
		return folderPath;
	}

	public static String getConf(String key) {
		String value = confMap.get(key);
		return value;
	}

	public static void main(String[] args) {
		long before1 = System.currentTimeMillis();
		System.out.println(SystemConf.getConf("aclUpdate"));
		long after1 = System.currentTimeMillis();
		System.out.println((after1 - before1) + ":");

		/*String url = "E:/project/uuc/server/target/test-classes/configuration.properties";
		System.out.println(url.replace("target/test-classes", "src/main/webapp/WEB-INF/classes"));*/
	}
}

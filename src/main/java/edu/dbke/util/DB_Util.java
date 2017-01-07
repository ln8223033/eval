package edu.dbke.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

/**
 * 数据库数据备份转移工具
 * 
 * @author figo
 */
public class DB_Util {
	private static Connection conn;

	/**
	 * 加裁mysql驱动
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private static void mysql() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ginkgo", "root", "a");
	}

	@SuppressWarnings("unused")
	private static void hsqldb() throws ClassNotFoundException, SQLException {


	 Class.forName("org.hsqldb.jdbcDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost", "sa", "");
	}

	/**
	 * 加裁oracle的驱动
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void oracle() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@210.41.225.46:1521:orcl", "dbke", "Dbke123");
	}

	public static void main(String[] args) throws Exception {
		 oracle();// 打开oracle数据库
//		mysql();// 打开mysql数据库
		// hsqldb();

//		 exportData("F:\\backup\\dbke\\", "T_TASK");// 导出数据

		 importData("F:\\backup\\dbke\\T_TASK.figo", "member");// 导入数据

//		importCSV("N:\\test.csv", "t_user");// 导入CSV数据
	}

	/**
	 * @param filePath
	 *            导出数据存放路径
	 * @param table
	 *            执行导出的表名
	 */
	@SuppressWarnings("unused")
	private static void exportData(String filePath, String table) {
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from " + table;
			ResultSet resultSet = statement.executeQuery(sql);

			File file = new File(filePath + table + ".figo");
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));
			int columnCount = resultSet.getMetaData().getColumnCount();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < columnCount; i++) {
				String name = resultSet.getMetaData().getColumnName(i + 1);
				sb.append(name + "\b");
			}
			pw.println(sb.toString());// 输出表头

			while (resultSet.next()) {
				sb = new StringBuilder();
				for (int i = 0; i < columnCount; i++) {
					sb.append(resultSet.getString(i + 1) + "\b");
				}
				pw.println(sb.toString());// 输出数据
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("data export success!");
	}

	/**
	 * 导入数据
	 * 
	 * @param filePath
	 *            导入的数据文件
	 * @param table
	 *            导入到的表名
	 */
	private static void importData(String filePath, String table) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filePath));
			String[] columnName = scanner.nextLine().split("\b");
			int columnCount = columnName.length;

			StringBuilder sql = new StringBuilder();// 生成sql语句
			sql.append("INSERT INTO " + table + "(");
			for (int i = 0; i < columnCount - 1; i++) {
				sql.append(columnName[i] + ",");
			}
			sql.append(columnName[columnCount - 1] + ")" + " VALUES (");
			for (int i = 0; i < columnCount - 1; i++) {
				sql.append("?,");
			}
			sql.append("?)");// 生成sql语句END
			PreparedStatement statement = conn.prepareStatement(sql.toString()); // 创建插入语句

			while (scanner.hasNext()) {
				String[] tableValues = scanner.nextLine().split("\b");
				for (int i = 0; i < columnCount; i++) {
					statement.setString(i + 1, tableValues[i]);
				}
				statement.execute();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("data import success！");
	}

	/**
	 * 导入CSV类型数据
	 * 
	 * @param filePath
	 *            文件路径
	 * @param table
	 *            表名
	 */
	@SuppressWarnings("unused")
	private static void importCSV(String filePath, String table) throws Exception {
		boolean isFistLine = false;// 第一行表头少一个\r会导致第二行多出一个\n
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File(filePath));
		scanner.useDelimiter("\n");
		String[] columnName = scanner.next().split(",");// 得到字段名
		int columnCount = columnName.length;// 字段个数

		StringBuilder sql = new StringBuilder();// 生成sql语句
		sql.append("INSERT INTO " + table + "(");
		for (int i = 0; i < columnCount - 1; i++) {
			sql.append(columnName[i].substring(0, columnName[i].length()) + ",");
		}
		sql.append(columnName[columnCount - 1].substring(1, columnName[columnCount - 1].length() - 1) + ")"
				+ " VALUES (");
		for (int i = 0; i < columnCount - 1; i++) {
			sql.append("?,");
		}
		sql.append("?)");// 生成sql语句END

		PreparedStatement statement = conn.prepareStatement(sql.toString());// 插入数据
		scanner.useDelimiter("\r\n");
		while (scanner.hasNext()) {
			String[] tableValues = scanner.next().split(",");
			for (int i = 0; i < columnCount; i++) {
				String value = null;
				if (isFistLine == false) {// 第一行数据每一个值从第二个索引处开始取子串
					value = tableValues[i].substring(0, tableValues[i].length());
					isFistLine = true;
				} else {
					value = tableValues[i].substring(1, tableValues[i].length());
					isFistLine = false;
				}
				statement.setString(i + 1, value);
			}
			statement.execute();
		}
		System.out.println("data import success！");
	}
}

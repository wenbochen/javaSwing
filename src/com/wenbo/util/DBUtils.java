package com.wenbo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库操作工具类
 * @Description 
 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
 * @date 2016年1月19日 下午4:54:03
 * @version V1.0
 */
public class DBUtils {
	/**
	 * 创建连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			//加载驱动
			Class.forName("org.sqlite.JDBC").newInstance();
			//创建链接 数据库不存在则自动创建
			 conn =DriverManager.getConnection("jdbc:sqlite:wenbo.db"); //wenbo.db是数据库名
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn == null) {
			throw new SQLException("DBUtils: Cannot get connection.");
		}
		return conn;
	}
	/**
	 * 关闭连接
	 * @param conn
	 */
	public static void close(Connection conn) {
		if (conn == null)
			return;
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("DBUtils: Cannot close connection.");
		}
	}
/**
 * 关闭申明
 * @param stmt
 */
	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBUtils: Cannot close statement.");
		}

	}
/**
 * 关闭结果集
 * @param rs
 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("DBUtils: Cannot close resultset.");
		}
	}
}

package com.wenbo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
/**
 * 数据库操作工具类
 * @Description 
 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
 * @date 2016年1月19日 下午4:54:03
 * @version V1.0
 */
public class DBUtils {
	/**
	 * 配置文件名
	 */
	private static String dbName;
	private static DBUtils dbu;
	private static final String sql_create_book ="create table if not exists book (name varchar(20),phone varchar(18),mobile varchar(18))";
	private static final String sql_create_user ="create table if not exists user (id integer PRIMARY KEY autoincrement,name varchar(20),password varchar(18))";
	private static final String sql_insert_user ="insert into  user values(10000,'admin','admin')";
	private static final String sql_insert_user2 ="insert into  user values(null,'wenbo','1120')";
	
	static {
		dbName = APPConfig.init().getKey("dbName");
	}
	
	public static DBUtils init(){
		if(dbu==null){
			dbu = new DBUtils();
		}
		return dbu;
	}
	
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
			 conn =DriverManager.getConnection("jdbc:sqlite:"+dbName); //wenbo.db是数据库名
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn == null) {
			throw new SQLException("DBUtils: Cannot get connection.");
		}
		return conn;
	}
	/**
	 * 初始化创建表
	 */
	public void createTables(){
		Connection conn = null;
		Statement state = null;
		try {
			conn = DBUtils.getConnection();
			 state = conn.createStatement();
			state.executeUpdate(sql_create_book);
			state.executeUpdate(sql_create_user);
			state.executeUpdate(sql_insert_user);
			state.executeUpdate(sql_insert_user2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
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

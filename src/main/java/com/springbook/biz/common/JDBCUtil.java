package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost/member_board?useSSL=false";
		String id = "giri";
		String pwd = "dks358";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void stmtClose(Statement statement) {
		if (statement != null) {
			try {
				if (!statement.isClosed())
					statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				statement = null;
			}
		}
	}

	public static void connClose(Connection connection) {
		if (connection != null) {
			try {
				if (!connection.isClosed())
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection = null;
			}
		}
	}

	public static void rsClose(ResultSet resultset) {
		if (resultset != null) {
			try {
				if (!resultset.isClosed())
					resultset.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				resultset = null;
			}
		}
	}
}

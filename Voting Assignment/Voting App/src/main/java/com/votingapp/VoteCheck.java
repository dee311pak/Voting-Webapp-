package com.votingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VoteCheck {

	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String dbSourceUrl = "jdbc:mysql://localhost/votingdatabase";
	static private Connection courseDbConn;
	static private String userId = "root";
	static private String dbPassword = "";

	public static Connection getConnection() throws SQLException {
		if (courseDbConn == null) {
			courseDbConn = DriverManager.getConnection(dbSourceUrl, userId, dbPassword);
		}

		return courseDbConn;
	}

	public int voteCheck(String name) throws SQLException, ClassNotFoundException {
		int count;

		Class.forName(JDBC_DRIVER);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/voteingapp","root","");

		String queryStr = "select username(*) from vote where username ='"+ name + "'";
		Statement queryStmt = con.createStatement();
		ResultSet result;
		result = queryStmt.executeQuery(queryStr);
		result.next();
		count = result.getInt(1);
		result.close();
		queryStmt.close();
		return count;
	}

	public void voteRegister(String name, String vote) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voteingapp", "root", "");

			Statement st = con.createStatement();
			int result = st.executeUpdate("insert into vote (username,vote) values ('" + name + "','" + vote + "')");
			con.close();
			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

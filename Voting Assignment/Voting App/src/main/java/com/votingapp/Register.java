package com.votingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		String number = request.getParameter("phoneno");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voteingapp", "root", "");

			Statement st = con.createStatement();
			int ep = st.executeUpdate(
					"insert into users values('" + uname + "','" + pass + "','" + email + "','" + number + "')");
			if (ep == 1) {
				out.println("<h3> Registered Successfully </h3>");
				request.getRequestDispatcher("Login.html").include(request, response); // Navigation bar
			}
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

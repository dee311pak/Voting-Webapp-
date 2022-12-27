package com.votingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String uname = request.getParameter("username");
		String pass = request.getParameter("password");

		if (pass.equals("admin") && uname.equals("admin")) {
			out.print("<h3>Welcome, " + uname + "</h3>");
			HttpSession session = request.getSession();
			session.setAttribute("name", uname);
		} else {
			out.println("<h3>Invalid Username and Password!!..</h3>");
			request.getRequestDispatcher("index.html").include(request, response);
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voteingapp", "root", "");
			PreparedStatement pr1 = con.prepareStatement("select * from vote");

			ResultSet rs1 = pr1.executeQuery();
			ResultSet rs2 = pr1.executeQuery();
			ResultSet rs3 = pr1.executeQuery();
			ResultSet rs4 = pr1.executeQuery();

			while (rs1.next()) {
				out.println(rs1.getString(1));
				out.println(rs1.getString(2));
			}
			while (rs2.next()) {
				out.println(rs2.getString(1));
				out.println(rs2.getString(2));
			}	
			while (rs2.next()) {
				out.println(rs3.getString(1));
				out.println(rs3.getString(2));
			}	
			while (rs4.next()) {
				out.println(rs4.getString(1));
				out.println(rs4.getString(2));
			}	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();
	}

}

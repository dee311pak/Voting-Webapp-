package com.votingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String uname = request.getParameter("username");
		String pass = request.getParameter("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voteingapp", "root", "");

			PreparedStatement pr = con.prepareStatement("select * from users where username = ? and password = ? ");
			pr.setString(1, uname);
			pr.setString(2, pass);
			ResultSet rs = pr.executeQuery();

			if (rs.next()) {
				out.println("<h3> Login Successfully </h3>");
				HttpSession session = request.getSession();
				session.setAttribute("name", uname);
				response.sendRedirect("Voting.html");
				
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
				out.println("<script>alert(\"Please check your Username or Password!\");</script>");
				rd.include(request, response);
			}
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

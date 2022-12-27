package com.votingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/Voting")
public class Voting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int count = 0;
		String name2;
		VoteCheck vb = new VoteCheck();
		PrintWriter out = response.getWriter();

		String candidate = request.getParameter("candidate");
		
		HttpSession session = request.getSession(false);

		if (session != null) {
			 name2 = (String) session.getAttribute("name");
		}

		try {
			count = vb.voteCheck(name);
			
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}
		if (count > 0) {
			try {
				String message = "Sorry! Your vote is already been registered!";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}

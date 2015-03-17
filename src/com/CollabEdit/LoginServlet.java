package com.CollabEdit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String email = request.getParameter("username");
		String pass = request.getParameter("password");
		if(Authentication.checkUserCredentials(email, pass))
		{
		RequestDispatcher rs = request.getRequestDispatcher("main.html");
		rs.forward(request, response);
		}
		else
		{
			out.println("<html><head></head><body><h4>Username or Password is incorrect</h4></body><html>");
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
		}
	}

}

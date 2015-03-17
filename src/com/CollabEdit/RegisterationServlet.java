package com.CollabEdit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterationServlet")
public class RegisterationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String uname = request.getParameter("usernamesignup");
		String email = request.getParameter("emailsignup");
		String pass = request.getParameter("passwordsignup");
		if(Authentication.createUserCredentials(uname, email, pass))
		{
		/*RequestDispatcher rs = request.getRequestDispatcher("index.html");
		rs.forward(request, response);*/
			out.println("<html> <body><h4>Sign Up Succesful. Please Login Now </h4></html> ");
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.forward(request, response);
		}
		else
		{
			out.println("Username or Password is not as per the standards.");
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
		}
	}

}

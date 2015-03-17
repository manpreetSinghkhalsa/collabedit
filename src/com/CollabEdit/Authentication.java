package com.CollabEdit;

import java.io.IOException;
import java.util.regex.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/Authentication")
public class Authentication extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public static boolean createUserCredentials(String uname, String email, String password)
	{
    	String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=CollabEditDB";
        String user = "sa";
        String Password = "minisiminoni";
        String classloading = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        PrintWriter out;
        Connection con;
        PreparedStatement stmt;
        boolean returnbool;
        String sql;
		String checkEmailRegex = "^.+@.+\\.[a-z]{2,4}$";
		String checkPasswordRegex = "^.+?(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[#$%^&*@]).{8,}$";
		if(email.matches(checkEmailRegex))
		{
			System.out.println("Email MATCHED");
			if(password.matches(checkPasswordRegex))
			{
				System.out.println("Password MATCHED");
				try
				{
					Class.forName(classloading);
					con = DriverManager.getConnection(dbURL, user, Password );
					sql = "Insert into ExistingUsers(Username, UserId, UserPassword ) values (?, ?, ?)";
					stmt = con.prepareStatement(sql);
					stmt.setString(1, uname);
					stmt.setString(2, email);
					stmt.setString(3, password);
				   int result = stmt.executeUpdate();
				   System.out.println(result);
				   if(result > 0)
					   return true;
				   else 
					   return false;
					
				}
				catch(Exception e)
				{
					System.out.println("Exception  " + e);
				}
				
				return true;
			}
			
			else 
			{
			
				return false;
			}
			
		}
		else
		{
			return false;
		}
	}
    public static boolean checkUserCredentials(String email, String password)
    {
    	String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=CollabEditDB";
        String user = "sa";
        String Password = "minisiminoni";
        String classloading = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        PrintWriter out;
        Connection con;
        PreparedStatement stmt;
        boolean returnbool;
        String sql;
        try
        {
			Class.forName(classloading);
			con = DriverManager.getConnection(dbURL, user, Password );
			sql = "select * from ExistingUsers where UserId = ? and userpassword = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
		   ResultSet rs = stmt.executeQuery();
		   returnbool = rs.next();
		   System.out.println(returnbool);
		 return returnbool;
        }
        catch(SQLException sqe)
        {
       returnbool = false;
        	
        }
        catch(Exception e)
        {
        	returnbool = false;
        }
		return returnbool;
	
      
    }
}

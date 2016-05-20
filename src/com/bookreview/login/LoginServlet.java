package com.bookreview.login;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookreview.dao.BookOperations;
import com.bookreview.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static int count=0; 
	public static Cookie visitor = new Cookie("visitor", "0");
   
	/**
     * @see HttpServlet#HttpServlet()
     */
	
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		User user = new User();
		user.setUsername(request.getParameter("uname"));
		user.setPassword(request.getParameter("pwd"));
		HttpSession session;
		
		boolean result = BookOperations.validateUser(user);
		
		if(result)
		{	count++;
			int visitorCount = Integer.parseInt(visitor.getValue());
			visitorCount++;
			
			visitor.setValue(String.valueOf(visitorCount));
			session = request.getSession();
			session.setAttribute("currentUser", user.getUsername());
			
			
			if(user.getUsername().equals("admin"))
				response.sendRedirect("DisplayBooksServlet?count="+count);
			else
				response.sendRedirect("SearchBook.html");
				
		}
		else
			response.getWriter().append("Invalid Username or Password!<a href='Login.html'>Click here to go back</a>");
		
		/*if(request.getAttribute("valid").equals("true"))
			response.sendRedirect("Login.html");*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

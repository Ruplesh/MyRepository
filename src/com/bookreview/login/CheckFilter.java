package com.bookreview.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import com.bookreview.dao.BookOperations;

/**
 * Servlet Filter implementation class CheckFilter
 */
//@WebFilter("/CheckFilter")
public class CheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		response.setContentType("text/html");
		Connection con = BookOperations.getConnection();
		PreparedStatement statement;
		System.out.println("Inside filter...");
		String user = request.getParameter("uname");
		//System.out.println(request.getParameter("uname")+",hhh "+request.getParameter("pwd"));
		if(request.getParameter("uname")!=""&&request.getParameter("pwd")!="")
		    {
				chain.doFilter(request, response);
				
				Timestamp timestamp = new Timestamp(new Date().getTime());
				
				try {
					statement = con.prepareStatement("insert into log values('"+user+"','"+timestamp+"')");
					statement.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		else 
		{
			request.getRequestDispatcher("errorServlet").forward(request, response);
			
			
		}
		    
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

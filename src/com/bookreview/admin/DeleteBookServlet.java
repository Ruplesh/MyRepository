package com.bookreview.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookreview.dao.BookOperations;
import com.bookreview.entity.Book;
import com.bookreview.login.LoginServlet;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		PrintWriter writer=response.getWriter();
		
		writer.append("No of users logged in = : "+new LoginServlet().count);
		writer.append("<br/>No of visitors = : "+new LoginServlet().visitor.getValue());
		writer.append("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href='Logout'>Log Out</a></br>");
		String str [] = request.getParameterValues("book");
		if(str!=null)
		{
			for (String string : str) {
		
				BookOperations.deleteBookEntry(Integer.parseInt(string));
			}
		
			writer.append("Book(s) Deleted Successfully");
			writer.append("</br><a href='DisplayBooksServlet'>go back</a>");
		}
		else
			response.sendRedirect("DisplayBooksServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
 * Servlet implementation class InsertBookServlet
 */
@WebServlet("/InsertBookServlet")
public class InsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter writer=response.getWriter();
		writer.append("No of users logged in = : "+new LoginServlet().count);
		writer.append("<br/>No of visitors = : "+new LoginServlet().visitor.getValue());
		writer.append("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href='Logout'>Log Out</a></br>");
		Book book=new Book(Integer.parseInt(request.getParameter("bookid")),request.getParameter("title"),request.getParameter("author"));
		
		if(BookOperations.insertBook(book)>0)
		{		
			writer.append("Book Inserted Successfully");
		}
		writer.append("</br><a href='DisplayBooksServlet'>go back</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

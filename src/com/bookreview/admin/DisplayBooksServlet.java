package com.bookreview.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookreview.dao.BookOperations;
import com.bookreview.entity.Book;
import com.bookreview.login.LoginServlet;

/**
 * Servlet implementation class DisplayBooksServlet
 */
@WebServlet("/DisplayBooksServlet")
public class DisplayBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayBooksServlet() {
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
		writer.append("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href='Logout'>Log Out</a>");
		List<Book> bookList=BookOperations.getBookList();
		writer.println("<form action='DeleteBookServlet'> <table border=1px><tr><td></td><td>BookID</td><td>Title</td><td>Auther</td></tr>");
		for (Book book : bookList) {
			writer.println("<tr><td><input type='checkbox' name ='book' value ='"+book.getBookid()+"'> </td><td>"+book.getBookid()+"</td><td>"+book.getTitle()+"</td><td>"+book.getAuthor()+"</td></tr>");
		}
		writer.print("</table>");
		

		writer.append("<a href='InsertBook.html'>Insert Book</a></br></br>");
		writer.append("<button type='submit' value='Delete Book(s)'>Delete Book</button></br></br>");
		writer.append("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

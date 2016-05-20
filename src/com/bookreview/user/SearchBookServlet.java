package com.bookreview.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookreview.dao.BookOperations;
import com.bookreview.entity.Book;
import com.bookreview.entity.BookReview;

/**
 * Servlet implementation class SearchBookServlet
 */
@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Book book = BookOperations.searchBook(request.getParameter("bookTitle"));
		if(book == null)
		{
			out.append("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href='Logout'>Log Out</a>");	
			 out.append("Book not found");
		}
		else
			{
				out.append("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href='Logout'>Log Out</a>");
				out.append("<form action='AddReview'>");
				out.append("</br>Details : <br/> Book Title : "+book.getTitle()+"&nbsp; Book Author : "+book.getAuthor());
				out.append("<input type='hidden' name='bookid' value='"+book.getBookid()+"'></input>");
				out.append("<br/></br>Reviews : <br/>");
				List<BookReview> reviews = BookOperations.fetchReview(book.getBookid());
				if(reviews.isEmpty())
				{
					out.append("No Reviews Found");
					
					
				}
				else
				{
				out.append("<table border=1px>");
				for (BookReview bookReview : reviews) {
					out.append("<tr><td>"+bookReview.getUsername()+"</td><td>"+bookReview.getReviewtext()+"</td></tr>");
					
				}
				out.append("</table>");
				}
				out.append("<br/> Add review<br/><input type='text' name='review'></input>");
				out.append("<br/> <input type='submit' value=Add Review></input>");
				
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

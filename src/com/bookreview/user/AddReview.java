package com.bookreview.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.bookreview.dao.BookOperations;
import com.bookreview.entity.BookReview;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		System.out.println("Inside Addreview...");
		PrintWriter out = response.getWriter();
		String str= (String) request.getParameter("bookid");
		int id = 0;
		//for (String string : str) {
			id = Integer.parseInt(str);
		System.out.println("Id= "+id);
		
		out.append("<a href='Logout'>Log Out</a>");
		String user = (String)session.getAttribute("currentUser");
		System.out.println(user);
		BookOperations.addReview(request.getParameter("review"), id,user);
		out.append("<br/>review added succesfully<br/><br/><a href='SearchBook.html'>Go Back</a>");
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

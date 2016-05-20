package com.bookreview.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import com.bookreview.entity.Book;
import com.bookreview.entity.BookReview;
import com.bookreview.entity.User;

public class BookOperations {

	static Connection con;
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static PreparedStatement statement;
	static int count=1;
	
	public static Connection getConnection()// Function to create connection
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			con=DriverManager.getConnection("jdbc:mysql://localhost/bookdb","root","admin");
			System.out.println("Connection successful!");			
			return con;
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}


	static 
	{
		 con = getConnection();
	}
	
	public static boolean validateUser(User user)
	{
		try {
			statement = con.prepareStatement("select username,password from user where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
				 return true;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		 return false;
	}
	public static int insertBook(Book book)
	{
		try {
			statement = con.prepareStatement("insert into bookinfo values("+book.getBookid()+",'"+book.getTitle()+"','"+book.getAuthor()+"')");
			return statement.executeUpdate();
	
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 0;
	}
	public static List<Book> getBookList()
	{
		List<Book> bookList=new ArrayList<>();
		try {
			statement = con.prepareStatement("select * from bookinfo");
			ResultSet rs=statement.executeQuery();
			while(rs.next())
			{
			    bookList.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}
	
	
	public static int deleteBookEntry(int bookId)
	{
		try {
			statement = con.prepareStatement("delete from bookinfo where bookid = "+bookId);
			return statement.executeUpdate();
	
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 0;
	}
	
	
	public static Book searchBook(String title)
	{
		ResultSet rs;
		try {
			statement = con.prepareStatement("select bookid,title,author from bookinfo where title = '"+title+"'");
			rs = statement.executeQuery();
			
			if(rs.next())
				return new Book(rs.getInt(1), rs.getString(2), rs.getString(3));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void addReview(String review,int bookId,String username)
	{
		try {
			statement = con.prepareStatement("insert into bookreview values("+count+","+bookId+",'"+username+"','"+review+"')");
			statement.executeUpdate();
			count++;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
	}
		
	public static List<BookReview> fetchReview(int bookid)
	{
		ResultSet rs;
		List<BookReview> reviews = new ArrayList<BookReview>();
		
		try {
			statement = con.prepareStatement("select * from bookreview where bookid = "+bookid);
			rs = statement.executeQuery();
			
			while(rs.next())
			{
				BookReview review = new BookReview(rs.getInt(1), rs.getInt(2), rs.getString(3)
						, rs.getString(4));
				reviews.add(review);
				
			}
			return reviews;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

package com.bookreview.entity;

public class Book {
	int bookid;
	String title;
	String author;
	
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
	public Book(int bookid, String title, String author) {
		
		this.bookid = bookid;
		this.title = title;
		this.author = author;
	}
	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", title=" + title + ", author=" + author + "]";
	}
	
	
}

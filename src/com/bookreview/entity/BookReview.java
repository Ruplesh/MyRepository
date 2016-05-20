package com.bookreview.entity;

public class BookReview {
	
	int reviewid;
	int bookid;
	String username;
	String reviewtext;
	
	public BookReview() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BookReview(int reviewid, int bookid, String username, String reviewtext) {
	
		this.reviewid = reviewid;
		this.bookid = bookid;
		this.username = username;
		this.reviewtext = reviewtext;
	}
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReviewtext() {
		return reviewtext;
	}
	public void setReviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
	}
	
	@Override
	public String toString() {
		return "BookReview [reviewid=" + reviewid + ", bookid=" + bookid + ", username=" + username + ", reviewtext="
				+ reviewtext + "]";
	}
	
	
	
}

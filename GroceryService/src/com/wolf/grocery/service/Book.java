package com.wolf.grocery.service;

import java.util.Date;

public class Book {
	
	private String title;
	private String author;
	private String isbn;
	private String publisher;
	private int pages;
	private Date relDate;
	private String[] test;
	
	public Book(String title, String author, String isbn, String publisher, int pages, Date relDate, String[] test){
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.pages = pages;
		this.relDate = relDate;
		this.test = test;
	}

	public String[] getTest() {
		return test;
	}

	public void setTest(String[] test) {
		this.test = test;
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}
}

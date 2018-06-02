package com.booklist.main;

import java.io.Serializable;

import com.booklist.graphics.BookPane;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title, author;
	private BookPane graphic;
	
	public Book(String name, String author) {
		this.title = name;
		this.author = author;
		graphic = new BookPane(title, author);
	}
	
	public BookPane getPane() {
		return graphic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		graphic.setText(title + " By " + author);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		graphic.setText(title + " By " + author);
	}
}

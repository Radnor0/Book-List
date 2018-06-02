package com.booklist.graphics;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.booklist.main.Book;
import com.booklist.main.Main;

public class BookWindow extends JFrame{
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 200;
	
	private JLabel l_title, l_author;
	private JTextField f_title, f_author;
	private JButton accept;
	
	private boolean newBook;
	private int index;
	
	public BookWindow() {
		super("New Book");
		f_title = new JTextField(40);
		f_author = new JTextField(40);
		newBook = true;
		setup();
	}
	
	public BookWindow(Book b, int index) {
		super("Edit Book");
		f_title = new JTextField(40);
		f_title.setText(b.getTitle());
		f_author = new JTextField(40);
		f_author.setText(b.getAuthor());
		newBook = false;
		this.index = index;
		setup();
	}
	
	private void setup() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new FlowLayout());
		l_title = new JLabel("Title:");
		titlePane.add(l_title);
		titlePane.add(f_title);
		getContentPane().add(titlePane);
		
		JPanel authorPane = new JPanel();
		authorPane.setLayout(new FlowLayout());
		l_author = new JLabel("Author:");
		authorPane.add(l_author);
		authorPane.add(f_author);
		getContentPane().add(authorPane);
		
		accept = new JButton("Accept");
		accept.setAlignmentX(.5f);
		accept.addActionListener((a) -> close());
		getContentPane().add(accept);
		//makes enter simulate the accept button
		getRootPane().setDefaultButton(accept);
		
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void close() {
		if (newBook) 
			Main.addBook(f_title.getText(), f_author.getText());
		else
			Main.editBook(index, f_title.getText(), f_author.getText());
		
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}

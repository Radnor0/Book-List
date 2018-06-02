package com.booklist.graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final int HEIGHT = 45;
	
	private JLabel text;
	
	public BookPane(String title, String author) {
		text = new JLabel(title + " By " + author);
		text.setAlignmentX(.5f);
		add(text);
		
		setMaximumSize(new Dimension(Window.WINDOW_WIDTH, HEIGHT));
		setBorder(BorderFactory.createLineBorder(new Color(000000)));
	}
	
	public void setText(String s) {
		text.setText(s);
	}
}

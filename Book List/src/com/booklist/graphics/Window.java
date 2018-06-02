package com.booklist.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.booklist.main.Main;

public class Window extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	public static final int TITLE_HEIGHT = 31;
	
	private JPanel list, buttonPane;
	private JButton add, save, open;

	public Window() {
		super("Book List");
		addMouseListener(this);
		
		getContentPane().setLayout(new BorderLayout());
		list = new JPanel();
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		getContentPane().add(list, BorderLayout.CENTER);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout());
		add = new JButton("New Book");
		add.addActionListener((a) -> new BookWindow());
		save = new JButton("Save");
		save.addActionListener((a) -> Main.save());
		open = new JButton("Open");
		open.addActionListener((a) -> Main.open());
		buttonPane.add(save);
		buttonPane.add(open);
		buttonPane.add(add);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//new BookWindow();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int index = getIndex(arg0.getY());
		
		if (arg0.getButton() == MouseEvent.BUTTON1 && arg0.getClickCount() == 2 && index < Main.getNumBooks())
			new BookWindow(Main.getBook(index), index);
		if (arg0.getButton() == MouseEvent.BUTTON3 && index < Main.getNumBooks()) {
			Main.removeBook(index);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	private int start;
	private boolean dragging;
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getButton() != MouseEvent.BUTTON1)
			return;
		
		if (getIndex(arg0.getY()) < Main.getNumBooks()) {
			start = getIndex(arg0.getY());
			Main.getBook(start).getPane().setBackground(Color.decode("#9BA1A3"));
			dragging = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getButton() != MouseEvent.BUTTON1 || !dragging)
			return;
		
		dragging = false;
		int end = getIndex(arg0.getY());
		if (end >= Main.getNumBooks())
			end = start;
	
		Main.moveBook(start, end);
	}
	
	private int getIndex(int y) {
		int trueY = y - TITLE_HEIGHT;
		return trueY / BookPane.HEIGHT;
	}
	
	public void addBook(BookPane pane) {
		list.add(pane);
		setVisible(true);
	}
	
	public void remakeList() {
		list.removeAll();
		for (int i = 0; i < Main.getNumBooks(); i++)
			list.add(Main.getBook(i).getPane());
		list.repaint();
		setVisible(true);
	}
}

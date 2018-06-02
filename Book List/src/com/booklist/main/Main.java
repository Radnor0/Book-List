package com.booklist.main;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.booklist.graphics.Window;

public class Main {

	private static Window win;
	
	private static List<Book> books;
	
	public static void main(String[] args) {
		win = new Window();
		books = new ArrayList<Book>();
	}
	
	public static int getNumBooks() {
		return books.size();
	}
	
	public static Book getBook(int index) {
		return books.get(index);
	}
	
	public static void editBook(int index, String title, String author) {
		Book b = books.get(index);
		b.setTitle(title);
		b.setAuthor(author);
	}
	
	public static void addBook(String title, String author) {
		Book b = new Book(title, author);
		books.add(b);
		win.addBook(b.getPane());
	}
	
	public static void removeBook(int index) {
		books.remove(index).getPane();
		win.remakeList();
	}
	
	public static void moveBook(int start, int end) {
		Book b = books.remove(start);
		books.add(end, b);
		b.getPane().setBackground(Color.decode("#EEEEEE"));
		win.remakeList();
	}
	
	public static void save() {
		JFileChooser saveChooser = new JFileChooser();
		saveChooser.setFileFilter(new CustomFilter());
		if (saveChooser.showSaveDialog(saveChooser) == JFileChooser.APPROVE_OPTION) {
			String fileName = saveChooser.getSelectedFile().toString();
			if (!fileName.endsWith(".bl"))
				fileName += ".bl";
			try {
				FileOutputStream fileOut = new FileOutputStream(new File(fileName));
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(books);
				objOut.close();
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static  void open() {
		JFileChooser openChooser = new JFileChooser();
		openChooser.setFileFilter(new CustomFilter());
		if (openChooser.showOpenDialog(openChooser) == JFileChooser.APPROVE_OPTION) {
			try {
				FileInputStream fileIn = new FileInputStream(openChooser.getSelectedFile());
				ObjectInputStream objIn = new ObjectInputStream(fileIn);
				books = (ArrayList<Book>) objIn.readObject();
				objIn.close();
				fileIn.close();
				win.remakeList();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class CustomFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory())
			return true;
		else {
			String name = f.getName().toLowerCase();
			return name.endsWith(".bl");
		}
	}

	@Override
	public String getDescription() {
		return "Book List Files (*.bl)";
	}
	
}

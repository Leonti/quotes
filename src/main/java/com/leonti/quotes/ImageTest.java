package com.leonti.quotes;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.leonti.quotes.services.ImageService;
import com.leonti.quotes.services.ImageServiceImpl;

public class ImageTest {

	public static class LayoutInfo {
		
		public final int height;
		public final double fontSize;
		
		public LayoutInfo(int height, double fontSize) {
			this.height = height;
			this.fontSize = fontSize;
		}
	}

	public static class HeightInfo {
		
		public final double totalHeight;
		public final double textHeight;
		
		public HeightInfo(double totalHeight, double textHeight) {
			this.totalHeight = totalHeight;
			this.textHeight = textHeight;
		}
	}
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 100;
	private static final int PADDING = 5;
	
	
	private static final String content = "Hello, world! So many words!";
	
	private static final String author = "Leonti, 12.05.1990";
	
	public static void main(String[] args) {

		ImageService imageService = new ImageServiceImpl();
        
        
        ImageIcon icon = new ImageIcon();
        icon.setImage(imageService.printQuote(content, author, WIDTH, HEIGHT, PADDING));
        JOptionPane.showMessageDialog(null, icon);        

	}



}

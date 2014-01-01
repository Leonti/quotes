package com.leonti.quotes.services;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.leonti.quotes.ImageTest.HeightInfo;
import com.leonti.quotes.ImageTest.LayoutInfo;

public class ImageServiceImpl implements ImageService {

	@Override
	public BufferedImage printQuote(String content, String author, int width,
			int height, int padding) {

		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(66, 63, 55));

		((Graphics2D) graphics).setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);        

        Rectangle textBounds = new Rectangle(padding, padding, width - padding, height - padding);

        Map<TextAttribute, Object> textAttributes = new HashMap<TextAttribute, Object>();
        textAttributes.put(TextAttribute.FAMILY, "Serif");
	    
        LayoutInfo layoutInfoText = estimateLayout(textBounds, textAttributes, 1000, content + "\n ", (Graphics2D) graphics);
        
        printText(graphics, content, textBounds, textAttributes, layoutInfoText.fontSize);      

        Rectangle authorBounds = new Rectangle(padding, textBounds.y + layoutInfoText.height + padding, width - padding * 2, (height - layoutInfoText.height) - padding * 3);
        
        Map<TextAttribute, Object> authorTextAttributes = new HashMap<TextAttribute, Object>();
        authorTextAttributes.put(TextAttribute.FAMILY, "Serif");
        authorTextAttributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        
        LayoutInfo layoutInfoAuthor = estimateLayout(authorBounds, authorTextAttributes, layoutInfoText.fontSize, author, (Graphics2D) graphics);
        printText(graphics, author, authorBounds, authorTextAttributes, layoutInfoAuthor.fontSize);
        
        return image;
	}

	
	private static void printText(Graphics graphics, String text, Rectangle textBounds, Map<TextAttribute, Object> textAttributes, double fontSize) {

		AttributedCharacterIterator paragraph = getAttributedString(text, textAttributes, fontSize).getIterator();
        int paragraphStart = paragraph.getBeginIndex();
        int paragraphEnd = paragraph.getEndIndex();
        FontRenderContext frc = ((Graphics2D) graphics).getFontRenderContext();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, frc);
        lineMeasurer.setPosition(paragraphStart);        
     
        float breakWidth = (float) textBounds.width;
        float drawPosY = textBounds.y;
        
        while (lineMeasurer.getPosition() < paragraphEnd) {    	
        	
        	TextLayout layout = lineMeasurer.nextLayout(breakWidth);
        	
        	drawPosY += layout.getAscent();
        	
        	layout.draw(((Graphics2D) graphics), textBounds.x, drawPosY);
        	
        	drawPosY += layout.getDescent() + layout.getLeading();
        	
        }
	}
	
	private static LayoutInfo estimateLayout(Rectangle bounds, Map<TextAttribute, Object> textAttributes, double maxFontSize, String text, Graphics2D graphics) {
        double estimatedHeight = 0;
        double fontSize = 1;
        double increment = 0.5;
        
        List<HeightInfo> heights = Lists.newArrayList();
        while (estimatedHeight <= bounds.height && fontSize <= maxFontSize) {

        	fontSize += increment;
        	HeightInfo heightInfo = estimateHeight(fontSize, textAttributes, bounds.width, text, (Graphics2D) graphics);
        	
        	estimatedHeight = heightInfo.totalHeight;
        	heights.add(heightInfo);
        }
        
        return new LayoutInfo((int) heights.get(heights.size() - 2).textHeight, fontSize - increment);
	}
	
	private static HeightInfo estimateHeight(double fontSize, Map<TextAttribute, Object> textAttributes, int width, String text, Graphics2D graphics) {
		
        AttributedCharacterIterator paragraph = getAttributedString(text, textAttributes, fontSize).getIterator();
        int paragraphStart = paragraph.getBeginIndex();
        int paragraphEnd = paragraph.getEndIndex();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, graphics.getFontRenderContext());
        lineMeasurer.setPosition(paragraphStart); 
        
        double height = 0;
        double lineHeight = 0;
        while (lineMeasurer.getPosition() < paragraphEnd) {
        	
        	int next = lineMeasurer.nextOffset(width);
        	int limit = next;
        	if (limit <= text.length()) {
        	  for (int i = lineMeasurer.getPosition(); i < next; ++i) {
        	    char c = text.charAt(i);
        	    if (c == '\n') {
        	      limit = i + 1;
        	      break;
        	    }
        	  }
        	}
        	
        	TextLayout layout = lineMeasurer.nextLayout(width, limit, false);
        	lineHeight = layout.getAscent() + layout.getDescent() + layout.getLeading();
        	height += lineHeight;
        } 		
		return new HeightInfo(height, height - lineHeight);
	}
	
	private static AttributedString getAttributedString(String text, Map<TextAttribute, Object> textAttributes, double fontSize) {
	    Map<TextAttribute, Object> map = new HashMap<TextAttribute, Object>();
	    
	    for (TextAttribute key : textAttributes.keySet()) {
	    	map.put(key, textAttributes.get(key));
	    }
	    map.put(TextAttribute.SIZE, new Float(fontSize));

	    return new AttributedString(text, map);
	}	
}

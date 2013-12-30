package com.leonti.quotes.resources;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import com.leonti.quotes.model.Quote;
import com.leonti.quotes.services.WidgetService;

public class PublicResourceImpl implements PublicResource {

	private WidgetService widgetService;

	@Inject
	public PublicResourceImpl(WidgetService widgetService) {
		this.widgetService = widgetService;
	}
	
	@Override
	public Quote getWidgetQuoteAsJson(Long widgetId) {
		return widgetService.getRandomQuoteForWidget(widgetId);
	}

	@Override
	public StreamingOutput getWidgetQuoteAsImage(Long widgetId, int width, int height) {

		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.white);
        
        Quote quote = widgetService.getRandomQuoteForWidget(widgetId);
        
        graphics.drawString(quote.getWhat(), 20, 20);
		
		return new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				ImageIO.write(image, "png", output);
			}
		};
	}
}

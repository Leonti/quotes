package com.leonti.quotes.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import com.leonti.quotes.model.Quote;
import com.leonti.quotes.services.ImageService;
import com.leonti.quotes.services.WidgetService;

public class PublicResourceImpl implements PublicResource {

	private final WidgetService widgetService;
	private final ImageService imageService;

	@Inject
	public PublicResourceImpl(WidgetService widgetService, ImageService imageService) {
		this.widgetService = widgetService;
		this.imageService = imageService;
	}
	
	@Override
	public Quote getWidgetQuoteAsJson(Long widgetId) {
		return widgetService.getRandomQuoteForWidget(widgetId);
	}

	@Override
	public StreamingOutput getWidgetQuoteAsImage(Long widgetId, int width, int height) {

        Quote quote = widgetService.getRandomQuoteForWidget(widgetId);
		
        final BufferedImage image = imageService.printQuote(quote.getWhat(), quote.getWho(), width, height, 5);
        
		return new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				ImageIO.write(image, "png", output);
			}
		};
	}

	@Override
	public String getWidgetQuoteAsIframe(Long widgetId, String css) {
		Quote quote = widgetService.getRandomQuoteForWidget(widgetId);
		
		String html = "<!doctype html><title></title><body>";
		html += "<style>" + css + "</style>";
		html += "<div id='content'>" + quote.getWhat() + "</div>";
		html += "<span id='author'>" + quote.getWho() + "</span>";
		html += "<span id='when'> " + (quote.getWhen() != null ? quote.getWhen() : "") + "</span>";
		
		return html;
	}
}

package com.leonti.quotes.services;

import java.awt.image.BufferedImage;

public interface ImageService {

	BufferedImage printQuote(String content, String author, int width, int height, int padding);
}

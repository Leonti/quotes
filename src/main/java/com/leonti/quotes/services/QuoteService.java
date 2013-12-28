package com.leonti.quotes.services;

import java.util.List;

import com.leonti.quotes.model.Quote;

public interface QuoteService {

	Quote create(Quote quote);

	List<Quote> readUserQuotes(String userId);

	Quote update(long id, Quote quote);

	Quote read(long id);

	List<Quote> getQuotesForTags(List<String> tags);

	Quote getRandomQuote();

	Quote getRandomQuoteForTags(List<String> tags);
	
	void remove(long id);

}

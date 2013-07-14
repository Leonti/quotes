package com.leonti.quotes.services;

import java.util.List;

import com.leonti.quotes.persistence.Quote;

public interface QuoteService {

	Quote create(Quote quote);

	Quote update(long id, Quote quote);

	Quote read(long id);

	List<Quote> getQuotesForTags(List<String> toHave, List<String> toNotHave);

	List<Quote> getQuotesForTags(List<String> toHave);

	Quote getRandomQuote();

	Quote getRandomQuoteForTags(List<String> toHave, List<String> toNotHave);

	Quote getRandomQuoteForTags(List<String> toHave);

}

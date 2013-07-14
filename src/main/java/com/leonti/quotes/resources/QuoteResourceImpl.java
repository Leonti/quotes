package com.leonti.quotes.resources;

import java.util.List;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.leonti.quotes.persistence.Quote;
import com.leonti.quotes.services.QuoteService;

public class QuoteResourceImpl implements QuoteResource {

	
	private final QuoteService quoteService;

	@Inject
	public QuoteResourceImpl(QuoteService quoteService) {
		this.quoteService = quoteService;
	}
	
	@Override
	public Quote create(Quote quote) {
		return quoteService.create(quote);
	}

	@Override
	public Quote update(long id, Quote quote) {
		return quoteService.update(id, quote);
	}

	@Override
	public Quote getQuote(long id) {
		return quoteService.read(id);
	}

	@Override
	public List<Quote> getQuotesForTags(String included, String excluded) {
		return quoteService.getQuotesForTags(toList(included), toList(excluded));
	}

	@Override
	public List<Quote> getQuotesForTags(String included) {
		return quoteService.getQuotesForTags(toList(included));
	}

	@Override
	public Quote getRandomQuote() {
		return quoteService.getRandomQuote();
	}

	@Override
	public Quote getRandomQuoteForTags(String included, String excluded) {
		return quoteService.getRandomQuoteForTags(toList(included), toList(excluded));
	}

	@Override
	public Quote getRandomQuoteForTags(String included) {
		return quoteService.getRandomQuoteForTags(toList(included));
	}
	
	private List<String> toList(String listAsString) {
		List<String> list = Lists.newArrayList(listAsString.split(","));
		
		return list;
	}

}

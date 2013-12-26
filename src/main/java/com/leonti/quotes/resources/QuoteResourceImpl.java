package com.leonti.quotes.resources;

import javax.inject.Inject;

import com.leonti.quotes.model.Quote;
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
	public Quote getRandomQuote() {
		return quoteService.getRandomQuote();
	}

	@Override
	public void removeQuote(long id) {
		quoteService.remove(id);
	}

}

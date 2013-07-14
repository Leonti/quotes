package com.leonti.quotes.services;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.leonti.quotes.persistence.Quote;
import com.leonti.quotes.persistence.dao.QuoteDao;

public class QuoteServiceImpl implements QuoteService {

	private final QuoteDao quoteDao;
	private final Random random;
	
	@Inject
	public QuoteServiceImpl(QuoteDao quoteDao) {
		this.quoteDao = quoteDao;
		this.random = new Random();
	}

	@Override
	public Quote create(Quote quote) {
		return quoteDao.save(quote);
	}

	@Override
	public Quote update(long id, Quote quote) {
		return quoteDao.save(quote);
	}

	@Override
	public Quote read(long id) {
		return quoteDao.read(id);
	}

	@Override
	public List<Quote> getQuotesForTags(List<String> toHave, List<String> toNotHave) {
		return quoteDao.getQuotesForTags(toHave, toNotHave);
	}

	@Override
	public List<Quote> getQuotesForTags(List<String> toHave) {
		return quoteDao.getQuotesForTags(toHave);
	}

	@Override
	public Quote getRandomQuote() {
		return getRandomQuote(quoteDao.readAll());
	}

	@Override
	public Quote getRandomQuoteForTags(List<String> toHave, List<String> toNotHave) {
		return getRandomQuote(quoteDao.getQuotesForTags(toHave, toNotHave));
	}

	@Override
	public Quote getRandomQuoteForTags(List<String> toHave) {
		return getRandomQuote(quoteDao.getQuotesForTags(toHave));
	}
	
	private Quote getRandomQuote(List<Quote> quotes) {
		return quotes.get(random.nextInt(quotes.size()));
	}	
}

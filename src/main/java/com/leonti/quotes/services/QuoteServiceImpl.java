package com.leonti.quotes.services;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.leonti.quotes.model.Quote;
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
	public List<Quote> readUserQuotes(String userId) {
		return quoteDao.readUserQuotes(userId);
	}	
	
	@Override
	public List<Quote> getQuotesForTags(List<String> tags) {
		return quoteDao.readQuotesForTags(tags);
	}

	@Override
	public Quote getRandomQuote() {
		return getRandomQuote(quoteDao.readAll());
	}

	@Override
	public Quote getRandomQuoteForTags(List<String> tags) {
		return getRandomQuote(quoteDao.readQuotesForTags(tags));
	}
	
	private Quote getRandomQuote(List<Quote> quotes) {
		return quotes.get(random.nextInt(quotes.size()));
	}

	@Override
	public void remove(long id) {
		quoteDao.remove(id);
	}
	
}

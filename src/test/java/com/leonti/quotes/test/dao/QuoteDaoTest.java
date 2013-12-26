package com.leonti.quotes.test.dao;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.leonti.quotes.model.Quote;
import com.leonti.quotes.persistence.dao.CounterDao;
import com.leonti.quotes.persistence.dao.QuoteDao;

public class QuoteDaoTest {

	@Test
	public void test() {
		CounterDao counterDao = new CounterDao(TestDaoBase.getMongoDB());
		
		QuoteDao quoteDao = new QuoteDao(TestDaoBase.getMongoDB(), counterDao);
		
		List<String> tags = Lists.newLinkedList();
		tags.add("Leonti");
		tags.add("unfunny");
		
		Quote quote = new Quote(null, "asfgvjabcfiuy", "Leonti", "Hello, world!", System.currentTimeMillis(), null, tags);
		Quote read = quoteDao.save(quote);
		
		System.out.println(read);
		System.out.println(quoteDao.getQuotesForTags(tags).size());
	}
	
	@Test
	public void getAlltags() {
		CounterDao counterDao = new CounterDao(TestDaoBase.getMongoDB());
		
		QuoteDao quoteDao = new QuoteDao(TestDaoBase.getMongoDB(), counterDao);
		
		System.out.println(quoteDao.getAllTags());
	}

}

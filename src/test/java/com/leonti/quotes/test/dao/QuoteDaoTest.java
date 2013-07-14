package com.leonti.quotes.test.dao;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.leonti.quotes.persistence.Quote;
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
		
		Quote quote = new Quote(null, "Leonti", "Hello, world!", System.currentTimeMillis(), null, tags);
		Quote read = quoteDao.save(quote);
		
		System.out.println(read.getId());
		System.out.println(read.getWhat());
		List<String> toHaveSlugs = Lists.newLinkedList();
		toHaveSlugs.add("funny");
		List<String> toNotHaveSlugs = Lists.newLinkedList();
		toNotHaveSlugs.add("leonti");		
		System.out.println(quoteDao.getQuotesForTags(toHaveSlugs, toNotHaveSlugs).size());
	}

}

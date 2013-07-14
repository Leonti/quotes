package com.leonti.quotes.test.dao;

import org.junit.Test;

import static org.junit.Assert.*;

import com.leonti.quotes.persistence.dao.CounterDao;

public class CounterDaoTest {

	@Test
	public void test() {
		CounterDao counterDao = new CounterDao(TestDaoBase.getMongoDB());
		
		long first = counterDao.next("testing");
		long second = counterDao.next("testing");
		
		assertTrue("Value should be incremented by one", second - first == 1);
	}

}

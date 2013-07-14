package com.leonti.quotes.persistence.dao;

import javax.inject.Inject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class CounterDao {

	private final DBCollection counters;
	
	@Inject
	public CounterDao(DB db) {
		this.counters = db.getCollection("counters");
	}
	
	public long next(String qualifier) {
		BasicDBObject key = new BasicDBObject("_id", qualifier);
		if (counters.findOne(key) == null) {
			counters.save(key.append("seq", 0l));
		}
		
		DBObject incQuery = new BasicDBObject("$inc", new BasicDBObject("seq", 1));		
		DBObject updated = counters.findAndModify(key, incQuery);
		
		return (Long) updated.get("seq");
	}
	
}

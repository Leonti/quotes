package com.leonti.quotes.persistence.dao;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import com.github.slugify.Slugify;
import com.google.common.collect.Lists;
import com.leonti.quotes.persistence.Quote;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class QuoteDao implements Dao<Quote, Long> {
	
	private final DBCollection quotes;
	private final CounterDao counterDao;
	private final MongoUtils.ToEntity<Quote> toEntity;
	
	@Inject
	public QuoteDao(DB db, CounterDao counterDao) {
		this.counterDao = counterDao;
		this.quotes = db.getCollection("quotes");
		
		toEntity = new MongoUtils.ToEntity<Quote>() {

			@Override
			public Quote convert(DBObject dbObject) {
				
				List<String> tags = Lists.newLinkedList();
				BasicDBList tagDbObjects = (BasicDBList) dbObject.get("tags");
				
				for (int i = 0; i < tagDbObjects.size(); i++) {
					DBObject tagDbObject = (BasicDBObject) tagDbObjects.get(i);
					tags.add((String) tagDbObject.get("name"));
				}
				
				return new Quote(
						MongoUtils.toId(dbObject),
						(String) dbObject.get("who"),
						(String) dbObject.get("what"),
						(Long) dbObject.get("when"),
						(Long) dbObject.get("added"),
						tags
						);
			}
		};
	}
	
	@Override
	public Quote read(Long id) {
		return MongoUtils.readEntity(quotes, MongoUtils.toPrimaryKey(id), toEntity);
	}
	
	@Override
	public List<Quote> readAll() {
		return MongoUtils.readAllEntities(quotes, toEntity);
	}

	@Override
	public Quote save(Quote quote) {

		long id = quote.getId() == null ? nextId() : quote.getId();
		
		BasicDBList tags = new BasicDBList();
		for (String tag : quote.getTags()) {
			tags.add(new BasicDBObject("name", tag).append("slug", toSlug(tag)));
		}
		
		DBObject dbObject = MongoUtils.toPrimaryKey(id)
				.append("who", quote.getWho())
				.append("what", quote.getWhat())
				.append("when", quote.getWhen())
				.append("added", quote.getAdded() == null ? System.currentTimeMillis() : quote.getAdded())
				.append("tags", tags);
		
		this.quotes.save(dbObject);
		
		return read(id);
	}
	
	public List<Quote> getQuotesForTags(List<String> toHaveSlugs) {
		return getQuotesForTags(toHaveSlugs, new LinkedList<String>());
	}
	
	public List<Quote> getQuotesForTags(List<String> toHaveSlugs, List<String> toNotHaveSlugs) {
		BasicDBList query = new BasicDBList();
		for (String slug : toHaveSlugs) {
			query.add(new BasicDBObject("tags.slug", slug));
		}
		
		for (String slug : toNotHaveSlugs) {
			query.add(new BasicDBObject("tags.slug", new BasicDBObject("$ne", slug)));			
		}
		
		return MongoUtils.readEntities(quotes, new BasicDBObject("$and", query), toEntity);
	}
	
	public String toSlug(String tag) {
		return Slugify.slugify(tag);
	}
	
	private long nextId() {
		return counterDao.next("quote");
	}
}
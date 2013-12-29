package com.leonti.quotes.persistence.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.leonti.quotes.model.Quote;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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
					tags.add((String) tagDbObjects.get(i));
				}

				return new Quote(MongoUtils.toId(dbObject),
						(String) dbObject.get("userId"),
						(String) dbObject.get("who"),
						(String) dbObject.get("what"),
						(Long) dbObject.get("when"),
						(Long) dbObject.get("added"), tags);
			}
		};
	}

	@Override
	public Quote read(Long id) {
		return MongoUtils.readEntity(quotes, MongoUtils.toPrimaryKey(id),
				toEntity);
	}

	@Override
	public List<Quote> readAll() {
		return MongoUtils.readAllEntities(quotes, toEntity);
	}

	@Override
	public Quote save(Quote quote) {

		long id = quote.getId() == null ? nextId() : quote.getId();

		DBObject dbObject = MongoUtils
				.toPrimaryKey(id)
				.append("userId", quote.getUserId())
				.append("who", quote.getWho())
				.append("what", quote.getWhat())
				.append("when", quote.getWhen())
				.append("added",
						quote.getAdded() == null ? System.currentTimeMillis()
								: quote.getAdded())
				.append("tags", quote.getTags());

		this.quotes.save(dbObject);

		return read(id);
	}

	@Override
	public void remove(Long id) {
		MongoUtils.removeEntity(quotes, MongoUtils.toPrimaryKey(id));
	}

	public List<Quote> readUserQuotes(String userId) {
		return sortById(MongoUtils.readEntities(quotes,
				MongoUtils.toFieldKey("userId", userId), toEntity));
	}

	public List<Quote> readQuotesForTags(List<String> tags) {
		BasicDBList query = new BasicDBList();
		for (String tag : tags) {
			query.add(new BasicDBObject("tags", tag));
		}

		return sortById(MongoUtils.readEntities(quotes, new BasicDBObject(
				"$and", query), toEntity));
	}

	public List<String> getAllTags() {		
		DBCursor dbCursor = quotes.find(new BasicDBObject(), new BasicDBObject(
				"tags", 1));

		return extractTags(dbCursor);
	}

	public List<String> getTagsForUser(String userId) {
		DBCursor dbCursor = quotes.find(new BasicDBObject("userId", userId), new BasicDBObject(
				"tags", 1));
		
		return extractTags(dbCursor);
	}	
	
	private List<String> extractTags(DBCursor dbCursor) {
		
		Set<String> tags = Sets.newHashSet();
		while (dbCursor.hasNext()) {
			DBObject dbObject = dbCursor.next();
			BasicDBList tagDbObjects = (BasicDBList) dbObject.get("tags");
			for (int i = 0; i < tagDbObjects.size(); i++) {

				tags.add((String) tagDbObjects.get(i));
			}
		}

		return Lists.newLinkedList(tags);
	}

	private List<Quote> sortById(List<Quote> quotes) {
		Collections.sort(quotes, new Comparator<Quote>() {

			@Override
			public int compare(Quote quote1, Quote quote2) {
				return quote2.getId().compareTo(quote1.getId());
			}
		});

		return quotes;
	}

	private long nextId() {
		return counterDao.next("quote");
	}
}
package com.leonti.quotes.persistence.dao;

import java.util.List;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoUtils {

	interface ToEntity<E> {

		E convert(DBObject dbObject);
	}

	public static BasicDBObject toPrimaryKey(long id) {
		return new BasicDBObject("_id", id);
	}

	public static long toId(DBObject dbObject) {
		return (Long) dbObject.get("_id");
	}

	public static <E> E readEntity(DBCollection collection,
			BasicDBObject primaryKey, ToEntity<E> toEntity) {
		DBObject result = collection.findOne(primaryKey);

		if (result == null) {
			throw new RuntimeException("Entity was not found");
		}

		return toEntity.convert(result);
	}

	public static <E> List<E> readAllEntities(DBCollection collection,
			ToEntity<E> toEntity) {

		List<E> result = Lists.newLinkedList();

		DBCursor dbCursor = collection.find();
		while (dbCursor.hasNext()) {
			DBObject dbObject = dbCursor.next();
			result.add(toEntity.convert(dbObject));
		}

		return result;
	}
	
	public static <E> List<E> readEntities(DBCollection collection, BasicDBObject query,
			ToEntity<E> toEntity) {

		List<E> result = Lists.newLinkedList();

		DBCursor dbCursor = collection.find(query);
		while (dbCursor.hasNext()) {
			DBObject dbObject = dbCursor.next();
			result.add(toEntity.convert(dbObject));
		}

		return result;
	}	
}

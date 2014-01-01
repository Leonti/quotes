package com.leonti.quotes.persistence.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.leonti.quotes.model.Widget;
import com.mongodb.BasicDBList;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class WidgetDao implements Dao<Widget, Long> {

	private final DBCollection widgets;
	private final CounterDao counterDao;
	private final MongoUtils.ToEntity<Widget> toEntity;	

	@Inject
	public WidgetDao(DB db, CounterDao counterDao) {
		this.counterDao = counterDao;
		this.widgets = db.getCollection("widgets");
		
		toEntity = new MongoUtils.ToEntity<Widget>() {

			@SuppressWarnings("unchecked")
			@Override
			public Widget convert(DBObject dbObject) {
				
				List<String> tags = Lists.newLinkedList();
				BasicDBList tagDbObjects = (BasicDBList) dbObject.get("tags");
				
				for (int i = 0; i < tagDbObjects.size(); i++) {
					tags.add((String) tagDbObjects.get(i));
				}
				
				List<Long> quoteIds = Lists.newLinkedList();
				BasicDBList quoteIdsDbObjects = (BasicDBList) dbObject.get("quoteIds");
				
				for (int i = 0; i < quoteIdsDbObjects.size(); i++) {
					quoteIds.add((Long) quoteIdsDbObjects.get(i));
				}				
				
				return new Widget(
						MongoUtils.toId(dbObject),
						(String) dbObject.get("name"),
						(String) dbObject.get("userId"),
						Widget.Type.valueOf((String) dbObject.get("type")),
						quoteIds,
						tags,
						(HashMap<String, String>) dbObject.get("configs")
						);
			}
		};
	}	
	
	@Override
	public Widget read(Long id) {
		return MongoUtils.readEntity(widgets, MongoUtils.toPrimaryKey(id), toEntity);
	}

	@Override
	public List<Widget> readAll() {
		return sortById(MongoUtils.readAllEntities(widgets, toEntity));
	}

	public List<Widget> readUserWidgets(String userId) {
		
		return sortById(MongoUtils.readEntities(widgets, MongoUtils.toFieldKey("userId", userId),
				toEntity));	
	}

	@Override
	public Widget save(Widget widget) {
		long id = widget.getId() == null ? nextId() : widget.getId();
		
		DBObject dbObject = MongoUtils.toPrimaryKey(id)
				.append("name", widget.getName())
				.append("userId", widget.getUserId())
				.append("type", widget.getType().name())
				.append("quoteIds", widget.getQuoteIds())
				.append("tags", widget.getTags())
				.append("configs", widget.getConfigs());
		
		this.widgets.save(dbObject);
		
		return read(id);
	}

	@Override
	public void remove(Long id) {
		MongoUtils.removeEntity(widgets, MongoUtils.toPrimaryKey(id));
	}	
	
	private List<Widget> sortById(List<Widget> widgets) {
		Collections.sort(widgets, new Comparator<Widget>() {

			@Override
			public int compare(Widget widget1, Widget widget2) {
				return widget2.getId().compareTo(widget1.getId());
			}		
		});
		
		return widgets;
	} 
	
	private long nextId() {
		return counterDao.next("widget");
	}

}

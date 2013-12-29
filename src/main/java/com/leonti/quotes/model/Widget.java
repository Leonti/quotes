package com.leonti.quotes.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Widget {
	
	public static enum Type {
		IDS,
		TAGS
	}
	
	private final Long id;
	private String name;
	private final String userId;
	private final Type type;
	private final List<Long> quoteIds;
	private final List<String> tags;
	
	@JsonCreator
	public Widget(
			@JsonProperty("id") Long id, 
			@JsonProperty("name") String name, 
			@JsonProperty("userId") String userId, 
			@JsonProperty("type") Type type, 
			@JsonProperty("quoteIds") List<Long> quoteIds,
			@JsonProperty("tags") List<String> tags) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.type = type;
		this.quoteIds = quoteIds;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}	
	
	public String getUserId() {
		return userId;
	}

	public Type getType() {
		return type;
	}

	public List<Long> getQuoteIds() {
		return quoteIds;
	}

	public List<String> getTags() {
		return tags;
	}
	
	@Override
	public String toString() {
		String toString = id + " " + type + ": \"" + tags + "\", tags: [ ";
		
		for (String tag : tags) {
			toString += tag + " ";
		}
		
		toString += "], ids: [ ";
		
		for (Long quoteId : quoteIds) {
			toString += quoteId + " ";
		}
		
		toString += "]";
		return toString;
	}	
}

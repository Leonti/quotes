package com.leonti.quotes.model;

import java.util.List;
import java.util.Map;

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
	private final Map<String, String> configs;
	
	@JsonCreator
	public Widget(
			@JsonProperty("id") Long id, 
			@JsonProperty("name") String name, 
			@JsonProperty("userId") String userId, 
			@JsonProperty("type") Type type, 
			@JsonProperty("quoteIds") List<Long> quoteIds,
			@JsonProperty("tags") List<String> tags,
			@JsonProperty("configs") Map<String, String> configs) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.type = type;
		this.quoteIds = quoteIds;
		this.tags = tags;
		this.configs = configs;
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
	
	public Map<String, String> getConfigs() {
		return configs;
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

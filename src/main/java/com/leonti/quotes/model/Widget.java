package com.leonti.quotes.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Widget {
	
	public enum Type {
		SINGLE_QUOTE,
		TAGS
	}
	
	private final Long id;
	private final String email;
	private final Type type;
	private final Long quoteId;
	private final List<String> tags;
	
	@JsonCreator
	public Widget(
			@JsonProperty("id") Long id, 
			@JsonProperty("email") String email, 
			@JsonProperty("type") Type type, 
			@JsonProperty("quoteId") Long quoteId,
			@JsonProperty("tags") List<String> tags) {
		this.id = id;
		this.email = email;
		this.type = type;
		this.quoteId = quoteId;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Type getType() {
		return type;
	}

	public Long getQuoteId() {
		return quoteId;
	}

	public List<String> getTags() {
		return tags;
	}	
}

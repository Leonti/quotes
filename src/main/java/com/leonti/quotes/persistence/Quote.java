package com.leonti.quotes.persistence;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Quote {

	private Long id;
	private final String who;
	private final String what;
	private final Long when;
	private final Long added;
	private final List<String> tags;
	
	@JsonCreator
	public Quote(
			@JsonProperty("id") Long id, 
			@JsonProperty("who") String who, 
			@JsonProperty("what") String what, 
			@JsonProperty("when") Long when, 
			@JsonProperty("added") Long added,
			@JsonProperty("tags") List<String> tags) {
		this.id = id;
		this.who = who;
		this.what = what;
		this.when = when;
		this.added = added;
		this.tags = tags;
	}
	
	public Long getId() {
		return id;
	}
	public String getWho() {
		return who;
	}
	public String getWhat() {
		return what;
	}
	public Long getWhen() {
		return when;
	}
	public Long getAdded() {
		return added;
	}
	public List<String> getTags() {
		return tags;
	}
}

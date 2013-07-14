package com.leonti.quotes.persistence.dao;

import java.util.List;

public interface Dao<T> {

	T read(long id);
	List<T> readAll();
	T save(T entity);
}

package com.leonti.quotes.persistence.dao;

import java.util.List;

public interface Dao<T, I> {

	T read(I id);
	List<T> readAll();
	T save(T entity);
	void remove(I id);
}

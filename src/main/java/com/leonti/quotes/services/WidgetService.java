package com.leonti.quotes.services;

import java.util.List;

import com.leonti.quotes.model.Widget;

public interface WidgetService {
	
	Widget create(Widget widget);

	Widget update(long id, Widget widget);

	Widget read(long id);
	
	List<Widget> readUserWidgets(String userId);

	void remove(long id);
}

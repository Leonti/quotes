package com.leonti.quotes.services;

import java.util.List;

import javax.inject.Inject;

import com.leonti.quotes.model.Widget;
import com.leonti.quotes.persistence.dao.WidgetDao;

public class WidgetServiceImpl implements WidgetService {

	private final WidgetDao widgetDao;

	@Inject
	public WidgetServiceImpl(WidgetDao widgetDao) {
		this.widgetDao = widgetDao;
	}
	
	@Override
	public Widget create(Widget widget) {
		return widgetDao.save(widget);
	}

	@Override
	public Widget update(long id, Widget widget) {
		return widgetDao.save(widget);
	}

	@Override
	public Widget read(long id) {
		return widgetDao.read(id);
	}

	@Override
	public List<Widget> readUserWidgets(String email) {
		return widgetDao.readUserWidgets(email);
	}

	@Override
	public void remove(long id) {
		widgetDao.remove(id);
	}
}

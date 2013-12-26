package com.leonti.quotes.resources;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.leonti.quotes.model.Widget;
import com.leonti.quotes.services.WidgetService;

public class WidgetResourceImpl implements WidgetResource {

	private final WidgetService widgetService;

	@Inject
	public WidgetResourceImpl(WidgetService widgetService) {
		this.widgetService = widgetService;
	}
	
	@Override
	public Widget create(Widget widget) {
		return widgetService.create(widget);
	}

	@Override
	public List<Widget> getWidgetsForUser() {
		Subject currentUser = SecurityUtils.getSubject();

		String email = (String) currentUser.getPrincipal();	
		return widgetService.readUserWidgets(email);
	}

	@Override
	public Widget getWidget(long id) {
		return widgetService.read(id);
	}

	@Override
	public void removeWidget(long id) {
		widgetService.remove(id);
	}

	@Override
	public Widget update(long id, Widget widget) {
		return widgetService.update(id, widget);
	}

}

package com.leonti.quotes.resources;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.leonti.quotes.model.User;
import com.leonti.quotes.model.Widget;
import com.leonti.quotes.services.UserService;
import com.leonti.quotes.services.WidgetService;

public class WidgetResourceImpl implements WidgetResource {

	private final WidgetService widgetService;
	private final UserService userService;

	@Inject
	public WidgetResourceImpl(WidgetService widgetService, UserService userService) {
		this.widgetService = widgetService;
		this.userService = userService;
	}
	
	@Override
	public Widget create(Widget widget) {
		return widgetService.create(widget);
	}

	@Override
	public List<Widget> getWidgetsForUser() {
		Subject currentUser = SecurityUtils.getSubject();

		String email = (String) currentUser.getPrincipal();
		User user = userService.readByEmail(email);
		return widgetService.readUserWidgets(user.getId());
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

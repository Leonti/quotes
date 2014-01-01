package com.leonti.quotes.test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leonti.quotes.model.Widget;
import com.leonti.quotes.persistence.dao.CounterDao;
import com.leonti.quotes.persistence.dao.WidgetDao;

public class WidgetDaoTest {

	@Test
	public void test() {
		CounterDao counterDao = new CounterDao(TestDaoBase.getMongoDB());
		
		WidgetDao widgetDao = new WidgetDao(TestDaoBase.getMongoDB(), counterDao);
		
		List<String> tags = Lists.<String>asList("Leonti", new String[] {"Unfunny"});
		List<Long> quoteIds = Lists.<Long>asList(1l, new Long[] {2l, 3l, 4l});
		
		Map<String, String> configs = Maps.newHashMap();
		configs.put("image_width", "300");
		
		Widget widget = new Widget(null, "Some widget", "sadasdsa", Widget.Type.IDS, quoteIds, tags, configs);
		
		Widget read = widgetDao.save(widget);
		
		System.out.println(read);
		System.out.println(read.getConfigs().get("image_width"));
	}
}

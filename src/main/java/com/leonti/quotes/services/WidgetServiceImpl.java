package com.leonti.quotes.services;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.leonti.quotes.model.Quote;
import com.leonti.quotes.model.Widget;
import com.leonti.quotes.persistence.dao.QuoteDao;
import com.leonti.quotes.persistence.dao.WidgetDao;

public class WidgetServiceImpl implements WidgetService {

	private final WidgetDao widgetDao;
	private final QuoteDao quoteDao;

	private final Random random = new Random();
	
	@Inject
	public WidgetServiceImpl(WidgetDao widgetDao, QuoteDao quoteDao) {
		this.widgetDao = widgetDao;
		this.quoteDao = quoteDao;
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
	public List<Widget> readUserWidgets(String userId) {
		return widgetDao.readUserWidgets(userId);
	}

	@Override
	public void remove(long id) {
		widgetDao.remove(id);
	}

	@Override
	public Quote getRandomQuoteForWidget(Long widgetId) {
		
		Widget widget = widgetDao.read(widgetId);
		
		if (widget.getType() == Widget.Type.IDS) {
			int position = random.nextInt(widget.getQuoteIds().size());
			return quoteDao.read(widget.getQuoteIds().get(position));
		}
		
		List<Quote> quotes = quoteDao.readQuotesForTags(widget.getTags());
		int position = random.nextInt(quotes.size());
		return quotes.get(position);
	}	
}

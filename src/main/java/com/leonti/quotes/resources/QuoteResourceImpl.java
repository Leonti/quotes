package com.leonti.quotes.resources;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.leonti.quotes.model.Quote;
import com.leonti.quotes.model.User;
import com.leonti.quotes.services.QuoteService;
import com.leonti.quotes.services.UserService;

public class QuoteResourceImpl implements QuoteResource {
	
	private final QuoteService quoteService;
	private final UserService userService;

	@Inject
	public QuoteResourceImpl(QuoteService quoteService, UserService userService) {
		this.quoteService = quoteService;
		this.userService = userService;
	}
	
	@Override
	public Quote create(Quote quote) {
		return quoteService.create(quote);
	}

	@Override
	public List<Quote> getQuotesForUser() {
		Subject currentUser = SecurityUtils.getSubject();

		String email = (String) currentUser.getPrincipal();
		User user = userService.readByEmail(email);
		return quoteService.readUserQuotes(user.getId());
	}	
	
	@Override
	public Quote update(long id, Quote quote) {
		return quoteService.update(id, quote);
	}

	@Override
	public Quote getQuote(long id) {
		return quoteService.read(id);
	}

	@Override
	public void removeQuote(long id) {
		quoteService.remove(id);
	}

	@Override
	public List<String> getTagsForUser() {
		Subject currentUser = SecurityUtils.getSubject();

		String email = (String) currentUser.getPrincipal();
		User user = userService.readByEmail(email);
		return quoteService.readTagsForUser(user.getId());
	}

}

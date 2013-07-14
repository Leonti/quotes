package com.leonti.quotes.modules;

import java.net.UnknownHostException;
import java.util.Arrays;

import javax.inject.Named;
import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.leonti.quotes.Configuration.Constants;
import com.leonti.quotes.persistence.dao.CounterDao;
import com.leonti.quotes.persistence.dao.QuoteDao;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class PersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CounterDao.class);
		bind(QuoteDao.class);
	}
	
	@Provides
	public DB provideDB(@Named(Constants.MONGO_DB) String mongodb, Mongo mongo) {
		return mongo.getDB(mongodb);
	}

	@Provides
	@Singleton
	public Mongo provideMongo(@Named(Constants.MONGO_USER) String user, @Named(Constants.MONGO_DB) String db,
			@Named(Constants.MONGO_PASSWORD) String password, @Named(Constants.MONGO_HOSTNAME) String hostname,
			@Named(Constants.MONGO_PORT) String port) throws UnknownHostException {
		MongoCredential credential = MongoCredential.createMongoCRCredential(user, db, password.toCharArray());
		
		return new MongoClient(new ServerAddress(hostname, Integer.valueOf(port)), Arrays.asList(credential));
	}	
}

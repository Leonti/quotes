package com.leonti.quotes.test.dao;

import java.util.Arrays;
import java.util.Properties;

import com.leonti.quotes.Configuration;
import com.leonti.quotes.Configuration.Constants;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class TestDaoBase {
	
	public static DB getMongoDB() {
		try {
			Properties config = Configuration.load();
			
			String user = config.getProperty(Constants.MONGO_USER);
			String db = config.getProperty(Constants.MONGO_DB);
			String password = config.getProperty(Constants.MONGO_PASSWORD);
			String hostname = config.getProperty(Constants.MONGO_HOSTNAME);
			String port = config.getProperty(Constants.MONGO_PORT);
			
			MongoCredential credential = MongoCredential.createMongoCRCredential(user, db, password.toCharArray());
			
			Mongo mongo = new MongoClient(new ServerAddress(hostname, Integer.valueOf(port)), Arrays.asList(credential));
			
			return mongo.getDB(db);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		throw new RuntimeException("Could not create mongo db");
	}
}

package com.leonti.quotes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	
	public interface Constants {
		String MONGO_DB = "mongo.db";
		String MONGO_USER = "mongo.user";
		String MONGO_PASSWORD = "mongo.password";
		String MONGO_HOSTNAME = "mongo.hostname";
		String MONGO_PORT = "mongo.port";
		String PERSONA_AUDIENCE = "persona.audience";
	}

	private static final File DEFAULT = new File(System.getProperty("user.home") + File.separator + ".quotes" + File.separator
			+ "configuration.properties");

	public static Properties load() {
		return load(DEFAULT);
	}

	public static Properties load(File file) {
		final Properties configuration = new Properties();

		try (FileInputStream in = new FileInputStream(file)) {
			configuration.load(in);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

		return configuration;
	}

	public static String getProperty(String name) {
		Object obj = load().get(name);
		return obj == null ? null : obj.toString();
	}	
}

package com.leonti.quotes.persistence.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.google.inject.Inject;
import com.leonti.quotes.model.User;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class UserDao implements Dao<User, String> {

	private final MongoUtils.ToEntity<User> toEntity;
	private final DBCollection users;

	@Inject
	public UserDao(DB db) {
		this.users = db.getCollection("users");

		toEntity = new MongoUtils.ToEntity<User>() {

			@Override
			public User convert(DBObject dbObject) {
				return new User(
						MongoUtils.toStringId(dbObject),
						(String) dbObject.get("email"),
						(Long) dbObject.get("registered"));
			}
		};
	}

	public User readByEmail(String email) {
		return MongoUtils.readEntity(users, MongoUtils.toFieldKey("email", email),
				toEntity);		
	}
	
	@Override
	public User read(String id) {
		return MongoUtils.readEntity(users, MongoUtils.toPrimaryKey(id),
				toEntity);
	}

	@Override
	public List<User> readAll() {
		return MongoUtils.readAllEntities(users, toEntity);
	}

	@Override
	public User save(User user) {

		DBObject dbObject = MongoUtils.toPrimaryKey(user.getId())
				.append("email", user.getEmail())
				.append("registered", user.getRegistered());

		this.users.save(dbObject);
		return user;
	}

	@Override
	public void remove(String email) {
		MongoUtils.removeEntity(users, MongoUtils.toPrimaryKey(email));
	}	
	
	public User create(String email) {
		return save(new User(emailToHash(email), email, System.currentTimeMillis()));
	} 
	
	private String emailToHash(String email) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(email.getBytes());
			
			byte byteData[] = md.digest();		
			
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<byteData.length;i++) {
				String hex=Integer.toHexString(0xff & byteData[i]);

				if (hex.length()==1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}

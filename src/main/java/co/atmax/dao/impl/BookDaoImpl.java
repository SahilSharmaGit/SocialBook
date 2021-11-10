package co.atmax.dao.impl;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import co.atmax.dao.BookDao;
import co.atmax.model.LogIn;
import co.atmax.model.User;

public class BookDaoImpl implements BookDao {

	static DBCollection collection;
	static DBCollection postCollection;
	static MongoClient client;
	static {
		createDBConnection();
		createDBConnectionForPostCollection();
	}

	public static void getConnectedWithMongoDB() {
		try {
			client = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static DBCollection createDBConnection() {
		if (collection == null) {
			getConnectedWithMongoDB();
			DB db = client.getDB("atmaxdb");
			db.createCollection("socialbook", null);
			collection = db.getCollection("socialbook");
			return collection;
		}
		return collection;
	}

	public static DBCollection createDBConnectionForPostCollection() {
		if (postCollection == null) {
			getConnectedWithMongoDB();
			DB db = client.getDB("atmaxdb");
			db.createCollection("postdata", null);
			postCollection = db.getCollection("postdata");
			return postCollection;
		}
		return postCollection;
	}

	@Override
	public Object register(User user) {
		if (null != user) {
			BasicDBObject doc = new BasicDBObject();
			doc.put("Email", user.getEmail());
			doc.put("Password", user.getPassword());
			doc.put("PhoneNo", user.getMobileno());
			doc.put("Age", user.getAge());
			return collection.save(doc);
		}
		return user;
	}

	@Override
	public int doLog(LogIn in) {
		if (null != in) {
			BasicDBObject bdbo = new BasicDBObject();
			bdbo.put("Email", in.getEmail());
			bdbo.put("Password", in.getPassword());
			DBCursor cursor = collection.find(bdbo);
			DBObject dbo = cursor.next();
			if (null != dbo) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public Object doLog(User user) {
		return user;
	}

	@Override
	public void savePost(String id, String message, String date) {

		BasicDBObject dbo = new BasicDBObject();
		dbo.put("UserId", id);
		dbo.put("Message", message);
		dbo.put("Time", date);
		postCollection.save(dbo);
	}
}
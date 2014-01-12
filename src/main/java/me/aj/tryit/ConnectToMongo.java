package me.aj.tryit;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class ConnectToMongo {

	MongoClient client = null;
	private static DB db;
	private static DBCollection DBCOLL;

	private ConnectToMongo() throws UnknownHostException {
		if (db == null || client == null) {
			client = new MongoClient("127.0.0.1", 27017);
			db = client.getDB("GameOfThrones");
			if (db == null || client == null) {
				System.out.println("CTM:Connection is null");
			} else
				System.out.println("CTM:Connected to database:"+db.getName());
		}
	}

	public static DBCollection getCollectionInstance(DBCollection coll)
			throws UnknownHostException {
		new ConnectToMongo();
		DBCOLL = coll;
		return DBCOLL;
	}

	public static DB getDBInstance() throws UnknownHostException {
		new ConnectToMongo();
		return db;
	}

	public static void main(String[] args) throws UnknownHostException {
		new ConnectToMongo();

	}

}

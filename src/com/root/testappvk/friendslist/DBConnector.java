package com.root.testappvk.friendslist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBConnector {

	private static final String TABLE_NAME = "vk_friends";

	private static final String COLUMN_ID = "_id";
	
	private static final String FIRSTNAME_KEY = "firstName";
	private static final String LASTNAME_KEY = "lastName";
	private static final String PHOTO_KEY = "photoURL";
	
	private static final int NUM_COLUMN_ID = 0;
	private static final int NUM_COLUMN_UID = 1;
	private static final int NUM_COLUMN_FIRST_NAME = 2;
	private static final int NUM_COLUMN_LAST_NAME = 3;
	private static final int NUM_COLUMN_PHOTO_URL = 4;
	private static final int NUM_COLUMN_ISMALE = 5;
	
	
	private SQLiteDatabase mDataBase;

	public DBConnector(Context context) {
		OpenHelper mOpenHelper = new OpenHelper(context);
		mDataBase = mOpenHelper.getWritableDatabase();
	}

	public void deleteAll() {
		mDataBase.delete(TABLE_NAME, null, null);
	}

	public void delete(long id) {
		mDataBase.delete(TABLE_NAME, COLUMN_ID + " = '" + id + "'", null);
	}

	public MyFriend select(long id) {
		Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null);

		mCursor.moveToFirst();
		long uid = mCursor.getLong(NUM_COLUMN_UID);
		String firstName = mCursor.getString(NUM_COLUMN_FIRST_NAME);
		String lastName = mCursor.getString(NUM_COLUMN_LAST_NAME);
		String photoURL = mCursor.getString(NUM_COLUMN_PHOTO_URL);
		int isMale = mCursor.getInt(NUM_COLUMN_ISMALE);

		return new MyFriend(id, uid, firstName, lastName, photoURL, isMale);
	}

	public List<MyFriend> selectAll() {
		Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null,
				null, null);

		List<MyFriend> arr = new ArrayList<MyFriend>();
		if (mCursor.moveToFirst()) {
			do {
				long id = mCursor.getLong(NUM_COLUMN_ID);
				long uid = mCursor.getLong(NUM_COLUMN_UID);
				String firstName = mCursor.getString(NUM_COLUMN_FIRST_NAME);
				String lastName = mCursor.getString(NUM_COLUMN_LAST_NAME);
				String photoURL = mCursor.getString(NUM_COLUMN_PHOTO_URL);
				int isMale = mCursor.getInt(NUM_COLUMN_ISMALE);

				arr.add(new MyFriend(id, uid, firstName, lastName, photoURL,
						isMale));
			} while (mCursor.moveToNext());

		}

		return arr;
	}

	public List<MyFriend> selectOffline() {
		String query = "SELECT " + "_id, " + "uid, " + "first_name, "
				+ "last_name, " + "photo_url " + "FROM " + "vk_friends "
				+ "WHERE " + "isOnline = '2' ";
		Cursor mCursor = mDataBase.rawQuery(query, null);
		List<MyFriend> arr = new ArrayList<MyFriend>();
		if (mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			do {
				long id = mCursor.getLong(NUM_COLUMN_ID);
				long uid = mCursor.getLong(NUM_COLUMN_UID);
				String firstName = mCursor.getString(mCursor
						.getColumnIndex("first_name"));
				String lastName = mCursor.getString(mCursor
						.getColumnIndex("last_name"));
				String photoURL = mCursor.getString(mCursor
						.getColumnIndex("photo_url"));

				arr.add(new MyFriend(id, uid, firstName, lastName, photoURL, 0));
			} while (mCursor.moveToNext());

		}
		return arr;
	}

	public List<MyFriend> selectOnline() {
		String query = "SELECT " + "_id, " + "uid, " + "first_name, "
				+ "last_name, " + "photo_url " + "FROM " + "vk_friends "
				+ "WHERE " + "isOnline = '1' ";
		Cursor mCursor = mDataBase.rawQuery(query, null);
		List<MyFriend> arr = new ArrayList<MyFriend>();
		if (mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			do {
				long id = mCursor.getLong(NUM_COLUMN_ID);
				long uid = mCursor.getLong(NUM_COLUMN_UID);
				String firstName = mCursor.getString(mCursor
						.getColumnIndex("first_name"));
				String lastName = mCursor.getString(mCursor
						.getColumnIndex("last_name"));
				String photoURL = mCursor.getString(mCursor
						.getColumnIndex("photo_url"));

				arr.add(new MyFriend(id, uid, firstName, lastName, photoURL, 0));
			} while (mCursor.moveToNext());

		}
		return arr;
	}

	public Map<String, String> currentUser(long uid) {
		String query = "SELECT " + "first_name, " + "last_name, "
				+ "photo_url " + "FROM " + "vk_friends " + "WHERE " + "_id ="
				+ uid + " ";
		Map<String, String> currentUserArr = new HashMap<String, String>();
		Cursor mCursor = mDataBase.rawQuery(query, null);
		if (mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			do {

				String firstName = mCursor.getString(mCursor
						.getColumnIndex("first_name"));
				String lastName = mCursor.getString(mCursor
						.getColumnIndex("last_name"));
				String photoURL = mCursor.getString(mCursor
						.getColumnIndex("photo_url"));

				currentUserArr.put(FIRSTNAME_KEY, firstName);
				currentUserArr.put(LASTNAME_KEY, lastName);
				currentUserArr.put(PHOTO_KEY, photoURL);
			} while (mCursor.moveToNext());

		}
		return currentUserArr;
	}

	public Boolean isEmpty() {
		List<MyFriend> arr = selectAll();
		if (arr.size() == 0) {
			return true;
		}

		return false;
	}

}

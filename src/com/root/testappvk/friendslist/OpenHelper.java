package com.root.testappvk.friendslist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class OpenHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "friends.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "vk_friends";

	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_UID = "uid";
	private static final String COLUMN_FIRST_NAME = "first_name";
	private static final String COLUMN_LAST_NAME = "last_name";
	private static final String COLUMN_PHOTO_URL = "photo_url";
	private static final String COLUMN_SEX = "isOnline";

	
	public OpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_UID
				+ " LONG, " + COLUMN_FIRST_NAME + " TEXT, "
				+ COLUMN_LAST_NAME + " TEXT, " + COLUMN_PHOTO_URL + " TEXT, " + COLUMN_SEX + " INTEGER); ";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	
}
package com.root.testappvk.friendslist;

import java.util.List;

import com.perm.kate.api.User;
import com.root.testappvk.ApiClass;

import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;

public class DownLoadService extends ForYouMyFriendService implements
		StoreCallBack {
	Thread thread;
	private static final String COLUMN_FIRST_NAME = "first_name";
	private static final String COLUMN_LAST_NAME = "last_name";
	private static final String COLUMN_PHOTO_URL = "photo_url";
	private static final String COLUMN_ISONLINE = "isOnline";

	public DownLoadService() {
		thread = new DownloadThread(this);
		thread.start();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		return START_STICKY;
	}

	public void onStore(List<User> users) {
		ContentValues values = new ContentValues();
		for (int i = 0; i < users.size(); i++) {
			String firstName = users.get(i).first_name;
			String lastName = users.get(i).last_name;
			String userPhoto = users.get(i).photo_medium;
			boolean isOnline = users.get(i).online;
			int flag = 0;
			if (isOnline) {
				flag = 1;
				values.put(COLUMN_ISONLINE, flag);
			} else {
				flag = 2;
				values.put(COLUMN_ISONLINE, flag);
			}

			values.put(COLUMN_FIRST_NAME, firstName);
			values.put(COLUMN_LAST_NAME, lastName);
			values.put(COLUMN_PHOTO_URL, userPhoto);

			ApiClass.getDatabaseHelper().getWritableDatabase()
					.insert("vk_friends", null, values);
			values.clear();
		}

	}

}

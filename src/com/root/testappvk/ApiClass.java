package com.root.testappvk;

import com.perm.kate.api.Api;
import com.root.testappvk.friendslist.OpenHelper;

import android.app.Application;

public class ApiClass extends Application {
	private static Api sApi;
	private static Account sAccount;
	private static OpenHelper mOpenHelper;
	private static ApiClass mApp;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApp = this;
		sAccount = new Account();
		sAccount.restore(this);
	}

	public static ApiClass getApp() {
		return mApp;
	}

	public static Api getApi() {
		if (sApi == null) {
			sApi = new Api(sAccount.access_token, Constants.API_ID);
		}
		return sApi;
	}

	public static Account getAccount() {
		if (sAccount.access_token == null) {
			sAccount = new Account();
			sAccount.restore(mApp);
		}
		return sAccount;
	}

	public static OpenHelper getDatabaseHelper() {
		if (mOpenHelper == null) {
			mOpenHelper = new OpenHelper(getApp());
		}

		return mOpenHelper;
	}
}

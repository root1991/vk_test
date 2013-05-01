package com.root.testappvk.friendslist;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONException;


import com.perm.kate.api.Api;
import com.perm.kate.api.KException;
import com.perm.kate.api.User;
import com.root.testappvk.Account;
import com.root.testappvk.ApiClass;
import com.root.testappvk.Constants;
import com.root.testappvk.MainActivity;


public class DownloadThread extends Thread {
	private StoreCallBack mStoreCallBack;

	DownloadThread(StoreCallBack storeCallBack) {
		mStoreCallBack = storeCallBack;
	}

	@Override
	public void run() {
		downloadFriends();
	}

	private void downloadFriends() {	
		Account acc = ApiClass.getAccount();
		Api api = new Api(acc.access_token, Constants.API_ID);
		
		try {
			List<User> friends = api.getFriends(acc.user_id, null, null, null,
					null);
			mStoreCallBack.onStore(friends);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

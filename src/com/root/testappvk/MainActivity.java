package com.root.testappvk;

import com.perm.kate.api.Api;
import com.root.testappvk.fragments.MyPageViewerTab;
import com.root.testappvk.friendslist.DBConnector;
import com.root.testappvk.friendslist.DownLoadService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private final int REQUEST_LOGIN = 1;

	private Button authorizeButton;
	private Account account = new Account();
	private Api api;
	public Context mContext = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupUI();
		account.restore(this);
	}

	private void setupUI() {
		authorizeButton = (Button) findViewById(R.id.authorize);
		authorizeButton.setOnClickListener(this);
	}

	private void startLoginActivity() {
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivityForResult(intent, REQUEST_LOGIN);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_LOGIN) {
			if (resultCode == RESULT_OK) {
				account.access_token = data.getStringExtra("token");
				account.user_id = data.getLongExtra("user_id", 0);
				account.save(MainActivity.this);
				api = new Api(account.access_token, Constants.API_ID);
				startMyService();
				startFriendsPageView();
			}
		}
	}

	private void startFriendsPageView() {
		if (account.access_token != null) {
			String t = account.access_token;
			api = ApiClass.getApi();
			Intent in = new Intent(this, MyPageViewerTab.class);
			startActivity(in);
		}
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.authorize:
			startLoginActivity();
			break;
		}
	}

	private void startMyService() {
		DBConnector connector = new DBConnector(this);
		if (connector.isEmpty() == true) {
			Intent in = new Intent(this, DownLoadService.class);
			startService(in);
		}
	}
}
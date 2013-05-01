package com.root.testappvk;

import java.util.Map;

import com.root.testappvk.friendslist.DBConnector;
import com.root.testappvk.imagecache.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendViewerActivity extends Activity{
	private ImageView userImage;
	private TextView userName;
	private DBConnector connector;
	
	private static final int DEFAULT_VALUE = 0;
	private static final String UID_KEY = "uid";
	private static final String FIRSTNAME_KEY = "firstName";
	private static final String LASTNAME_KEY = "lastName";
	private static final String PHOTO_KEY = "photoURL";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_viewer);
		
		createUI();
		setData();
	}
	
	private void createUI() {
		userImage = (ImageView)findViewById(R.id.friend_avatar_big);
		userName = (TextView)findViewById(R.id.friend_name);
		
	}
	
	private void setData() {
		ImageLoader il = new ImageLoader(this);
		connector = new DBConnector(this);
		Intent in = getIntent();
		Map<String, String> currentUser = connector.currentUser(in.getLongExtra(UID_KEY, DEFAULT_VALUE));
		userName.setText(currentUser.get(FIRSTNAME_KEY)+"  "+currentUser.get(LASTNAME_KEY));
		il.displayImage(currentUser.get(PHOTO_KEY), userImage);
	}

}

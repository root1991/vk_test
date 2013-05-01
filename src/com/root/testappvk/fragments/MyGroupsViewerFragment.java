package com.root.testappvk.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.root.testappvk.FriendViewerActivity;
import com.root.testappvk.R;
import com.root.testappvk.friendslist.DBConnector;
import com.root.testappvk.friendslist.FriendsAdapter;

public class MyGroupsViewerFragment extends SherlockFragment implements OnItemClickListener{
	private ListView mFriends;
	private static final String UID_KEY = "uid";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.my_groups_viewer_fragment, container, false);
		mFriends = (ListView)view.findViewById(R.id.offline_friends_lv);
		DBConnector connector = new DBConnector(getSherlockActivity());		
		FriendsAdapter adapter = new FriendsAdapter(getSherlockActivity(), connector.selectOffline());
		mFriends.setAdapter(adapter);
		mFriends.setOnItemClickListener(this);
		return view;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent(getSherlockActivity(), FriendViewerActivity.class);
		i.putExtra(UID_KEY, id);
		startActivity(i);
		
	}
}

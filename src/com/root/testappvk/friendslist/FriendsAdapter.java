package com.root.testappvk.friendslist;

import java.util.List;

import com.root.testappvk.R;
import com.root.testappvk.imagecache.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendsAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private	List<MyFriend> mMyData;
	ImageLoader il;

	public FriendsAdapter(Context context, List<MyFriend> myData) {
		il = new ImageLoader(context);
		mLayoutInflater = LayoutInflater.from(context);
		mContext = context;
		mMyData = myData;
	}

	public int getCount() {
		return mMyData.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		MyFriend myFriend = mMyData.get(position);
		if(myFriend!=null) {
			return myFriend.getId();
		}
		return 0;
	}
	
	class ViewHolder {
		TextView firstName;
		TextView lastName;
		ImageView userImage;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if(convertView==null) {
			
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item, null);		
			holder.firstName = (TextView)convertView.findViewById(R.id.friend_first_name);
			holder.lastName = (TextView)convertView.findViewById(R.id.friend_last_name);
			holder.userImage = (ImageView)convertView.findViewById(R.id.friend_image);
			
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		MyFriend friend = mMyData.get(position);
		String firstName = friend.getFirstName();
		
		if(firstName!=null) {
			if(holder.firstName!=null) {
				holder.firstName.setText(firstName);
				holder.lastName.setText(friend.getLastName());
				il.displayImage(friend.getPhotoURL(), holder.userImage);
			}
		}
		
		return convertView;
	}

}

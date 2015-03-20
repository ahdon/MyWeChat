package com.example.mywechat.photo;


import com.example.mywechat.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;




public class LoaderAdapter extends BaseAdapter{

	private static final String TAG = "LoaderAdapter";
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	
	private ImageLoader mImageLoader;
	private int mCount;
	private Context mContext;
	private String[] urlArrays;
	
	
	public LoaderAdapter(int count, Context context, String []url) {
		this.mCount = count;
		this.mContext = context;
		urlArrays = url;
		mImageLoader = new ImageLoader(context);
	}
	
	public ImageLoader getImageLoader(){
		return mImageLoader;
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.text = (TextView) convertView
					.findViewById(R.id.info);
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.img);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String url = "";
		url = urlArrays[position % urlArrays.length];
		
//		viewHolder.mImageView.setImageResource(R.drawable.ic_launcher);
		

		if (!mBusy) {
			mImageLoader.DisplayImage(url, viewHolder.mImageView, false);
			viewHolder.text.setText("--" + position
					+ "--IDLE ||TOUCH_SCROLL");
			viewHolder.title.setText("°¢¶«");
		} else {
			mImageLoader.DisplayImage(url, viewHolder.mImageView, false);		
			viewHolder.text.setText("--" + position + "--FLING");
			viewHolder.title.setText("°¢¶«");
		}
		return convertView;
	}

	static class ViewHolder {
		TextView text;
		TextView title;
		ImageView mImageView;
	}
}

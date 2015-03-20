package com.example.mywechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mywechat.R;
import com.example.mywechat.photo.LoaderAdapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TabFragment extends Fragment {
	
	private String mTitle="Default";
	
	public static final String TITLE = "title";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(getArguments()!=null){
			mTitle=getArguments().getString(TITLE);
		}
		
		ListView listView = new ListView(getActivity());
		LoaderAdapter adapter =new LoaderAdapter (URLS.length, getActivity(), URLS);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent (getActivity(),ChatActivity.class);			
				startActivity(intent);
			}
		});
		
		return listView;
	}

	private static final String[] URLS = {
		"http://img0.bdstatic.com/img/image/a65447f1f3c79fe4d9e104eb666879a31418615226.jpg",
		"http://img0.bdstatic.com/img/image/6446027056db8afa73b23eaf953dadde1410240902.jpg",
		"http://img0.bdstatic.com/img/image/2043d07892fc42f2350bebb36c4b72ce1409212020.jpg",
		"http://img0.bdstatic.com/img/image/c2ec489f509fcbf92d6f2d5e9a96a7ea1409130769.jpg"};
}

package com.example.mywechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShouyeActivity extends Activity{
	
	Button loginBtn,signBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.shouye);
		initview();
	}

	private void initview() {
		loginBtn=(Button) findViewById(R.id.login);
		signBtn=(Button) findViewById(R.id.sign);
		
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(intent);
			}
		});
		signBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),SignActivity.class);
				startActivity(intent);
			}
		});
	}
}

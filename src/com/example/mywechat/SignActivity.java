package com.example.mywechat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mywechat.util.HttpUtil;;

public class SignActivity extends BaseActivity{
	
	Button signBtn;
	EditText editName,editPassword,surePassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign);
		initview();
	}

	private void initview() {
		
		signBtn=(Button) findViewById(R.id.signBtn);
		editName=(EditText) findViewById(R.id.editName);
		editPassword=(EditText) findViewById(R.id.editPassword);
		surePassword=(EditText) findViewById(R.id.surePassword);
		
		signBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					
					String name=editName.getText().toString().trim();
					String password=editPassword.getText().toString().trim();
					String passwordsure=surePassword.getText().toString().trim();
					
					String sUrl = HttpUtil.BASE_URL + "signup?name="+name+"&&password="+password+"";
					String sJson = HttpUtil.sendByHttpClient(SignActivity.this, sUrl);
					
					if(sJson!="failed"){
						JSONObject json=new JSONObject(sJson);
						String h_id=json.getString("h_id");
						int id=json.getInt("id");
						SharedPreferences sp=getSharedPreferences("weChatDatas", MODE_PRIVATE);
						Editor editor=sp.edit();
						editor.putString("name", name);
						editor.putString("password", password);
						editor.putString("h_id", h_id);
						editor.putInt("id", id);
						editor.apply();
						Toast.makeText(getApplicationContext(), sp.getString("name","null"),
							     Toast.LENGTH_LONG).show();
						Log.d("123", sJson);
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(intent);
			}
		});
	}
}

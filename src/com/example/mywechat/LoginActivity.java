package com.example.mywechat;

import org.json.JSONObject;

import com.example.mywechat.util.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity{
	
	Button loginBtn;
	EditText editName,editPassword;
	private String name,password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initview();
	}

	private void initview() {
		loginBtn=(Button) findViewById(R.id.loginBtn);
		editName=(EditText) findViewById(R.id.editName);
		editPassword=(EditText) findViewById(R.id.editPassword);
		checkData();
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					name=editName.getText().toString().trim();
					password=editPassword.getText().toString().trim();
					String sUrl = HttpUtil.BASE_URL + "login?name="+name+"&&password="+password+"";
					String sJson = HttpUtil.sendByHttpClient(LoginActivity.this, sUrl);
					Log.d("1111111111111111111111111111111", name);
					if(sJson!=null){
						JSONObject json=new JSONObject(sJson);
						String h_id=json.getString("h_id");
						int id=json.getInt("id");
						String photoPath=json.getString("photopath");
						SharedPreferences sp=getSharedPreferences("weChatDatas", MODE_PRIVATE);
						Editor editor=sp.edit();
						editor.putString("name", name);
						editor.putString("password", password);
						editor.putString("h_id", h_id);
						editor.putInt("id", id);
						editor.putString("photoPath", photoPath);
						editor.apply();
						Intent intent=new Intent(getApplicationContext(),MainActivity.class);
						startActivity(intent);
					}
					else{
						AlertDialog.Builder alertDialog=new AlertDialog.Builder(getApplicationContext())
						.setTitle("µÇÂ¼Ê§°Ü")
						.setMessage("ÓÃ»§Ãû»òÃÜÂë´íÎó");
						alertDialog.show();
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	private void checkData() {
		SharedPreferences sp=getSharedPreferences("weChatDatas", MODE_PRIVATE);
		name=sp.getString("name", "");
		password=sp.getString("password", "");
		editName.setText(name);
		editPassword.setText(password);
	}
}

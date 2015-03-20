package com.example.mywechat.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtil {
	public static final String BASE_URL = "http://192.168.1.110:9009/WeChatServer/"; 
	private static HttpClient client = new DefaultHttpClient();
	public static final String LOGIN_SUCCESS = "1";
	public static final String LOGIN_FAIL = "0";

	private HttpUtil() {

	}

	//测试通过HttpClient来传递数据
	public static String sendByHttpClient(Context context, String sUrl) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		String result = null;
		if (networkInfo != null && networkInfo.isConnected()) {
			// TODO Auto-generated method stub
			//将url封装成一个HttpGet请求
			HttpGet httpGet = new HttpGet(sUrl);
			
			//HttpPost httpPost = new HttpPost(sUrl);
			try {
				HttpResponse response = client.execute(httpGet);
				//请求成功  
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					//取得返回的字符串  
					result = EntityUtils.toString(response.getEntity());
				} else {
					result = "请求错误";
				}
			} catch (Exception e) {
				e.printStackTrace();
				result = e.toString();
			}
		} else {
			result = "网络连接有误";
		}

		return result;
	}

}

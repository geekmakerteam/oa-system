package org.geeklub.http;

import android.content.Context;

import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

public class OaSyncHttpClient {
	//	private static final String BASE_URL = "http://geekmakerteam.duapp.com/";
	private static final String BASE_URL = "http://192.168.137.158:8080/oa-system/";



	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}







	private static SyncHttpClient syncHttpClient = new SyncHttpClient(){
		@Override
		public String onRequestFailed(Throwable arg0, String arg1) {
			return arg1;
		}
	};




	public static  String get(String url,Context context){
		PersistentCookieStore cookieStore = new PersistentCookieStore(context);
		syncHttpClient.setCookieStore(cookieStore);
		return syncHttpClient.get(getAbsoluteUrl(url));
	}

	public static String post(String url,RequestParams requestParams,Context context){
		PersistentCookieStore cookieStore = new PersistentCookieStore(context);
		syncHttpClient.setCookieStore(cookieStore);
		return syncHttpClient.post(getAbsoluteUrl(url), requestParams);
	}

}

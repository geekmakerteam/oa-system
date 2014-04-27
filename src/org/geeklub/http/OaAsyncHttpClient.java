package org.geeklub.http;



import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class OaAsyncHttpClient {
	//	private static final String BASE_URL = "http://geekmakerteam.duapp.com/";

	private static final String BASE_URL = "http://192.168.137.158:8080/oa-system/";

	private static AsyncHttpClient client = new AsyncHttpClient();



	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler,Context context) {
		PersistentCookieStore cookieStore = new PersistentCookieStore(context);
		client.setCookieStore(cookieStore);
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * 用来二维码签到的方法
	 * @param url
	 * @param params
	 * @param responseHandler
	 * @param context
	 */
	public static void getQR_CODE(String url, RequestParams params, AsyncHttpResponseHandler responseHandler,Context context){
		PersistentCookieStore cookieStore = new PersistentCookieStore(context);
		client.setCookieStore(cookieStore);
		client.get(url, params, responseHandler);
	}

	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler,Context context) {
		PersistentCookieStore cookieStore = new PersistentCookieStore(context);
		client.setCookieStore(cookieStore);
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
}

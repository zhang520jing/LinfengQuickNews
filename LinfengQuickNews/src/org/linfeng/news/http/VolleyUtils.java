package org.linfeng.news.http;

import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.util.Log;

/**
 * 重写JsonRequest的方法  
 * 对返回数据的编码的转换
 * @author Administrator
 *
 */
public class VolleyUtils {
	public  static void getVolley(String url,Context context,final ResponseData responseData){
		RequestQueue requestQueue=Volley.newRequestQueue(context);
		JsonObjectRequest request=new JsonObjectRequest(url, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				  Log.d("TAG", error.getMessage(), error);
				
			}
		}){
			@Override
			protected Response<JSONObject> parseNetworkResponse(
					NetworkResponse response) {
				try {
					JSONObject jsonObject=new JSONObject(new String(response.data,"UTF-8"));
					responseData.getResonseData(0, jsonObject.toString());
					return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
				} catch (Exception e) {
					return Response.error(new ParseError(e));
				}
			}
		};
		requestQueue.add(request);
	}
}

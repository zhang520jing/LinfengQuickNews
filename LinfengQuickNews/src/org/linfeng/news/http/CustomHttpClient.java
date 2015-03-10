package org.linfeng.news.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.linfeng.news.util.LogUtils2;

import android.content.Context;

public class CustomHttpClient {
	public  static String TAG="CustomHttpClient";
	public static final String CHARSET_UTF8=HTTP.UTF_8;
	public static final String CHARSET_GB2312 = "GB2312";
	private static DefaultHttpClient customerHttpClient;
    private static CookieStore cookieStore;
    
    public static CookieStore getCookieStore() {
		return cookieStore;
	}
    
    private  CustomHttpClient() {
    	
	}
    public  static String postFromWebByHttpClient(Context context,String url,NameValuePair...nameValuePairs)throws Exception{
    	try {
			ArrayList<NameValuePair>params=new ArrayList<NameValuePair>();
			if(nameValuePairs!=null){
				for(NameValuePair nameValuePair:nameValuePairs){
					params.add(nameValuePair);
				}
			}
			UrlEncodedFormEntity urlEncodedFormEntity=new UrlEncodedFormEntity(params,CHARSET_GB2312);
			HttpPost httpPost=new HttpPost();
			httpPost.setEntity(urlEncodedFormEntity);
			DefaultHttpClient defaultHttpClient=getDefaultHttpClient(context);
			if(cookieStore!=null){
				defaultHttpClient.setCookieStore(cookieStore);
			}
			HttpResponse response=defaultHttpClient.execute(httpPost);
			if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
			      throw new RuntimeException("请求失败");
			}
			HttpEntity resEntity=response.getEntity();
			cookieStore=defaultHttpClient.getCookieStore();
			return (resEntity==null)?null:EntityUtils.toString(resEntity,CHARSET_UTF8);
			
		} catch (UnsupportedEncodingException e) {
			LogUtils2.w(TAG,"----UnsupportedEncodingException"
                    + e.getMessage());
		} catch (ClientProtocolException e) {
			LogUtils2.w(TAG,"----ClientProtocolException"
                    + e.getMessage());
		} catch (IOException e) {
			 LogUtils2.w(TAG, "----IOException" + e.getMessage());
		}
    	
    	return null;
    }
    /**
     * 构建DefaultClient  模拟火狐浏览器的请求
     * @param context
     * @return
     */
    public  static synchronized DefaultHttpClient getDefaultHttpClient(Context context){
    		HttpParams params=new BasicHttpParams();
        	//设置基本的参数
        	
        	HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        	HttpProtocolParams.setContentCharset(params, CHARSET_UTF8);
        	HttpProtocolParams.setUseExpectContinue(params, true);
        	HttpProtocolParams.setUserAgent(params,   "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
                    + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
        	//超时的设置
        	//从连接池取得连接超时
        	ConnManagerParams.setTimeout(params, 10000);
        	/*连接超时*/
        	int connectionTimeOut=30000;
        	if(!HttpUtil.isWifiDataEnable(context)){
        		connectionTimeOut=1000000;
        	}
        	HttpConnectionParams.setConnectionTimeout(params, connectionTimeOut);
        	/*
        	 * 请求超时
        	 */
        	HttpConnectionParams.setSoTimeout(params, 40000);
        	//设置我们的HttpClient支持Http和Http两种模式
        	SchemeRegistry scRegistry=new SchemeRegistry();
        	scRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        	scRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        	//使用线程安全的连接管理来创建HttpClient
        	ClientConnectionManager conManager=new ThreadSafeClientConnManager(params, scRegistry);
        	customerHttpClient=new  DefaultHttpClient(conManager, params);
    	
    	
    	return customerHttpClient;
    	
    }
    public static String getFromWebByHttpClient(Context context, String url,
            NameValuePair... nameValuePairs)throws Exception{
    	  // http地址
        // String httpUrl =
        // "http://192.168.1.110:8080/httpget.jsp?par=HttpClient_android_Get";
    	StringBuilder sb=new StringBuilder();
    	sb.append(url);
    	if(nameValuePairs!=null&&nameValuePairs.length>0){
    		sb.append("?");
    		  for (int i = 0; i < nameValuePairs.length; i++) {
                  if (i > 0) {
                      sb.append("&");
                  }
                  sb.append(String.format("%s=%s",
                          nameValuePairs[i].getName(),
                          nameValuePairs[i].getValue()));
              }
    	}
    	//HttpGet连接对象
    	HttpGet httpGet=new HttpGet(sb.toString());
    	//取得httpClient对象
    	DefaultHttpClient httpClient=getDefaultHttpClient(context);
    	if(cookieStore!=null){
    		httpClient.setCookieStore(cookieStore);
    	}
        // 请求HttpClient，取得HttpResponse
    	HttpResponse httpResponse=httpClient.execute(httpGet);
    	//请求成功
    	if(httpResponse.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
    		  throw new RuntimeException(
    				  "请求失败");
    	}
    	  cookieStore = httpClient.getCookieStore();
    	  String content=EntityUtils.toString(httpResponse.getEntity());
          LogUtils2.e("content=="+content);
    	return content;
    }
    
    
}

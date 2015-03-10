package org.linfeng.news.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

public class NetWorkHelper {
	 private static String LOG_TAG = "NetWorkHelper";
	/**
	 * 获取网络连接管理
	 * @param context
	 * @return
	 */
	public static ConnectivityManager getConnectiveManager(Context context){
		return  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
	
	/**
	 * 判断网络连接是否可用
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkAviable(Context context){
		ConnectivityManager connManager=getConnectiveManager(context);
		if(connManager!=null){
			NetworkInfo[]infos=connManager.getAllNetworkInfo();
			if(infos!=null){
				for(NetworkInfo info:infos){
					if(info.getState()==NetworkInfo.State.CONNECTED){
						return true;
					}
				}
			}
		}
		return false;
	}
	  public static boolean isNetworkRoaming(Context context) {
	        ConnectivityManager connectivity =getConnectiveManager(context);
	        if (connectivity == null) {
	            Log.w(LOG_TAG, "couldn't get connectivity manager");
	        } else {
	            NetworkInfo info = connectivity.getActiveNetworkInfo();
	            if (info != null
	                    && info.getType() == ConnectivityManager.TYPE_MOBILE) {
	                TelephonyManager tm = (TelephonyManager) context
	                        .getSystemService(Context.TELEPHONY_SERVICE);
	                if (tm != null && tm.isNetworkRoaming()) {
	                    Log.d(LOG_TAG, "network is roaming");
	                    return true;
	                } else {
	                    Log.d(LOG_TAG, "network is not roaming");
	                }
	            } else {
	                Log.d(LOG_TAG, "not using mobile network");
	            }
	        }
	        return false;
	    }
	  /**
	     * 判断MOBILE网络是否可用
	     * 
	     * @param context
	     * @return
	     * @throws Exception
	     */
	    public static boolean isMobileDataEnable(Context context) throws Exception {
	        ConnectivityManager connectivityManager =getConnectiveManager(context);

	       if(connectivityManager!=null){
	    	   return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
	       }

	        return false;
	    }

	    /**
	     * 判断wifi 是否可用
	     * 
	     * @param context
	     * @return
	     * @throws Exception
	     */
	    public static boolean isWifiDataEnable(Context context) throws Exception {
	        ConnectivityManager connectivityManager =getConnectiveManager(context);
	        if(connectivityManager!=null){
	        	return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
	        }
	        return false;
	    }
}

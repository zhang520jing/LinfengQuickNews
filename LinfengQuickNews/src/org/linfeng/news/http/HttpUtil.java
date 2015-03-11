package org.linfeng.news.http;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;

public class HttpUtil {
	
	private static String TAG="HttpUtil";
	   // ���������ж�
	
    // �������Ӳ���

    public static String postByHttpURLConnection(String strUrl,
            NameValuePair... nameValuePairs) {
        return CustomHttpURLConnection.PostFromWebByHttpURLConnection(strUrl,
                nameValuePairs);
    }

    public static String getByHttpURLConnection(String strUrl,
            NameValuePair... nameValuePairs) {
        return CustomHttpURLConnection.GetFromWebByHttpUrlConnection(strUrl,
                nameValuePairs);
    }

    public static String postByHttpClient(Context context, String strUrl,
            NameValuePair... nameValuePairs) throws Exception {
        String result = CustomHttpClient.postFromWebByHttpClient(context, strUrl, nameValuePairs);
        return result;
    }

    public static String getByHttpClient(Context context, String strUrl,
            NameValuePair... nameValuePairs) throws Exception {
        String result = CustomHttpClient.getFromWebByHttpClient(context, strUrl, nameValuePairs);
        return result;
    }
    /**
     * �ж��Ƿ�������
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
			return NetWorkHelper.isMobileDataEnable(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }

    /**
     * �ж�mobile�����Ƿ����
     */
    public static boolean isMobileDataEnable(Context context) {
        String TAG = "httpUtils.isMobileDataEnable()";
        try {
            return NetWorkHelper.isMobileDataEnable(context);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * �ж�wifi�����Ƿ����
     */
    public static boolean isWifiDataEnable(Context context) {
        String TAG = "httpUtils.isWifiDataEnable()";
        try {
            return NetWorkHelper.isWifiDataEnable(context);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * �ж��Ƿ�Ϊ����
     */
    public static boolean isNetworkRoaming(Context context) {
        return NetWorkHelper.isNetworkRoaming(context);
    }

}

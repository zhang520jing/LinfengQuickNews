package org.linfeng.news.http.json;

import org.json.JSONObject;

import android.content.Context;

public abstract class JsonPacket {
	private  final Context mContext;
	
	public JsonPacket(Context context) {
		this.mContext=context;
	}
	/**
	 * 
	 * @param key
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 */
	public  static String getString(String key,JSONObject jsonObject)throws Exception{
		String res="";
		if(jsonObject.has(key)){
			if(key==null){
				return "";
			}
			res=jsonObject.getString(key);
		}
		return res;		
	}
	
	public static int getInt(String key, JSONObject jsonObject)throws Exception{
		int res=-1;
		if(jsonObject.has(key)){
			res=jsonObject.getInt(key);
		}
		return res;
		
	}
	 /**
     * @param key
     * @param jsonObject
     * @return
     * @throws Exception
     */
    public static double getDouble(String key, JSONObject jsonObject) throws Exception {
        double res = 0l;
        if (jsonObject.has(key)) {
        	
            res = jsonObject.getDouble(key);
        }
        return res;
    }
	
}

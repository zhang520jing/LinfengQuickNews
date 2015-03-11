package org.linfeng.news.http.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.linfeng.news.bean.NewDetailModel;

import android.content.Context;

public class NewDetailJson extends JsonPacket {
	public static NewDetailJson newDetailJson;
	public NewDetailModel  newDetailModle;
	private  NewDetailJson(Context context) {
		super(context);
	}
	public NewDetailModel readJsonNewModels(String res, String newId){
		  try {
	            if (res == null || res.equals("")) {
	                return null;
	            }
	            JSONObject jsonObject = new JSONObject(res).getJSONObject(newId);
	            newDetailModle = readNewModel(jsonObject);
	        } catch (Exception e) {

	        } finally {
	            System.gc();
	        }
	        return newDetailModle;
		
	}
	public  static NewDetailJson getInstance(Context context){
		if(newDetailJson==null){
			newDetailJson=new NewDetailJson(context);
		}
		return newDetailJson;
	}
	public  NewDetailModel readNewModel(JSONObject jsonObject) throws Exception{
        NewDetailModel newDetailModle = null;
        String docid = "";
        String title = "";
        String source = "";
        String ptime = "";
        String body = "";
        String url_mp4 = "";
        String cover = "";
        
        docid = getString("docid", jsonObject);
        title = getString("title", jsonObject);
        source = getString("source", jsonObject);
        ptime = getString("ptime", jsonObject);
        body = getString("body", jsonObject);
        if(jsonObject.has("video")){
        	JSONObject jsonObje = jsonObject.getJSONArray("video").getJSONObject(0);
        	 url_mp4 = getString("url_mp4", jsonObje);
             cover = getString("cover", jsonObje);
        }
        JSONArray jsonArray = jsonObject.getJSONArray("img");
        List<String> imgList = readImgList(jsonArray);
        newDetailModle = new NewDetailModel();

        newDetailModle.setDocid(docid);
        newDetailModle.setImgList(imgList);
        newDetailModle.setPtime(ptime);
        newDetailModle.setSource(source);
        newDetailModle.setTitle(title);
        newDetailModle.setBody(body);
        newDetailModle.setUrl_mp4(url_mp4);
        newDetailModle.setCover(cover);
		return newDetailModle;
	}
	private List<String> readImgList(JSONArray jsonArray) throws JSONException, Exception {
		ArrayList<String>imgList=new ArrayList<String>();
		for(int i=0;i<jsonArray.length();i++){
			imgList.add(getString("src", jsonArray.getJSONObject(i)));
		}
		return imgList;
	}
	
	

}

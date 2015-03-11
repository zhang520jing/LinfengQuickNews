package org.linfeng.news.http.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.linfeng.news.bean.PhotoModle;

import android.content.Context;

public class PhotoListJson extends JsonPacket {
    public List<PhotoModle> photoModles = new ArrayList<PhotoModle>();
    public static PhotoListJson photoListJso;   
	private  PhotoListJson(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	  public static PhotoListJson instance(Context context) {
	        if (photoListJso == null) {
	            photoListJso = new PhotoListJson(context);
	        }
	        return photoListJso;
	    }
	   public List<PhotoModle> readJsonPhotoListModles(String res) {
	        photoModles.clear();
	        try {
	            if (res == null || res.equals("")) {
	                return null;
	            }
	            PhotoModle photoModle = null;
	            JSONArray jsonArray = new JSONArray(res);
	            for (int i = 0; i < jsonArray.length(); i++) {
	                photoModle = readJsonPhotoModle(jsonArray.getJSONObject(i));
	                photoModles.add(photoModle);
	            }
	        } catch (Exception e) {

	        } finally {
	            System.gc();
	        }
	        return photoModles;
	    }

	  private PhotoModle readJsonPhotoModle(JSONObject jsonObject) throws Exception {

	        PhotoModle photoModle = null;

	        String setid = "";
	        String seturl = "";
	        String clientcover = "";
	        String setname = "";

	        setid = getString("setid", jsonObject);
	        seturl = getString("seturl", jsonObject);
	        clientcover = getString("clientcover1", jsonObject);
	        setname = getString("datetime", jsonObject);

	        setname = setname.split(" ")[0];

	        photoModle = new PhotoModle();

	        photoModle.setClientcover(clientcover);
	        photoModle.setSetid(setid);
	        photoModle.setSetname(setname);
	        photoModle.setSeturl(seturl);

	        return photoModle;
	    }

}

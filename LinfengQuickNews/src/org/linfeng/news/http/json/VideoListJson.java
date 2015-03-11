package org.linfeng.news.http.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.linfeng.news.bean.VideoModle;

import android.content.Context;
import android.text.TextUtils;

public class VideoListJson extends JsonPacket {
	public static VideoListJson newListJson;

	public List<VideoModle> videoModles;

	public VideoListJson(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public static VideoListJson getInstance(Context context) {
		if (newListJson == null) {
			newListJson = new VideoListJson(context);
		}
		return newListJson;
	}

	private String getTitle(String title) {
		if (title.contains("&quot")) {
			title = title.replace("&quot;", "\"");
		}
		return title;
	}

	public List<VideoModle> readJsonVideoModles(String res, String value) {
		{
			videoModles = new ArrayList<VideoModle>();
			try {
				if (TextUtils.isEmpty(res)) {
					return null;
				}
				VideoModle videoModle = null;
				JSONObject jsonObject = new JSONObject(res);
				JSONArray jsonArray = jsonObject.getJSONArray(value);
				for (int i = 0; i < jsonArray.length(); i++) {
					videoModle = readVideoModle(jsonArray.getJSONObject(i));
					videoModles.add(videoModle);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				System.gc();
			}
		}
		return videoModles;
	}

	private VideoModle readVideoModle(JSONObject jsonObject) throws Exception {
		VideoModle videoModle = null;
		String vid = "";
		String title = "";
		int length = 0;
		// String cover = "";
		String mp4Hd_url = "";
		vid = getString("vid", jsonObject);
		title = getString("title", jsonObject);
		length = getInt("length", jsonObject);
		// cover = getString("cover", jsonObject);
		mp4Hd_url = getString("mp4_url", jsonObject);
		videoModle = new VideoModle();
		if (length == -1) {
			videoModle.setTitle(getString("sectiontitle", jsonObject));
			videoModle.setLength(getTitle(title));
		} else {
			videoModle.setLength(getTime(length));
			videoModle.setTitle(getTitle(title));
		}
		videoModle.setMp4Hd_url(mp4Hd_url);
		videoModle.setTitle(vid);
		return videoModle;
	}

	private String getTime(int length) {
		int fen = length / 60;
		int miao = length % 60;
		String fenString = fen + "";
		String miaoString = miao + "";
		return fenString + ":" + miaoString;
	}

}

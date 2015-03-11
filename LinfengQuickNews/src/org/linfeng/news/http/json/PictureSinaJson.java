package org.linfeng.news.http.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.linfeng.news.bean.PicuterDetailModle;
import org.linfeng.news.bean.PicuterModle;

import android.content.Context;

public class PictureSinaJson extends JsonPacket {
	private final List<PicuterModle> PicuterModles = new ArrayList<PicuterModle>();

	private List<PicuterDetailModle> picuterDetailModles;

	public static PictureSinaJson picuterJson;

	private String title;

	private PictureSinaJson(Context context) {
		super(context);
	}
	
	
	public static PictureSinaJson instance(Context context) {
		if (picuterJson == null) {
			picuterJson = new PictureSinaJson(context);
		}
		return picuterJson;
	}
	 /**
     * �õ�ͼƬ��������
     * 
     * @param jsonObject
     * @return
     * @throws Exception
     */
    private PicuterDetailModle readJsonPicuterDetailModle(JSONObject jsonObject) throws Exception {

        PicuterDetailModle picuterDetailModle = null;

        String pic = "";
        String alt = "";

        pic = getString("pic", jsonObject);
        alt = getString("alt", jsonObject);

        picuterDetailModle = new PicuterDetailModle();

        picuterDetailModle.setAlt(alt);
        picuterDetailModle.setPic(pic);
        picuterDetailModle.setTitle(title);

        return picuterDetailModle;
    }
    /**
	 * �õ�ͼƬ��ϸ���б�
	 * @param res
	 * @return
	 */
	public List<PicuterDetailModle> readJsonPicuterModle(String res) {
	        picuterDetailModles = new ArrayList<PicuterDetailModle>();
	        try {
	            if (res == null || res.equals("")) {
	                return null;
	            }
	            PicuterDetailModle picuterDetailModle = null;
	            JSONObject jsonObject = new JSONObject(res).getJSONObject("data");
	            title = getString("title", jsonObject);
	            JSONArray jsonArray = jsonObject.getJSONArray("pics");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                picuterDetailModle = readJsonPicuterDetailModle(jsonArray.getJSONObject(i));
	                picuterDetailModles.add(picuterDetailModle);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            System.gc();
	        }
	        return picuterDetailModles;
	    }
	 /**
     * �õ�ͼƬ�б�
     * 
     * @param res
     * @return
     */
    public List<PicuterModle> readJsonPhotoListModles(String res) {
        PicuterModles.clear();
        try {
            if (res == null || res.equals("")) {
                return null;
            }
            PicuterModle PicuterModle = null;
            JSONArray jsonArray = new JSONObject(res).getJSONObject("data").getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                PicuterModle = readJsonPictureModel(jsonArray.getJSONObject(i));
                PicuterModles.add(PicuterModle);
            }
        } catch (Exception e) {

        } finally {
            System.gc();
        }
        return PicuterModles;
    }
	/**
	 * ͼƬ�б�����
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 */
	private PicuterModle readJsonPictureModel(JSONObject jsonObject) throws Exception {
		PicuterModle picuterModle = null;

		String id = "";
		String title = "";
		String pic = "";
	    id = getString("id", jsonObject);
        title = getString("title", jsonObject);
        pic = getString("pic", jsonObject);

        picuterModle = new PicuterModle();

        picuterModle.setId(id);
        picuterModle.setPic(pic);
        picuterModle.setTitle(title);

        return picuterModle;
	}

}

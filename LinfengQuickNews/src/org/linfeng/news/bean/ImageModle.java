package org.linfeng.news.bean;

import java.util.List;

import org.linfeng.news.adapter.BaseModel;

public class ImageModle extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   /**
     * docid
     */
    private String docid;
    /**
     * title
     */
    private String title;
    /**
     * ͼƬ��
     */
    private List<String> imgList;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}

package org.linfeng.news.bean;

import org.linfeng.news.adapter.BaseModel;

public class NewModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * docid
     */
    private String docid;
    /**
     * ����
     */
    private String title;
    /**
     * С����
     */
    private String digest;
    /**
     * ͼƬ��ַ
     */
    private String imgsrc;
    /**
     * ��Դ
     */
    private String source;
    /**
     * ʱ��
     */
    private String ptime;
    /**
     * TAG
     */
    private String tag;
    /**
     * �б�
     */
    private ImageModle imagesModle;
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
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public ImageModle getImagesModle() {
		return imagesModle;
	}
	public void setImagesModle(ImageModle imagesModle) {
		this.imagesModle = imagesModle;
	}
    
}

package org.linfeng.news.bean;

import org.linfeng.news.wedget.city.ContactItemInterface;

public class CityItem implements ContactItemInterface {

	private String nickName;
	private String fullName;

	public CityItem(String nickName, String fullName) {
		super();
		this.nickName = nickName;
		this.setFullName(fullName);
	}

	@Override
	public String getItemForIndex() {
		// TODO Auto-generated method stub
		return fullName;
	}

	@Override
	public String getDisplayInfo() {
		// TODO Auto-generated method stub
		return nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}

package org.linfeng.news.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.linfeng.news.dao.ChannelDao;
import org.linfeng.news.db.SQLHelper;

import android.database.SQLException;
import android.util.Log;

public class ChanneManage {
	public static ChanneManage channeManage;
	/**
	 * Ĭ���û�ѡ���Ƶ���б�
	 */
	public static ArrayList<ChannelItem> defaultUserChannels;;
	/**
	 * Ĭ�ϵ�����Ƶ���б�
	 */
	public static ArrayList<ChannelItem> defaultOtherChannels;
	private ChannelDao channelDao;
	/*
	 * �ж����ݿ����Ƿ�����û�����
	 */
	private boolean userExist;
	static {
		defaultUserChannels = new ArrayList<ChannelItem>();
		defaultOtherChannels = new ArrayList<ChannelItem>();
		defaultUserChannels.add(new ChannelItem(1, "ͷ��", 1, 1));
		defaultUserChannels.add(new ChannelItem(2, "����", 2, 1));
		defaultUserChannels.add(new ChannelItem(3, "����", 3, 1));
		defaultUserChannels.add(new ChannelItem(4, "����", 4, 1));
		defaultUserChannels.add(new ChannelItem(5, "�ƾ�", 5, 1));
		defaultUserChannels.add(new ChannelItem(6, "�Ƽ�", 6, 1));
		// defaultUserChannels.add(new ChannelItem(7, "ͼƬ", 1, 0));
		defaultOtherChannels.add(new ChannelItem(7, "CBA", 1, 0));
		defaultOtherChannels.add(new ChannelItem(8, "Ц��", 2, 0));
		defaultOtherChannels.add(new ChannelItem(9, "����", 3, 0));
		defaultOtherChannels.add(new ChannelItem(10, "ʱ��", 4, 0));
		defaultOtherChannels.add(new ChannelItem(11, "����", 5, 0));
		defaultOtherChannels.add(new ChannelItem(12, "����", 6, 0));
		defaultOtherChannels.add(new ChannelItem(13, "����", 7, 0));
		defaultOtherChannels.add(new ChannelItem(14, "��Ϸ", 8, 0));
		defaultOtherChannels.add(new ChannelItem(15, "��ѡ", 9, 0));
		defaultOtherChannels.add(new ChannelItem(16, "��̨", 10, 0));
		defaultOtherChannels.add(new ChannelItem(17, "���", 11, 0));
		defaultUserChannels.add(new ChannelItem(18, "��Ӱ", 12, 0));
		defaultUserChannels.add(new ChannelItem(19, "NBA", 13, 0));
		defaultUserChannels.add(new ChannelItem());
		defaultUserChannels.add(new ChannelItem(21, "�ƶ�", 15, 0));
		defaultUserChannels.add(new ChannelItem(22, "��Ʊ", 16, 0));
		defaultUserChannels.add(new ChannelItem(23, "����", 17, 0));
		defaultUserChannels.add(new ChannelItem(24, "��̳", 18, 0));
		defaultOtherChannels.add(new ChannelItem(25, "����", 19, 0));
		defaultOtherChannels.add(new ChannelItem(26, "�ֻ�", 20, 0));
		defaultOtherChannels.add(new ChannelItem(27, "����", 21, 0));
		defaultOtherChannels.add(new ChannelItem(28, "���", 22, 0));
		defaultOtherChannels.add(new ChannelItem(29, "�Ҿ�", 23, 0));
		defaultOtherChannels.add(new ChannelItem(30, "��ѩ", 24, 0));
		defaultUserChannels.add(new ChannelItem(31, "����", 25, 0));
	}

	private ChanneManage(SQLHelper paramDBHelper) throws SQLException {
		if (channelDao == null) {
			channelDao = new ChannelDao(paramDBHelper.getContext());
		}

	}

	public static ChanneManage getManage(SQLHelper dbHelper) {
		if (channeManage == null)
			channeManage = new ChanneManage(dbHelper);
		return channeManage;
	}

	/**
	 * ������е�Ƶ��
	 */
	public void deleteAllChannel() {
		channelDao.clearFeedTable();
	}

	/**
	 * ��ȡ������Ƶ��
	 * 
	 * @return ���ݿ�����û����� ? ���ݿ��ڵ��û�ѡ��Ƶ�� : Ĭ���û�ѡ��Ƶ�� ;
	 */
	public ArrayList<ChannelItem> getUserChannel() {
		Object cacheList=channelDao.listCache(SQLHelper.SELECTED, new String[]{"1"});
		 if (cacheList != null && !((List<?>) cacheList).isEmpty()) {
	            userExist = true;
	            List<Map<String, String>> maplist = (List) cacheList;
	            int count = maplist.size();
	            List<ChannelItem> list = new ArrayList<ChannelItem>();
	            ChannelItem navigate=null;
	            for (int i = 0; i < count; i++) {
	                navigate= new ChannelItem();
	                navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
	                navigate.setName(maplist.get(i).get(SQLHelper.NAME));
	                navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
	                navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
	                list.add(navigate);
	            }
	            return (ArrayList<ChannelItem>) list;
	        }
		 initDefaultChannel();
		 return defaultUserChannels;
	}
	 /**
     * ��ȡ������Ƶ��
     * 
     * @return ���ݿ�����û����� ? ���ݿ��ڵ�����Ƶ�� : Ĭ������Ƶ�� ;
     */
	  public List<ChannelItem> getOtherChannel() {
	        Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?", new String[] {
	                "0"
	        });
	        List<ChannelItem> list = new ArrayList<ChannelItem>();
	        if (cacheList != null && !((List) cacheList).isEmpty()) {
	        	userExist=true;
	            List<Map<String, String>> maplist = (List) cacheList;
	            int count = maplist.size();
	            ChannelItem navigate=null;
	            for (int i = 0; i < count; i++) {
	                navigate= new ChannelItem();
	                navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
	                navigate.setName(maplist.get(i).get(SQLHelper.NAME));
	                navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
	                navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
	                list.add(navigate);
	            }
	            return list;
	        }
	        if (userExist) {
	            return list;
	        }
	        cacheList = defaultOtherChannels;
	        return (List<ChannelItem>) cacheList;
	    }
	  public void saveUserChannel(List<ChannelItem> userList) {
	        for (int i = 0; i < userList.size(); i++) {
	            ChannelItem channelItem = userList.get(i);
	            channelItem.setOrderId(i);
	            channelItem.setSelected(Integer.valueOf(1));
	            channelDao.addCache(channelItem);
	        }
	    }
	  /**
	     * ��������Ƶ�������ݿ�
	     * 
	     * @param otherList
	     */
	    public void saveOtherChannel(List<ChannelItem> otherList) {
	        for (int i = 0; i < otherList.size(); i++) {
	            ChannelItem channelItem = otherList.get(i);
	            channelItem.setOrderId(i);
	            channelItem.setSelected(Integer.valueOf(0));
	            channelDao.addCache(channelItem);
	        }
	    }
	    /**
	     * ��ʼ�����ݿ��ڵ�Ƶ������
	     */
	    private void initDefaultChannel() {
	        Log.d("deleteAll", "deleteAll");
	        deleteAllChannel();
	        saveUserChannel(defaultUserChannels);
	        saveOtherChannel(defaultOtherChannels);
	    }
	
}

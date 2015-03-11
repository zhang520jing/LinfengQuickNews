package org.linfeng.news.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.linfeng.news.bean.ChannelItem;
import org.linfeng.news.db.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ChannelDao implements ChannelDaoInface {
	private SQLHelper helper;

	public ChannelDao(Context context) {
		helper = new SQLHelper(context);
	}

	public void clearFeedTable() {
		String sql = "delete from " + SQLHelper.TABLE_CHANNEL;
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(sql);
		resertSeq();
	}

	private void resertSeq() {
		String sql = "update sqlite_sequence set seq=0 where name= "
				+ SQLHelper.TABLE_CHANNEL + "'";
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(sql);

	}

	@SuppressWarnings("unused")
	@Override
	public boolean addCache(ChannelItem item) {
		boolean flag = false;
		SQLiteDatabase database = null;
		long id = -1;
		try {
			database = helper.getWritableDatabase();
			database.beginTransaction();
			ContentValues values = new ContentValues();
			java.lang.Class<? extends ChannelItem> claszz = item.getClass();
			String tableName = claszz.getSimpleName();
			Method[] methods = claszz.getMethods();
			for (Method method : methods) {
				String mName = method.getName();
				if (mName.startsWith("get") && !mName.startsWith("getClass")) {
					String fieldName = mName.substring(3, mName.length())
							.toLowerCase();
					Object value = method.invoke(item, null);
					if (value instanceof String) {
						values.put(fieldName, (String) value);
					}
				}

			}
			id = database.insert(tableName, null, values);
			flag = (id != -1 ? true : false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.endTransaction();
				database.close();
			}
		}
		return flag;
	}

	@Override
	public boolean deleteCache(String whereClause, String[] whereArgs) {
		boolean flag = false;
		SQLiteDatabase database = null;
		int count = 0;
		try {
			database = helper.getWritableDatabase();
			database.beginTransaction();
			count = database.delete(SQLHelper.TABLE_CHANNEL, whereClause,
					whereArgs);
			flag = (count > 0 ? true : false);
			database.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.endTransaction();
				database.close();
			}
		}
		return flag;
	}

	@Override
	public boolean updateCache(ContentValues values, String whereClause,
			String[] whereArgs) {
		boolean flag = false;
		SQLiteDatabase database = null;
		int count = 0;
		try {
			database = helper.getWritableDatabase();
			count = database.update(SQLHelper.TABLE_CHANNEL, values,
					whereClause, whereArgs);
			flag = (count > 0 ? true : false);
			database.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			if (database != null) {
				database.endTransaction();
				database.close();
			}
		}
		return flag;
	}

	@Override
	public Map<String, String> viewCache(String selection,
			String[] selectionArgs) {
		SQLiteDatabase database = null;
		Cursor cursor = null;

		Map<String, String> map = new HashMap<String, String>();
		try {
			database = helper.getReadableDatabase();
	        database.beginTransaction();
			cursor = database.query(true, SQLHelper.TABLE_CHANNEL, null,
					selection, selectionArgs, null, null, null, null);
			int cols_len = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				for (int i = 0; i < cols_len; i++) {
					String cols_name = cursor.getColumnName(i);
					String cols_values = cursor.getString(cursor
							.getColumnIndex(cols_name));
					if (cols_values == null) {
						cols_values = "";
					}
					map.put(cols_name, cols_values);
				}
			}
		} catch (Exception e) {
		} finally {
			if (database != null) {
                database.endTransaction();
				cursor.close();
				database.close();
			}
		}
		return map;
	}

	@Override
	public List<Map<String, String>> listCache(String selection,
			String[] selectionArgs) {
		 SQLiteDatabase database = null;
	        Cursor cursor = null;
	        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	        try {
	            database = helper.getReadableDatabase();
	            database.beginTransaction();
	            cursor = database.query(false, SQLHelper.TABLE_CHANNEL, null, selection, selectionArgs,
	                    null, null, null, null);
	            int cols_len = cursor.getColumnCount();
	            while (cursor.moveToNext()) {
	                Map<String, String> map = new HashMap<String, String>();
	                for (int i = 0; i < cols_len; i++) {

	                    String cols_name = cursor.getColumnName(i);
	                    String cols_values = cursor.getString(cursor
	                            .getColumnIndex(cols_name));
	                    if (cols_values == null) {
	                        cols_values = "";
	                    }
	                    map.put(cols_name, cols_values);
	                }
	                list.add(map);
	            }

	            database.setTransactionSuccessful();
	        } catch (Exception e) {
	        } finally {
	            if (database != null) {
	                database.endTransaction();
	                cursor.close();
	                database.close();
	            }
	        }
	        return list;
	}

}

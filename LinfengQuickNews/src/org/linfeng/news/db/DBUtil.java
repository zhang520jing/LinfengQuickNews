package org.linfeng.news.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	private static DBUtil mInstance;
	private SQLHelper mSQLHelp;
	private SQLiteDatabase mSQLiteReadDatabase;
	private SQLiteDatabase mSqLiteWriteDatabase;

	private DBUtil(Context context) {
		mSQLHelp = new SQLHelper(context);
		mSQLiteReadDatabase = mSQLHelp.getReadableDatabase();
		mSqLiteWriteDatabase = mSQLHelp.getWritableDatabase();
	}

	public static DBUtil getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DBUtil(context);
		}
		return mInstance;
	}

	public void close() {
		mSQLiteReadDatabase.close();
		mSQLiteReadDatabase = null;
		mSqLiteWriteDatabase.close();
		mSqLiteWriteDatabase = null;
		mSQLHelp.close();
		mSQLHelp = null;
		mInstance = null;
	}

	public void insertData(ContentValues values) {
		mSqLiteWriteDatabase.insert(SQLHelper.TABLE_CHANNEL, null, values);
	}

	public void updateData(ContentValues values, String whereClause,
			String[] whereArgs) {
		mSqLiteWriteDatabase.update(SQLHelper.TABLE_CHANNEL, values,
				whereClause, whereArgs);
	}

	public void deleteData(String whereClause, String[] whereArgs) {
		mSqLiteWriteDatabase.delete(SQLHelper.TABLE_CHANNEL, whereClause,
				whereArgs);
	}

	public Cursor selectData(String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		Cursor cursor = mSQLiteReadDatabase.query(SQLHelper.TABLE_CHANNEL,
				columns, selection, selectionArgs, groupBy, having, orderBy);
		return cursor;
	}

}

package org.linfeng.news.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.text.format.DateFormat;

public class TimeUtils {
	public static String dateToWeek(int position){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.getDefault());
        Date currentDate = new Date();
	    List<String> list = new ArrayList<String>();
        Long fTime = currentDate.getTime();
        Date fdate=null;
        for (int a = 0; a < 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(sdf.format(fdate));
        }
        return list.get(position);
	    
        
	}
	   /**
     * @return
     */
    public static String getCurrentTime() {
        return getFormatDateTime(new Date(), "yyyy��M��dd��");
    }

    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }
    public static String getLocalTime(Context context,String time){
        // ȡ�������������Ƚ��ַ�������
    	  String str_curTime = DateFormat.format("yyyy-MM-dd", new Date()).toString();
    	  int result = str_curTime.compareTo(time.substring(0, time.indexOf(" ")));
    	  if (result > 0) {
              return "����"
                      + time.substring(time.indexOf(" "), time.lastIndexOf(":"));
          } else if (result == 0) {
              return "����"
                      + time.substring(time.indexOf(" "), time.lastIndexOf(":"));
          } else {
              return time;
          } 
    }
}

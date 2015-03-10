package org.linfeng.news.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Utils {
	
	public static void createDirs(File path){
		if (path != null && !path.exists()) {
			path.mkdirs();
		}
	}
	public static boolean  isFileExist(File file) {
		if (file != null && file.exists()) {
			return true;
		}
		return false;
	}
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f) - 15;
	}
	public static int px2sp(float pxValue, float fontScale) {
		return (int) (pxValue / fontScale + 0.5f);
	}
	public static int sp2px(float spValue, float fontScale) {
		return (int) (spValue * fontScale + 0.5f);
	}
	public static boolean sdCardIsAvailable() {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED))
			return false;
		return true;
	}
	public static boolean validateMobileNumber(String mobileNumber) {
		if (matchingText("^(13[0-9]|15[0-9]|18[7|8|9|6|5])\\d{4,8}$", mobileNumber)) {
			return true;
		}
		return false;
	}
	private static boolean matchingText(String expression, String text) {
		Pattern p = Pattern.compile(expression); 
		Matcher m = p.matcher(text); 
		boolean b = m.matches();
		return b;
	}
	public static String getVersionName(Context context) {
		try {
			ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			String name = appInfo.metaData.getString("version_name");
			if (name != null) {
				return name;
			}
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		}
		catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static int getChannel(Context context, String metaName) {
		try {
			ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			return appInfo.metaData.getInt(metaName);

		}
		catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public static int parseStr2Int(String str) {
		if (str == null) {
			return -1;
		}
		try {
			return Integer.parseInt(str);
		}
		catch (NumberFormatException e) {
			return -1;
		}
	}
	public static boolean isHexString(String str) {
		if (str == null) {
			return false;
		}
		return Pattern.matches("^[0-9a-fA-F]++$", str);
	}
	public static long parseStr2Long(String str) {
		if (str == null) {
			return -1;
		}
		try {
			return Long.parseLong(str);
		}
		catch (NumberFormatException e) {
			return -1;
		}
	}
	public static void hideSoftInput(EditText view, Context context) {
		InputMethodManager inputMeMana = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMeMana.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
	public static void showSoftInput(Context context) {
		InputMethodManager inputMeMana = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMeMana.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	public static int countWord(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int n = s.length(), a = 0, b = 0;
		int len = 0;	
		char c;
		for (int i = 0; i < n; i++) {
			c = s.charAt(i);
			if (Character.isSpaceChar(c)) {
				++b;
			}
			else if (isAscii(c)) {
				++a;
			}
			else {
				++len;
			}
		}
		return len + (int) Math.ceil((a + b) / 2.0);
	}
	public static boolean isAscii(char c) {
		return c <= 0x7f;
	}
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		}
		catch (Exception e) {
			flag = false;
		}

		return flag;
	}
	 public static String html2Text(String inputString) {  
	        String htmlStr = inputString; // 含html标签的字符串  
	        String textStr = "";  
	        java.util.regex.Pattern p_script;  
	        java.util.regex.Matcher m_script;  
	        java.util.regex.Pattern p_style;  
	        java.util.regex.Matcher m_style;  
	        java.util.regex.Pattern p_html;  
	        java.util.regex.Matcher m_html;  
	  
	        try {  
	            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>  
	                                                                                                        // }  
	            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>  
	                                                                                                    // }  
	            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
	  
	            p_script = java.util.regex.Pattern.compile(regEx_script,  
	                    java.util.regex.Pattern.CASE_INSENSITIVE);  
	            m_script = p_script.matcher(htmlStr);  
	            htmlStr = m_script.replaceAll(""); // 过滤script标签  
	  
	            p_style = java.util.regex.Pattern.compile(regEx_style,  
	                    java.util.regex.Pattern.CASE_INSENSITIVE);  
	            m_style = p_style.matcher(htmlStr);  
	            htmlStr = m_style.replaceAll(""); // 过滤style标签  
	  
	            p_html = java.util.regex.Pattern.compile(regEx_html,  
	                    java.util.regex.Pattern.CASE_INSENSITIVE);  
	            m_html = p_html.matcher(htmlStr);  
	            htmlStr = m_html.replaceAll(""); // 过滤html标签  
	  
	            textStr = htmlStr;  
	  
	        } catch (Exception e) {  
	            System.err.println("Html2Text: " + e.getMessage());  
	        }  
	  
	        return textStr;// 返回文本字符串  
	    }  
	 public static void saveBitmap(Bitmap bitmap, String filePath) {
			File file = new File(filePath);
			FileOutputStream out=null;
			try {
				out = new FileOutputStream(file);
				if (bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out)) {
					out.flush();
					out.close();
				}
			}
			catch (FileNotFoundException e) {
				LogUtils.printStackTrace(e);
			}
			catch (IOException e) {
				LogUtils.printStackTrace(e);
			}
		}
	 public static void downloadImageAndSave(String imgUrl, String filePath) {
			URL url=null;
			InputStream is = null;
			FileOutputStream fos = null;
			URLConnection conn=null;
			try {
				url = new URL(imgUrl);
				conn = url.openConnection();
				is = conn.getInputStream();
				fos = new FileOutputStream(new File(filePath));
				Utils.copyStream(is, fos);
			}
			catch (Exception e) {
				LogUtils.printStackTrace(e);
			}
			finally {
				try {
					is.close();
					fos.close();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		public static void copyStream(InputStream is, OutputStream os) throws IOException {
			if (is == null || os == null) {
				return;
			}
			BufferedInputStream bufIs;
			boolean shouldClose = false;
			if (is instanceof BufferedInputStream) {
				bufIs = (BufferedInputStream) is;
			}
			else {
				bufIs = new BufferedInputStream(is);
				shouldClose = true;
			}

			int bufLen = 102400;
			byte[] buf = new byte[bufLen];
			int len;
			while (true) {
				len = bufIs.read(buf);
				if (len < 0) {
					break;
				}
				os.write(buf, 0, len);
			}
			if (shouldClose) {
				bufIs.close();
			}
		}
		public static int getWinWidth(Activity context) {
			// TODO Auto-generated constructor stub
			return context.getWindowManager().getDefaultDisplay().getWidth();
		}
		public static int getWinHight(Activity context) {
			// TODO Auto-generated constructor stub
			return context.getWindowManager().getDefaultDisplay().getHeight();
		}
		public static int calculateCharLength(String src) {
			int counter = -1;
			if (src != null) {
				counter = 0;
				final int len = src.length();
				for (int i = 0; i < len; i++) {
					char sigleItem = src.charAt(i);
					if (isAlphanumeric(sigleItem)) {
						counter++;
					}
					else if (Character.isLetter(sigleItem)) {
						counter = counter + 2;
					}
					else {
						counter++;
					}
				}
			}
			else {
				counter = -1;
			}

			return counter;
		}
		public static boolean isAlphanumeric(char ch) {
			final int DIGITAL_ZERO = 0;
			final int DIGITAL_NINE = 9;
			final char MIN_LOWERCASE = 'a';
			final char MAX_LOWERCASE = 'z';
			final char MIN_UPPERCASE = 'A';
			final char MAX_UPPERCASE = 'Z';

			if ((ch >= DIGITAL_ZERO && ch <= DIGITAL_NINE) || (ch >= MIN_LOWERCASE && ch <= MAX_LOWERCASE)
					|| (ch >= MIN_UPPERCASE && ch <= MAX_UPPERCASE)) {
				return true;
			}
			else {
				return false;
			}
		}
		public static String unEscape(String src) {
			StringBuffer tmp = new StringBuffer();
			tmp.ensureCapacity(src.length());
			int lastPos = 0, pos = 0;
			char ch;
			while (lastPos < src.length()) {
				pos = src.indexOf("%", lastPos);
				if (pos == lastPos) {
					if (src.charAt(pos + 1) == 'u') {
						ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
						tmp.append(ch);
						lastPos = pos + 6;
					}
					else {
						ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
						tmp.append(ch);
						lastPos = pos + 3;
					}
				}
				else {
					if (pos == -1) {
						tmp.append(src.substring(lastPos));
						lastPos = src.length();
					}
					else {
						tmp.append(src.substring(lastPos, pos));
						lastPos = pos;
					}
				}
			}
			return tmp.toString();
		}
}

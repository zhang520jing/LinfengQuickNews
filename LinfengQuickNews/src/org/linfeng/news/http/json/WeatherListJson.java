package org.linfeng.news.http.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.linfeng.news.bean.WeatherModle;
import org.linfeng.news.util.TimeUtils;

import android.content.Context;

public class WeatherListJson extends JsonPacket {

	private WeatherListJson(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public List<WeatherModle> photoListModles = new ArrayList<WeatherModle>();

	public static WeatherListJson weatherListJson;
	
	public static WeatherListJson instance(Context context) {
		if (weatherListJson == null) {
			weatherListJson = new WeatherListJson(context);
		}
		return weatherListJson;
	}
	 public List<WeatherModle> readJsonPhotoListModles(String res) {
	        photoListModles.clear();
	        try {
	            if (res == null || res.equals("")) {
	                return null;
	            }
	            WeatherModle weatherModle = null;
	            JSONObject jsonObject = new JSONObject(res);
	            JSONObject jsonArray = jsonObject.getJSONObject("result").getJSONObject("future");
	            for (int i = 0; i < 7; i++) {
	                weatherModle = readJsonWeatherModle(jsonArray.getJSONObject("day_"
	                        + TimeUtils.dateToWeek(i)));
	                photoListModles.add(weatherModle);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            System.gc();
	        }
	        return photoListModles;
	    }
	 private WeatherModle readJsonWeatherModle(JSONObject jsonObject) throws Exception {

	        WeatherModle weatherModle = null;

	        String temperature = "";
	        String weather = "";
	        String wind = "";
	        String week = "";
	        String date = "";

	        temperature = getString("temperature", jsonObject);
	        weather = getString("weather", jsonObject);
	        wind = getString("wind", jsonObject);
	        week = getString("week", jsonObject);
	        date = getString("date", jsonObject);

	        weatherModle = new WeatherModle();

	        weatherModle.setDate(date);
	        weatherModle.setTemperature(temperature);
	        weatherModle.setWeather(weather);
	        weatherModle.setWeek(week);
	        weatherModle.setWind(wind);

	        return weatherModle;
	    }
	 
	 
}

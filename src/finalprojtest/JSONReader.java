package finalprojtest;
/**
 * @file JSONReader
 * @brief Module for reading JSON files from url 
 * @author Dimitri Tsampiras
 * @date April 7th
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {
	
	/**
	 * @brief Reads a Reader object and converts it to a string
	 * @param rd Reader object
	 * @return String version of reader file
	 * @throws IOException
	 */
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	/**
	 * @brief Readers url and converts it to a JSON object
	 * @param url JSON link for parsing
	 * @return JSONObject containing info from url
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is;
		try {
			is = new URL(url).openStream();
		} catch (Exception e) {
			return null;
		}
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	
	/**
	 * @brief Finds distance between two restaurant objects
	 * @param r1 restaurant object 1
	 * @param r2 restaurant object 2
	 * @return double representing distance in miles between restaurants
	 * @throws IOException
	 * @throws JSONException
	 */
	public static double getDistance(Restaurant r1, Restaurant r2) throws IOException, JSONException {
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + r1.urlFormat()
				+ "&destinations=" + r2.urlFormat() + "&key=AIzaSyAjIgYcy5QgLUQy-vPTSBXxKdH5TT6P4Fs";
		if (readJsonFromUrl(url) == null) {
			return Double.POSITIVE_INFINITY;
		}
		
		try {
			JSONObject json = readJsonFromUrl(url);
			String distance = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0)
					.getJSONObject("distance").get("text").toString();
			return Double.parseDouble(distance.substring(0, distance.indexOf(' ')));
		} catch (Exception e) {
			return -1.0;
		}
	}

}
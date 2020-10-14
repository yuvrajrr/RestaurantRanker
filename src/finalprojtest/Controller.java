package finalprojtest;
/**
 * @file Controller.java
 * @brief Controller Data Type
 * @author Dimitri Tsampiras 
 * @date April 8, 2020
 */


import java.io.IOException;
import java.util.*;

import org.json.JSONException;

/**
 * @brief Controller handles input from standard IO
 */
public class Controller {
	
	private static Scanner sc;
	
	/**
	 * @brief Prompts user for their initial restaurant choices
	 * @return array of strings based on that input
	 */
	public static String[] promptRestaurantInput() {
		sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(", ");
		return input;
	}
	
	/**
	 * @brief Prompts user for their alternate restaurant choices
	 * @return array of strings based on that input
	 */
	public static String[] promptAlternateInput() {
		sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(", ");
		return input;
	}
	
	/**
	 * @brief Prompts user for their final restaurant choices
	 * @return array of strings based on that input
	 */
	public static String[] promptFinalInput() {
		sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(", ");
		return input;
	}
	
	/**
	 * @brief prompts user for their location and determines if location is correct
	 * @return Restaurant object based on location for easy comparison
	 * @throws IOException
	 * @throws JSONException
	 */
	public static Restaurant promptLocation() throws IOException, JSONException {
		sc = new Scanner(System.in);
		String input = sc.nextLine();
		if (!validRestaurant(input)) return null;
		Restaurant location = Restaurant.convert(input);
		
		if (JSONReader.getDistance(location, location) == -1.0) {
			return null;
		}
		else return location;
	}
	
	/**
	 * @brief Checks if restaurant format is valid for Restaurant constructor
	 * @details Also ensures that restaurant 
	 * @param r string format of restaurant
	 * @return boolean based on validity of restaurant
	 */
	public static boolean validRestaurant(String r) {
		if (!r.contains(", ")) 
			return false;
		String[] addr = r.split(", ");
		if (!(addr[1].toUpperCase().equals("LA") || addr[1].toUpperCase().equals("LOS ANGELES"))) 
			return false;
		if (!(addr[2].toUpperCase().equals("CA"))) 
			return false;
		return true;
	}

}
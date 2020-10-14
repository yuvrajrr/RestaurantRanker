package finalprojtest;
/**
 * @file Restaurant.java
 * @brief Restaurant Data Type
 * @author Yuvraj Randhawa, Dimitri Tsampiras, Tevis Doe 
 * @date April 8, 2020
 */

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

/**
 * Restaurant Abstract Data Type
 */
public class Restaurant {

	/**
	 * Properties of a Restaurant object
	 */
	private String name;
	private ArrayList<String> violations;
	private String grade;
	private String address;
	private String city;
	private String state;
	private String zip;
	private int type;
	private int score;

	/**
	 * Restaurant contructor
	 * 
	 * @param n  facility name
	 * @param v  description of the violation
	 * @param g  letter grade score
	 * @param a  street address
	 * @param c  city
	 * @param s  state
	 * @param z  zip code
	 * @param t  type of restuarant
	 * @param sc rank of restaurant
	 */
	public Restaurant(String n, ArrayList<String> v, String g, String a, String c, String s, String z, int t, int sc) {
		name = n;
		violations = v;
		grade = g;
		address = a;
		city = c;
		state = s;
		zip = z;
		type = t;
		score = sc;
	}

	/**
	 * @brief Get name of restaurant.
	 * @return name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @brief Getter for the violations list.
	 * @return violations list.
	 */
	public ArrayList<String> getViolations() {
		return violations;
	}

	/**
	 * @brief Getter for grade field
	 * @return grade field
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @brief Getter for address field
	 * @return address field
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @brief Getter for city field
	 * @return city field
	 */
	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	/**
	 * @brief Getter for zip field
	 * @return zip field
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @brief Getter for type field
	 * @return type field
	 */
	public int getType() {
		return type;
	}

	/**
	 * @brief Getter for score field
	 * @return score field
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @brief compares two restaurant names
	 * @param n name of restaurant
	 * @return integer based on comparison of two restaurants names
	 */
	public int compareName(String n) {
		return name.compareTo(n);
	}

	/**
	 * @brief compares
	 * @param t type of restaurant
	 * @return boolean based on comparison of two restaurants types
	 */
	public boolean compareType(int t) {
		return type < t;
	}

	/**
	 * @brief compares
	 * @param sc score of restaurant
	 * @return boolean based on comparison of two restaurants ranks
	 */
	public boolean compareScore(int sc) {
		return score < sc;
	}

	/**
	 * @brief Converts address of restaurant to url format
	 * @return url format of address
	 */
	public String urlFormat() {
		String a = address.replace(" ", "+");
		String c = city.replace(" ", "+");
		return a + "+" + c + "+" + state;
	}

	/**
	 * @brief Local function for adding key to adjacency list
	 * @param key string of city
	 * @param e   edge object
	 */
	public static Restaurant convert(String address) {
		return new Restaurant("Your location", new ArrayList<String>(), "", address, "LOS ANGELES", "CA", "", 0, 0);
	}

	/**
	 * @brief Gets the closest restaurant
	 * @param rs list of restaurants
	 * @return restaurant object thats closest to reference
	 * @throws IOException
	 * @throws JSONException
	 */
	public Restaurant getClosest(ArrayList<Restaurant> rs) throws IOException, JSONException {
		double min = Integer.MAX_VALUE;
		Restaurant closest = null;
		for (Restaurant r : rs) {
			double d = JSONReader.getDistance(this, r);
			if (d < min) {
				min = d;
				closest = r;
			}
		}
		return closest;
	}

}
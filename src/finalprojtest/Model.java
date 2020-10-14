package finalprojtest;
/**
 * @file Model.java
 * @brief Model Data Type
 * @author Yuvraj Randhawa, Dimitri Tsampiras 
 * @date April 8, 2020
 */

// package finalprojtest;

import java.io.IOException;
import java.util.*;

import org.json.JSONException;

/**
 * @brief Model class that handles app control flow
 */
public class Model {

	ArrayList<Restaurant> allRestaurants;
	ArrayList<Restaurant> restaurantsByName;
	ArrayList<Restaurant> restaurantsByType;
	ArrayList<Restaurant> restaurantsByScore;
	ArrayList<Restaurant> choices = new ArrayList<Restaurant>();
	ArrayList<Restaurant> finalChoices = new ArrayList<Restaurant>();
	Restaurant startLocation;
	
	/**
	 * @brief Constructor for new Model
	 * @throws IOException
	 * @throws JSONException
	 */
	public Model() throws IOException, JSONException {
		View.startUpMessage();
		allRestaurants = csvFileReader.readRestaurantFile();
		restaurantsByName = Sort.sortByName(allRestaurants);
		restaurantsByType = Sort.sortByType(allRestaurants);
		restaurantsByScore = Sort.sortByScore(allRestaurants);
		allRestaurants.clear();
		run();
	}
	
	/**
	 * @brief Runs all 4 sections of program
	 * @throws IOException
	 * @throws JSONException
	 */
	public void run() throws IOException, JSONException {
		startUp();
		section1();
		section2();
		section3();
	}

	/**
	 * @brief Asks for users location
	 * @throws JSONException
	 * @throws IOException
	 */
	public void startUp() throws IOException, JSONException {
		View.chooseLocation();
		Restaurant r = Controller.promptLocation();
		while (r == null) {
			View.invalidLocation();
			View.chooseLocation();
			r = Controller.promptLocation();
		}
		startLocation = r;
	}

	/**
	 * @brief First part of program.
	 * @details Asks for users restaurants, stores them, and displays their scores
	 * @throws IOException
	 * @throws JSONException
	 */
	public void section1() {

		ArrayList<String> firstRestaurants = new ArrayList<String>();
		boolean start;
		do {
			View.chooseRestaurant();
			start = true;
			String[] input = Controller.promptRestaurantInput();
			for (String s : input) {
				if (BinarySearch.indexName(restaurantsByName, s.toUpperCase()) != -1
						&& !firstRestaurants.contains(s.toUpperCase())) {
					firstRestaurants.add(s.toUpperCase());
				} else {
					start = false;
					firstRestaurants.clear();
					View.invalidRestaurant();
					break;
				}
			}
		} while (!start);

		for (String s : firstRestaurants) {
			ArrayList<Restaurant> current = new ArrayList<Restaurant>();
			current = BinarySearch.nameList(restaurantsByName, s);
			choices.add(current.get(0));
			View.displayRestaurant(current.get(0));
		}
	}

	/**
	 * @brief Asks user for alternate Restaurants
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public void section2() throws IOException, JSONException {
		ArrayList<String> choicesStrings = new ArrayList<String>();

		for (Restaurant r : choices) {
			choicesStrings.add(r.getName());
			ArrayList<Restaurant> types = new ArrayList<Restaurant>();
			types = BinarySearch.typeList(restaurantsByType, r.getType());
			ArrayList<Restaurant> alternate = BinarySearch.altChoices(types, r);
			View.displayAlternates(alternate, r);
			for (Restaurant alternateChoices : alternate) {
				choicesStrings.add(alternateChoices.getName());
			}
		}

		ArrayList<String> finalRestaurants = new ArrayList<String>();
		boolean start;
		do {
			View.chooseFinal();
			start = true;
			String[] input = Controller.promptFinalInput();
			for (String s : input) {
				if (BinarySearch.indexName(restaurantsByName, s.toUpperCase()) != -1
						&& !finalRestaurants.contains(s.toUpperCase()) && choicesStrings.contains(s.toUpperCase())) {
					finalRestaurants.add(s.toUpperCase());
				} else {
					start = false;
					finalRestaurants.clear();
					View.invalidRestaurant();
					break;
				}
			}
		} while (!start);
		
		View.displayFinalSearch();
		int c = 0;
		for (String s : finalRestaurants) {
			ArrayList<Restaurant> current = new ArrayList<Restaurant>();
			current = BinarySearch.nameList(restaurantsByName, s);
			Restaurant closestOption = startLocation.getClosest(current);
			finalChoices.add(closestOption);
			View.loadingMessage(c);
			c += 1;
		}

	}

	/**
	 * @brief Section for building the graph and MST
	 * @throws IOException
	 * @throws JSONException
	 */
	public void section3() throws IOException, JSONException {
		Graph g = new Graph(finalChoices, startLocation);
		MST m = new MST(g);
		HashMap<String, Edge> path = m.findPath();
		View.displayFinal(path, startLocation.getName());

		
	}
	
	/**
	 * @brief Main for running program
	 * @param args
	 * @throws IOException
	 * @throws JSONException
	 */
	public static void main(String[] args) throws IOException, JSONException {
		Model m = new Model();
	}

}
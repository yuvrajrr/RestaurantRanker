package finalprojtest;

/**
 * @file Sort.java
 * @brief Restaurant Data Type
 * @author Yuvraj Randhawa, Dimitri Tsampiras 
 * @date April 8, 2020
 */
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @brief View class displays messages, prompts and results to the screen
 */
public class View {

	/**
	 * @brief Displays the loading messages before displaying the final route to take
	 * @param c An integer that represents which message to print
	 */
	public static void loadingMessage(int c) {
		ArrayList<String> words = new ArrayList<String>();
		words.add("...finding map...");
		words.add("...exploring paths...");
		words.add("...optimizing route...");
		words.add("...finalizing destination...");
		words.add("...hacking into the main frame...");
		words.add("...initalizing nuclear launch...");
		
		if (c < words.size()) System.out.println(words.get(c));
	}

	/**
	 * @brief Displays the message to input initial restaurant choices
	 */
	public static void chooseRestaurant() {
		System.out.println("\nEnter a list of restaurants seperated by a ',' (Burger King, Pizza Hut...):");
	}

	
	/**
	 * @brief Displays the message that and invalid restaurant was inputted
	 */
	public static void invalidRestaurant() {
		System.out.print("Invalid Input - Ensure restaurants are valid and not repeated\n");
		
	}

	/**
	 * @brief Displays thhe message for alternate restaurants
	 * @param r Restaurant object that alternates are being searched for
	 */
	public static void displayThisIsAlternate(Restaurant r){
		System.out.print("\nThese are the alternate options for " + r.getName() + ": \n");
	}

	/**
	 * @brief Displays all the fields of a restaurant
	 * @param r Restaurant object 
	 */
	public static void displayRestaurant(Restaurant r) {
        System.out.print(r.getName() + "   -->   " + "Score: " + r.getScore() + ", Grade: " + r.getGrade() + ", Violation: " + r.getViolations() + "\n");
    }

	/**
	 * @brief Displays that no alternate options are available 
	 * @param r Restaurant object
	 */
	public static void displayNoAlternates(Restaurant r){
		System.out.print("There are no alternate options for " + r.getName() + "\n");
	}

	/**
	 * @brief Displays the alternate options for a restaurant
	 * @param alternates An ArrayList of the alternate restaurants
	 * @param r The Restaurant object that alternates are being displayed for
	 */
	public static void displayAlternates(ArrayList<Restaurant> alternates, Restaurant r) {
		HashSet<String> visited = new HashSet<String>();
		if(alternates.size() > 0){
			displayThisIsAlternate(r);
			for(Restaurant a: alternates){
				if(!visited.contains(a.getName())){
					displayRestaurant(a);
					visited.add(a.getName());
				}
			}
			System.out.print("\n");
		}

		else{
			displayNoAlternates(r);
		}
	}

	/**
	 * @brief Displays a message to prompt input of the final restaurant choices
	 */
	public static void chooseFinal() {
		System.out.print("Enter your final restaurant choices sepereated by a ',' (Burger King, Pizza Hut...):\n");
		
	}

	/**
	 * @brief Displays a message to prompt the user to input their location
	 */
  	public static void chooseLocation() {
		System.out.print("Enter your current location in the format {address}, {city}, {state}\n");
		System.out.print("Note: Location must be in the Los Angeles, CA area.\n");
	}

	/**
	 * @brief Displays a message that says the inputted location is invalid
	 */
 	public static void invalidLocation() {
    System.out.print("Invalid Location - Ensure address is valid and inputted correctly\n");

  	}

	  /**
	   * @brief Displays a message while the final path is loading
	   */
  	public static void displayFinalSearch() {
    System.out.print("\nSEARCHING FOR OPTIMAL PATH\n");
  	}

	 
	  /**
	   * @brief Displays the results of the final path 
	   * @param path A HashMap of Strings to Edge objects that represents the optimal path
	   * @param start A String that represents the start location
	   */
 	public static void displayFinal(HashMap<String, Edge> path, String start) {
    	String current = start;
    	System.out.print("\n");
    	System.out.print(String.format("%-40s | %-40s | %-60s | %-20s \r\n", "Start", "To", "Address", "Distance (miles)"));
    	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    	while (path.get(current) != null) {
      		Edge location = path.get(current);
      		System.out.print(String.format("%-40s | %-40s | %-60s | %-20s \r\n", current, location.getTo(),
          	location.getAddress(), location.getDistance()));
      		System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
      		current = location.getTo();
    	}
  	}

	  /**
	   * @brief Displays the start message of the app
	   */
  	public static void startUpMessage() {
		System.out.print("    *---------------------------------------------------------------------------------------*\n");
		System.out.print("    |                         -Hello. Welcome to Restaurant Ranker-                         |\n");
		System.out.print("    |       Restaurant Ranker is designed to help you find restaurants in your area         |\n");
		System.out.print("    |       with the best health inspection score.                                          |\n");
		System.out.print("    *---------------------------------------------------------------------------------------*\n\n");
  	}

}
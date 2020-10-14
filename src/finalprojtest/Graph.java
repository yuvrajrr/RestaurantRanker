package finalprojtest;
/**
 * @file Graph.java
 * @brief Graph Data Type
 * @author Dimitri Tsampiras 
 * @date April 8, 2020
 */

import java.io.IOException;
import java.util.*;
import org.json.*;

public class Graph {

	private HashMap<String, ArrayList<Edge>> adj;
	private Restaurant start;

	/**
	 * @brief Constructor for graph object
	 * @param s array lists of restaurants of the same type
	 */
	public Graph(ArrayList<Restaurant> s, Restaurant a) throws IOException, JSONException {
		
		int c = 0;
		adj = new HashMap<String, ArrayList<Edge>>();
		start = a;
		
		for (Restaurant r : s) {
			String f = start.getName();
			String t = r.getName();
			double d = JSONReader.getDistance(start, r);
			addToHashMap(f, new Edge(f, t, d, r.getAddress()));
		}
		
		for (int i = 0; i < s.size(); i++) {
			for (int j = 0; j < s.size(); j++) {
				if (i != j) {
					String f = s.get(i).getName();
					String t = s.get(j).getName();
					double d = JSONReader.getDistance(s.get(i), s.get(j));
					String addr = s.get(j).getAddress();
					addToHashMap(f, new Edge(f, t, d, addr));
				}
			}
			String f = s.get(i).getName();
			String t = start.getName();
			double d = JSONReader.getDistance(s.get(i), start);
			String addr = start.getAddress();
			addToHashMap(f, new Edge(f, t, d, addr));
			View.loadingMessage(c++);
		}
	}

	/**
	 * @brief Local function for adding key to adjacency list
	 * @param key string of city
	 * @param e   edge object
	 */
	private void addToHashMap(String key, Edge e) {

		if (!adj.containsKey(key)) {
			adj.put(key, new ArrayList<Edge>(Arrays.asList(e)));
		} else {
			adj.get(key).add(e);
		}
	}

	/**
	 * @breif Method that returns the set of keys
	 * @return set of keys of HashMap
	 */
	public Set<String> getKeys() {
		return adj.keySet();
	}
	
	/**
	 * @brief To string function for debugging purposes
	 */
	public void display() { 
		for (String s : adj.keySet()) {
			System.out.print(s + " ----- ");
			for (Edge e : adj.get(s)) {
				System.out.print(e.getTo() + " " + e.getDistance() + "m  |  ");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Getter for adjacency list
	 * @return adj 
	 */
	public HashMap<String, ArrayList<Edge>> getMap() {
		return adj;
	}
	
	/**
	 * Getter for start location
	 * @return start 
	 */
	public String getStart() {
		return start.getName();
	}
	

}
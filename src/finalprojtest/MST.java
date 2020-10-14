package finalprojtest;
/**
 * @file MST.java
 * @brief MST Data Type
 * @author Yuvraj Randhawa 
 * @date April 8, 2020
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;

/**
 * @brief MST class that finds the shortest path to visit all nodes in a graph object
 */
public class MST {
    private HashMap<String, Edge> path = new HashMap<String, Edge>();
	private String start;
	private HashMap<String, ArrayList<Edge>> map;

	/**
	 * @brief Constructor for MST that initializes the state variables 
	 * @param g Graph that contains a start string and a HashMap that represents an adjacency list
	 */
	public MST(Graph g) {
		start = g.getStart();
		map = g.getMap();
	}

	/**
	 * @brief A modified kruskal's algorithm that finds the shortest path to visit all the restaurants 
	 * @return A HashMap of Strings to Edge objects where a string represents a location and the edge is the next location it visits
	 */
	public HashMap<String, Edge> findPath() {
		String current = start;
		boolean search = true;
		HashSet<String> visited = new HashSet<String>();
		while (search == true) {
			ArrayList<Edge> connected = map.get(current);
			int isMin = getMin(visited, connected);
			if (isMin != -1) {
				double min = connected.get(isMin).getDistance();
				Edge min_edge = connected.get(isMin);
				for (int i = 0; i < connected.size(); i++) {
					if (connected.get(i).getDistance() < min && !visited.contains(connected.get(i).getTo())) {
						min = connected.get(i).getDistance();
						min_edge = connected.get(i);
					}
				}
				path.put(current, min_edge);
				visited.add(current);
				current = min_edge.getTo();
			} else {
				search = false;
			}
		}
		return path;
	}

	/**
	 * @brief Sets the initial minimum value to later search for the smallest distance of a location to an edge object. 
	 * @param visited A HashSet of Strings that represents which locations have been visited
	 * @param nodes An ArrayList of Edge objects that represent edges on the graph 
	 * @return An integer that represents the index of where the inital value to be set as minimum is. A -1 is returned if there exists no value to be set as minimum because all edges have been visited
	 */
	private int getMin(HashSet<String> visited, ArrayList<Edge> nodes) {
		if (nodes.size() == 1 && !visited.contains(nodes.get(0).getTo())) {
			return 0;
		}
		for (int i = 0; i < nodes.size(); i++) {
			if (!visited.contains(nodes.get(i).getTo())) {
				return i;
			}
		}
		return -1;
	}

}


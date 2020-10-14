package finalprojtest;


/**
 * @file BinarySearch.java
 * @brief Static class for binary search
 * @author Yuvraj Randhawa, Dimitri Tsampiras
 * @date April 8, 2020
 */

import java.util.ArrayList;

/**
 * @brief BinarySearch Class
 */
public class BinarySearch {

  /**
   * @brief Returns the index where the String being searched for exists in the specified ArrayList.
   * @param a An ArrayList of Restaurant objects that must be sorted in ascending order by name
   * @param key the search key
   * @return index of key in array a if present; -1 otherwise
   */
  public static int indexName(ArrayList<Restaurant> a, String key) {
    int lo = 0;
    int hi = a.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      Restaurant r = a.get(mid);
      if (r.compareName(key) > 0)
        hi = mid - 1;
      else if (r.compareName(key) < 0)
        lo = mid + 1;
      else
        return mid;
    }
    return -1;
  }

    /**
   * @brief Returns the index where the integer being searched for exists in the specified ArrayList.
   * @param a The array of Restaurant objects that must be sorted in descending order by type
   * @param key The search key that is of type int
   * @return Index of key in array a if present; -1 otherwise
   */
  public static int indexType(ArrayList<Restaurant> a, int key) {
    int lo = 0;
    int hi = a.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      Restaurant r = a.get(mid);
      if (r.getType() == key)
        return mid;
      else if (r.compareType(key))
        hi = mid - 1;
      else
        lo = mid + 1;
    }
    return -1;
  }

  /**
   * @brief Returns an ArrayList of Restaurant objects that all have the same name
   * @param a An ArrayList of Restaurant objects sorted in ascending order by name
   * @param key A String of the restaurant name being searched for
   * @return An ArrayList of Restaurant objects that all have the same name
   */
  public static ArrayList<Restaurant> nameList(ArrayList<Restaurant> a, String key) {
    int start = indexName(a, key);
    ArrayList<Restaurant> nameRestaurantList = new ArrayList<Restaurant>();

    for (int i = start; i > -1; i--) {
      if (a.get(i).compareName(key) == 0) {
        nameRestaurantList.add(a.get(i));
      } else {
        break;
      }
    }

    for (int i = start + 1; i < a.size(); i++) {
      if (a.get(i).compareName(key) == 0) {
        nameRestaurantList.add(a.get(i));
      } else {
        break;
      }
    }

    return nameRestaurantList;
  }


 /**
   * @brief Returns an ArrayList of Restaurant objects that all have the same type
   * @param a An ArrayList of Restaurant objects sorted in descending order by type
   * @param key An integer of the type of Restaurant being searched for
   * @return An ArrayList of Restaurant objects that all have the same type
   */
  public static ArrayList<Restaurant> typeList(ArrayList<Restaurant> a, int key) {
    int start = indexType(a, key);
    ArrayList<Restaurant> typeRestaurantList = new ArrayList<Restaurant>();

    for (int i = start; i > -1; i--) {
      if (a.get(i).getType() == key) {
        typeRestaurantList.add(a.get(i));
      } else {
        break;
      }
    }

    for (int i = start + 1; i < a.size(); i++) {
      if (a.get(i).getType() == key) {
        typeRestaurantList.add(a.get(i));
      } else {
        break;
      }
    }

    return typeRestaurantList;
  }

   /**
   * @brief Returns an ArrayList of Restaurant objects that are the same type and have a greater or equal score than the inputted Restaurant object
   * @param types An ArrayList of Restaurant objects that all have the same type
   * @param names A Restaurant object that represents the Restaurant that alternatives are being searched for
   * @return An ArrayList of Restaurant objects that are the same type but have the same or greater score than the inputted Restaurant
   */
  public static ArrayList<Restaurant> altChoices(ArrayList<Restaurant> types, Restaurant names) {
    ArrayList<Restaurant> scoredTypes = Sort.sortByScore(types);
    int count = 0;
    int initialScore = names.getScore();
    String initialName = names.getName();
    ArrayList<Restaurant> alternate = new ArrayList<Restaurant>();
    if (scoredTypes.size() > 5) {
      for (int i = 0; i < scoredTypes.size(); i++) {
        if (scoredTypes.get(i).compareName(initialName) != 0 && !scoredTypes.get(i).compareScore(initialScore)
            && !alternate.contains(scoredTypes.get(i))) {
          alternate.add(scoredTypes.get(i));
          count += 1;
          if (count == 5) {
            break;
          }
        }
      }
    }

    else {
      for (int i = 0; i < scoredTypes.size(); i++) {
        if (scoredTypes.get(i).compareName(initialName) != 0 && !scoredTypes.get(i).compareScore(initialScore)
            && !alternate.contains(scoredTypes.get(i))) {
          alternate.add(scoredTypes.get(i));
        }
      }
    }

    return alternate;

  }

}
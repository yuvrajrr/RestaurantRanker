package finalprojtest;
/**
 * @file Sort.java
 * @brief Static class for quick sort
 * @author Yuvraj Randhawa
 * @date April 8, 2020
 */

import java.util.ArrayList;

/**
 * @brief Sort Module that implements three different quicksorts based on different parameters
 */
public class Sort {

  public static ArrayList<Restaurant> copyList( ArrayList<Restaurant> array2){
    ArrayList<Restaurant> array1 = new ArrayList<Restaurant>();
    for (int i = 0; i < array2.size(); i++) {
      Restaurant temp = new Restaurant(array2.get(i).getName(), array2.get(i).getViolations(), array2.get(i).getGrade(),
          array2.get(i).getAddress(), array2.get(i).getCity(), array2.get(i).getState(), array2.get(i).getZip(),
          array2.get(i).getType(), array2.get(i).getScore());
      array1.add(temp);
    }
    return array1;
  }

  /**
   * @brief Sorts the input read from the file by name of restaurants
   * @param info ArrayList of Restaurant objects
   */
  public static ArrayList<Restaurant> sortByName(ArrayList<Restaurant> info) {
    ArrayList<Restaurant> NameSorted = copyList(info);
    quickName(NameSorted, 0, info.size()-1);
    return NameSorted;
  }

  /**
   * @brief Sorts the input read from the file by the score of restaurants
   * @param info ArrayList of Restaurant objects 
   */
  public static ArrayList<Restaurant> sortByScore(ArrayList<Restaurant> info) {
    ArrayList<Restaurant> ScoreSorted = copyList(info);
    quickScore(ScoreSorted, 0, info.size()-1);
    return ScoreSorted;
  }
  
  /**
   * @brief Sorts the input read from the file by the Type of restaurants
   * @param info ArrayList of Restaurant objects 
   */
  public static ArrayList<Restaurant> sortByType(ArrayList<Restaurant> info) {
    ArrayList<Restaurant> TypeSorted = copyList(info);
    quickType(TypeSorted, 0, info.size()-1);
    return TypeSorted;
  }


    /**
   * @brief Partitions the ArrayList of retaurants by Name
   * @param info ArrayList of Restaurant objects
   * @param low Indicating the index of where the partition starts
   * @param high Indicating the index of the partition element
   * @return returns an integer that represents where the partition element is located
   */
  public static int partitionName(ArrayList<Restaurant> info, int low, int high){
    String pivot = info.get(high).getName();
    int i = (low-1);

    for(int j = low; j < high; j++){
      if(stringCompare(info, j, pivot)){
        i++;
        swap(info, i, j);
      }
    }
    swap(info, i+1, high);
    return(i+1);
  }


  /**
   * @brief Partitions the ArrayList of retaurants by Score
   * @param info ArrayList of Restaurant objects
   * @param low Indicating the index of where the partition starts
   * @param high Indicating the index of the partition element
   * @return returns an integer that represents where the partition element is located
   */
  public static int partitionScore(ArrayList<Restaurant> info, int low, int high){
    int pivot = info.get(high).getScore();
    int i = (low-1);

    for(int j = low; j < high; j++){
      if(scoreCompare(info, j, pivot)){
        i++;
        swap(info, i, j);
      }
    }
    swap(info, i+1, high);
    return(i+1);
  }



    /**
   * @brief Partitions the ArrayList of retaurants by Type
   * @param info ArrayList of Restaurant objects
   * @param low Indicating the index of where the partition starts
   * @param high Indicating the index of the partition element
   * @return returns an integer that represents where the partition element is located
   */
  public static int partitionType(ArrayList<Restaurant> info, int low, int high){
    int pivot = info.get(high).getType();
    int i = (low-1);

    for(int j = low; j < high; j++){
      if(typeCompare(info, j, pivot)){
        i++;
        swap(info, i, j);
      }
    }
    swap(info, i+1, high);
    return(i+1);
  }



    /**
   * @brief Compares the name of two Restaurants
   * @param info ArrayList of Restaurant objects
   * @param j Index that represents the location of the first Restaurant object to be compared
   * @param pivot String that is being compared with the name of the Restaurant object
   * @return boolean value true returned if the string being compared is less than or equal to the pivot string, boolean value false returned otherwise
   */
  public static boolean stringCompare(ArrayList<Restaurant> info, int j, String pivot){
    return info.get(j).getName().compareTo(pivot) <= 0;
  }


    /**
   * @brief Compares the score of two Restaurants 
   * @param info ArrayList of Restaurant objects
   * @param j Index that represents the location of the first Restaurant object to be compared
   * @param pivot Integer that is being compared with the name of the Restaurant object
   * @return boolean value true returned if the score being compared is less than or equal to the pivot, boolean value false returned otherwise
   */
  public static boolean scoreCompare(ArrayList<Restaurant> info, int j, int pivot){
    return info.get(j).getScore() > pivot;
  }


     /**
   * @brief Compares the type of two Restaurants 
   * @param info ArrayList of Restaurant objects
   * @param j Index that represents the location of the first Restaurant object to be compared
   * @param pivot Integer that is being compared with the name of the Restaurant object
   * @return boolean value true returned if the type being compared is less than or equal to the pivot, boolean value false returned otherwise
   */
  public static boolean typeCompare(ArrayList<Restaurant> info, int j, int pivot){
    return info.get(j).getType() > pivot;
  }



    /**
   * @brief Quicksorts the ArrayList of Restaurant objects 
   * @param info ArrayList of Restaurant objects
   * @param low Index representing the starting index to be quicksorted
   * @param high Index representing the last index to be quicksorted
   */
  public static void quickName(ArrayList<Restaurant> info, int low, int high){
    if(low<high){
      int middle = partitionName(info, low, high);

      quickName(info, low, middle - 1);
      quickName(info, middle+1, high);
    }
  }


        /**
   * @brief Quicksorts the ArrayList of Restaurant objects based on score 
   * @param info ArrayList of Restaurant objects
   * @param low Index representing the starting index to be quicksorted
   * @param high Index representing the last index to be quicksorted
   */
  public static void quickScore(ArrayList<Restaurant> info, int low, int high){
    if(low<high){
      int middle = partitionScore(info, low, high);

      quickScore(info, low, middle - 1);
      quickScore(info, middle+1, high);
    }
  }


      /**
   * @brief Quicksorts the ArrayList of Restaurant objects based on Type 
   * @param info ArrayList of Restaurant objects
   * @param low Index representing the starting index to be quicksorted
   * @param high Index representing the last index to be quicksorted
   */
    public static void quickType(ArrayList<Restaurant> info, int low, int high){
      if(low<high){
        int middle = partitionType(info, low, high);
  
        quickType(info, low, middle - 1);
        quickType(info, middle+1, high);
      }
    }



  /**
   * @brief Swaps two restaurant objects
   * @param info ArrayList that contains Restaurant objects
   * @param i Index of the first Restaurant object to be swapped
   * @param j Index of the second Restaurant object to be swapped
   */
  public static void swap(ArrayList<Restaurant> info, int i, int j){
    Restaurant temp =  info.get(i);
    info.set(i, info.get(j));
    info.set(j, temp);
  }

}
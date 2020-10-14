package finalprojtest;

/**
 * @file Edge.java
 * @brief Edge Data Type
 * @author Dimitri Tsampiras 
 * @date April 8, 2020
 */

public class Edge {

  private final String from;
  private final String to; 
  private final double distance;
  private final String address;

  public Edge(String f, String t, double d, String a) {
    from = f;
    to = t;
    distance = d;
    address = a;
  }

  /**
   * @brief Getter for from
   * @return from field
   */
  public String getFrom() {
    return from;
  }

  /**
   * @brief Getter for to
   * @return to field
   */
  public String getTo() {
    return to;
  }

  /**
   * @brief Getter for distance
   * @return distance field
   */
  public double getDistance() {
    return distance;
  }
  
  /**
   * @brief Getter for address
   * @return address field
   */
  public String getAddress() {
    return address;
  }

}

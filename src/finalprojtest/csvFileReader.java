package finalprojtest;
/**
 * @csvFileReader.java
 * @brief File Reader 
 * @author Zhiming Zhao, Tevis Doe, Dimitri Tsampiras, Yuvraj Randhawa
 * @date April 8, 2020
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @brief Class designed to read from the Restaurant data set into an arraylist.
 */
public class csvFileReader {

	/**
	 * @brief Reads from the csv file into an Array list of restaurants.
	 * @return A filled out array list of restaurants.
	 */
	public static ArrayList<Restaurant> readRestaurantFile() {
		String csvFile = "data/datanew.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();

		try {
			br = new BufferedReader(new FileReader(csvFile));
			String firstline = br.readLine();
			String[] header = firstline.split(cvsSplitBy);
			String lastCode = "";
			int count = 0;
			while ((line = br.readLine()) != null) {
				if( (count > 282862 && count < 282884) || (count > 291892 && count < 291909) || (count > 299962 && count < 299983) ){
					count++;
					continue;
				}
				String[] Restaurant = line.split(cvsSplitBy); // comma is the separator
				Restaurant = process(Restaurant); // this is to deal with the commas within quotation marks (which split
													// the elements and shifts them into the wrong places)
				String currentCode = Restaurant[0];

				ArrayList<String> vls = new ArrayList<String>();
				vls.add(Restaurant[4]);
				if (currentCode.equals(lastCode)) {

					vls.add(Restaurant[4]);

				}

				else {

					String name = Restaurant[2];

					if (name.contains("#")) {
						name = Restaurant[2].substring(0, Restaurant[2].indexOf('#'));
					}
					
					String address = Restaurant[8];

					if (address.contains("#")) {
						address = Restaurant[8].substring(0, Restaurant[8].indexOf('#'));
					}

					restaurantsList.add(new Restaurant(name.trim(), vls, Restaurant[7], address, Restaurant[9],
							Restaurant[11], Restaurant[12], Integer.parseInt(Restaurant[17]),
							Integer.parseInt(Restaurant[21])));
					lastCode = Restaurant[0];

				}

				count++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("The file cannot be found, check if the file is under root directory");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Input & Output operations error");
		} finally {

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		return restaurantsList;
	}

	/**
	 * @brief This function specifically deal with the issue of commas within the quotation marks
	 * @detail Tt gets the index number of the 2 elements containing the quotation marks, then concats them all. It works with multiple quotation marks on the same line
	 * @param data String array
	 * @return Array of Strings
	 */
	public static String[] process(String[] data) {
		int index1 = -1; // initialize the index of the first ", -1 for empty
		int index2 = 0; // initialize the index of the second ", 0 for empty
		for (int i = 0; i < data.length; i++) {
			if (String.valueOf(data[i].charAt(0)).equals("\"") && index1 == -1) { // if index1 is not empty and the
																					// first char of current element is
																					// "
				index1 = i; // set index1 to current index number
			}
			if (String.valueOf(data[i].charAt(data[i].length() - 1)).equals("\"") && index1 != -1) { // if index1 is not
																										// empty and the
																										// last char of
																										// current
																										// element is "
				index2 = i; // set index2 to current index number
				multiconcat(index1, index2, data); // concat the elements between index1 and index2
				data = multidelet(index1 + 1, index2, data); // delete the elements that were copied (index1+1:index2)
				i = index1; // this is to reset the cursor back to index1
				index1 = -1; // set index1 to empty

			}
		}
		return data;
	}

	/**
	 * @brief opy all elements between index1 and index2 to index1
	 * @param index1 Integer representing the first index
	 * @param index2 Integer representing the second index
	 * @param data String array representing the data
	 */
	public static void multiconcat(int index1, int index2, String[] data) {
		for (int i = index1 + 1; i <= index2; i++) {
			data[index1] += data[i];
		}
	}

	/**
	 * @brief Deletes the elements between index1+1 and index2
	 * @param index1 Integer representing the first index
	 * @param index2 Integer representing the second index
	 * @param data String array representing the data 
	 * @return
	 */
	public static String[] multidelet(int index1, int index2, String[] data) {
		String[] newarr = new String[data.length - (index2 - index1 + 1)];
		int n = 0;
		for (int i = 0; i < data.length; i++) {
			if (index1 <= i && i <= index2)
				continue;
			newarr[n] = data[i];
			n++;
		}
		return newarr;

	}

}
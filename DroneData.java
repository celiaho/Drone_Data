/* *****************************************
 *  Author : Celia Ho
 *  Created On : Sat Feb 17 2024 @ 4:33 PM
 *  File : Topic 2 - Individual Project #1 - Drone Data
 *  Description : This program demonstrates the following learning outcomes:
 *    - Program creation and structure
 *    - Program documentation
 *    - Data input and output
 *    - Method creation
 *    - Array creation and utilization
 *    - Algorithm design
 *  
 *  A drone is flown on a path across the White Mountains of New Hampshire. The drone handler needs
 *  to keep track of the following information: longitude,  latitude, and altitude. Create a 
 *  program that will hold a multi-dimensional array of 20 points. Using separate methods, your 
 *  program should determine:
 *    - the distance cumulatively traveled between the start and the end position,
 *    - the minimum altitude,
 *    - the maximum altitude
 *    - the altitude range.
 *  Make sure you output this information in the main method, not in your new methods. You may use
 *  a class if you wish!
 * 
 *  The formula for the great circle distance (in radians) (to find out the distance between the 
 *  start and end) is
 *  d = radius * arccos(sin(lat1)*sin(lat2)+cos(lat1)*cos(lat2)*cos(long2-long1))
 *  where radius = 6371 km.
 * 
 *  Convert degrees (which lat and long are typically stored in) to radians using Math.toRadians
 *  method.  
 *  
 *  Test Data (format: latitude, longitude, altitude): See "DroneCoordinates.txt" file.
 * ******************************************/

import java.util.Scanner;   // To read from keyboard
import java.io.File;        // To read from file
import java.io.FileNotFoundException;

public class DroneData {

  static final int RADIUS = 6371;   // Set constant for radius

  // METHOD DECLARATION SECTION

  // Method for calculating the great circle distance (in radians) to find the cumulative distance between start and end
  public static double distance(double lat1, double long1, double lat2, double long2) {
    return RADIUS * Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(long2-long1));
  }

  // Method for calculating the minimum altitude with degrees converted to radians using Math.toRadians method.  
  static double minimumAltitude(double[][] coordinates) {
      // Search through all altitudes (coordinates[i][2]) and find smallest value
      double minAlt = coordinates[0][2];   // variable to store smallest altitude, initialized to first altitude
      // Starting with altitude for row 1, while row # < total rows in array, execute statements below, then move on to next row
      for (int row = 1; row < coordinates.length; row++)
      // if altitude for current row < minAlt...
      if (coordinates[row][2] < minAlt)
        // ... assign coordinates[i][2] to max
        minAlt = coordinates[row][2];
        
      return Math.toRadians(minAlt);
    }
      
  // Method for calculating the maximum altitude with degrees converted to radians using Math.toRadians method. 
  static double maximumAltitude(double[][] coordinates) {
    // Search through all altitudes (coordinates[i][2]) and find largest value
    double maxAlt = coordinates[0][2];   // variable to store largest altitude, initialized to first altitude
    // Starting with altitude for row 1, while row # < total rows in array, execute statements below, then move on to next row
    for (int row = 1; row < coordinates.length; row++)
      // if altitude for current row > maxAlt...
      if (coordinates[row][2] > maxAlt)
        // ... assign coordinates[i][2] to max
        maxAlt = coordinates[row][2];

    return Math.toRadians(maxAlt);
  }

  // Method for calculating the altitude range in radians
  static double altitudeRange(double[][] coordinates) {
    // Largest altitude - smallest altitude
    double altRange = maximumAltitude(coordinates) - minimumAltitude(coordinates);

    return altRange;
  }
  
  // MAIN SECTION
  public static void main(String[] args) throws FileNotFoundException {

    // Create a multi-dimensional array of 20 rows with 3 elements each
    double[][] coordinates = new double[20][3];

    // Read from file
    File file = new File("DroneCoordinates.txt");

    Scanner fileReader = new Scanner(file);

    int i = 0;

    // Read lines from a file with a while loop
    while (fileReader.hasNextLine()) {
        String aLine = fileReader.nextLine();

        // Test code that line was read correctly
        // System.out.println(aLine);

        // Split line by delimiter "," and save data to an array of strings called "temp"
        String[] temp = aLine.split(",");

        // Use Double method to convert elements to data type double & store in coordinates array
        coordinates[i][0] = Double.parseDouble(temp[0]);  // latitudes
        coordinates[i][1] = Double.parseDouble(temp[1]);  // longitudes
        coordinates[i][2] = Double.parseDouble(temp[2]);  // altitudes

        // Increment array
        i++;

        // Test that longitudes were stored correctly
        // System.out.println(temp[0]);
    }

    // Loop to calculate cumulative distance traveled between each successive longitude/latitude point
    double totalDistance = 0;

    for (int j = 0; j < coordinates.length - 1; j++)  
        totalDistance += distance(coordinates[j][0], coordinates[j][1], coordinates[j+1][0], coordinates[j+1][1]);

    // Using separate methods, output in main...
    // ...the distance cumulatively traveled,...
    System.out.printf("%s%4.2f%s\n", "Total distance traveled: ", totalDistance, "  kilometers");
    // ...the minimum altitude,...
    System.out.printf("%s%4.2f%s\n", "Minimum altitude: ", minimumAltitude(coordinates), " radians");
    // ...the maximum altitude, and...
    System.out.printf("%s%4.2f%s\n", "Maximum altitude: ", maximumAltitude(coordinates), " radians");
    // ...the altitude range.
    System.out.printf("%s%4.2f%s\n", "Altitude range: ", altitudeRange(coordinates), " radians");
    }
}
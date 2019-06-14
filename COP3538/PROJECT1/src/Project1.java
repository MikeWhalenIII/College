import java.util.Scanner;
import java.io.*;

/**
 * COP3538 Project 1 - Array Searches and Sorts - Summer 2019
 * <p>
 * This class is the starting point to the program with the main method and
 * various searching and sorting methods. Upon starting the program the user is prompted
 * with menu with various options. The user can enter a options 1-6, each option does something
 * different. There are 1 print option, 3 sort options, 1 find option, and 1 exit option.
 * <p>
 * Option 1, the program will display a list of all of the imported states. The state name, 
 * capital, abbreviation, population, region, and the number of US house seats will be displayed.<br> 
 * Option 2, the states will be sorted by their name using the bubble sort.<br> 
 * Option 3, the states will be sorted by their population using selection sort.<br> 
 * Option 4, the states will be sorted by their capital city using insertion sort.<br> 
 * Option 5, the program will prompt the user to enter a search key and the program will call
 * the findState() method to find the capital matching the specified key.<br> 
 * Option 6, the program will exit.<br> 
 * 
 * @author Michael Whalen (N01425161)
 * @version 1.0, 05/31/2019
 */

public class Project1 {

	private static State[] states; // Specify the array used to store the
									// states.
/**
 * The main method for the program. It calls the readFile() method and displays the welcome message.
 * 
 * @param args Unused.
 * @throws IOException On input error
 */
	public static void main(String[] args) throws IOException {

		int numRecords = readFile(); // Call the method to read the CSV file.
		welcomeMessage(numRecords); // Display welcome message at start of program.

		Scanner input = new Scanner(System.in);
		int choice = 0;
		boolean sortedByCapital = false; // Used to determine if the program should use binary or sequential search.

		while (choice != 6) {
			try {
				System.out.print("1. Print a state report\n"
							   + "2. Sort by State name\n"
							   + "3. Sort by Population\n"
							   + "4. Sort by Capital\n"
							   + "5. Find and print a given state\n"
							   + "6. Quit\n"
							   + "Enter your choice: ");
				choice = Integer.parseInt(input.nextLine());

				switch (choice) {
				case 1:
					printStateReport(numRecords); // Print state report
					break;
				case 2:
					sortedByCapital = sortByStateName(numRecords); // Sort by state name
					break;
				case 3:
					sortedByCapital = soryByPopulation(numRecords); // Sort by population
					break;
				case 4:
					sortedByCapital = sortByCapital(numRecords); // sort by capital
					break;
				case 5:
					System.out.print("Enter the capital city name: ");
					String searchKey;
					searchKey = input.nextLine();
					findState(numRecords, sortedByCapital, searchKey); // find the state that matches the given capital
					break;
				case 6:
					quit();
					break;
				default:
					throw new Exception();
				}

			} catch (Exception e) {
				System.out.println("Invalid choice enter 1-6\n");
			}

		}
		input.close(); // Close the scanner

	}
/**
 * This method displays a welcome message to the user with the number of records imported.
 * 
 * @param numRecords takes in the number of records read in from the csv file.
 */
	private static void welcomeMessage(int numRecords) {
		System.out.println("COP3538 Project 1\n"
						 + "Instructor: Xudong Liu\n\n"
						 + "Array Searches and Sorts\n\n"
						 + "There were " + numRecords + " state records read.\n");
	}
/**
 * This method reads the data from the specified CSV file.
 * 
 * @return Returns the number of records imported.
 * @throws IOException Throws error if the cannot be read or cannon be found.
 */
	private static int readFile() throws IOException {
		states = new State[100];
		int i = 0;

		try {
			BufferedReader readCSV = new BufferedReader(new FileReader("States1.csv"));

			String fileRead = readCSV.readLine();

			while ((fileRead = readCSV.readLine()) != null) {
				String[] field = fileRead.split(",");

				String tempStateName = field[0];
				String tempCapital = field[1];
				String tempAbbreviation = field[2];
				double tempPopulation = Double.parseDouble(field[3]);
				String tempRegion = field[4];
				int tempHouseSeats = Integer.parseInt(field[5]);
				{
					states[i] = new State(tempStateName, tempCapital, tempAbbreviation, tempPopulation, tempRegion,
							tempHouseSeats);
					i++;
				}
			}
			readCSV.close(); // Close the reader

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		return i; // return the number of records imported.
	}
/**
 * This method prints out all of the states saved in the array.
 * 
 * @param numRecords takes in the number of records saved in the array.
 */
	private static void printStateReport(int numRecords) {
		System.out.println();
		System.out.printf("%-26s %-17s %-12s %-12s %-16s %-12s\n", "Name", "Capital City", "Abbr", "Population",
				"Region", "US House Seats");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		for (int i = 0; i < numRecords; i++) {
			System.out.printf("%-26s %-17s %-12s %-12.0f %-20s %-12d\n", states[i].getStateName(),
					states[i].getCapital(), states[i].getAbbreviation(), states[i].getPopulation(),
					states[i].getRegion(), states[i].getHouseSeats());
		}
		System.out.println();
	}
/**
 * This method uses the bubble sort to sort the states by their name in alphabetical order.
 * 
 * @param numRecords Takes in the number of records.
 * @return Returns whether or not the states were sorted by the capital. In this case it is false.
 */
	private static boolean sortByStateName(int numRecords) { // Bubble Sort
		int out, in;

		for (out = numRecords - 1; out > 1; out--) {
			for (in = 0; in < out; in++) {
				if (states[in].compareTo(states[in + 1]) > 0) {
					swap(in, in + 1);
				}
			}
		}
		System.out.println("\nStates sorted by State name.\n");
		Boolean sortedByCapital = false;

		return sortedByCapital;
	}
/**
 * This method uses selection sort to sort the array of State objects by population from least to greatest. 
 * 
 * @param numRecords takes in the number of records.
 * @return Returns whether or not the states were sorted by the capital. In this case it is false.
 */
	private static boolean soryByPopulation(int numRecords) { // Selection Sort
		int out, in, min;
		for (out = 0; out < numRecords - 1; out++) {
			min = out;
			for (in = out + 1; in < numRecords; in++)
				if (states[in].getPopulation() < states[min].getPopulation())
					min = in;
			swap(out, min);
		}
		System.out.println("\nStates sorted by Population.\n");
		Boolean sortedByCapital = false;

		return sortedByCapital;
	}
/**
 * This method uses insertion sort to sort the array of State objects by their capital.
 * 
 * @param numRecords Takes in the number of records.
 * @return Returns TRUE to specify that the States have been sorted by their capitals.
 */
	private static boolean sortByCapital(int numRecords) { // Insertion Sort
		int in, out;

		for (out = 1; out < numRecords; out++) {
			State temp = states[out];
			in = out;
			while (in > 0 && states[in - 1].getCapital().compareTo(temp.getCapital()) > 0) {
				states[in] = states[in - 1];
				--in;
			}
			states[in] = temp;
		}
		System.out.println("\nStates sorted by Capital cities.\n");
		Boolean sortedByCapital = true;

		return sortedByCapital;
	}
/**
 * This method will search for a state's capital that matches the given search key. If the states
 * have been sorted by their capital's, then the method will use binary search to look for the key. 
 * If the states have NOT been sorted by their capitals, then the method will use sequential search to
 * look for the provided key.
 * 
 * @param numRecords Takes in the number of State objects within the array.
 * @param sortedByCapital Takes in whether or not the states have been sorted by their capitals.
 * @param searchKey Takes in the key the search algorithm will look for.
 */
	private static void findState(int numRecords, boolean sortedByCapital, String searchKey) {

		if (sortedByCapital == true) { // Use Binary Search
			System.out.println("\nBinary search is used\n");

			int lowerBound = 0;
			int upperBound = numRecords - 1;
			int curIndex;

			while (true) { 
				curIndex = (lowerBound + upperBound) / 2;
				if (states[curIndex].getCapital().compareTo(searchKey) == 0) {
					states[curIndex].printState();
					break;
				}

				else if (lowerBound > upperBound) {
					System.out.println("Error: " + searchKey + " is not a state capital\n");
					break;
				}

				else {
					if (states[curIndex].getCapital().compareTo(searchKey) < 0)
						lowerBound = curIndex + 1; // it’s in the UPPER half
					else
						upperBound = curIndex - 1; // it’s in the LOWER half
				}
			}

		} else { // Use Sequential Search

			int j;

			System.out.println("\nSequential search is used\n");

			for (j = 0; j < numRecords; j++) {
				if (states[j].getCapital().compareTo(searchKey) == 0)
					break;
			}
			if (j == numRecords) {
				System.out.println("Error: " + searchKey + " is not a state capital\n");
			} else {
				states[j].printState(); // Found the capital, print the State
										// information
			}

		}

	}
/**
 * This method is used by the bubble sort and selection sort algorithms.
 * It swaps the location of two objects within the array in order to sort them.
 * 
 * @param s1 Takes in the index of the first object.
 * @param s2 Takes in the index of the second object.
 */
	private static void swap(int s1, int s2) {
		State temp = states[s1];
		states[s1] = states[s2];
		states[s2] = temp;
	}
/**
 * This method will exit the program when executed.
 * Activated by option 6 in the menu.
 */
	private static void quit() {
		System.out.print("Goodbye!");
		System.exit(1);
	}

}

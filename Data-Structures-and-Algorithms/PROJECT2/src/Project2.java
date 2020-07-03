import java.io.*;

/**
 * COP 3538: Project 2 – Stacks and Priority Queues - Summer 2019
 * <p>
 * This class is the starting point to the program with the main method. There
 * is no user input for this program, you only need to provide a CSV file for
 * the program to use.<br>
 * 
 * The program will read a csv file (States2.csv), then import states from the
 * following regions: South Pacific, West, and Middle Atlantic. These states
 * will be added to a single stack as state objects, and then displayed to the
 * user.<br>
 * The program will then pop the states from the stack one by one into 3
 * separate priority queues for their respective regions. Within these priority
 * queues, the states will be sorted by their populations. With the greatest
 * population having the highest priority. The program will then print all 3
 * queues.<br>
 * The program will then remove the states from the priority queues one by one
 * in this order: South Pacific, West, Middle Atlantic. The removed states
 * will be places back onto the stack and the stack will be displayed one last
 * time.
 * <p>
 * 
 * @author Michael Whalen (n01425161)
 * @version 1.0, 06/14/2019
 *
 */

public class Project2 {

	private static Stack stack = new Stack();

	/**
	 * The main method for the program. This method will call the readFile
	 * method and welcome method, then print the stack, create 3 priority
	 * groups, pop the states from the stack into their corresponding priority
	 * groups, print the 3 priority groups, remove the states from the 3
	 * priority groups back onto the stack and then print the stack one final
	 * time.
	 * 
	 * @param args
	 *            Unused.
	 * @throws IOException
	 *             For the file I/O.
	 */
	public static void main(String[] args) throws IOException {

		int numStates = readFile();
		welcomeMessage(numStates);

		// Print the stack
		System.out.println("\nStack Contents:");
		stack.printStack();

		// Create 3 Priority Queues
		PriorityQueue southPacificPG = new PriorityQueue(12);
		PriorityQueue westPG = new PriorityQueue(12);
		PriorityQueue middleAtlanticPG = new PriorityQueue(12);

		// Pop states from the stack into their corresponding priority queue
		while (!stack.isEmpty()) {
			State poppedState = stack.pop();

			if (poppedState.getRegion().equals("South Pacific")) {
				southPacificPG.insert(poppedState);
			} else if (poppedState.getRegion().equals("West")) {
				westPG.insert(poppedState);
			} else if (poppedState.getRegion().equals("Middle Atlantic")) {
				middleAtlanticPG.insert(poppedState);
			}
		}

		// Print 3 Priority Queues
		System.out.println("\nSouth Pacific Priority Queue Contents:");
		southPacificPG.printQueue();
		System.out.println("\nWest Priority Queue Contents:");
		westPG.printQueue();
		System.out.println("\nMiddle Atlantic Priority Queue Contents:");
		middleAtlanticPG.printQueue();

		// Remove states from the South Pacific Priority Group and push them
		// onto the stack.
		while (!southPacificPG.isEmpty()) {
			stack.push(southPacificPG.remove());
		}
		// Remove states from the West Priority Group and push them onto the
		// stack.
		while (!westPG.isEmpty()) {
			stack.push(westPG.remove());
		}
		// Remove states from the Middle Atlantic Priority Group and push them
		// onto the stack.
		while (!middleAtlanticPG.isEmpty()) {
			stack.push(middleAtlanticPG.remove());
		}

		// Print the stack
		System.out.println("\nStack Contents:");
		stack.printStack();
	}

	/**
	 * This method displays a welcome message to the user with the number of
	 * records imported.
	 * 
	 * @param numRecords
	 *            takes in the number of records read in from the csv file.
	 */
	private static void welcomeMessage(int numNodes) {
		System.out.println("COP3538 Project 2\n" + "Instructor: Xudong Liu\n\n" + "Stacks and Priority Queues\n\n"
				+ "There were " + numNodes + " state records put on the stack.\n");

	}

	/**
	 * This method reads the data from the specified CSV file. Then pushes the
	 * states onto the stack.
	 * 
	 * @return Returns the number of states imported from the csv file.
	 * @throws IOException
	 *             Throws error if the cannot be read or cannot be found.
	 */
	private static int readFile() throws IOException {

		try {
			BufferedReader readCSV = new BufferedReader(new FileReader("States2.csv"));

			String fileRead = readCSV.readLine();

			while ((fileRead = readCSV.readLine()) != null) {
				String[] field = fileRead.split(",");

				if (field[4].equalsIgnoreCase("South Pacific") || field[4].equalsIgnoreCase("West")
						|| field[4].equalsIgnoreCase("Middle Atlantic")) {

					String tempStateName = field[0];
					String tempCapital = field[1];
					String tempAbbreviation = field[2];
					double tempPopulation = Double.parseDouble(field[3]);
					String tempRegion = field[4];
					int tempHouseSeats = Integer.parseInt(field[5]);
					{
						State states = new State(tempStateName, tempCapital, tempAbbreviation, tempPopulation,
								tempRegion, tempHouseSeats);
						stack.push(states); // Push the state onto the stack.
					}
				}
			}
			readCSV.close(); // Close the reader

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		return stack.numLinks(); // return the number of records imported.
	}
}

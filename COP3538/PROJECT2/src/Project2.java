import java.io.*;

public class Project2 {

	private static Stack stack = new Stack();

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

		while (!southPacificPG.isEmpty()) {
			stack.push(southPacificPG.remove());
		}
		while (!westPG.isEmpty()) {
			stack.push(westPG.remove());
		}
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

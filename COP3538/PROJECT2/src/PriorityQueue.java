/**
 * This class is used to implement a priority queue. The states with the higher
 * population have the higher priority.
 * 
 * @author Michael Whalen (n01425161)
 * @version 1.0, 06/14/2019
 */
public class PriorityQueue {

	private int maxSize;
	private State[] stateQueue;
	private int numItems;

	/**
	 * The constructor for the priority queue.
	 * 
	 * @param max
	 *            Takes in a integer value for the max size of the priority
	 *            queue.
	 */
	public PriorityQueue(int max) {
		maxSize = max;
		stateQueue = new State[maxSize];
		numItems = 0;
	}

	/**
	 * Used to insert states into the priority queue.
	 * 
	 * @param state
	 *            Takes in a state object.
	 */
	public void insert(State state) {
		int i;

		if (numItems == 0) {
			stateQueue[0] = state;
			numItems++;
		} else {
			for (i = numItems - 1; i >= 0; i--) {

				if (state.getPopulation() < stateQueue[i].getPopulation()) {
					stateQueue[i + 1] = stateQueue[i];
				} else {
					break;
				}
			}
			stateQueue[i + 1] = state;
			numItems++;
		}
	}

	/**
	 * Used to remove states from the priority queue.
	 * 
	 * @return Returns a state object.
	 */
	public State remove() {
		return stateQueue[--numItems];
	}

	/**
	 * Used to print all of the states within the priority queue.
	 */
	public void printQueue() {
		System.out.println();
		System.out.printf("%-25s %-18s %-12s %-19s %-14s %1s", "State Name", "Capital City", "State Abbr",
				"State Population", "Region", "US House Seats");
		System.out.println(
				"\n-----------------------------------------------------------------------------------------------------------");

		for (int i = numItems - 1; i >= 0; i--) {
			if (stateQueue[i] != null)
				System.out.printf("%-26s %-20s %-12s %,-16.0f %-20s %1d\n", stateQueue[i].getStateName(),
						stateQueue[i].getCapital(), stateQueue[i].getAbbreviation(), stateQueue[i].getPopulation(),
						stateQueue[i].getRegion(), stateQueue[i].getHouseSeats());
		}

	}

	/**
	 * Used to check if the queue is empty.
	 * 
	 * @return Returns true if empty, and false if not.
	 */
	public boolean isEmpty() {
		return (numItems == 0);
	}

	/**
	 * Used to check if the queue is full.
	 * 
	 * @return Returns true if full, and false if not.
	 */
	public boolean isFull() {
		return (numItems == maxSize);
	}
}
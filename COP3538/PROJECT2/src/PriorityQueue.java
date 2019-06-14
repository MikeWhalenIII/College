
public class PriorityQueue {

	private int maxSize;
	private State[] stateQueue;
	private int numItems;

	/**
	 * Constructor
	 * 
	 * @param maxSize
	 */
	public PriorityQueue(int max) {
		maxSize = max;
		stateQueue = new State[maxSize];
		numItems = 0;
	}

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
	
	public State remove() {
		return stateQueue[--numItems];
	}
	
	public void printQueue() {
		System.out.println();
		System.out.printf("%-25s %-18s %-12s %-19s %-14s %1s", "State Name", "Capital City", "State Abbr",
				"State Population", "Region", "US House Seats");
		System.out.println(
				"\n-----------------------------------------------------------------------------------------------------------");
		
		for (int i = numItems - 1; i >= 0; i--) {
			if (stateQueue[i] != null)
			System.out.printf("%-26s %-20s %-12s %-16.0f %-20s %1d\n", stateQueue[i].getStateName(), stateQueue[i].getCapital(),
					stateQueue[i].getAbbreviation(), stateQueue[i].getPopulation(), stateQueue[i].getRegion(), stateQueue[i].getHouseSeats());
		}

	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public boolean isFull() {
		return (numItems == maxSize);
	}
}
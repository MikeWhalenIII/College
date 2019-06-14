/**
 * This class is used to implement a stack of state objects using a single
 * linked list.
 * 
 * @author Michael Whalen (n01425161)
 * @version 1.0, 06/14/2019
 */

public class Stack {

	private Link top;
	private int numLinks; // Used to keep track of how many states are in the
							// stack.

	public Stack() {
		top = null;
		numLinks = 0;
	}

	/**
	 * Used to push a state onto the stack. Using a singly linked list.
	 * 
	 * @param data
	 *            Takes a state object.
	 */
	public void push(State data) {
		Link newLink = new Link(data);

		if (isEmpty() == true) {
			top = newLink;
		} else {
			newLink.next = top;
			top = newLink;
		}
		numLinks += 1; // Add 1 to the number of total links.
	}

	/**
	 * Used to pop a state off the the stack and return it.
	 * 
	 * @return Returns a state object.
	 */
	public State pop() {
		State states = top.data;

		if (isEmpty() == true) {
			System.out.println("Stack is empty!");
		} else {
			top = top.next;
		}
		numLinks -= 1; // Decrease the number of total links by 1.
		return states;
	}

	/**
	 * Used to print out all of the state objects within the stack.
	 */
	public void printStack() {

		if (isEmpty()) { // Check to see if the stack is empty
			System.out.println("The stack is empty!");

		} else {

			System.out.println();
			System.out.printf("%-25s %-18s %-12s %-19s %-14s %1s", "State Name", "Capital City", "State Abbr",
					"State Population", "Region", "US House Seats");
			System.out.println(
					"\n-----------------------------------------------------------------------------------------------------------");

			Link current = top;
			while (current != null) {
				State states = current.data;

				System.out.printf("%-26s %-20s %-12s %,-16.0f %-20s %1d\n", states.getStateName(), states.getCapital(),
						states.getAbbreviation(), states.getPopulation(), states.getRegion(), states.getHouseSeats());

				current = current.next; // Move to the next state.
			}
		}
	}

	/**
	 * Used to determine if the stack is empty.
	 * 
	 * @return Returns true if empty and false if its not empty.
	 */
	public boolean isEmpty() {
		return (top == null);
	}

	/**
	 * Used to check if the stack is full.
	 * 
	 * @return Always returns false.
	 */
	public boolean isFull() {
		return false;
	}

	/**
	 * Used to return the number of links within the stack.
	 * 
	 * @return returns an integer value of the number of links.
	 */
	public int numLinks() {
		return numLinks;
	}

	/**
	 * This is the class to implement the singly linked list used for the stack.
	 * 
	 * @author Michael Whalen (n01425161)
	 * @version 1.0, 06/14/2019
	 */
	// Class for singly linked list
	class Link {
		private State data;
		private Link next;

		/**
		 * Constructor for singly linked list.
		 * 
		 * @param value
		 *            Takes in a state object.
		 */
		public Link(State data) {
			this.data = data;
			next = null;
		}
	}
}

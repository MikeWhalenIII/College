
/***
 * 
 * @author mwhalen
 *
 */
public class Stack {

	private Link top;
	private int numLinks; // Used to keep track of how many states are in the stack.

	public Stack() {
		top = null;
		numLinks = 0;
	}

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

				System.out.printf("%-26s %-20s %-12s %-16.0f %-20s %1d\n", states.getStateName(), states.getCapital(),
						states.getAbbreviation(), states.getPopulation(), states.getRegion(), states.getHouseSeats());

				current = current.next; // Move to the next state.
			}
		}
	}

	public boolean isEmpty() {
		return (top == null);
	}

	public boolean isFull() {
		return false;
	}

	public int numLinks() {
		return numLinks;
	}

	// Class for singly linked list
	class Link {
		private State data;
		private Link next;

		/**
		 * Constructor
		 * 
		 * @param value
		 */
		public Link(State data) {
			this.data = data;
			next = null;
		}
	}
}

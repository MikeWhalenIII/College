import java.text.DecimalFormat;

/**
 * This class is used when creating objects of the State type. It also
 * implements Comparable which is used for compareTo in the compareTo method.
 * 
 * @author Michael Whalen (N01425161)
 * @version 1.0, 05/31/2019
 */
public class State implements Comparable<State> {

	private String stateName;
	private String capital;
	private String abbreviation;
	private double population;
	private String region;
	private int houseSeats;

	/**
	 * The constructor for the State class.
	 * 
	 * @param stateName
	 *            Takes in the state name.
	 * @param capital
	 *            Takes in the states capital.
	 * @param abbreviation
	 *            Takes in the states abbreviation.
	 * @param population
	 *            Takes in the states population.
	 * @param region
	 *            Takes in the states region.
	 * @param houseSeats
	 *            Takes in the number of house seats for a specified state.
	 */
	public State(String stateName, String capital, String abbreviation, double population, String region,
			int houseSeats) {
		this.stateName = stateName;
		this.capital = capital;
		this.abbreviation = abbreviation;
		this.population = population;
		this.region = region;
		this.houseSeats = houseSeats;
	}

	/**
	 * Getter method for the states name.
	 * 
	 * @return stateName Returns the name of the state.
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * Setter method for the states name.
	 * 
	 * @param stateName
	 *            Takes in a new state name for a state and updates the name.
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * Getter for the states capital.
	 * 
	 * @return capital Returns the states capital.
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Setter method for a states capital.
	 * 
	 * @param capital
	 *            Takes in a new capital name for a state and changes it.
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Getter method for a states abbreviation.
	 * 
	 * @return abbreviation Returns a states abbreviation.
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * Setter method for a states abbreviation.
	 * 
	 * @param abbreviation
	 *            Takes in a new abbreviation for a state and sets it.
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * Getter method for a states population.
	 * 
	 * @return population Returns a states population.
	 */
	public double getPopulation() {
		return population;
	}

	/**
	 * Setter method for a states population.
	 * 
	 * @param population
	 *            Takes in a new population for a state and sets it.
	 */
	public void setPopulation(double population) {
		this.population = population;
	}

	/**
	 * Getter method for a states region.
	 * 
	 * @return region Returns a states region.
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Setter method for a states region.
	 * 
	 * @param region
	 *            Takes in a new states region and sets it.
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Getter method for the number of house seats a state has.
	 * 
	 * @return houseSeats Returns the number state seats a state has.
	 */
	public int getHouseSeats() {
		return houseSeats;
	}

	/**
	 * Setter method for the number of house seats a state has.
	 * 
	 * @param houseSeats
	 *            Takes in a new number of house seats for a state and sets it.
	 */
	public void setHouseSeats(int houseSeats) {
		this.houseSeats = houseSeats;
	}

	/**
	 * Compares two states by their names. Overrides the compareTo method in the
	 * Comparable interface.
	 * 
	 * @return Returns a negative number, zero, or a positive number.
	 */
	@Override
	public int compareTo(State s2) {
		return this.stateName.compareTo(s2.stateName);
	}

	/**
	 * This method is used to display a single state and it's information.
	 */
	public void printState() {
		DecimalFormat formatter = new DecimalFormat("#,###"); // Used to format
																// population
																// with commas

		System.out.printf("%-20s %s %s", "Name: ", stateName, "\n");
		System.out.printf("%-20s %s %s", "Capital City: ", capital, "\n");
		System.out.printf("%-20s %s %s", "Abbr: ", abbreviation, "\n");
		System.out.printf("%-21s", "Population: ");
		System.out.println(formatter.format(population));
		System.out.printf("%-20s %s %s", "Region: ", region, "\n");
		System.out.printf("%-20s %d %s", "US House Seats: ", houseSeats, "\n\n");
	}

}

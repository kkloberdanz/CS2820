package production;

import java.util.*;

/**
 * 
 * @author Tyler
 *
 */

public class MockOrders implements Orders, Tick {
	
	private Inventory I;
	// private RobotScheduler R; need to work with RobotScheduler to do this
	private ArrayList<Order> orderQueue;
	private SimRandom randSource;
	
	// Constructor
	public MockOrders(Inventory I, SimRandom rand) {
		this.I = I;
		randSource = rand;
		orderQueue = new ArrayList<Order>();
		for (int i = 0; i < 3; i++) {
			orderQueue.add(generateRandomOrder());
		}
	}
	
	// Will work on this more later
	public void tick(int count){}
	
	// Will also work on this more later
	public void notify(Robot R, Shelf S){};
	
	// Generates a random order.
	public Order generateRandomOrder() {
		String randAddress = new Address(randSource).createAddress();
		Order returnOrder = new Order();
		returnOrder.updateAddress(randAddress);
		int numItems = (1+randSource.nextInt(5));
		// Will eventually fix this to work in conjunction with the Inventory
		// For now, it simply produces a certain amount of MockItems
		for (int i = 0; i < numItems; i++){
			MockItem myItem = new MockItem(123, "test");
			returnOrder.addItem(myItem);
		}
		return returnOrder;
	}

}

// This is a local class that is purely designed to generate random
// addresses for the orders.
class Address {
	
	/**
	 * @author Tyler
	 * 
	 * This code was based on Professor Ted Herman's code.
	 */
	
	SimRandom rand;
	
	
	/* This class is used to generate random
	 * addresses that will be needed for each
	 * order.
	 */
	
	public Address(SimRandom rand) {
		this.rand = rand;
	}
	
	// This function picks a street name for the address.
	private String chooseStreet() {
		final String[] streetNames = {"Gilbert Street", "College Street", "Iowa Avenue", "Dubuque Street", "Melrose Avenue", "Park Road", "Dodge Street"};
		return streetNames[rand.nextInt(streetNames.length)];
	}
	
	// This function chooses a random number for the address street number.
	private int chooseNumber() {
		return 1+rand.nextInt(998);
	}
	
	// This function chooses a random first name for the address.
	private String chooseFirstName() {
		final String[] firstNames = {"Grant", "Tom", "Tyler", "Ben", "Kendall", "Bri", "Max", "Steve", "Holly", "Sam", "Andrew", "Laura", "Jack", "Chuck", "Amanda", "Kristin", "Annie"};
		return firstNames[rand.nextInt(firstNames.length)];
	}
	
	// This function chooses a random last name for the address.
	private String chooseLastName() {
		final String[] lastNames = {"Gertsen", "Bowman", "Foster", "Jacobs", "Johnson", "Hammer", "Peterson", "Harrington", "Mindemann", "Kaplan", "Novitskiy", "Schneider", "Rose", "Carlson", "Stout", "Witz", "Majure"};
		return lastNames[rand.nextInt(lastNames.length)];
	}
	
	// This function chooses a random state for the address.
	private String chooseState() {
		final String[] stateNames = {"IA", "IL", "MN", "MI", "MO", "KS", "OK", "TX", "NY", "OR", "CA", "AZ", "NE", "NV", "MA", "IN", "OH", "TN", "WA"};
		return stateNames[rand.nextInt(stateNames.length)];
	}
	
	// This function chooses a random city for the address.
	private String chooseCity() {
		final String[] cityNames = {"Winterfell", "King's Landing", "Highgarden", "Riverrun", "Pyke", "Pentos", "Braavos", "Qarth", "Astapor", "Yunkai", "Meereen", "Harrenhaal", "Casterly Rock", "Sunspear"};
		return cityNames[rand.nextInt(cityNames.length)];
	}
	
	// This function creates a random zip code for the address.
	private String chooseZip(){
		String zipCode = "";
		for (int i = 0; i < 6; i++){
			zipCode += "0123456789".charAt(rand.nextInt(10));
		}
		return zipCode;
	}
	
	public String createAddress() {
		String firstName = chooseFirstName();
		String lastName = chooseLastName();
		String number = new Integer(chooseNumber()).toString();
		String streetName = chooseStreet();
		String city = chooseCity();
		String state = chooseState();
		String zip = chooseZip();
		String address = firstName + " " + lastName + "\n";
		address = number + " " + streetName + "\n";
		address = city + " " + state + " " + zip;
		return address;
	}
}



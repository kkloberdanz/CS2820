package production;

import java.util.*;

/**
 * 
 * @author Tyler
 *
 */

public class MockOrders implements Tick {
	
	static ArrayList<Order> orderQueue; // a queue of orders
	static private SimRandom randSource;
	static private String randAddress;
	static Inventory I;
	static int id = 1; // keeps track of order ID number
	static ArrayList<Item> itemDatabase = new ArrayList<Item>();
	static ArrayList<Integer> itemQuantities = new ArrayList<Integer>();
	
	// Constructor
	public MockOrders() {
		MockOrders.orderQueue = new ArrayList<Order>(3);
		for (int i = 0; i < 3; i++) {
			orderQueue.add(generateRandomOrder());
		}
	}
	
	// Constructor that takes randomness argument
	public MockOrders(SimRandom rand){
		MockOrders.randSource = rand;
		MockOrders.orderQueue = new ArrayList<Order>();
		for (int i = 0; i < 3; i++) {
			orderQueue.add(generateRandomOrder());
		}
	}
	
	// This method generates a random item from CatItem
	public static Item randomItem() {
		int k = randSource.nextInt(CatItem.catalog.length);
		return new Item(CatItem.catalog[k].id, CatItem.catalog[k].description);
	}	

	// this method gets the next order in the queue
	public static Order getNextOrder(){
		return orderQueue.remove(0);
	}
	
	// Generates a random order.
	public static Order generateRandomOrder() {
		randAddress = new Address(randSource).createAddress();
		Order returnOrder = new Order();
		returnOrder.updateAddress(randAddress);
		int numItems = (1+randSource.nextInt(5));
		for (int i = 0; i < numItems; i++){
			Item myItem = randomItem();
			returnOrder.addItem(myItem);
		}
		returnOrder.orderID = id;
		id += 1;
		return returnOrder;
	}

	// this method will generate new orders as time goes on; for now,
	// it simply will add an order whenever the queue loses an order,
	// but eventually I would like generation to be random if possible
	public static void receiveNewOrders(){
		while (orderQueue.size() < 3) {
			orderQueue.add(generateRandomOrder());
		}
	}
	
	// For every tick, MockOrders needs to simply generate
	// more orders for the queue; the picker will be doing
	// most of the other tick-by-tick work.
	public void tick(int count){
		receiveNewOrders();
		return;
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
	
	// This function chooses a random name for the address.
	private String chooseName() {
		final String[] names = {"Eddard Stark", "Catelyn Stark", "Arya Stark", "Sansa Stark", "Robb Stark", "Jon Snow", "Bran Stark", "Rickon Stark", "Jaime Lannister", "Cersei Lannister", "Robert Baratheon", "Tyrion Lannister", "Tywin Lannister", "Joffrey Baratheon", "Daenerys Targaryen", "Petyr Baelish", "Jorah Mormont", "Theon Greyjoy", "The Hound", "Khal Drogo", "Viserys Targaryen", "Samwell Tarly", "Tommen Baratheon", "Melisandre", "Margaery Tyrell", "Davos Seaworth", "Stannis Baratheon", "Shireen Baratheon", "Brienne of Tarth", "Roose Bolton", "Ramsay Bolton", "Tormund Giantsbane", "Daario Naharis", "Missandei", "Ellaria Sand", "Oberyn Martell"};
		return names[rand.nextInt(names.length)];
	}

	// This function chooses a random state for the address.
	private String chooseState() {
		final String[] stateNames = {"IA", "IL", "MN", "MI", "MO", "KS", "OK", "TX", "NY", "OR", "CA", "AZ", "NE", "NV", "MA", "IN", "OH", "TN", "WA"};
		return stateNames[rand.nextInt(stateNames.length)];
	}
	
	// This function chooses a random city for the address.
	private String chooseCity() {
		final String[] cityNames = {"Winterfell", "King's Landing", "Highgarden", "Riverrun", "Pyke", "Pentos", "Braavos", "Qarth", "Astapor", "Yunkai", "Meereen", "Harrenhal", "Casterly Rock", "Sunspear", "Valyria", "Volantis", "Vaes Dothrak", "The Eyrie", "Castle Black", "The Dreadfort", "The Twins"};
		return cityNames[rand.nextInt(cityNames.length)];
	}
	
	// This function creates a random zip code for the address.
	private String chooseZip(){
		String zipCode = "";
		for (int i = 0; i < 5; i++){
			zipCode += "0123456789".charAt(rand.nextInt(10));
		}
		return zipCode;
	}
	
	public String createAddress() {
		String name = chooseName();
		/*String firstName = chooseFirstName();
		String lastName = chooseLastName();*/
		String number = new Integer(chooseNumber()).toString();
		String streetName = chooseStreet();
		String city = chooseCity();
		String state = chooseState();
		String zip = chooseZip();
		String address = name + "\n";
		address += number + " " + streetName + "\n";
		address += city + " " + state + " " + zip;
		return address;
	}
}

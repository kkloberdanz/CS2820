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
	static Inventory Inventory;
	static MockFloor floor;
	static int id = 1; // keeps track of order ID number
	static ArrayList<Item> itemDatabase = new ArrayList<Item>();
	static ArrayList<Integer> itemQuantities = new ArrayList<Integer>();
	static Order finish;//* author haoyang wei help tells the inventory to delete database*//
	/**
	 * The constructor for MockOrders; it takes a SimRandom variable, inventory,
	 * and floor as arguments, and also initializes the orderQueue while
	 * generating three random orders to go with it.
	 * 
	 * @author Tyler Foster
	 * @param rand
	 * @param I
	 * @param F
	 */
	public MockOrders(SimRandom rand, Inventory I, MockFloor F){
		MockOrders.randSource = rand;
		MockOrders.Inventory = I;
		MockOrders.floor = F;
		MockOrders.orderQueue = new ArrayList<Order>();
		for (int i = 0; i < 3; i++) {
			orderQueue.add(generateRandomOrder());
		}
	}
	
	/**
	 * Generates a random CatItem from the catalog and returns that as an
	 * Item.
	 * 
	 * @author Tyler Foster
	 * @return Item
	 */
	public static Item randomItem() {
		int k = randSource.nextInt(CatItem.catalog.length);
		return new Item(CatItem.catalog[k].id, CatItem.catalog[k].description);
	}	

	/**
	 * Pops the next order from the queue and returns it.
	 * 
	 * @author Tyler Foster
	 * @return Order
	 */
	public static Order getNextOrder(){
		finish=orderQueue.get(0);
		return orderQueue.remove(0);
	}
	
	/**
	 * Takes an Item as an argument and searches through the shelves to
	 * determine whether or not that Item is in stock.
	 * 
	 * @author Tyler Foster
	 * @param I
	 * @return boolean
	 */
	public static boolean locateItem(Item I) {
		for (Integer i : MockFloor.getShelves().keySet()) {
			Shelf s = MockFloor.getShelves().get(i);
			if (s.contains(I.get_id_number())) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void initialize() {
		for (int i = 0; i < CatItem.catalog.length; i++) {
			
		}
	}
	
	/**
	 * Uses the local class Address to generate a random Order. It also
	 * generates a random list of items to go with that Order; if one of the
	 * Items that has been generated is not in stock, then this method also
	 * calls the floor to order more items from the supplier.
	 * 
	 * @author Tyler Foster
	 * @return Order
	 */
	public static Order generateRandomOrder() {
		randAddress = new Address(randSource).createAddress();
		Order returnOrder = new Order();
		returnOrder.updateAddress(randAddress);
		int numItems = (1+randSource.nextInt(5));
		for (int i = 0; i < numItems; i++){
			Item myItem = randomItem();
			/*if (locateItem(myItem) == false) {
				MockFloor.OrderFromSupplier(myItem, 3);
			}*/
			returnOrder.addItem(myItem,numItems);
			/* author haoyang wei add item with quantity*//
		}
		returnOrder.orderID = id;
		id += 1;
		return returnOrder;
	}

	/**
	 * Generates another random order if the queue lost an order.
	 * 
	 * @author Tyler Foster
	 */
	public static void receiveNewOrders(){
		while (orderQueue.size() < 3) {
			orderQueue.add(generateRandomOrder());
		}
	}
	
	/**
	 * Synchronizes the actions of MockOrders with the rest of the
	 * the warehouse.
	 * 
	 * @author Tyler Foster
	 */
	public void tick(int count){
		// On every tick we need to just update the orderQueue if
		// it is low; most of the rest of Orders work is done in the
		// tick method of Picker.
		receiveNewOrders();
		return;
	}
		
	
}

/**
 * Local class that generates a random address. Code is based on Professor Herman's
 * code, with several alterations.
 * 
 * @author Tyler Foster
 * @author Ted Herman
 */
class Address {
	

	SimRandom rand;
	
	
	/**
	 * Address constructor; takes SimRandom as an argument.
	 * 
	 * @author Tyler Foster
	 * @param rand
	 */
	public Address(SimRandom rand) {
		this.rand = rand;
	}
	
	/**
	 * Randomly outputs from a list of street names.
	 * 
	 * @author Tyler Foster
	 * @return String
	 */
	private String chooseStreet() {
		final String[] streetNames = {"Gilbert Street", "College Street", "Iowa Avenue", "Dubuque Street", "Melrose Avenue", "Park Road", "Dodge Street"};
		return streetNames[rand.nextInt(streetNames.length)];
	}
	
	/**
	 * Randomly outputs an integer between 1 and 999.
	 * 
	 * @author Tyler Foster
	 * @return int
	 */
	private int chooseNumber() {
		return 1+rand.nextInt(998);
	}
	
	/**
	 * Randomly chooses a Game of Thrones character for the order holder.
	 * 
	 * @author Tyler Foster
	 * @return String
	 */
	private String chooseName() {
		final String[] names = {"Eddard Stark", "Catelyn Stark", "Arya Stark", "Sansa Stark", "Robb Stark", "Jon Snow", "Bran Stark", "Rickon Stark", "Jaime Lannister", "Cersei Lannister", "Robert Baratheon", "Tyrion Lannister", "Tywin Lannister", "Joffrey Baratheon", "Daenerys Targaryen", "Petyr Baelish", "Jorah Mormont", "Theon Greyjoy", "The Hound", "Khal Drogo", "Viserys Targaryen", "Samwell Tarly", "Tommen Baratheon", "Melisandre", "Margaery Tyrell", "Davos Seaworth", "Stannis Baratheon", "Shireen Baratheon", "Brienne of Tarth", "Roose Bolton", "Ramsay Bolton", "Tormund Giantsbane", "Daario Naharis", "Missandei", "Ellaria Sand", "Oberyn Martell"};
		return names[rand.nextInt(names.length)];
	}

	
	/**
	 * Randomly chooses a state for the order.
	 * 
	 * @author Tyler Foster
	 * @return String
	 */
	private String chooseState() {
		final String[] stateNames = {"IA", "IL", "MN", "MI", "MO", "KS", "OK", "TX", "NY", "OR", "CA", "AZ", "NE", "NV", "MA", "IN", "OH", "TN", "WA"};
		return stateNames[rand.nextInt(stateNames.length)];
	}
	
	/**
	 * Randomly chooses a Game of Thrones city for the order location.
	 * 
	 * @author Tyler Foster
	 * @return String
	 */
	private String chooseCity() {
		final String[] cityNames = {"Winterfell", "King's Landing", "Highgarden", "Riverrun", "Pyke", "Pentos", "Braavos", "Qarth", "Astapor", "Yunkai", "Meereen", "Harrenhal", "Casterly Rock", "Sunspear", "Valyria", "Volantis", "Vaes Dothrak", "The Eyrie", "Castle Black", "The Dreadfort", "The Twins"};
		return cityNames[rand.nextInt(cityNames.length)];
	}
	
	/**
	 * Randomly chooses a 5-digit zip code for the address.
	 * 
	 * @author Tyler Foster
	 * @return String
	 */
	private String chooseZip(){
		String zipCode = "";
		for (int i = 0; i < 5; i++){
			zipCode += "0123456789".charAt(rand.nextInt(10));
		}
		return zipCode;
	}
	
	/**
	 * Calls numerous methods of Address class to construct a random address.
	 * 
	 * @author Tyler Foster
	 * @return String
	 */
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

package production;

import java.util.ArrayList;
import java.lang.Cloneable;

/**
 * 
 * @author Tyler
 *
 */

public class Order implements Tick, Cloneable {
	
	String orderAddress;			// address of this order
	int orderID;					// order ID number
	ArrayList<Item> orderItems;		// list of items included in order
	ArrayList<Item> filledItems;	// list of items that have been filled
	boolean isFilled;				// has the order been filled?
	
	public Order () {
		this.isFilled = false;
		this.orderID = 0;
		this.orderAddress = null;
		this.orderItems = new ArrayList<Item>();
		this.filledItems = new ArrayList<Item>();
	}
	
	public Order(Order o) {
		this.isFilled = o.isFilled;
		this.orderID = o.orderID;
		this.orderAddress = o.orderAddress;
		this.orderItems = o.orderItems;
		this.filledItems = o.filledItems;
	}
	
	public void tick(int count) {
		// TODO: Do something with tick
	}
	
	// Updates the address of the order
	public void updateAddress(String address){
		orderAddress = address;
	}
	
	
	// Updates the id number of the order
	public void updateID(int idNum){
		orderID = idNum;
	}
	
	// Adds an item to the list of items required for this order
	public void addItem(Item a) {
		orderItems.add(a);
	}
	
	// Adds an item to the list of filled items for the order
	public void fillItem(Item a) {
		filledItems.add(a);
	}
	
	// Removes the item from the list of items required for this order
	public void removeItem(Item a) {
		orderItems.remove(a);
	}
	
	// Queries on whether or not the item is required for this order
	public boolean containsItem(Item a) {
		return orderItems.contains(a);
	}
	
	// Returns the address the order is being shipped to
	public String getAddress() {
		return orderAddress;
	}
	
	// Returns the list of items within this order
	public ArrayList<Item> getItems() {
		return orderItems;
	}
	
	// Updates the status of the isFilled boolean
	public void updateFilled(){
		isFilled = true;
	}
	
	// Returns the status of the isFilled boolean
	public boolean isFilled(){
		return isFilled;
	}
	
	// This method converts the order object into a string
	@Override
	public String toString(){
		String output = "";
		output += "Order address:\n" + orderAddress + "\nOrder ID: " + orderID + "\n";
		for (int i = 0; i < orderItems.size(); i++) {
			output += "Item " + (i+1) + ": " + orderItems.get(i) + " " + orderItems.get(i).inOrder() + "\n";
		}
		output += "Has this order been filled? " + isFilled + "\n";
		return output;
	}
}
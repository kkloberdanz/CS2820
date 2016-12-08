package production;

import java.util.ArrayList;
import java.lang.Cloneable;

/**
 * 
 * The Order class is used to create Order objects. Order objects are created when
 * orders come into the warehouse.
 * 
 * @author Tyler Foster
 *
 */

public class Order implements Cloneable {
	
	String orderAddress;			// address of this order
	int orderID;					// order ID number
	ArrayList<Item> orderItems;		// list of items included in order
	ArrayList<Integer> orderQuantities;// author by haoyang wei quantity of the item in the order//
	boolean isFilled;				// has the order been filled?
	
	/**
	 * Constructor for Order.
	 *
	 * @author Tyler Foster
	 */
	public Order () {
		this.isFilled = false;
		this.orderID = 0;
		this.orderAddress = null;
		this.orderItems = new ArrayList<Item>();
		this.orderQuantities=new ArrayList<Integer>();
	}
	
	/**
	 * Clones an Order object.
	 *
	 * @author Tyler Foster
	 * @param o
	 */
	public Order(Order o) {
		this.isFilled = o.isFilled;
		this.orderID = o.orderID;
		this.orderAddress = o.orderAddress;
		this.orderItems = o.orderItems;
		this.orderQuantities=o.orderQuantities;
	}
	
	/**
	 * Setter method that updates the order of the address.
	 *
	 * @author Tyler Foster
	 * @param address
	 */
	public void updateAddress(String address){
		orderAddress = address;
	}
	
	/**
	 * Setter method that updates the id of the order.
	 * 
	 * @author Tyler Foster
	 * @param idNum
	 */
	public void updateID(int idNum){
		orderID = idNum;
	}
	
	/**
	 * Adds an item to the order list.
	 * 
	 * @author Tyler Foster
	 * @param a
	 * @param q
	 */
	public void addItem(Item a,int q) {
		orderItems.add(a);
		orderQuantities.add(q);
	}
	
	/**
	 * Removes an item from order list.
	 * 
	 * @author Tyler Foster
	 * @param a
	 */
	public void removeItem(Item a) {
		orderItems.remove(a);
	}

	/**
	 * Getter method that returns the address stored in the order.
	 *
	 * @author Tyler Foster
	 * @return The string containing the order's address.
	 */
	public String getAddress() {
		return orderAddress;
	}
	
	/**
	 * Getter method that returns the ArrayList of items in the order.
	 *
	 * @author Tyler Foster
	 * @return The ArrayList containing the item's orders.
	 */
	public ArrayList<Item> getItems() {
		return orderItems;
	}
	
	/**
	 * Setter method that sets the status of the order as filled.
	 *
	 * @author Tyler Foster
	 */
	public void updateFilled(){
		isFilled = true;
	}
	
	/**
	 * Getter method that returns the boolean on whether or not
	 * the order has been filled.
	 *
	 * @author Tyler Foster
	 * @return Boolean determining whether or not order is filled.
	 */
	public boolean isFilled(){
		return isFilled;
	}
	
	/**
	 * Converts the order's contents into a legible set of string lines.
	 * 
	 * @author Tyler Foster
	 * @return String
	 */
	@Override
	public String toString(){
		String output = "";
		output += "Order address:\n" + orderAddress + "\nOrder ID: " + orderID + "\n";
		for (int i = 0; i < orderItems.size(); i++) {
			output += "Item " + (i+1) + ": " + orderItems.get(i);
			if (orderItems.get(i).inOrder()){
				output += ", retrieved.\n";
			}
			else{
				output += ", not retrieved.\n";
			}
		}
		output += "Has this order been filled? " + isFilled + "\n";
		return output;
	}
}

package production;

import java.util.ArrayList;

/**
 * 
 * @author Tyler
 *
 */

public class Order implements Tick {
	
	String orderAddress;			// address of this order
	String orderName;				// who this order is going to
	ArrayList<MockItem> orderItems;		// list of items included in order
	boolean isFilled;				// has the order been filled?
	
	
	// Constructor
	public Order (String address, ArrayList<MockItem> items){
		orderAddress = address;
		orderItems = items;
		isFilled = false;
	}
	
	public void tick(int count) {
		// TODO: Do something with tick
	}
	
	// Adds an item to the list of items required for this order
	public void addItem(MockItem a) {
		orderItems.add(a);
	}
	
	// Removes the item from the list of items required for this order
	public void removeItem(MockItem a) {
		orderItems.remove(a);
	}
	
	// Queries on whether or not the item is required for this order
	public boolean containsItem(MockItem a) {
		return orderItems.contains(a);
	}
	
	// Returns the address the order is being shipped to
	public String getAddress() {
		return orderAddress;
	}
	
	// Returns the name of the person who placed the order
	public String getName() {
		return orderName;
	}
	
	// Returns the list of items within this order
	public ArrayList<MockItem> getItems() {
		return orderItems;
	}
}
    
    /*public class Item {
        public Item(){}
    }
    
    public class Orders {
        ArrayList<Item> items;
        String status;
        
        public Orders(){
            items = new ArrayList<>();
            status = "not started";
        }
        
        public void addItem(Item a){
            items.add(a);
        }
        
        public void removeItem(Item a){
            items.remove(a);
        }
        
        public void contains(Item a){
            items.contains(a);
        }
        
        public ArrayList<Item> getItems(){
            return items;
        }
        
    }*/

package production;

import java.lang.Cloneable;

public class Picker implements Tick, Cloneable {
	
	static Order currentOrder; // current order that the Picker is working on
	static Order copiedOrder; // order that is going to be put into the bin
	static Bin currentBin; // current bin that is going to be sent to the belt
	static Item neededItem; // the item that the picker is currently attemping to get
	static Shelf atStation; // if a robot has arrived with a shelf, update this variable
	
	static MockOrders O; // order subsystem, check for arraylist of orders
	static Bin pickerBin; // this is the bin that orders go in once complete
	static MockFloor F; // get the floor
	static Inventory I; // get the inventory
	static MockRobotScheduler R; // get the robot scheduler
	
	// constructor
	public Picker() {
		currentOrder = null;
		currentBin = null;
		neededItem = null;
		atStation = null;
	}
	
	@Override
	public String toString(){
		String output = "There is currently no order.";
		if (currentOrder != null){
			output = "Current order at the picker station:\n" + currentOrder.toString();
		}
		if (currentBin != null){
			output+= "\nCurrent bin at the picker station:\n" + currentBin.toString();
		}
		if (currentBin == null){
			output+= "\nThe picker bin is not full yet.\n";
		}
		if (neededItem == null){
			output+= "\nThere is currently no needed item.";
		}
		else{
			output+= "\nThe picker is currently looking for a " + neededItem.get_name();
			return output;
		}
		return output;
	}
	
	// returns the current bin that the picker is using
	public static Bin getCurrentBin() {
		return currentBin;
	}
	
	// the Belt will use this to remove the current bin AFTER
	// it has been taken to the belt
	public static void removeCurrentBin() {
		currentBin = null;
	}
	
	// Updates the current order that the picker is working on
	public static void updateOrder(Order o){
		currentOrder = o;
	}
	
	// Updates the current item that the picker needs
	public static void updateNeededItem(Item i){
		neededItem = i;
	}
	
	// Gets the current item that the picker needs
	public static Item getNeededItem(Item i){
		return neededItem;
	}
	
	// This method locates the shelf that contains the needed item
	public static Shelf locateItem(MockFloor F, Item I){
		for (int i = 0; i < MockFloor.shelves.size(); i++) {
			if (MockFloor.shelves.get(i).contents.containsKey(I)) {
				return MockFloor.shelves.get(i);
			}
		}
		return null;
	}
	
	// This method notifies the RobotScheduler of what Shelf needs to be brought
	// to the picker
	public static void notifyRobot(Shelf S, MockRobotScheduler R){
		
	}

	// This method transfers the items from a shelf into the order.
	public static void transferItems(Item i, int num, Shelf S){
		boolean validTransfer = S.removeItem(i, num, false);
		if (validTransfer){
			for (int j = 0; j < currentOrder.orderItems.size(); j++) {
				if (currentOrder.orderItems.get(j).get_id_number() == i.get_id_number()) {
					currentOrder.orderItems.get(j).setInOrder();
				}
			}
		}
	}
	
	// This method checks the list of filled order items to see if it the same
	// size as the needed order items list; if it is, then all items have been
	// collected
	public static boolean checkOrderItems(){
		for (int i = 0; i < currentOrder.orderItems.size(); i++) {
			if (currentOrder.filledItems.contains(currentOrder.orderItems.get(i)) == false) {
				return false;
			}
		}
		currentOrder.updateFilled();
		return true;
	}
	
	// This method checks the isFilled boolean of the order; if it
	// is true, then put the order in a bin and send it to the
	// the belt, if not then do nothing.
	/*public static boolean checkCompletion(){
		if (currentOrder.isFilled()){
			currentBin = new Bin();
			copiedOrder = new Order(currentOrder);
			currentBin.setOrder(copiedOrder);
			currentBin.setComplete();
			currentOrder = MockOrders.getNextOrder();
			MockOrders.receiveNewOrders();
			return true;
		}
		return false;
	}*/
	
	// This method moves the current order to the bin.
	public static void moveFinishedOrder(){
		currentBin = new Bin();
		copiedOrder = new Order(currentOrder);
		currentBin.setOrder(copiedOrder);
		currentBin.setComplete();
	}
	
	public void tick(int count){
		// If there is currently no order, then we need to pull the first
		// order from the queue.
		if (currentOrder == null){
			updateOrder(MockOrders.getNextOrder());
			System.out.println("Picker is starting a new order.");
			return;
		}
		
		// If the current order has been filled, then it is time to move
		// move it to the bin. 
		if (currentOrder.isFilled()){
			moveFinishedOrder();
			System.out.println("Picker put the completed order in the bin.");
			currentOrder = null;
			return;
		}
		
		// If the order is neither null or complete, then check to see if
		// we are still waiting on an item from a robot. If yes, then just
		// wait more.
		if (neededItem != null) {
			return;
		}
		
		// At this point we should iterate through the currentOrder.orderItems()
		// to see which ones have been retrieved
		Item nextItem = null;
		for (Item myItem : currentOrder.orderItems) {
			if (myItem.inOrder()){
				continue;
			}
			nextItem = myItem;
			break; // This means we found an item not in the order
		}
		
		// If there is no nextItem then that means all items have been picked
		if (nextItem == null) {
			currentOrder.updateFilled();
			return;
		}
		
		// At this point, we need to look for the item
		Shelf s = locateItem(F, nextItem);
		
		// If the shelf is not available then the item does not exist,
		// and inventory needs to restock
		if (s == null) {
			return;
		}
		
		// Set the field of neededItem to be the next item
		neededItem = nextItem;
		
		// The very last part of this tick involves communicating with the
		// robot to request a shelf
	};
}

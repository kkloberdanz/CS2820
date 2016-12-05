package production;

import java.awt.Point;
import java.lang.Cloneable;
import java.util.*;

public class Picker implements Tick, Cloneable {
	
	static Order currentOrder; // current order that the Picker is working on
	static Order copiedOrder; // order that is going to be put into the bin
	static Bin currentBin; // current bin that is going to be sent to the belt
	static Item neededItem; // the item that the picker is currently attempting to get
	static Shelf currentShelf; // keeps track of what shelf the picker currently wants
	static Shelf shelfToReturn; // keeps track of the shelf that the robot needs to return
	static Point returnLocation; // keeps track of the point that the shelf needs to be returned to
	
	static MockOrders O; // order subsystem, check for arrayList of orders
	static MockFloor F; // get the floor
	static Inventory I; // get the inventory
	static MockRobotScheduler R; // get the robot scheduler
	
	// constructor
	public Picker(MockFloor F, MockOrders O, Inventory I, MockRobotScheduler R) {
		Picker.F = F;
		Picker.O = O;
		Picker.I = I;
		Picker.R = R;
		currentOrder = null;
		currentBin = null;
		neededItem = null;
		currentShelf = null;
		shelfToReturn = null;
		returnLocation = null;
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
	
	// the Belt will use this to remove the current bin after
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
		for (int i = 0; i < MockFloor.getShelves().size(); i++) {
			if (MockFloor.getShelves().get(i).contents.containsKey(I)) {
				return MockFloor.getShelves().get(i);
			}
		}
		return null;
	}
	
	// This method notifies the RobotScheduler of what Shelf needs to be brought
	// to the picker
	public static void notifyRobot(Shelf S, MockRobotScheduler R){
		
	}

	// This method transfers the items from a shelf into the order, and then it
	// updates the boolean of that item to be in the order
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
	
	// This method moves the current order to the bin.
	public static void moveFinishedOrder(){
		currentBin = new Bin();
		copiedOrder = new Order(currentOrder);
		currentBin.setOrder(copiedOrder);
		currentBin.setComplete();
	}
	
	// This is the tick method for Picker; it synchronizes the Picker's actions
	// with the rest of the static objects in the warehouse.
	public void tick(int count){
		// If there is currently no order, then we need to pull the first
		// order from the queue.
		Point pickerStation = new Point(0,1);
		
		if (currentOrder == null){
			updateOrder(MockOrders.getNextOrder());
			System.out.println("Picker is starting a new order.");
			System.out.println(currentOrder.toString());
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
		// point(0,1) is occupied; if not, then just remain idle for this
		// tick while waiting for the robot to arrive
		if ((F.isSpaceOccupied(pickerStation) == false) && currentShelf != null) {
			return;
		}
		
		// If the space next to the picker station HAS been occupied by
		// a robot, transfer the necessary items from the shelf to the
		// current order, and then set the current shelf equal to null
		if (F.isSpaceOccupied(pickerStation) && currentShelf != null) {
			transferItems(neededItem, 1, currentShelf);
			shelfToReturn = currentShelf;
			currentShelf = null;
			return;
		}
		
		// If the space next to the picker station is occupied but the
		// current shelf is null, this means we have already transferred items
		// but are simply waiting to move the shelf, so send the shelf back
		// to where it came from
		if (F.isSpaceOccupied(pickerStation) && currentShelf == null) {
			MockRobotScheduler.moveShelf(shelfToReturn, returnLocation);
		}
		
		// At this point we should iterate through the currentOrder.orderItems()
		// to see which ones have been retrieved
		Item nextItem = null;
		for (Item myItem : currentOrder.orderItems) {
			if (myItem.inOrder()){
				continue;
			}
			System.out.println("The picker is looking for a " + myItem.get_name());
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
		
		// Set the field of neededItem to be the next item, currentShelf to be the
		// needed shelf
		neededItem = nextItem;
		currentShelf = s;
		returnLocation = s.shelfLoc;
		
		// Finally, request a new robot to bring the needed shelf to the picker.
		int valid = MockRobotScheduler.moveShelf(currentShelf, pickerStation);
		
		// If no robots are available, reset the previous variables to be null
		// and wait another tick.
		if (valid == -1){
			neededItem = null;
			currentShelf = null;
			returnLocation = null;
		}
	};
}

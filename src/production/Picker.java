package production;

import java.awt.Point;
import java.lang.Cloneable;
import java.util.*;

public class Picker implements Tick, Cloneable {

	static Order currentOrder; // current order that the Picker is working on
	static Order copiedOrder; // order that is going to be put into the bin
	static Bin currentBin; // current bin that is going to be sent to the belt
	static Item neededItem; // the item that the picker is currently attempting
							// to get
	static Shelf currentShelf; // keeps track of what shelf the picker currently
								// wants
	static Shelf shelfToReturn; // keeps track of the shelf that the robot needs
								// to return
	static Point returnLocation; // keeps track of the point that the shelf
									// needs to be returned to
	static int robotIndex; // keeps track of the index of the robot that is
									// currently moving to the picker station

	static MockOrders O; // order subsystem, check for arrayList of orders
	static MockFloor F; // get the floor
	static Inventory I; // get the inventory
	static MockRobotScheduler R; // get the robot scheduler

	/**
	 * The constructor for Picker; takes the floor, orders, inventory, and
	 * robotScheduler as arguments, and has several fields: currentOrder,
	 * currentBin, neededItem, shelfToReturn, and returnLocation.
	 * 
	 * @author Tyler Foster
	 * @param F
	 * @param O
	 * @param I
	 * @param R
	 */
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
		robotIndex = -1;
	}


	@Override
	/**
	 * Converts the Picker object to a string.
	 * 
	 * @author Tyler Foster
	 */
	public String toString() {
		String output = "There is currently no order.";
		if (currentOrder != null) {
			output = "Current order at the picker station:\n" + currentOrder.toString();
		}
		if (currentBin != null) {
			output += "\nCurrent bin at the picker station:\n" + currentBin.toString();
		}
		if (currentBin == null) {
			output += "\nThe picker bin is not full yet.\n";
		}
		if (neededItem == null) {
			output += "\nThere is currently no needed item.";
		} else {
			output += "\nThe picker is currently looking for a " + neededItem.get_name();
			return output;
		}
		return output;
	}

	/**
	 * Getter method that returns the currentBin Picker field.
	 * 
	 * @author Tyler Foster
	 * @return The currentBin field of Picker.
	 */
	public static Bin getCurrentBin() {
		return currentBin;
	}

	/**
	 * Setter method that updates the currentBin Picker field.
	 * @author Tyler Foster
	 */
	public static void removeCurrentBin() {
		currentBin = null;
	}

	/**
	 * Setter method that updates the currentOrder Picker field.
	 * 
	 * @author Tyler Foster
	 * @param o
	 */
	public static void updateOrder(Order o) {
		currentOrder = o;
	}

	/**
	 * Setter method that updates the neededItem Picker field.
	 * 
	 * @author Tyler Foster
	 * @param i
	 */
	public static void updateNeededItem(Item i) {
		neededItem = i;
	}

	/**
	 * Getter method that returns the neededItem Picker field.
	 * 
	 * @author Tyler Foster
	 * @param i
	 * @return The neededItem field of Picker.
	 */
	public static Item getNeededItem(Item i) {
		return neededItem;
	}

	/**
	 * This method takes an Item as input and finds the Shelf that the
	 * desired item is on.
	 * 
	 * @author Tyler Foster
	 * @param I
	 * @return The shelf that the needed item is on.
	 */
	
	public static Object locateItem(Item I) {
		Object[] keys = MockFloor.getShelves().keySet().toArray();
		for (Object o : keys) {
			if (MockFloor.getShelves().get(o).contains(I.get_name())) {
				return o;
			}
		}
		return null;
	}
	
	/*public static Shelf locateItem(Item I) {
		for (Integer i : MockFloor.getShelves().keySet()) {
			Shelf s = MockFloor.getShelves().get(i);
			if (s.contains(I)) {
				return s;
			}
		}
		return null;
	}*/
	//

	/**
	 * This method transfers the desired item from the Shelf at (0,1) to
	 * the current order.
	 * 
	 * @author Tyler Foster
	 * @param i
	 * @param num
	 * @param S
	 */
	public static void transferItems(Item i, int num, Shelf S) {
		boolean validTransfer = MockFloor.getShelves().get(S.getID()).removeItem(i, num, false);
		if (validTransfer) {
			MockFloor.setShelf(S);
			for (int j = 0; j < currentOrder.orderItems.size(); j++) {
				if (currentOrder.orderItems.get(j).get_id_number() == i.get_id_number()) {
					currentOrder.orderItems.get(j).setInOrder();
				}
			}
		}
		else {
			Inventory.restockItem(i);
		}
	}

	/**
	 * This method creates a new bin and transfers the completed order into that bin.
	 * 
	 * @author Tyler Foster
	 */
	public static void moveFinishedOrder() {
		System.out.println("Picker moves a finished order to the bin.");
		currentBin = new Bin();
		copiedOrder = new Order(currentOrder);
		currentBin.setOrder(copiedOrder);
		currentBin.setComplete();
	}

	/**
	 * 
	 * Tick synchronizes the actions of the Picker with the rest of the warehouse.
	 * 
	 * @author Tyler Foster
	 */
	public void tick(int count) {
		// Find out whether or not there is a robot at the picker station
		boolean atStation = false;
		for (int i = 0; i < MockFloor.robots.size(); i++) {
			Robot r = MockFloor.robots.get(i);
			if (Debug.verboseLevel() >= 3) {
				System.out.println(r.toString());
			}
			double x = r.getLocation(r).getX();
			double y = r.getLocation(r).getY();
			if ((x == 0.0) && (y == 1.0) && (neededItem != null)) {
				atStation = true;
			}
		}

		// If there is currently no order, then we need to pull the first
		// order from the queue.
		if (currentOrder == null) {
			updateOrder(MockOrders.getNextOrder());
			if (Debug.verboseLevel() >= 1) {
				System.out.println("\nPicker is starting a new order.\n");
				System.out.println(currentOrder.toString());
			}
			return;
		}

		// If the current order has been filled, then it is time to move
		// move it to the bin.
		if (currentOrder.isFilled()) {
			moveFinishedOrder();
			if (Debug.verboseLevel() >= 1) {
				System.out.println("\nPicker put the completed order in the bin.");
				System.out.println(currentOrder.toString());
			}
			currentOrder = null;
			return;
		}

		// If the order is neither null or complete, then check to see if
		// point(0,1) is occupied; if not, then just remain idle for this
		// tick while waiting for the robot to arrive
		if (!atStation && currentShelf != null) {
			if (Debug.verboseLevel() >= 3) {
				System.out.println("Picker is waiting on Robot " + robotIndex + " to retrieve a " + neededItem.get_name() + ".");
			}
			return;
		}

		// If the space next to the picker station HAS been occupied by
		// a robot, transfer the necessary items from the shelf to the
		// current order, and then set the current shelf equal to null
		if (atStation && (currentShelf != null)) {
			transferItems(neededItem, 1, currentShelf);
			if (Debug.verboseLevel() >= 1) {
				System.out.println("A " + neededItem.get_name() + " has been transferred to the current order.");
			}
			shelfToReturn = currentShelf;
			currentShelf = null;
			return;
		}

		// If the space next to the picker station is occupied but the
		// current shelf is null, this means we have already transferred items
		// but are simply waiting to move the shelf, so send the shelf back
		// to where it came from
		if (atStation && (currentShelf == null)) {
			if (Debug.verboseLevel() >= 2) {
				System.out.println("Sending Robot " + robotIndex + " back.");
			}
			MockRobotScheduler.returnShelf(shelfToReturn, returnLocation, robotIndex);
			shelfToReturn = null;
			robotIndex = -1;
			neededItem = null;
			return;
		}

		// At this point we should iterate through the currentOrder.orderItems()
		// to see which ones have been retrieved
		Item nextItem = null;
		for (Item myItem : currentOrder.orderItems) {
			if (myItem.inOrder()) {
				continue;
			}
			if (Debug.verboseLevel() >= 3) {
				System.out.println("The picker is looking for a " + myItem.get_name());
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
		Object shelfKey = locateItem(nextItem);
		Shelf s = MockFloor.getShelves().get(shelfKey);

		// If the shelf is not available then the item does not exist,
		// and inventory needs to restock
		if (s == null) {
			if (Debug.verboseLevel() >= 1) {
				System.out.println("Could not find a " + nextItem.get_name() + " from the shelves.");
			}
			Inventory.restockItem(nextItem);
			return;
		}

		// Set the field of neededItem to be the next item, currentShelf to be
		// the
		// needed shelf
		neededItem = nextItem;
		currentShelf = s;
		returnLocation = s.shelfLoc;

		// Finally, request a new robot to bring the needed shelf to the picker.
		int valid = MockRobotScheduler.moveShelf(currentShelf, new Point(0, 1));

		// If no robots are available, reset the previous variables to be null
		// and wait another tick..
		if (valid == -1) {
			if (Debug.verboseLevel() >= 2) {
				System.out.println("No robots are available.");
			}
			neededItem = null;
			currentShelf = null;
			returnLocation = null;
		}
		
		if (valid != -1) {
			robotIndex = valid;
			if (Debug.verboseLevel() >= 1) {
				System.out.println("Robot " + robotIndex + " is going to retrieve shelf " + s.getID());
			}
		}
	};
}

package production;

import java.lang.Cloneable;

public class Picker implements Tick, Cloneable {
	
	static Order currentOrder; // current order that the Picker is working on
	static Order copiedOrder; // order that is going to be put into the bin
	static Bin currentBin; // current bin that is going to be sent to the belt
	static MockOrders O; // order subsystem, check for arraylist of orders
	static Bin pickerBin; // this is the bin that orders go in once complete
	static MockFloor F; // get the floor
	static Inventory I; // get the inventory
	static RobotScheduler R; // get the robot scheduler
	
	// constructor
	public Picker() {
		currentOrder = null;
		currentBin = null;
	}
	
	@Override
	public String toString(){
		String output = "There is currently no order.";
		if (currentOrder != null){
			output = "Current order at the picker station:\n" + currentOrder.toString();
		}
		if (currentBin != null){
			output+= "\nCurrent bin at the picker station:\n" + currentBin.toString();
			return output;
		}
		else {
			output+= "\nThe picker bin is not full yet.";
			return output;
		}
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
	
	// This method locates the shelf that contains the needed item
	public static Shelf locateItem(Floor F, Inventory I){
		return null;
	}
	
	// This method notifies the RobotScheduler of what Shelf needs to be brought
	// to the picker
	public static void notifyRobot(Shelf S, RobotScheduler R){
		
	}

	// This method transfers the items from a shelf into the order.
	// NOTE: Using MockItem until Inventory works in conjunction with Orders, 
	// so fillItem currently creates an error.
	public static void transferItems(Item I, int num, Shelf S){
		boolean validTransfer = S.removeItem(I, num, false);
		if (validTransfer){
			for (int k = 0; k < num; k++){
				currentOrder.fillItem(I);
			}
		}
	}
	
	// This method checks the isFilled boolean of the order; if it
	// is true, then put the order in a bin and send it to the
	// the belt, if not then do nothing.
	public static boolean checkCompletion(){
		if (currentOrder.isFilled()){
			currentBin = new Bin();
			copiedOrder = new Order(currentOrder);
			currentBin.setOrder(copiedOrder);
			currentBin.setComplete();
			currentOrder = O.getNextOrder();
			return true;
		}
		return false;
	}
	
	public void tick(int count){
		boolean isDone = checkCompletion();
		if (isDone == false){
			// this is where I need to figure out stuff with the robot
		}
	};
}
package production;

import java.lang.Cloneable;

public class Picker implements Tick, Orders, Cloneable {
	
	static Order currentOrder; // current order that the Picker is working on
	static Order copiedOrder; // order that is going to be put into the bin
	static Bin currentBin; // current bin that is going to be sent to the belt
	static MockOrders O; // order subsystem, check for arraylist of orders
	static Bin pickerBin; // this is the bin that orders go in once complete
	static Floor F; // get the floor
	static Inventory I; // get the inventory
	static RobotScheduler R; // get the robot scheduler
	
	// constructor
	public Picker() {
		currentOrder = null;
		currentBin = null;
	}
	
	// returns the current bin that the picker is using
	public static Bin getCurrentBin() {
		return currentBin;
	}
	
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
				// omg this works
			}
		}
		// 
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
		checkCompletion();
	};
}

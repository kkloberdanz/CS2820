package production;

import java.lang.Cloneable;

public class Picker implements Tick, Orders, Cloneable {
	
	Order currentOrder; // current order that the Picker is working on
	Order copiedOrder; // order that is going to be put into the bin
	Bin pickerBin; // this is the bin that orders go in once complete
	Floor F; // get the floor
	Inventory I; // get the inventory
	RobotScheduler R; // get the robot scheduler
	
	public Picker() {
		currentOrder = null;
	}
	
	// Updates the current order that the picker is working on
	public void updateOrder(Order o){
		currentOrder = o;
	}
	
	// This method locates the shelf that contains the needed item
	public Shelf locateItem(Floor F, Inventory I){
		return null;
	}
	
	// This method notifies the RobotScheduler of what Shelf needs to be brought
	// to the picker
	public void notifyRobot(Shelf S, RobotScheduler R){
		
	}

	// This method transfers the items from a shelf into the order.
	// NOTE: Using MockItem until Inventory works in conjunction with Orders, 
	// so fillItem currently creates an error.
	public void transferItems(Item I, int num, Shelf S){
		boolean validTransfer = S.removeItem(I, num, false);
		if (validTransfer){
			for (int k = 0; k < num; k++){
				currentOrder.fillItem(I);
			}
		}
		// 
	}
	
	public void sendBintoBelt(Bin x, Belt B){
		// Communicate with Belt for this
	}
	
	// This method checks the isFilled boolean of the order; if it
	// is true, then put the order in a bin and send it to the
	// the belt, if not then do nothing.
	public boolean checkCompletion(){
		if (currentOrder.isFilled()){
			pickerBin = new Bin();
			copiedOrder = new Order(currentOrder);
			pickerBin.setOrder(copiedOrder);
			pickerBin.setComplete();
			// NOTIFY THE BELT THAT THE PICKER BIN IS COMPLETE
			currentOrder = null;
			return true;
		}
		return false;
	}
	
	public void tick(int count){
		
	};
}

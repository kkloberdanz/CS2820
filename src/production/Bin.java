package production;

import java.lang.Cloneable;

/**
 * 
 * @author Tyler
 *
 */

public class Bin implements Cloneable {
	Order order; // the Order that this bin is associated with
	boolean complete; // this will be set to true once the order is complete
	
	// constructor. An empty bin initially has no order and is not complete.
	public Bin() {
		order = null;
		complete = false;
	}
	
	// converts bin to a string representation
	@Override
	public String toString(){
		String output = order.toString();
		output += "\nIs this order complete? " + complete;
		return output;
	}
	
	// this constructor clones a bin
	public Bin(Bin b) {
		this.order = b.order;
		this.complete = b.complete;
	}
	
	// this method returns the completion status of the bin
	public boolean isComplete() {
		return complete;
	}
	
	// this method sets the completion status of the bin
	public void setComplete() {
		complete = true;
	}
	
	// this method returns the order that is placed inside the bin
	public Order getOrder() {
		return order;
	}
	
	// this method places an order in the bin
	public void setOrder(Order x) {
		order = x;
	}
}

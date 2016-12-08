package production;

/**
 * The Bin class creates a Bin object that is used to transfer orders from
 * the Picker to the Belt.
 * 
 * @author Tyler Foster
 *
 */

public class Bin{
	Order order; // the Order that this bin is associated with
	boolean complete; // this will be set to true once the order is complete
	
	/**
	 * Bin constructor, starts off with a null order inside and
	 * a false completion status.
	 *
	 * @author Tyler Foster
	 */
	public Bin() {
		order = null;
		complete = false;
	}
	
	/**
	 * 
	 * Converts the Bin object to a readable String.
	 * 
	 * @author Tyler Foster
	 * 
	 */
	@Override
	public String toString(){
		String output = order.toString();
		output += "\nIs this order complete? " + complete;
		return output;
	}
	
	/**
	 * Getter method that returns the completion status of the bin.
	 *
	 * @author Tyler Foster
	 * @return boolean
	 */
	public boolean isComplete() {
		return complete;
	}
	
	/**
	 * Setter method that sets the completion status of the bin to be true.
	 *
	 * @author Tyler Foster
	 */
	public void setComplete() {
		complete = true;
	}
	
	/**
	 * Getter method that returns the current order inside the bin.
	 *
	 * @author Tyler Foster
	 * @return
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * Setter method that sets the current order inside the bin.
	 *
	 * @author Tyler Foster
	 * @param x
	 */
	public void setOrder(Order x) {
		order = x;
	}
}

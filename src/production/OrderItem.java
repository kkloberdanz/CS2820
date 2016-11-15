package production;

/**
 * 
 * @author Tyler
 *
 */

public class OrderItem extends Item {
	boolean inBin;
	
	// This is a subtype of the Item object that
	// has an additional field that determines
	// whether or not an item is in a bin.
	
	public OrderItem(int number, String title){
		super(number, title);
		inBin = false;
	}
	
	public OrderItem(Item N){
		this(N.get_id_number(), N.get_name());
	}
	
	public boolean filled() {return inBin; }
	public void setFilled() {inBin = true; }
}

package production;

import java.util.ArrayList;

/**
 * Belt class for warehouse project. Moves items along the belt
 * @author Tyler Sporrer
 */

public class Belt implements Tick{
	
	ArrayList<Integer> belt = new ArrayList<Integer>(10);
	ArrayList<Integer> ship = new ArrayList<Integer>();
	
	int tick = 0;
	
	/**
	 * packBin method takes order id and puts it into an Array list. When Bin gets to the 10th position, Boxes item and sends to shipping.
	 * @author Tyler Sporrer
	 */
	public void packBin(int x){
		int y;
		belt.add(x);
		if(belt.get(9) != 0){
			y = belt.get(9);
			ship.add(y);
			belt.remove(9);
		}else{
			belt.remove(9);
		}	
	}
	
	/**
	 * showBelt method shows the the order ID of the bins on the belt
	 * @author Tyler Sporrer
	 */
	public void showBelt(){
		for(int i = 0; i < belt.size(); i++){
			System.out.println(belt.get(i));
		}
	}
	
	/**
	 * showShip method shows the order ID of the orders that have been packed and sent to shipping
	 * @author Tyler Sporrer
	 */
	public void showShip(){
		for(int i = 0; i < ship.size(); i++){
			System.out.println(ship.get(i));
		}
	}
	
	/**
	 * tick method will use the previous methods when called.
	 * @author Tyler Sporrer
	 */
	public void tick(int x){
		
	}

}
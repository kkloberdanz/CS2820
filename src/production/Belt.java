package production;

import java.util.ArrayList;

/**
 * Belt class for warehouse project. Moves items along the belt
 * @author Tyler Sporrer
 */

public class Belt implements Tick{
	
	ArrayList<Object> belt = new ArrayList<Object>();
	ArrayList<Object> ship = new ArrayList<Object>();
	int start = 0;
	int current = 0;
	
	
	/**
	 * packBin method takes a bin object and puts it into an Array list. 
	 * @author Tyler Sporrer
	 */
	public void loadBelt(Object x){
		belt.add(x);
		if(x != null){ // if the bin is not empty(null), then calls removeCurrentBin to reset the bin to null
			Picker.removeCurrentBin();
			if(Debug.verboseLevel() >= 1){
				System.out.println("Full bin added to belt.");
			}
		}
	}
		
	/**
	 * packBox checks contents of Arrray list at the 10th position. If there is an object, packs it into the shipping array. if null, removes the null.
	 * @author Tyler Sporrer
	 */
	public void packBox(int x){
		Object y;
		if(belt.get(9) != null){
			y = belt.get(9);
			ship.add(y);
			if(Debug.verboseLevel() >= 1){
				System.out.println("Box added to shipping belt.");
			}
			belt.remove(9);
			delay(x); // look at
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
	 * checks if a package is at the picker position, if it is creates a delay for 3 ticks.
	 * @author Tyler Sporrer
	 */
	public boolean delay(int x){ //work on
		if(start == 0){
			if(belt.get(9) != null){
				start = x;
				return true;
			}else{
				return false;
			}
		}else{
			current = x;
		}
			
		if(current - start == 3){
			start = 0;
			current = 0;
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * tick method will use the previous methods when called.
	 * @author Tyler Sporrer
	 */
	public void tick(int x){
		if(delay(x) == false){
			this.loadBelt(Picker.getCurrentBin()); // checks/grabs current bin from picker and adds to belt
			this.packBox(x); // loads the boxed item onto the shipping belt
		}
				
	}

}
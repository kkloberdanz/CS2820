package production;

import java.util.ArrayList;

/**
 * Belt class for warehouse project. Moves items along the belt
 * @author Tyler Sporrer
 */

public class Belt implements Tick{
	
	ArrayList<Object> belt = new ArrayList<Object>();
	ArrayList<Object> ship = new ArrayList<Object>();
	
	
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
	public void packBox(){
		Object y;
		if(belt.get(9) != null){
			y = belt.get(9);
			ship.add(y);
			if(Debug.verboseLevel() >= 1){
				System.out.println("Box added to shipping belt.");
			}
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
		
		this.loadBelt(Picker.getCurrentBin()); // checks/grabs current bin from picker and adds to belt
		this.packBox(); // loads the boxed item onto the shipping belt
		

		
	}

}
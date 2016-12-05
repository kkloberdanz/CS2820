package production;

import java.util.ArrayList;

/**
 * Belt class for warehouse project. Moves items along the belt
 * @author Tyler Sporrer
 */

public class Belt implements Tick{
	
	static ArrayList<Object> belt = new ArrayList<Object>();
	static ArrayList<Object> ship = new ArrayList<Object>();
	static int start = 0;
	static int current = 0;
	
	
	/**
	 * packBin method takes a bin object and puts it into an Array list. 
	 * @author Tyler Sporrer
	 */
	public static void loadBelt(Object x){
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
	public static void packBox(int x){
		Object y;
		if(belt.get(0) != null){
			y = belt.get(0);
			ship.add(y);
			if(Debug.verboseLevel() >= 1){
				System.out.println("Box added to shipping belt.");
			}
			belt.remove(0);
		}else{
			belt.remove(0);
		}	
	}

	/**
	 * checks if a package is at the picker position, if it is creates a delay for 3 ticks.
	 * @author Tyler Sporrer
	 */
	public static boolean delay(int x){ //work on
		if(start == 0){
			if(belt.get(4) != null){
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
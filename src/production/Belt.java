package production;

import java.util.*;

/**
 * Belt class for warehouse project. Moves items along the belt
 * @author Tyler Sporrer
 */

public class Belt implements Tick{
	
	static LinkedList<Object> belt = new LinkedList<Object>();
	static LinkedList<Object> ship = new LinkedList<Object>();
	static int start = 0;
	static int current = 0;
	static int number = 0;
	static Picker picker;
	
	
	/**
	 * packBin method takes a bin object and puts it into an LinkedList. 
	 * @author Tyler Sporrer
	 */
	public static void loadBelt(Object x){
		belt.addFirst(x);
		number = number + 1;
		if(x != null){ // if the bin is not empty(null), then calls removeCurrentBin to reset the bin to null
			Picker.removeCurrentBin();
			if(Debug.verboseLevel() >= 1){
				System.out.println("Full bin added to belt.");
			}
		}
		
	}
		
	/**
	 * packBox checks contents of LinkedList at the 5th position. If there is an object, packs it into the shipping LinkedList. if null, removes the null.
	 * @author Tyler Sporrer
	 */
	public static void packBox(int x){
		Object y;
		if(number == 5){
			if(Debug.verboseLevel() >= 5){
				System.out.println("----------------"+belt.get(4)+"----------------");
			}
			if(belt.get(4) == null){
				belt.remove(4);
				if(Debug.verboseLevel() >= 5){
					System.out.println("Null is removed from belt");
				}
				number = number - 1;
			}else{
				y = belt.get(4);
				ship.addFirst(y);
				belt.remove(4);
				number = number - 1;
				if(Debug.verboseLevel() >= 1){
					System.out.println("Box added to shipping belt.");
				}
			}
		}
	}

	/**
	 * checks if a package is at the picker position, if it is creates a delay for 3 ticks.
	 * @author Tyler Sporrer
	 */
	public static boolean delay(int x){ //work on
		if(start == 0){
			if(number == 4){
				//System.out.println("------------------------NUMBER = 4 ---------------------------------");
				if(belt.peekLast() == null){
					//System.out.println("------------------------ No Belt Delay------------------------------");
					return false;
				}else{
					start = x;
					//System.out.println("----------------------- start Belt delay ---------------------------");
					return true;
				}
			}else{
				//System.out.println("-------------------------- No Belt Delay 2-----------------------------");
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
			Belt.loadBelt(Picker.getCurrentBin()); // checks/grabs current bin from picker and adds to belt
			Belt.packBox(x); // loads the boxed item onto the shipping belt
		}
				
	}

}
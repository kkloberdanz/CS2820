package production;

import java.awt.Point;
import java.util.HashMap;


public class Shelf implements Cloneable {
	HashMap<String,Integer> contents;
	boolean beingCarried;
	Point shelfLoc;
	int capacity;
	int currItems = 0;
	String sName;
	private Integer id;
	
	private static int counter = 0;
	
	public Shelf(Point point, boolean bool, int Int, String name){
		contents = new HashMap<>();
        shelfLoc=point;
        beingCarried=bool;
        capacity = Int;
        sName = name;
        currItems = 0;
        id = counter++;
        
	}
	
	public Shelf(Shelf s) {
		this.contents = s.contents;
		this.shelfLoc = s.shelfLoc;
		this.beingCarried = s.beingCarried;
		this.capacity = s.capacity;
		this.sName = s.sName;
		this.currItems = s.currItems;
		this.id = s.id;
	}
	
	/**
	 * This method takes an Item as input and returns a boolean that depicts
	 * whether or not the item was on this shelf.
	 * 
	 * @author Tyler Foster
	 * @param I
	 * @return boolean
	**/
	
	public boolean contains(String I) {
		for (String i : contents.keySet()) {
			if (I == i) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @author Kyle Kloberdanz
	 * @param b
	 */
	public void setBeingCarried(boolean b) {
		this.beingCarried = b;
	}
	
	/**
	 * @author Kyle Kloberdanz
	 * @return
	 */
	public boolean isBeingCarried() {
		return this.beingCarried;
	}
	
	/**
	 * adds an amount of an item to the shelf
	 * 
	 * @param Item I
	 * @param int num
	 * @return boolean
	 */
	public boolean addItem(Item I,int num){
		if(num+currItems > capacity){
			return false;
		}
		if (contents.containsKey(I)){
			//adds the number of given items to the already 
			//listed items if the item already exists in the hashmap
                    contents.put(I.get_name(), contents.get(I)+num);
                    currItems+=num;
                    return true;
                }else{
                	//adds item to the hashmap
                    contents.put(I.get_name(),num);
                    currItems+=num;
                    return true;
                }
	}
	/**
	 * takes in an item and how many of them to remove, returns 
	 * false if the item is not in the hash map or you try to remove too many
	 * 
	 * returns true if the specified amount is removed properly of it the 
	 * removeAllFlag is true and all of the item was removed
	 * @param Item I
	 * @param int num
	 * @return boolean
	 */
	public boolean removeItem(Item I, int num, boolean removeAllFlag){
		//removes all if the removeAllFlag is true
		if(removeAllFlag){
			contents.remove(I.get_name());
			currItems -= num;
			return true;
		}
		
		//return false if the item is not in the hashmap
		if(!contents.containsKey(I.get_name())){
			System.out.println("Can't remove an Item that does not exist in this shelf!");
			return false;
		}
		
		//return false if you try and remove too many items
		if(contents.get(I.get_name())-num < 0){
			System.out.println("Can't remoThis shelf does not have enough of the requested Item");
			return false;
		}
		
		//return true if successfully removed
		System.out.println("~~~~~~~~~~~~~~~****"+contents.containsKey(I.get_name())+"*****~~~~~~~~~~~~~~~~~~~~~~~~");
		//only remove the number we want
		contents.put(I.get_name(), contents.get(I.get_name())-num);
		if(contents.get(I.get_name())==0){
			contents.remove(I.get_name());
		}
		currItems -= num;
		System.out.println("~~~~~~~~~~~~~~~****"+contents.containsKey(I.get_name())+"*****~~~~~~~~~~~~~~~~~~~~~~~~");
		return true;
	}
	/**
	 * sets if the shelf is being carried or not
	 * @param carried
	 */
	public void setCarry(boolean carried){
		beingCarried = carried;		
	}
	
	/**
	 * @author Kyle Kloberdanz
	 */
	public Integer getID() {
		return this.id;
	}
	
	/**
	 * returns current items
	 * @return int
	 */
	public int capacity(){
		return capacity;
	}
	/**
	 * returns the current number of items in the shelf
	 * @return int 
	 */
	public int size(){
		return currItems;
}
	/**
	 * sets the shelfs current location
	 * @param int x
	 * @param int y
	 */
	public void setLoc(int x, int y){
		shelfLoc.setLocation(x,y);
	}
        @Override
        /**
         * provides a toString() method for debugging
         */
        public String toString(){
            return "Name: "+ sName + "||" +shelfLoc.toString()+","+ Boolean.toString(beingCarried);
        }
}

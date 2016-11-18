package production;

import java.awt.Point;
import java.util.HashMap;


public class Shelf {
	HashMap<Item,Integer> contents;
	boolean beingCarried;
	Point shelfLoc;
	int capacity;
	int currItems = 0;
	String sName;
	public Shelf(Point point, boolean bool, int Int, String name){
		contents = new HashMap<>();
                shelfLoc=point;
                beingCarried=bool;
                capacity = Int;
                sName = name;
	}
	/**
	 * adds an amount of an item to the shelf
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
                    contents.put(I, contents.get(I)+num);
                    currItems+=num;
                    return true;
                }else{
                	//adds item to the hashmap
                    contents.put(I,num);
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
			contents.remove(I);
			return true;
		}
		//return false if the item is not in the hashmap
		if(!contents.containsKey(I)){
			return false;
		}
		//return false if you try and remove too many items
		if(contents.get(I)-num < 0){
			return false;
		}
		//return true if successfully removed
		contents.remove(I);
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

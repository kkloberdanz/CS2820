package production;

import java.awt.Point;

/**
 * 
 * all this does is make a charger and store a point and if it is occupied
 *
 */
public class charger {
	boolean occupied;
	Point charLoc;
	public charger(Point location){
		charLoc = location;
	}
	public void setState(boolean occupied1){
		occupied = occupied1;
	}
	
	@Override
	public String toString(){
		return charLoc.toString()+Boolean.toString(occupied);
	}
}
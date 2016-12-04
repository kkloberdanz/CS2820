
package production;

import java.awt.Point;
import java.util.ArrayList;


public interface Floor {
	/**
	 * this function will set the used locations
	 */
    void setUsedLocations();
    /**
     * this will return a path from point A to point B
     * It currently is a very simple version, and  just moves along the x axis and then the y axis
     * @param Point A
     * @param Point B
     * @return Path
     */
    //Path makePath(Point A, Point B, boolean flag);
    /**
     * This Function will return true if the location passed is occupied
     * @param loc
     * @return True if the location is taken
     */
    boolean isSpaceOccupied(Point loc);
    /**
     * This function returns the picker location
     * @return picker
     */
    Point getPicker(); 
    /**
     * This function returns the packer location
     * @return 
     */
    Point getPacker(); 
    /**
     * This function returns the Shipping Dock location
     * @return shippingDock
     */
    Point getShippingDock();
    /**
     * This Function returns the Receiving Dock Location
     * @return receivingDock
     */
    Point getReceivingDock(); 
    /**
     * This function returns an ArrayList of Points that have chargers at that location
     * @return Array List of Points
     */
    ArrayList<Point> getChargers(); 
    /**
     * This will set empty shelves in the specified positions using A 
     * as the top left point and B as the bottom left point of a block
     * @param Point A
     * @param Point B
     */
    void setShelfBlock(Point A, Point B);
    /**
     * this will set a robot on every charger at the beginning of the program
     */
    void setRobots();
}

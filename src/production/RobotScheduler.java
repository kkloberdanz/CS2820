package CS2820;

import java.awt.Point;

/**
 *
 * @author Shuhao Liu
 */
public interface RobotScheduler {
	
	/**
     * 
     * 
     * @param Point a is the robot need to be move
     * the robot will move one step at each time
     * @return 
     */
	
	void moveRobot(Robot a);
    /**
     * 
     * It deliever shelf A to the location B
     * @param Point A is the shelf
     * @param Point B is the destination that shelf should be moved to
     * @return 
     */
    void moveShelf(Shelf A, Point p);
    /**
     * 
     * It turns back shelf A to the location B
     * @param Point A is the shelf
     * @param Point P is the point A need to come back
     * @param i is the robot i
     * @return 
     */
    void returnShelf(Shelf A, Point p, int i);
    /**
     * find an available robot which is not used and in the best location with available charge
     */
    
    /**
     * charge robot
     */
    void chargeRobot(Robot a);
    /**
     * 
     */
    int getAvailableRobotIndex(); 
    
}
    

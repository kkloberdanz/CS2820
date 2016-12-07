package production;

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

    void chargeRobot(Robot a);
    /**
     * 
     */
    
}
    

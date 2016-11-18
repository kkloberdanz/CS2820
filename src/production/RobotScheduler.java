/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;

import java.awt.Point;

/**
 *
 * @author lenovo
 */
public interface RobotScheduler {
    /**
     * 
     * It deliever shelf A to the location B
     * @param Point A is the shelf
     * @param Point B is the destination that shelf should be moved to
     */
    void moveShelf(Shelf A, Point p);
    /**
     * find an available robot which is not used and in the best location with available charge
     */
    boolean findAvailableRobot(Robot r);
    /**
     * charge robot
     */
    void chargeRobot(Robot a);
    /**
     * 
     */
    
}
    
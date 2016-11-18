/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse2;

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
    void moveShelf(Shelf A, picker B);
    /**
     * find an available robot which is not used and in the best location with available charge
     */
    boolean findAvailableRobot();
    /**
     * charge robot
     */
    void chargeRobot(Robot a);
    /**
     * 
     */
    
}
    
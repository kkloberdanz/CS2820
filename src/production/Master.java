/**
 * @author Kyle Kloberdanz
 * Controls the opperations of the entire production
 */

package production;

import java.awt.Point;

public class Master {

    /**
     * @author Kyle Kloberdanz
     * @return void
     *
     * Sends a tick with the current count to each section of
     * the ware house. Each section utilizes these ticks to keep
     * synchronized with eachother.
     */
    public static void main(String[] args) { 

    	System.out.println("Running Simulation");
    
        MockFloor     floor     = new MockFloor();
        Inventory     inventory = new Inventory();
        Order         order     = new Order();
        Belt          belt      = new Belt();
        RobotSchedule robot     = new RobotSchedule();

        for (int i = 0;; ++i) {
            floor.tick(i);
            inventory.tick(i);
            order.tick(i);
            belt.tick(i);
            robot.tick(i);
            System.out.println("Current Count = " + i);
        }
    }
}

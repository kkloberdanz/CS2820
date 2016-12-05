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
	
	static SimRandom rand;
	
    public static void main(String[] args) { 
    	
    	rand = new SimRandom();

    	if (Debug.verboseLevel() >= 1) {
    		System.out.println("Running Simulation");
    	}
        MockFloor     		floor     = new MockFloor();
        Inventory     		inventory = new Inventory(rand);
        MockOrders    		orders    = new MockOrders(rand, inventory);
        MockRobotScheduler 	robot     = new MockRobotScheduler();
        Picker        		picker	  = new Picker(floor, orders, inventory, robot);
        Belt          		belt      = new Belt();
        
        for (int i = 0; i < 20; ++i) {
            floor.tick(i);
            inventory.tick(i);
            orders.tick(i);
            robot.tick(i);
            picker.tick(i);
            belt.tick(i);
            System.out.println("Current Count = " + i);
        }
    }
}

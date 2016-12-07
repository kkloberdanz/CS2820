/**
 * @author Kyle Kloberdanz
 * Controls the opperations of the entire production
 */

package production;

//import java.awt.Point;

import java.util.*;

public class Master {

	/**
	 * @author Kyle Kloberdanz
	 * @return void
	 *
	 *         Sends a tick with the current count to each section of the ware
	 *         house. Each section utilizes these ticks to keep synchronized
	 *         with eachother.
	 */

	static SimRandom rand;

	public static void main(String[] args) {

		rand = new SimRandom();

		if (Debug.verboseLevel() >= 1) {
			System.out.println("Initializing");
		}
		MockFloor floor = new MockFloor();
		Inventory inventory = new Inventory(rand, floor);
		MockOrders orders = new MockOrders(rand, inventory, floor);
		MockRobotScheduler robot = new MockRobotScheduler();
		Picker picker = new Picker(floor, orders, inventory, robot);
		Belt belt = new Belt();

		if (Debug.verboseLevel() >= 1) {
			System.out.println("\nRunning Simulation");
		}
		Scanner keyboard = new Scanner(System.in);
		String exitFlag = "a";
		
		for (int i = 0; !exitFlag.equals("q") ; ++i) {

			System.out.println("\n*************************** TICK " + i + " ******************************");

			/*
			 * if (Debug.verboseLevel() >= 5) {
			 * System.out.println("Current Count = " + i); }
			 */

			floor.tick(i);
			// inventory.tick(i);
			orders.tick(i);
			robot.tick(i);
			picker.tick(i);
			belt.tick(i);

			if (Debug.stepThrough()) {
				exitFlag = keyboard.nextLine();
			}
		}
		System.out.println("####### Completed and Shipped Orders #######");
		ShippedOrders.shippedOrders();
		keyboard.close();
		
	}
}

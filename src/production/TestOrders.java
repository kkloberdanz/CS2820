package production;

import java.util.*;

/**
 * 
 * @author Tyler
 *
 */

public class TestOrders {

	static MockOrders orders;
	static Inventory inventory;
	static MockRobotScheduler robotScheduler;
	static SimRandom rand;
	
	public static void main(String[] args){
		
		rand = new SimRandom();
		new MockOrders(inventory, robotScheduler, rand);
		for (int i = 0; i < MockOrders.orderQueue.size(); i++) {
			System.out.println(MockOrders.orderQueue.get(i).toString());
		}
	}
}

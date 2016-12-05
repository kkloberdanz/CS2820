package production;

import java.util.*;

/**
 * 
 * @author Tyler
 *
 *
 */

public class TestOrders {

	static MockOrders orders;
	static Inventory inventory;
	static MockRobotScheduler robotScheduler;
	static MockFloor floor;
	static SimRandom rand;
	static Picker picker;
	static ArrayList<Item> itemDatabase = new ArrayList<Item>();
	static ArrayList<Integer> itemQuantities = new ArrayList<Integer>();
	
	public static void main(String[] args){
		
		// Code for testing the MockOrders constructor
		rand = new SimRandom();
		picker = new Picker(floor, orders, inventory, robotScheduler);
		String divider = "******************************************************************";
		
		// Tests the constructor
		new MockOrders(rand, inventory, floor);
		new Inventory(rand, floor);
		System.out.println("Current order queue:\n");
		for (int i = 0; i < MockOrders.orderQueue.size(); i++) {
			System.out.println(MockOrders.orderQueue.get(i).toString());
		}
		System.out.println(divider);
		
		// Code for testing Picker contents
		Picker.updateOrder(MockOrders.orderQueue.get(0));
		System.out.println(picker.toString());
		System.out.println(divider);
		
		// Code for testing the ability to ADD orders to the queue
		MockOrders.orderQueue.add(MockOrders.generateRandomOrder());
		System.out.println("Current order queue:\n");
		for (int i = 0; i < MockOrders.orderQueue.size(); i++) {
			System.out.println(MockOrders.orderQueue.get(i).toString());
		}
		System.out.println(divider);
		
		// Code for testing the ability to REMOVE orders from the queue
		MockOrders.orderQueue.remove(MockOrders.orderQueue.get(MockOrders.orderQueue.size()-1));
		System.out.println("Current order queue:\n");
		for (int i = 0; i < MockOrders.orderQueue.size(); i++) {
			System.out.println(MockOrders.orderQueue.get(i).toString());
		}
		System.out.println(divider);
		
		// Code for testing the getNextOrder() method
		Order selectedOrder = MockOrders.getNextOrder();
		System.out.println(selectedOrder.toString());
		System.out.println(divider);
		
		// Code for testing whether or not getNextOrder() updated the queue
		System.out.println("Current order queue:\n");
		for (int i = 0; i < MockOrders.orderQueue.size(); i++) {
			System.out.println(MockOrders.orderQueue.get(i).toString());
		}
		System.out.println(divider);
		
		// Code for testing what happens in Picker when order is filled
		selectedOrder.updateFilled();
		System.out.println(picker.toString());
		System.out.println(divider);
		
		// Code for testing the checkCompletion() method of Picker
		Picker.moveFinishedOrder();
		Picker.currentOrder = MockOrders.getNextOrder();
		MockOrders.receiveNewOrders();
		System.out.println(picker.toString());
		System.out.println(divider);
		
		// Now get rid of the bin and check the Picker again
		Picker.removeCurrentBin();
		System.out.println(picker.toString());
		System.out.println(divider);
		
		// Check whether or not checkCompletion() updated the queue
		System.out.println("Current order queue:\n");
		for (int i = 0; i < MockOrders.orderQueue.size(); i++) {
			System.out.println(MockOrders.orderQueue.get(i).toString());
		}
		System.out.println(divider);
		
		// Now test out some stuff with items
		
	}
}

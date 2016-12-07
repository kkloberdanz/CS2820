package production;

public class ShippedOrders {
	/**
	 * shippedOrders prints out the completed and shipped orders at the end of the program.
	 * @author Tyler Sporrer
	 */
	public static void shippedOrders(){
		for(int i = 0; i < Belt.ship.size(); i++){
			int num = i + 1;
			System.out.println("");
			System.out.println("----Completed Order" + num + "----");
			System.out.println(Belt.ship.get(i));
		}
		//h
	}
}

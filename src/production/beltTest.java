package production;

public class beltTest {
	 private String name;
	 
	 public String toString(){
		 return name;
	 }
	 public String getName(){
		 return name;
	 }
	 
	static SimRandom rand;
	static MockOrders order;
	/**
	 * Tester for loadBelt and packBox.
	 * @author Tyler Sporrer
	 */
	public static void main(String args[]){
		beltTest a = new beltTest();
		beltTest b = new beltTest();
		beltTest c = new beltTest();
		beltTest d = new beltTest();
		beltTest e = new beltTest();
		beltTest f = new beltTest();
		a.name = "Tyler";
		b.name = "k";
		c.name = "l";
		d.name = "Matt";
		e.name = "Steve";
		f.name = "p";
		Belt.loadBelt(a);
		Belt.loadBelt(null);
		Belt.loadBelt(null);
		Belt.loadBelt(null);
		Belt.loadBelt(e);
		Belt.packBox(2);
		Belt.loadBelt(f);
		Belt.packBox(3);
		System.out.println(Belt.belt);
		System.out.println(Belt.ship);
		
		//rand = new SimRandom();
		
		/**Bin a = new Bin();
		Bin b = new Bin();
		Bin c = new Bin();
		Bin d = new Bin();
		Bin e = new Bin();
		Bin f = new Bin();
		a.order = MockOrders.generateRandomOrder();
		b.order = MockOrders.generateRandomOrder();
		c.order = null;
		d.order = MockOrders.generateRandomOrder();
		e.order = null;
		f.order = MockOrders.generateRandomOrder();
		Belt.loadBelt(a);
		Belt.loadBelt(b);
		Belt.loadBelt(b);
		Belt.loadBelt(d);
		Belt.loadBelt(e);
		Belt.packBox(2);
		Belt.loadBelt(f);
		Belt.packBox(3);
		System.out.println(Belt.belt);
		System.out.println(Belt.ship);
		*/
	}
		
}


/**
 * @author Gary Ridgway
 */
package production;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class MockFloor implements Floor, Tick{
    static Point picker       ;
    static Point packer       ;
    static Point shippingDock ;
    static Point receivingDock;
    static int unique_id=0;/** author haoyang wei , a unique id for shelf starts from 0**/
    static int x_dimension;
    static int y_dimension;
    
    static ArrayList<charger> chargers     ;
    static ArrayList<Point  > usedLocations;
    //static ArrayList<Shelf  > shelves      ;
    static ArrayList<Robot  > robots       ;
    
    private static HashMap<Integer, Shelf> shelves;
    
    public MockFloor(){
    	picker        = new Point(0,0 );
        packer        = new Point(5,0 );
        shippingDock  = new Point(0,12);
        receivingDock = new Point(9,13);
        
        x_dimension = 10;
        y_dimension = 14;
        
        chargers      = new ArrayList<>();
        usedLocations = new ArrayList<>();
        //shelves       = new ArrayList<>();
        shelves       = new HashMap<>();
        robots        = new ArrayList<>();
    	
    	//create chargers
    	charger ch1 = new charger(new Point(9,2));ch1.setState(true);chargers.add(ch1);
    	charger ch2 = new charger(new Point(9,4));ch2.setState(true);chargers.add(ch2);
    	charger ch3 = new charger(new Point(9,6));ch3.setState(true);chargers.add(ch3);
    	
    	//set used locations
    	setUsedLocations();
    	
    	//set Robot Locations
    	setRobots();
    	
    	//set shelf blocks
    	setShelfBlock(new Point(2,3),new Point(3,9));
    	setShelfBlock(new Point(6,3),new Point(7,9));
    }
    
    /**
     * @author Kyle Kloberdanz
     */
    public static HashMap<Integer, Shelf> getShelves() {
    	return shelves;
    }
    
    /**
     * @author Kyle Kloberdanz
     */
    public static void setShelf(Shelf s) {
    	shelves.put(s.getID(), s);
    }
    
    /**
     * @author Kyle Kloberdanz
     * If the item is not in the warhouse, then order it
     * from the supplier, and put it on the shelf.
     * 
     * returns true if successful, false if there are no more shelves available
     */
    public static boolean OrderFromSupplier(Item item, int num_items) {
    	for (Integer i : shelves.keySet()) {
    		if ((shelves.get(i).size()+num_items < shelves.get(i).capacity()) && (!shelves.get(i).isBeingCarried()) ) {
    			shelves.get(i).addItem(item, num_items);
    			System.out.println("We have ordered some " + item.get_name() + " from the supplier, placed on Shelf " + i + ".");
    			System.out.println("Current size of Shelf " + i + " is " + shelves.get(i).size());
    			return true;
    		}
    	}
    	//if (Debug.verboseLevel() >= 1) {
    	System.out.println("Error: We are out of shelves!");
    	//}
    	return false;
    }


    /**
     *This Function will set the currently used locations by objects in the area. 
     * Considered adding shelves, but I think I would like to have shelves with
     * space under them so robots can drive underneath them
     */
    @Override
    public void setUsedLocations(){
        usedLocations.clear();
        for (charger c:chargers){
            usedLocations.add(c.charLoc);
        }
        usedLocations.add(picker);
        usedLocations.add(packer);
        usedLocations.add(shippingDock);
        usedLocations.add(receivingDock);
    }
    
    public static Path makePath(Point A, Point B) {
    	return makePath(A, B, false);
    }
    /**
     * this will return a path from point A to point B
     * It currently is a very simple version, and  just moves along the x axis and then the y axis
     * @param Point A
     * @param Point B
     * @return Path
     */
    public static Path makePath(Point A, Point B, boolean flag){
    	int xCurr=A.x;
    	int yCurr=A.y;
    	boolean lorrX;
    	boolean lorrY;
    	if((B.x-A.x)<0){
    		lorrX=true;
    	}else{
    		lorrX=false;
    	}
    	if((B.y-A.y)<0){
    		lorrY=true;
    	}else{
    		lorrY=false;
    	}
    	Path fPath = null;
    	Point temp;
    	boolean trueForY = flag;
    	if(xCurr==B.x){
    		trueForY=true;   
    	}
    	if (A.x==B.x && A.y==B.y){
    		return new Path(A,null);
    	}else if(!trueForY){
    		if(lorrX){
    			temp = new Point(xCurr-1,yCurr);
    		}else{
    			temp = new Point(xCurr+1,yCurr);
    		}
    		fPath = new Path(A,makePath(temp,B,trueForY));
    	}else if (trueForY){
    		if(lorrY){
    			temp = new Point(xCurr,yCurr-1);
    		}else{
    			temp = new Point(xCurr,yCurr+1);
    		}
    		fPath = new Path(A,makePath(temp,B,trueForY));
    	}
		return fPath;
    }
    /**
     * This function returns the picker location
     * @return picker
     */
    @Override
    public Point getPicker() {return picker;}
    /**
     * This function returns the packer location
     * @return 
     */
    @Override
    public Point getPacker() {return packer;}
    /**
     * This function returns the Shipping Dock location
     * @return shippingDock
     */
    @Override
    public Point getShippingDock() {return shippingDock;}
    /**
     * This Function returns the Receiving Dock Location
     * @return receivingDock
     */
    @Override
    public Point getReceivingDock() {return receivingDock;}
    /**
     * This function returns an ArrayList of Points that have chargers at that location
     * @return Array List of Points
     */
    @Override
    public ArrayList<Point> getChargers() {
        ArrayList<Point> Temp = new ArrayList<>();
        for (charger c: chargers){
            Temp.add(c.charLoc);
        }
        return Temp;
    }

    /**
     * This Function will return true if the location passed is occupied
     * @param loc
     * @return True if the location is taken
     */
    @Override
    public boolean isSpaceOccupied(Point loc){
    	setUsedLocations();
        return (usedLocations.contains(loc));
    }
    /**
     * This will set empty shelves in the specified positions using A 
     * as the top left point and B as the bottom left point of a block
     * @param Point A
     * @param Point B
     */
    public void setShelfBlock(Point A, Point B){
        int xCurr=A.x;
        int yCurr=A.y;
        while(!(xCurr>B.x)){
            while(!(yCurr>B.y)){
            	shelves.put(unique_id,new Shelf(new Point(xCurr,yCurr),false,100,"S("+Integer.toString(xCurr)+","+Integer.toString(yCurr)+")"));
            	unique_id++;
		/** author haoyang wei 
		change the arraylist to hashmap
		update capacity so that we can put all things onto shelf
		*/
            	yCurr+=1;
            }
            yCurr=A.y;
            xCurr+=1;
        }
    }
    /**
     * this will set a robot on every charger at the beginning of the program
     */
    public void setRobots(){
    	int num = 0;
    	for (charger c:chargers){
    		Robot temp = new Robot(c.charLoc);
    		temp.charge = 100;
    		temp.state = false;
    		temp.NumberofRobot = num;// false mean unworking
    		robots.add(temp);
    		num++;
    	}
    }
    /**
     * This implements Tick
     */
    public void tick(int count){
    	if (Debug.verboseLevel()>=4){
    		for (charger c:chargers){
    			System.out.println(c.toString());
    		}
    		for (Robot r:robots){
    			System.out.println(r.toString());
    		}
    	}
    }
}

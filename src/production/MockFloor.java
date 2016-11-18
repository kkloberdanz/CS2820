/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;
import java.awt.Point;
import java.util.ArrayList;

public class MockFloor implements Floor, Tick{
    final Point picker        = new Point(0,0 );
    final Point packer        = new Point(5,0 );
    final Point shippingDock  = new Point(0,12);
    final Point receivingDock = new Point(9,13);
    
    final int x_dimension = 10;
    final int y_dimension = 14;
    
    ArrayList<charger> chargers      = new ArrayList<>();
    ArrayList<Point>   usedLocations = new ArrayList<>();
    ArrayList<Shelf>   shelves       = new ArrayList<>();
    ArrayList<Robot>   robots        = new ArrayList<>();
    
    public MockFloor(){
    	
    	//create chargers
    	charger ch1 = new charger(new Point(9,2));
    	charger ch2 = new charger(new Point(9,4));
    	charger ch3 = new charger(new Point(9,6));
    	chargers.add(ch1);
    	chargers.add(ch2);
    	chargers.add(ch3);
    	
    	//set used locations
    	setUsedLocations();
    	
    	//set Robot Locations
    	setRobots();
    	
    	//set shelf blocks
    	setShelfBlock(new Point(2,3),new Point(3,9));
    	setShelfBlock(new Point(6,3),new Point(7,9));
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
    /**
     * this will return a path from point A to point B
     * It currently is a very simple version, and  just moves along the x axis and then the y axis
     * @param Point A
     * @param Point B
     * @return Path
     */
    public Path makePath(Point A, Point B, boolean flag){
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
                shelves.add(new Shelf(new Point(xCurr,yCurr),false,10,"S("+Integer.toString(xCurr)+","+Integer.toString(yCurr)+")"));
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
    		Robot temp = new Robot(100, num);
    		temp.setRobotlocation(c.charLoc);
    		robots.add(temp);
    		num++;
    	}
    }
    /**
     * This implements Tick
     */
    public void tick(int count){
    }
}
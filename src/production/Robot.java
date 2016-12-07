/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;
import java.awt.Point;
/**
 *
 * @author Shuhao Liu
 * @author Gary Ridgway -- 11/15/2016 -- changed Location from Object to Point so it would work with the MockFloor
 * @author Kyle Kloberdanz
 */
public class Robot {
	int NumberofRobot; //Added this field back for usage by other methods
    public static int chargebound = 50;
    Point location;
    Point destination;
    int charge;
    Shelf s; // the shelf robot need to carry
    Path path; // the path robot move
    Point pickerR;
    boolean state;
    private boolean busy = false;
    
    public Robot(Point Createlocation){
    	this.busy = false;
        location = Createlocation;
        destination = null;
        path = null;
        s = null;
        pickerR = null;
    }
    
    /**
     * @author Kyle Kloberdanz
     */
    public void setShelf(Shelf shelf) {
    	this.s = shelf;
    }
    
    /**
     * @author Kyle Kloberdanz
     */
    public boolean isBusy() {
    	return this.busy;
    }
    
    /**
     * @author Kyle Kloberdanz
     */
    public void setBusy(boolean b) {
    	this.busy = b;
    }
    
    /**
     * @author Kyle Kloberdanz
     */
    public void setPath(Path p) {
    	this.path = p;
    }
    
    /**
     * @author Kyle Kloberdanz
     * @author Tyler Foster
     * takes one step along path. when finished, sets busy to false
     */
    public void step() {
    	
    	this.charge--;
    	if (this.charge <= 0) {
    		
    		// If this happens, the robot is dead.
    		if (Debug.verboseLevel() >= 1) {
    			System.out.println("Warning: Robot: " + this.NumberofRobot + " is out of charger");
    		}
    	} else {
			s.shelfLoc = path.getPos();
			
	    	if (Debug.verboseLevel() >= 1) {
	    		System.out.println("Robot " + NumberofRobot + " is moving to: " + s.shelfLoc.getX() + ", " + s.shelfLoc.getY());
	    	}
	
	    	this.path = path.step();
	    	this.setRobotlocation(new Point((int)s.shelfLoc.getX(), (int)s.shelfLoc.getY())); // author Tyler Foster
	    	
			MockFloor.setShelf(s);
	
	    	// then done
	    	if (this.path == null) {
	        	MockFloor.getShelves().get(s.getID()).setBeingCarried(false);
	        	
	        	// Returns Robot to its charger
	        	if (!this.location.equals(MockFloor.chargers.get(this.NumberofRobot).charLoc)) {
	        		this.path = MockFloor.makePath(this.location, MockFloor.chargers.get(this.NumberofRobot).charLoc);
	        		if (Debug.verboseLevel() >= 2) {
	        			System.out.println("Robot: " + this.NumberofRobot + " is moving back to its charger");
	        		}
	        		
	        	} else {
	        		if (Debug.verboseLevel() >= 2) {
	        			System.out.println("Robot: " + this.NumberofRobot + " is back at its charger");
	        		}
	        		this.charge = 100;
	        		this.busy = false;
	        	}
	    	}
    	}
    }
    
    public Point getLocation(Robot a){
            return a.location; 
    }
    /**
        * this will set the current locaton of the robot
        * @author Gary Ridgway
        * @param Point A
        */
    public void setRobotlocation(Point A){
       location = A;
       MockFloor.robots.get(this.NumberofRobot).location = A;
       }
       /**
        * added a toString() method so that I could properly see that all the proper fields were created correctly
        * @return String
        */
       @Override
       public String toString(){
    	   return "Robot #"+Integer.toString(NumberofRobot) + "|| At :"+location.toString()+"|| With Charge :"+Integer.toString(charge);
       }
        
}       
    
    


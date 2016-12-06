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
     * takes one step along path. when finished, sets busy to false
     */
    public void step() {
		s.shelfLoc = path.getPos();
		
    	if (Debug.verboseLevel() >= 1) {
    		System.out.println("Robot " + NumberofRobot + " is moving to: " + s.shelfLoc.getX() + ", " + s.shelfLoc.getY());
    	}

    	this.path = path.step();
    	this.setRobotlocation(new Point((int)s.shelfLoc.getX(), (int)s.shelfLoc.getY())); // author Tyler Foster
    	
    	// TODO: This is a dummy placeholder.
    	// This will not work. This needs to access the static shelves from MockFloor, and set the location there
		
		MockFloor.setShelf(s);

    	// then done
    	if (path == null) {
    		this.busy = false;
    		this.s.beingCarried = false; // set the shelf down
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
    
    


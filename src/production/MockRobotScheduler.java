/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Shuhao Liu
 */
public class MockRobotScheduler implements RobotScheduler,Tick{
    //private Floor F;
    static Point picker;
    private static ArrayList<Robot> robots;
    
    /**
     * @author Kyle Kloberdanz
     */
    public MockRobotScheduler() {
    	robots = new ArrayList<Robot>();
    }
    
    
    public void moveRobot(Robot a){
        a.location = a.path.pos;
    }
    
    /**
     * 
     * @author Kyle Kloberdanz
     * @param A: Shelf
     * @param p: Point
     * @return index of robot that is not busy is robots list, -1 if no robot available
     * 
     * @author Tyler Foster
     * Added the beingCarried field change
     */
    public static int moveShelf(Shelf A, Point p){
    	int notBusyIndex = getAvailableRobotIndex();

    	if ((notBusyIndex != -1) && (A.beingCarried == false)) {
        	MockFloor.getShelves().get(A.getID()).setBeingCarried(true);
    		MockFloor.robots.get(notBusyIndex).setPath(MockFloor.makePath(A.shelfLoc, p, false));
    		MockFloor.robots.get(notBusyIndex).setPathToShelf(MockFloor.makePath(MockFloor.chargers.get(MockFloor.robots.get(notBusyIndex).NumberofRobot).charLoc, A.shelfLoc, false));
    		MockFloor.robots.get(notBusyIndex).setBusy(true);
    		MockFloor.robots.get(notBusyIndex).setShelf(A);
    		A.beingCarried = true;
    		return notBusyIndex;
    	} 
    	else if (A.beingCarried == true){
    		if (Debug.verboseLevel() >= 2) {
    			System.out.println("The requested shelf is currently being moved.");
    		}
    		return -1;
    	}
    	
    	else {
    		return -1;
    	}
    }
    
    /**
     * Returns the shelf at the picker station to its previous location.
     * 
     * @author Tyler Foster
     * @param A
     * @param p
     * @param i
     */
    public static void returnShelf(Shelf A, Point p, int i) {
    	// set shelf to being carried here
    	MockFloor.getShelves().get(A.getID()).setBeingCarried(true);
    	MockFloor.robots.get(i).setPath(MockFloor.makePath(A.shelfLoc, p, false));
    	MockFloor.robots.get(i).setBusy(true);
    	MockFloor.robots.get(i).setShelf(A);
    	A.beingCarried = true;
    }
    
    /**
     * @author Kyle Kloberdanz
     * returns the index of a robot that is not busy
     */
    public static int getAvailableRobotIndex() {
    	for (int i = 0; i < MockFloor.robots.size(); ++i) {
    		if (!MockFloor.robots.get(i).isBusy()) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public Robot GetAvailableRobot(){
        Robot r = robots.get(0);
        return r;
        // only one robot now
    }
    @Override
    public boolean findAvailableRobot(Robot r){
        return r.state;
    }
    @Override
    public void chargeRobot(Robot a){
    	a.charge = 100;
    }
	public void tick(int count) {
		for (int i = 0; i < MockFloor.robots.size(); ++i) {
			if (MockFloor.robots.get(i).isBusy()) {
				MockFloor.robots.get(i).step();
			}
		}
	}
    
}

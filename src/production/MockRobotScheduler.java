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
    Floor F;
    static Point picker;
    ArrayList<Robot> robots;
    
    /*
     * @author Kyle Kloberdanz
     */
    public MockRobotScheduler() {
    	;
    }
    
    public MockRobotScheduler(MockFloor F){
        this.F = F;
        robots = F.robots;
    }
    public void moveRobot(Robot a){
        a.location = a.path.pos;
    }
    
    @Override
    public void moveShelf(Shelf A, Point p){
    	int notBusyIndex = getAvailableRobotIndex();
    	if (notBusyIndex != -1) {
    		robots.get(notBusyIndex).setPath(F.makePath(A.shelfLoc, p, true));
    		robots.get(notBusyIndex).setBusy(true);
    	}
    	/*
        Robot robot = GetAvailableRobot();
        robot.path = F.makePath(A.shelfLoc,p, false);
        robot.pickerR = this.picker;
        robot.s = A;
        robot.state = true;
        */
    }
    
    /*
     * @author Kyle Kloberdanz
     * returns the index of a robot that is not busy
     */
    public int getAvailableRobotIndex() {
    	for (int i = 0; i < robots.size(); ++i) {
    		if (!robots.get(i).isBusy()) {
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
		for (int i = 0; i < robots.size(); ++i) {
			if (robots.get(i).isBusy()) {
				robots.get(i).step();
			}
		}
		/*
	    for(Robot a:robots) {
	    	if (a.path.next == null){
	    		a.path = null;
	    	}
	    	else{
	    		a.path.step();
	    	}
	    }
	    */
	}
    
}

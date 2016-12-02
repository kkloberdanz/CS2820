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
    public MockRobotScheduler(MockFloor F){
        this.F = F;
        robots = F.robots;
    }
    public void moveRobot(Robot a){
        a.location = a.path.pos;
    }
    @Override
    public void moveShelf(Shelf A, Point p){
        Robot robot = GetAvailableRobot();
        robot.path = F.makePath(A.shelfLoc,p, false);
        robot.pickerR = this.picker;
        robot.s = A;
        robot.state = true;
        
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
	    for(Robot a:robots) {
	    	if (a.path.next == null){
	    		a.path = null;
	    	}
	    	else{
	    		a.path.step();
	    	}
	    }
	}
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse2;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class MockRobotScheduler implements RobotScheduler,Tick{
    Floor F;
    ArrayList<Robot> robots;
    public MockRobotScheduler(MockFloor F){
        this.F = F;
        robots = F.getRobot();
    }
    public void moveRobot(Robot a){
        
    }
    @Override
    public void moveShelf(Shelf A, picker B){
        Robot robot = GetAvailableRobot();
        robot.path = makePath(A.getLocation(),B.getLocation(), True);
        robot.picker = B;
        robot.s = A;
        robot.state = true;
        
    }
    public Robot GetAvailableRobot(){
        Robot r = robots.get(0);
        return r;
    }
    public boolean RobotAvailable(robot r){
        return r.state;
    }
    
}

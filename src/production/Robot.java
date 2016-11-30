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
    int charge;
    Shelf s; // the shelf robot need to carry
    Path path; // the path robot move
    Point pickerR;
    boolean state;
    
    public Robot(Point Createlocation){
        location = Createlocation;
        path = null;
        s = null;
        pickerR = null;
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
    
    


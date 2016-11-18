/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;

import java.awt.Point;

/**
*
* @author lenovo
* @author Gary Ridgway -- 11/15/2016 -- changed Location from Object to Point so it would work with the MockFloor
*/
public class Robot {
    int QuantityCharge;
    boolean Needtocharge;
    int notenoughcharge;
    int NumberofRobot;
    Point location;
    public Robot(int QuantityCharge,int NumberofRobot){
        this.QuantityCharge = QuantityCharge;
        this.NumberofRobot = NumberofRobot;
        
    }
    
    public void tick(int count) {
    	// TODO: finish tick
    }
    
   public boolean needcharge(){
       if(QuantityCharge >= notenoughcharge){
           return (Needtocharge = false);
       }
       else{return Needtocharge = true;}
   }
   public void move(){};
   public void moveshelf(){};
   public Point getRobotlocation(){
       return location;
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
	   return "Robot #"+Integer.toString(NumberofRobot) + "|| At :"+location.toString()+"|| With Charge :"+Integer.toString(QuantityCharge);
   }
}

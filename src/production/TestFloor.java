/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;

import java.awt.Point;

/**
 *
 * @author Garth Vader
 */
public class TestFloor {

    /**
     * @param args the command line arguments
     */
	
	
    static MockFloor floor;
    static Point p;
    static Shelf s;
    static int xCurr;
    static int yCurr;
    static Path pickToCharge=null;
    static Point A;
    static Point B;
    /**
     * testLevel options
     * 1= basic nonsense tests
     * 2= tick
     */
    static int  testLevel = 1;
    
    public static void main(String[] args) {
        floor = new MockFloor();     
/////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * This will initialize all shelves in the given area 
         * (set in MockFloor as .topLeftShelfArea and .bottomRightShelfArea)
         * Currently it prints all the shelves with Name, Location, and whether it is being carried or not
         */
        if (testLevel==1){
	        System.out.println("\n-----Shelves-----\n");
	        for (Integer i: MockFloor.getShelves().keySet()){
	            System.out.println(MockFloor.getShelves().get(i));
	        }
        }
//////////////////////////////////////////////////////////////////////////////////////////////
        if (testLevel==1){
        System.out.println("\n-----chargers-----\n");
        for(charger c:floor.chargers){
        	System.out.println(c);
        }
        }
////////////////////////////////////////////////////////////////////////////////////////////// 
        if (testLevel==1){
        System.out.println("\n-----robots-----\n");
        for(Robot r:floor.robots){
    	System.out.println(r);
    	}
        }
//////////////////////////////////////////////////////////////////////////////////////////////
        if (testLevel==1){
        System.out.println("\n-----Path-----\n");
        /**
         * This will create a simple Path and then print that Path
         */
        Point A=new Point(0,1);
        Point B=new Point(9,2);
        pickToCharge =floor.makePath(A,B , false);
        System.out.print("***Last Point***\n");
        System.out.println(pickToCharge.getLast().toString());
        System.out.print("****************\n\n");
        while(pickToCharge!=null){
            System.out.println(pickToCharge.toString());
            pickToCharge =pickToCharge.step();
        }
        System.out.println("\n-----Path Found!!-----\n");
        }
/////////////////////////////////////////////////////////////////////////////////////////
        /*
         * test tick
         */
        if (testLevel==2){
        	floor.tick(1);
        }

        
        
        

    }
    
    
}

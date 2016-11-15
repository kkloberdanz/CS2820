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
    public static void main(String[] args) {
        floor = new MockFloor();     
/////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * This will initialize all shelves in the given area 
         * (set in MockFloor as .topLeftShelfArea and .bottomRightShelfArea)
         * Currently it prints all the shelves with Name, Location, and whether it is being carried or not
         */
        xCurr=floor.topLeftShelfArea.x;
        yCurr=floor.topLeftShelfArea.y;
        while(!(xCurr>floor.bottomRightShelfArea.x)){
        	while(!(yCurr>floor.bottomRightShelfArea.y)){
        		p= new Point(xCurr,yCurr);s= new Shelf(p,false,10,"S("+Integer.toString(xCurr)+","+Integer.toString(yCurr)+")");floor.shelves.add(s);
        		yCurr+=1;
        	}
        	yCurr=floor.topLeftShelfArea.y;
        	xCurr+=1;
        }
        for (Shelf s:floor.shelves){
            System.out.println(s.toString());
        }
//////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * This will create a simple Path and then print that Path
         */
        Point A=new Point(0,1);
        Point B=new Point(9,2);
        pickToCharge =floor.makePath(A,B , false);
        while(pickToCharge!=null){
            System.out.println(pickToCharge.toString());
            pickToCharge =pickToCharge.step();
        }


    }
    
    
}

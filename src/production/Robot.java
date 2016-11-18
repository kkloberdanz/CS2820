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
 */
public class Robot {
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
        
    }       
    
    


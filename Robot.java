/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse2;
import java.awt.Point;
/**
 *
 * @author lenovo
 */
public class Robot {
    public static int chargebound = 50;
    Point location;
    int charge;
    Shelf s; // the shelf robot need to carry
    Picker picker; // the picker robot need to go
    Path path; // the path robot move
    boolean state;
    public Robot(Point Createlocation){
        location = Createlocation;
        path = null;
        s = null;
        picker = null;
    }
    public Point getLocation(Robot a){
            return a.location; 
         }
        
    }       
    
    
}

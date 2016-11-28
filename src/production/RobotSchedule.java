package production;

import java.util.ArrayList;

public class RobotSchedule implements Tick {
	
	public RobotSchedule() {
		;
	}
    ArrayList<Robot> Robotlist = new ArrayList<Robot>();
    /*
    Robot NUMBER1 = new Robot(100,1);
    Robot NUMBER2 = new Robot(100,2);
    */
    public void addtoRobotlist(){}
    //put all of the robot to the arraylist
    // create robot instance here
    public void placeRobot(){};
    public void asklocation(Shelf a){
        //a.getlocation();
        // this method is from Inventory
    }
    public void rechargeRobot(){};
    public void findavailableRobot(ArrayList<Robot> Robotlist){}
    // find the idel robot which is at the best location to move the shelf
    public void getRoute(Robot A,Object location){};
    // get the route form the robot to the location of the shelf
    public void getRoute(Object Station, Object location){}
    // get the route from the shelf to the station
    
    public void tick(int count) {
    	//TODO write code here to implement tick
    }
}
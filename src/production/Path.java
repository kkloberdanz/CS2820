package production;

import java.awt.Point;
/**
 * this class will create an object that will be able to hold
 * a position and the next node in the path. 
 * 
 * If a Path object is null, then the path has been finished.
 *
 */
public class Path {
	Point pos;
	Path next;
	public Path(Point I,Path path){
		pos=I;
		next=path;
	}
	/**
	 * this will return the next step in the path in the form of another Path object
	 * @return Path next
	 */
	Path step(){
		return next;
	}
	
	@Override
	public String toString(){
		return pos.toString();
	}

}

package production;

/**
 * 
 * @author Kyle Kloberdanz
 * This class is used to guard print statements, so that they
 * only print at certain verboseness levels.
 */
public class Debug {
	/*
	private static final boolean verbose = false;
	
	public static boolean isVerbose() {
		return verbose;
	}
	*/
	
	private static boolean step = false;
	private static int level = 3;
	
	/**
	 * @author Kyle Kloberdanz
	 * @return int level (Low verboseness means print general things, high means more detail)
	 */
	public static int verboseLevel() {
		return level;
	}
	
	/**
	 * @author Kyle Kloberdanz
	 * @param int l
	 */
	public static void setLevel(int l) {
		if (l >= 0) {
			
		}
		level = l;
	}
	
	/**
	 * @author Kyle Kloberdanz
	 * @return step
	 * 
	 * Used in master to set if you want to step through the loop by pressing ENTER
	 * for each tick
	 */
	public static boolean stepThrough() {
		return step;
	}

}

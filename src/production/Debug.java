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
	
	private static boolean step = true;
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
	
	public static boolean stepThrough() {
		return step;
	}

}

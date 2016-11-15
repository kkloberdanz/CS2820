package production;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;

public class TestMockFloor {

	@Test
	public void testGetPicker() {
		MockFloor floor = new MockFloor();
		assertTrue(floor.getPicker() instanceof Point);
	}
	
	@Test
	public void testGetPacker() {
		MockFloor floor = new MockFloor();
		assertTrue(floor.getPacker() instanceof Point);
	}
	
	@Test
	public void testGetShippingDock() { 
		MockFloor floor = new MockFloor();
		assertTrue(floor.getShippingDock() instanceof Point);
	}

    @Test
    public void testGetReceivingDock() {
		MockFloor floor = new MockFloor();
		assertTrue(floor.getReceivingDock() instanceof Point);
    }

    @Test
    public void testGetChagers() {
		MockFloor floor = new MockFloor();
        ArrayList<Point> arr = new ArrayList(floor.getChargers());
        for (Point p : arr) {
            assertTrue((p.getY() > 0) && (p.getX() > 0));
        }
    }
}

package nsort.test;

import static org.junit.Assert.*;
import nsort.model.ProgressMeter;

/**
 * Unit test for ProgressMeter class. * 
 * @author 	Josh.Eads
 * @date 	05/05/2015 *
 */

import org.junit.Test;

public class ProgressMeter_UT {

	
	@Test
	public void testProgressMeterInstantiation() {
		ProgressMeter meter = new ProgressMeter(10, true);
		assertNotNull(meter);		
	}	
	
	@Test
	public void testProgressMeterSetCurrentBound() {
		ProgressMeter meter = new ProgressMeter(10, true);
		meter.setCurrentBound(8);
		//Expected value is first.
		assertEquals(8, meter.getCurrentBound());
	}
	
	@Test
	public void testProgressMeterGetSelectedState() {
		ProgressMeter meter = new ProgressMeter(10, true);
		meter.setSelectedState(false);
		/*
		 * With booleans, best practice is to use "assertTrue", "assertFalse", as opposed to "assertEquals"
		 * (used "assertEquals" below for comparison/educational purposes - Unit Test passes either way). - josh eads 
		 */
		assertFalse(meter.getSelectedState());
		assertEquals(false, meter.getSelectedState());
		
	}
	


}

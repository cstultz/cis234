/**
 * 
 */
package nsort.test;

import static org.junit.Assert.*;

import nsort.model.Item;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class Item_UT {

	
	/**
	 * Test method for {@link nsort.model.Item#Item(java.lang.String)}.
	 */
	@Test
	public void testItemInstantiation() {
		Item item = new Item();
		assertNotNull("Item instantiation failed.", item);
	}

	/**
	 * Test method for {@link nsort.model.Item#getValue()}.
	 */
	@Test
	public void testGetValueWithDefault() {
		Item item = new Item();
		assertEquals("Item getValue did not return empty string", "", item.getValue());
	}

	/**
	 * Test method for {@link nsort.model.Item#setValue(java.lang.String)}.
	 */
	@Test
	public void testSetValue() {
		Item item = new Item();
		item.setValue("Apples");
		assertEquals("Item setValue did not set string as expected", "Apples", item.getValue());
	}
	
	/**
	 * Test method for {@link nsort.model.Item#setValue(java.lang.String)}.
	 */
	@Test
	public void testGetValueWithAssignedValue() {
		Item item = new Item();
		item.setValue("apples");
		assertEquals("Item getValue did not get expected string", "apples", item.getValue());
	}
	
	/**
	 * Test assertTrue for equals method.
	 */
	@Test
	public void testEqualsValueTrue() {
		Item item = new Item();
		item.setValue("apples");
		assertTrue("Item setValue does not equal the getValue", item.equals(item.getValue()));
	}

}

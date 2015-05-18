package nsort.test;

import static org.junit.Assert.*;
import nsort.model.Item;
import nsort.model.ItemList;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */

public class ItemList_UT {

	@Test
	public void objectConstructed() 
	{
		
		Item item = new Item();
		assertEquals("constructed item is not an empty string", "", item.getValue());
	}

	@Test
	public void ObjectCreatedAndAddWordToTest() 
	{
		ItemList itemList = new ItemList();
		Item item = new Item();
		item.setValue("Havarti");
		itemList.addItem(item);
		assertEquals("Item added does not equal item in Item List", item, itemList.getItem(item.getValue()));
	}
	
	@Test
	public void removeItemFromList()
	{
		ItemList itemList = new ItemList();
		Item item = new Item();
		item.setValue("Havarti");
		itemList.addItem(item);
		itemList.removeItem(item.getValue());
		assertNull("Item not removed from Item List", itemList.getItem(item.getValue()));
	}
	
	@Test
	public void getItemFromItemListByIndex()
	{
		ItemList itemList = new ItemList();
		Item item = new Item();
		item.setValue("Havarti");
		itemList.addItem(item);
		assertEquals("Item does not equal the item in the list", item, itemList.getItem(0));
	}

	@Test
	public void getItemFromItemListByValue()
	{
		ItemList itemList = new ItemList();
		Item item = new Item();
		item.setValue("Havarti");
		itemList.addItem(item);
		assertEquals("Item does not equal the item in the list", item, itemList.getItem(item.getValue()));
	}

	@Test
	public void getItemListSize()
	{
		ItemList itemList = new ItemList();
		assertEquals("Item List does not equal size returned", 0, itemList.getSize());
		Item item = new Item();
		item.setValue("Havarti");
		itemList.addItem(item);
		assertEquals("Item List does not equal size returned", 1, itemList.getSize());
		item.setValue("Bacardi");
		itemList.addItem(item);
		assertEquals("Item List does not equal size returned", 2, itemList.getSize());
		item.setValue("ToParty");
		itemList.addItem(item);
		assertEquals("Item List does not equal size returned", 3, itemList.getSize());
	}
	
	@Test 
	public void removeFromEmptyItemListByvalue()	
	{
		ItemList itemList = new ItemList();
		Item item = new Item();
		assertFalse("removeItem returned true, Item was removed.", itemList.removeItem(item.getValue()));
	}

}

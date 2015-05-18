package nsort.test;

import static org.junit.Assert.*;
import nsort.model.*;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class SqlUser_234a_t1_UT {

	@Test
	public void ObjectCreated() {
		SqlUser_234a_t1 t1 = SqlUser_234a_t1.instance();
		assertNotNull(t1);
	}

	@Test
	public void testConnection() {
		SqlUser_234a_t1 t1 = SqlUser_234a_t1.instance();
		t1.connect();
		
	}

	@Test
	public void pullExistingWordsNotNull() {
		SqlUser_234a_t1 t1 = SqlUser_234a_t1.instance();
		ItemList items = new ItemList();
		items = t1.pullItems("Existing Items");
		assertNotNull(items);
	}
	
	@Test
	public void saveTest() {
		SqlUser_234a_t1 t1 = SqlUser_234a_t1.instance();
		ItemList existingItems = new ItemList();
		existingItems = t1.pullItems("Existing Items");
		
		Item item1 = existingItems.getItem(0);
		Item item2 = existingItems.getItem(1);
		
		ItemList newTestList = new ItemList();
		
		newTestList.addItem(item1);
		newTestList.addItem(item2);
		
		t1.saveTest(newTestList);
		ItemList savedTestList = new ItemList();
		savedTestList = t1.pullItems("Test Items");
		assertEquals(newTestList.getSize(), savedTestList.getSize());
	}
	
	@Test
	public void deleteCurrentTestItems()
	{
		SqlUser_234a_t1 t1 = SqlUser_234a_t1.instance();
		ItemList existingItems = new ItemList();
		existingItems = t1.pullItems("Existing Items");
		
		Item item1 = existingItems.getItem(0);
		Item item2 = existingItems.getItem(1);
		
		ItemList newTestList = new ItemList();
		
		newTestList.addItem(item1);
		newTestList.addItem(item2);
		
		t1.saveTest(newTestList);
		
		ItemList testItemList = new ItemList();
		testItemList = t1.pullItems("Test Items");
		assertTrue("test items list is null", (testItemList.getSize() == 2));
		t1.deleteCurrentTestItems();
		testItemList = t1.pullItems("Test Items");
		assertTrue("test items list is not null", (testItemList.getSize() == 0));
		
	}
	
	@Test
	public void pullTestItemIDsByValue()
	{
		//TODO
	}

	@Test
	public void addTestitems()
	{
		//TODO
	}

	@Test
	public void addNewItem()
	{
		//TODO
	}

	@Test
	public void validateUser()
	{
		//TODO
	}

	@Test
	public void getUser()
	{
		//TODO
	}
	
//	@Test
//	public void pullTestItemsCount()
//	{
//		SqlUser_234a_t1 t1 = SqlUser_234a_t1.instance();
//		int count = 0;
//		
//		count = t1.pullTestItemsCount();
//		
//		assertTrue("The item count is not greater than zero", count > 0);
//	}
}

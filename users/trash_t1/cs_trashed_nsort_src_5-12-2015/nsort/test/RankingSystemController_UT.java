package nsort.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

import nsort.controller.RankingSystemController;
import nsort.model.ItemList;
import nsort.view.LoginFrame;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class RankingSystemController_UT {

	@Test
	public void testCreateObject() {
		RankingSystemController controller = new RankingSystemController();
		assertNotNull("controller is null", controller);
	}

	@Test
	public void testStart() {
		RankingSystemController controller = new RankingSystemController();
		controller.start();
		assertNotNull("controller is null", controller);
	}

	@Test
	public void testLogin() {
		RankingSystemController controller = new RankingSystemController();
		String username = "admin";
		LoginFrame loginFrame = new LoginFrame(controller);
		assertNotNull("loginFrame is null", loginFrame);
		controller.start();
		controller.login(username);
		assertNotNull("controller is null", controller);
	}

	@Test
	public void testLoadItemsToBothTheExistingListAndTheTestList() {
		RankingSystemController controller = new RankingSystemController();
		DefaultListModel<String> JListModelExistingItems = new DefaultListModel<String>();
		DefaultListModel<String> JListModelTestItems = new DefaultListModel<String>();
		
		String listName = "Existing Items";		
		controller.loadItemsToList(JListModelExistingItems, listName);
		assertNotNull("Existing Items List is null", JListModelExistingItems);
		
		listName = "Test Items";
		controller.loadItemsToList(JListModelTestItems, listName);
		assertNotNull("Test Items List is null", JListModelTestItems);
}

	@Test
	public void testAddItemToTestItemsList() {
		
		String value = "zingers";
		
		RankingSystemController controller = new RankingSystemController();
		DefaultListModel<String> JListModelExistingItems = new DefaultListModel<String>();
		DefaultListModel<String> JListModelTestItems = new DefaultListModel<String>();
		
		String listName = "Test Items";
		controller.loadItemsToList(JListModelTestItems, listName);

		listName = "Existing Items";
		controller.loadItemsToList(JListModelExistingItems, listName);

		
		//check to see if the existing items list does not have the value
		if (! JListModelExistingItems.contains(value)){
			//add the value
			JListModelExistingItems.addElement(value);
			addItemToItemDatabase(value);
		}
		
		//check to see if the test items list does have the value
		if (JListModelTestItems.contains(value))
		{
			//remove the value so it can be added
			JListModelTestItems.removeElement(value);
		}
		
		//safe to add 'zingers' to test items list
		controller.addExistingItemToTestItemsList(JListModelTestItems, value);
		assertTrue("Test Items List contains " + value, JListModelTestItems.contains(value));

		//test clean-up
		JListModelTestItems.removeElement(value);
		JListModelExistingItems.removeElement(value);
		removeItemFromItemDatabase(value);
	}

	@Test
	public void testRemoveItemFromTestItemList() {
		RankingSystemController controller = new RankingSystemController();
		DefaultListModel<String> JListModelTestItems = new DefaultListModel<String>();
		
		String listName = "Test Items";
		controller.loadItemsToList(JListModelTestItems, listName);
		
		String value = "zingers";
		
		//check to see if the test items list does not have the value		
		if (! JListModelTestItems.contains(value))
		{
			//add the value so it can be removed
			JListModelTestItems.addElement(value);
		}
		
		ItemList items = new ItemList();
		items = controller.getTestItemsList();

		//safe to remove 'zingers' from test items list
		controller.removeItemFromTestItemList(JListModelTestItems, value);
		assertFalse(value + " was not removed from the test Items List view", JListModelTestItems.contains(value));
		assertNull(value + " was not removed from the testItemsList model", items.getItem(value));
	}

	@Test
	public void testAddNewItemToExistingItemsList() {
		RankingSystemController controller = new RankingSystemController();
		DefaultListModel<String> JListModelExistingItems = new DefaultListModel<String>();
		
		String listName = "Existing Items";
		controller.loadItemsToList(JListModelExistingItems, listName);
		
		String value = "zingers";
		
		//check to see if the existing items list does have the value
		if (JListModelExistingItems.contains(value)){
			//remove the value so it can be added
			JListModelExistingItems.removeElement(value);
			removeItemFromItemDatabase(value);
		}

		//load again to refresh
		listName = "Existing Items";
		JListModelExistingItems.clear();
		controller.loadItemsToList(JListModelExistingItems, listName);
		
		//safe to add 'zingers' to the existing items list
		controller.addNewItemToExistingItemsList(JListModelExistingItems, value);

		assertTrue("Existing Items List view does not contain " + value, JListModelExistingItems.contains(value));

		ItemList items = new ItemList();
		items = controller.getExistingItemsList();
		
		assertEquals(value + " was not added to the Existing Items List model", value, items.getItem(value).getValue());
		
		//test clean-up
		if (JListModelExistingItems.contains(value)){
			//remove the value so it can be added
			JListModelExistingItems.removeElement(value);
			removeItemFromItemDatabase(value);
		}

	}

	@Test
	public void testListIsUnique() {
		//TODO
	}

	@Test
	public void testAddExistingItemToTestItemsList() {
		//TODO
	}

	@Test
	public void testTestItemsListMeetsMinimumRequirements() {
		//TODO
	}

	@Test
	public void testModelToList() {
		//TODO
	}

	@Test
	public void testSaveTestList() {
		//TODO
	}

	@Test
	public void testCancelTest() {
		//TODO
	}

	@Test
	public void testAllListAerEqualAllTheTime() {
		//TODO
	}
	
	/**
	 * retrieve the itemID based on the value from the Item table
	 * @param value being retrieved
	 * @return itemID of the value in the Item table
	 */
	public int getItemID(String value)
	{
		
		int itemID = 0;
		
		Connection conn = null;
		/**********************************database connection url*******************************************/
		String url = "jdbc:sqlserver://cisdbss.pcc.edu;"
				           + " databaseName=234a_t1; "
				           + "user=234a_t1; "
				           + "password=1t_a432@#";
		
		try
		{
			conn = DriverManager.getConnection(url);
    	}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement("SELECT itemID FROM Item WHERE Item.value = ?;");
	         preparedStmt.setString(1, value);
	         // execute the preparedstatement
	         ResultSet rs = preparedStmt.executeQuery();
	         while (rs.next())
	 			{
	 				itemID = rs.getInt("itemID");
	        	    return itemID;
	 			};
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return itemID;
	}

	/**
	 * add value to Item table in the database
	 * @param value to be added
	 */
	public void addItemToItemDatabase(String value)
	{
		Connection conn = null;
		/**********************************database connection url*******************************************/
		String url = "jdbc:sqlserver://cisdbss.pcc.edu;"
				           + " databaseName=234a_t1; "
				           + "user=234a_t1; "
				           + "password=1t_a432@#";
		
		try
		{
			conn = DriverManager.getConnection(url);
    	}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement("INSERT INTO Item VALUES (?);");
	         preparedStmt.setString(1, value);
	         // execute the preparedstatement
	         preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * remove value from Item table in the database
	 * @param value to be removed
	 */
	public void removeItemFromItemDatabase(String value)
	{
		Connection conn = null;
		/**********************************database connection url*******************************************/
		String url = "jdbc:sqlserver://cisdbss.pcc.edu;"
				           + " databaseName=234a_t1; "
				           + "user=234a_t1; "
				           + "password=1t_a432@#";
		
		try
		{
			conn = DriverManager.getConnection(url);
    	}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement("DELETE FROM Item WHERE Item.value = ?;");
	         preparedStmt.setString(1, value);
	         // execute the preparedstatement
	         preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

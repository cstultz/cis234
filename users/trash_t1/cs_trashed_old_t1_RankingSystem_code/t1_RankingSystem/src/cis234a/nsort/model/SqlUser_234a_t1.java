package cis234a.nsort.model;

import java.sql.*;
import java.util.ArrayList;

/**
 * The SqlUser234A_t1 class is the model for all connection operations to the 234a_t1 MS SQL database.
 * Only 1 test can be saved in the database at any given time. testID = 1
 * Only 1 progress meter selectedState can be save to the database at any given time. progressMeterID = 1
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/17/2015)
 */
public class SqlUser_234a_t1 {

	private static SqlUser_234a_t1 instance = null;
	
	private Connection conn = null;
	/**********************************database connection url*******************************************/
	private final static String url = "jdbc:sqlserver://cisdbss.pcc.edu;"
			                        + " databaseName=234a_t1; "
			                        + "user=234a_t1; "
			                        + "password=1t_a432@#";
	
	/********************The One, The Only Test table testID to Rule them all****************************/
	private final static int theOneTheOnlyTestID = 1;
	/********************The One, The Only Test table testID to Rule them all****************************/
	
	/***************************The Only Progress Meter Selected State***********************************/
	private final static int progressMeterSelectedStateID = 1;
	/***************************The Only Progress Meter Selected State***********************************/

	/**********************************queries for AdminTestSetupScreen)*********************************/
	private final static String queryDeleteTestItems = "DELETE FROM TestItems WHERE TestItems.test_ID = ?;";
	private final static String queryPullItemIDsByValue = "SELECT itemID FROM Item WHERE value = ?;";
	private final static String queryInsertTestItems = "INSERT INTO TestItems(test_ID, item_ID) VALUES (?, ?);";
	private final static String queryPullExistingItems = "SELECT [value] FROM [234a_t1].dbo.Item ORDER BY [value];";
	private final static String queryPullTestItems= "SELECT [value] FROM Item JOIN TestItems ON Item.itemID = TestItems.item_ID WHERE TestItems.test_ID = "+ theOneTheOnlyTestID + "  ORDER BY [value];";
	private final static String queryCheckUser = "SELECT username FROM [User] WHERE username = ?;";
	private final static String queryGetUser = "SELECT firstName, lastName, eMail FROM [User] WHERE username = ?;";
	private final static String queryAddNewItem = "INSERT INTO Item(value) VALUES (?);";
	private final static String queryPullTestItemsCount = "SELECT COUNT(testItemsID) as [Count] FROM TestItems WHERE test_ID = ?;";
	private final static String queryPullUserAccessRole = "SELECT role FROM [User] JOIN UserAccess ON [User].userID = UserAccess.user_ID WHERE [User].username = ?;";
	private final static String queryPullProgressMeterSelectedState = "SELECT selectedState FROM ProgressMeter WHERE progressMeterID =?;";
	private final static String queryupdateProgressMeterSelectedState = "UPDATE ProgressMeter SET selectedState = ? WHERE progressMeterID = ?;";
	private final static String queryCreateTestSession = "INSERT INTO TestSessions(user_ID) VALUES(?);";
	private final static String queryPullTestSessionIDScopeIdentity = "SELECT DISTINCT IDENT_CURRENT('TestSessions') as [Test Session ID];";
	private final static String queryInsertTestResults = "INSERT INTO TestResults(testSession_ID, item_ID, wins, losses, ties) VALUES (?, ?, ?, ?, ?);";
	private final static String queryPullUserID = "SELECT userID FROM [User] WHERE username = ?;";
	/**********************************queries for AdminTestSetupScreen)*********************************/

	/**
     * Constructor for the class. Does nothing but construct the object.
     */
    public SqlUser_234a_t1()
    {
    	//do nothing
    }
    
    public static SqlUser_234a_t1 instance() {
    	if ( null == SqlUser_234a_t1.instance ) {
    		SqlUser_234a_t1.instance = new SqlUser_234a_t1();
    	}
    	return SqlUser_234a_t1.instance;
    }
    
    /**
     * Creates the connects to the database. 
     * 
     * NOTE: This must be called EVERYTIME you want to submit a statement to the database. 
     */
    public void connect()
    {
		try
		{
			conn = DriverManager.getConnection(url);
    	}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
    }
    
    /**
     * Pull the items from the database to be added a ItemList.
     * 
     * @param listName of the specified list to determine the query to run in the database. 
     * @return ItemList created from the items returned from the database
     */
    public ItemList pullExistingItems()
    {
    	connect();
    	ItemList itemList = new ItemList();                       //create an empty Item List to be populated with the result set
    	
    	try {
    		Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryPullExistingItems);
			while (rs.next())
			{
				String input = rs.getString("value");
				Item item = new Item();
				item.setValue(input);
				itemList.addItem(item);
			}
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
    	return itemList;
    }
    
    /**
     * Pull the items from the database to be added a ItemList.
     * 
     * @param listName of the specified list to determine the query to run in the database. 
     * @return ItemList created from the items returned from the database
     */
    public ItemList pullTestItems()
    {
    	connect();
    	ItemList itemList = new ItemList();                       //create an empty Item List to be populated with the result set
    	
    	try {
    		Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryPullTestItems);
			while (rs.next())
			{
				String input = rs.getString("value");
				Item item = new Item();
				item.setValue(input);
				itemList.addItem(item);
			}
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
    	return itemList;
    }

    /**
     * Saves the modified Test Items List to the TestItems database.
     *  
     * @param the Test Items List to be saved to the database.
     */
    public void saveTest(ItemList TestItemsList)
    {
    	ArrayList<Integer> itemIDList = new ArrayList<Integer>();
    	
    	deleteCurrentTestItems();
    	itemIDList = pullTestItemIDsByValue(TestItemsList, itemIDList);
    	addTestitems(itemIDList);
    }
    
    /**
     * Deletes ALL the test items from the TestItems table in the database for theOneTheOnlyTestID.  
     */
    public void deleteCurrentTestItems()
    {
		try {
			connect();
			PreparedStatement preparedStmt = conn.prepareStatement(queryDeleteTestItems);
			preparedStmt.setInt(1, theOneTheOnlyTestID);
	         // execute the preparedstatement
	         preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }
    
    /**
     * Pulls the itemID into an ArrayList<Integer> from the Items table in the database 
     * based on the value of each item in the ItemList provided.
     * 
     * @param the ItemList of item values listed on the test Items List.
     * @return the ArrayList<Integer> itemID for the Items in the TestItems table in the database.
     */
    public ArrayList<Integer> pullTestItemIDsByValue(ItemList itemList, ArrayList<Integer> itemIDList)
    {
		try {
			for (int i = 0; i < itemList.getSize(); i++)
			{
		    	connect();
				PreparedStatement preparedStmt = conn.prepareStatement(queryPullItemIDsByValue);
				preparedStmt.setString(1, itemList.getItem(i).getValue());
		         // execute the preparedstatement
		         ResultSet rs = preparedStmt.executeQuery();
		         while (rs.next())
		 			{
		 				int input = rs.getInt("itemID");
		        	    itemIDList.add(input);
		 			}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return itemIDList;
	}
    
    /**
     * get the itemID from the Item table of an item based on its item value
     * 
     * @return itemID for the item value from the Item table in the database
     */
    public int pullTestItemIDByValue(String value)
    {
		try {
	    	connect();
			PreparedStatement preparedStmt = conn.prepareStatement(queryPullItemIDsByValue);
			preparedStmt.setString(1, value);
	         // execute the preparedstatement
	         ResultSet rs = preparedStmt.executeQuery();
	         while (rs.next())
	 			{
	 				int input = rs.getInt("itemID");
	 				return input;
	 			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}

    
    /**
     * Insert itemIDs into the TestItems table in the database for theOneTheOnlyTestID
     * 
     * @param itemIDList of the items to add to the TestItems table in the database
     */
    public void addTestitems(ArrayList<Integer> itemIDList)
    {
    	try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryInsertTestItems);
			for (int i : itemIDList) {
				preparedStmt.setInt(1, theOneTheOnlyTestID);
				preparedStmt.setInt(2, i);
				preparedStmt.addBatch();
			}
			preparedStmt.executeBatch();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    /**
     * Insert a new item into the Item table in the database.
     *   
     * @param value to be added to Item table in the database.
     */
    public void addNewItem(String value)
    {
    	try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryAddNewItem);
			preparedStmt.setString(1, value);
			preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    /**
     * Validate the username against the User table in the database 
     * 
     * @return the true if user is valid; false if not.
     */
    public boolean validateUser(String username)
    {
    	String value = " ";
    	try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryCheckUser);
			preparedStmt.setString(1, username);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next())
			{
				value = rs.getString("username");
			}
			if (username.equalsIgnoreCase(value))
			{
				return true;
			}
			else return false;
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
    	return false;
    }

    /**
     * Pulls a user from the User table in the database based on the username
     * 
     * @return the User
     */
    public ArrayList<String> getUser(String username)
    {
    	ArrayList<String> user = new ArrayList<String>();
    	
    	try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryGetUser);
			preparedStmt.setString(1, username);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next())
			{
				user.add(rs.getString("firstName"));
				user.add(rs.getString("lastName"));
				user.add(rs.getString("eMail"));
			}

			user.add(username);
			
			return user;
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
    	return null;
    }
    
    /**
     * get the count of the items in the TestItem table in the database
     * 
     * @return the count of items in the TestItem table in the database
     */
    public int pullTestItemsCount()
    {
    	int count = 0;
    	try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryPullTestItemsCount);
			preparedStmt.setInt(1, theOneTheOnlyTestID);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next())
			{
				count = rs.getInt("Count");
			}
				return count;
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
    	return count;
    }
    
    /**
     * Check the user against the UserAccess table in the database to see if it is an Admin role.
     * 
     * @param username of the current user
     * @return true if the user is an Admin; false if not
     */
    public Role.UserAccessRole getUserAccessRole(String username)
    {
    	try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryPullUserAccessRole);
			preparedStmt.setString(1, username);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next())
			{
				String role = rs.getString("role");
				
				if (role.equals("Admin")) {return Role.UserAccessRole.Admin;}
				if (role.equals("User")) {return Role.UserAccessRole.User;}
			}
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
    	return Role.UserAccessRole.Unassigned;
    }
    

    /**
     * get the selectedState of the progress meter from the ProgressMeter table in the database
     * 
     * @param username of the current user
     * @return true if the progress meter is selected; false if not.
     */
    public boolean getProgressMeterSelectedState()
    {
    	int selectedState;
    	try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryPullProgressMeterSelectedState);
    		preparedStmt.setInt(1, progressMeterSelectedStateID);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next())
			{
				selectedState = rs.getInt("selectedState");
		    	if(selectedState == 0)
		    	{
		    		return false;
				}
		    	else if(selectedState == 1)
		    	{
		    		return true;
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return false;
    }
    
    /**
     * set the selected state of the progress meter. 1 for true, 2 for false
     *   
     * @param the selected state of the progress meter. 1 for true, 2 for false
     */
    public void setProgressBarSelectedState(int selectedState)
    {
    	try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryupdateProgressMeterSelectedState);
			preparedStmt.setInt(1, selectedState);
			preparedStmt.setInt(2, progressMeterSelectedStateID);
			preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    /**
     * 
     * 
     * @param query
     * @return
     */
    public ResultSet sqlQuery(String query)
	{
		connect();
		ResultSet rs = null;
		try
		{
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return rs;
	}

    /**
     * close the database connection. 
     */
	public void closeConnection() 
	{
		// TODO Auto-generated method stub
		if (conn != null)
		{
			try
			{
			conn.close();
			}
			catch (Exception e)
			{
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Save the item test results win-loss-tie ranking to the database
	 * 
	 * @param Test Items List of the test
	 * @param the unique test session ID of the test
	 */
	public void saveTestResults(ItemList testItemsList, int testSessionID)
	{
		try {
    		connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryInsertTestResults);
			for (int i = 0; i<testItemsList.getSize(); i++) {
				preparedStmt.setInt(1, testSessionID);
				preparedStmt.setInt(2, pullTestItemIDByValue(testItemsList.getItem(i).getValue()));
				preparedStmt.setInt(3, testItemsList.getItem(i).getWins());
				preparedStmt.setInt(4, testItemsList.getItem(i).getLosses());
				preparedStmt.setInt(5, testItemsList.getItem(i).getTies());
				preparedStmt.addBatch();
			}
			preparedStmt.executeBatch();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Create and save the newly created user unique test session ID of the test 
	 * 
	 * @param userID of the user who took the test
	 */
	public void createUserNewTestSessionID(int userID)
	{
		try {
			connect();
			PreparedStatement preparedStmt = conn.prepareStatement(queryCreateTestSession);
			preparedStmt.setInt(1, userID);
			preparedStmt.execute();
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * get the number for the next unique test session ID based on the next row to be added to the TestSession table in the database
	 * 
	 * @return the unique test session ID for the test
	 */
	public int getTestSessionIDScopeIdentity()
	{
		//queryPullTestSessionIDScopeIdentity
		int testSessionID = 0;
		
		try {
			connect();
    		Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryPullTestSessionIDScopeIdentity);
			while (rs.next())
			{
				testSessionID = rs.getInt("Test Session ID");
				return testSessionID;
			}
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return testSessionID;
	}
	
	/**
	 * get the userID from the User table in the database based on the username
	 * 
	 * @param username of the user
	 * @return the userID of the user from the User table in the database
	 */
	public int getUserID(String username)
	{
		int userID = 0;
		try {
			connect();
    		PreparedStatement preparedStmt = conn.prepareStatement(queryPullUserID);
    		preparedStmt.setString(1, username);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next())
			{
				userID = rs.getInt("userID");
				return userID;
			}
		}
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return userID;
	}
}
package cis234a.nsort.model;

import java.awt.List;
import java.sql.*;

/**
 * The Report class captures the behavior of the test results reporting independent of the user interface. 
 * and the SqlUsers_234a_t1 component.
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/21/2015)
 */

public class Report 
{
	private Object[][] columnData;
	private SqlUser_234a_t1 resultSet = SqlUser_234a_t1.INSTANCE;
	private List userNames;
	private List userTestID;
	final private String [] testColumns = {"Item", "Win", "Loss", "Tie"} ;
		 
	/**
	 * queries the database for 
	 * @return
	 */
	public Object[][] queryColumnData(String name, int testID)
	{
		String query = "SELECT Item.[value] AS 'ITEM', wins AS 'WINS', losses AS 'LOSSES', ties AS 'TIES'  " +
					   "FROM dbo.[User] JOIN dbo.TestSessions ON [User].userID = dbo.TestSessions.user_ID " +
					   "JOIN dbo.TestResults ON dbo.TestSessions.testSessionID = dbo.TestResults.testSession_ID "+
					   "JOIN dbo.Item on dbo.TestResults.item_ID = Item.itemID " +
					   "WHERE (dbo.[User].firstName + ' ' + dbo.[User].lastName) = " + "'" + name + "'" + 
					   "AND dbo.TestResults.testSession_ID = " + testID + 
					   "ORDER BY Item.[value], WINS DESC, TIES DESC, LOSSES DESC;";
		try
		{
			int counter = 0;
  		    ResultSet x  = resultSet.sqlQuery(query);
  		    x.last();
  		    int rowCount = x.getRow();
  		    x.beforeFirst();
			columnData = new Object[rowCount][4];
			while (x.next())
			{
				columnData [counter][0] = x.getObject("ITEM");
				columnData [counter][1] = x.getObject("WINS");
				columnData [counter][2] = x.getObject("LOSSES");
				columnData [counter][3] = x.getObject("TIES");
				counter++;
			}
		}
		catch (Exception e)
		{
			
		}
		finally
		{
			try
			{
				resultSet.closeConnection();
			}
			catch (Exception e)
			{
				
			}
		}
		return columnData;
	}
	
	/**
	 * sets the Columns for the report
	 * @return testColumns - returns String[] with column information
	 */
	public String[] getColumnData()
	{	
		return testColumns;
	}
	
	/**
	 * Query the database for a list of all eligible users/test takers
	 * @return userNames - returns a List of all users that returned from the result of the query
	 */
	public List getUsers()
	{
		userNames = new List();
		String query = "SELECT (firstName + ' ' + lastName) AS 'NAMES' " + 
						"FROM dbo.[User] AS X INNER JOIN dbo.UserAccess AS Y " +
				        "ON X.userID = Y.user_ID "+
						"WHERE Y.role = 'User';";
		try
		{ 
			ResultSet x = resultSet.sqlQuery(query);;
			x.beforeFirst();
			while (x.next())
			{			
				String value = x.getString("NAMES");
				if (value != null)
				{
					userNames.add(value);
				}
			}
			
		}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				resultSet.closeConnection();
				
			}
			catch (Exception e)
			{
					System.err.println("Got an exception! ");
					System.err.println(e.getMessage());
			}
		}
		if (userNames.getItemCount() == 0)
		{
			userNames.add("No users have taken a test", 0);
		}
		else
		{
			userNames.add("Please select a user", 0);
		}
		return userNames;
	}

	/**
	 * Query the database and return a list of TestID's 
	 * that corresponds with the tests that the specified user has taken
	 * @param user - First and Last Name of the user
	 * @return userTestID - list of all testID numbers that the user has taken
	 */
	public List getUsersTestID(String user)
	{
		userTestID = new List();
		String query = "SELECT (CONVERT(VARCHAR, testSessionID, 0) + ' - ' + CONVERT(VARCHAR,insertDate, 0)) AS 'TESTID' " + 
						"FROM dbo.TestSessions AS X INNER JOIN dbo.[User] AS Y " +
				        "ON X.user_ID = Y.userID "+
				        "WHERE (Y.firstName + ' ' + Y.lastName) = " + "'" + user + "'" +";";
		try
		{ 
			ResultSet x = resultSet.sqlQuery(query);;
			x.beforeFirst();
			while (x.next())
			{			
				String value = x.getString("TESTID");
				if (value != null)
				{
					userTestID.add(value);
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				resultSet.closeConnection();
				
			}
			catch (Exception e)
			{
					System.err.println("Got an exception! ");
					System.err.println(e.getMessage());
			}
		}
		if (userTestID.getItemCount() == 0)
		{
			userTestID.add("User has not taken any tests.  Please try again", 0);
		}
		else
		{
			userTestID.add("Please select a TestID and Date", 0);
		}
		return userTestID;
	}
}
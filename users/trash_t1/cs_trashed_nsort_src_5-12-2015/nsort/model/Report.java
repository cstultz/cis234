package nsort.model;

import java.awt.List;
import java.sql.*;

/**
 * Report is an nsort.model component.  The ReportModel handles the data interaction between the ReportController
 * and the SqlUsers_234a_t1 component.
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/21/2015)
 */

public class Report 
{
	private String[] columnNames;
	private Object[][] columnData;
	private SqlUser_234a_t1 resultSet = new SqlUser_234a_t1();
	private List userNames;
	private List userTestID;
	
	/**
	 * returns ColumnNames - might destroy as this is not needed anymore
	 * @return
	 * @throws SQLException
	 */
	public String[] queryColumnNames() throws SQLException
	{
		String query = "select (select value from Item where Item.itemID = TestItems.item_ID) AS 'COLUMN' from TestItems where test_ID = 1 ORDER BY item_ID";
		try
		{ 
			ResultSet x = resultSet.sqlQuery(query);
			x.last();
			int rowCount = x.getRow();
			x.beforeFirst();
			columnNames = new String[rowCount];
			int count = 0;
			while (x.next())
			{			
				String value = x.getString("COLUMN");
				if (value != null)
				{
					System.out.println(value);
					if (columnNames.length >= 1)
					{
						columnNames[count] = value;
					}
					count++;
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

		return columnNames;
	}
	 
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
					   "ORDER BY WINS DESC, LOSSES DESC, TIES DESC;";
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
	{	String [] testColumns = {"Item", "Win", "Loss", "Tie"};
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
		userNames.add("Please select a user", 0);
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
		String query = "SELECT (testSessionID) AS 'TESTID' " + 
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
		userTestID.add("Please select a TestID", 0);
		return userTestID;
	}
}

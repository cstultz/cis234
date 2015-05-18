package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToSQL {

	private String userName = "234a_t1";
	private String password = "1t_a432@#";
	private String url = "jdbc:sqlserver://cisdbss.pcc.edu/234a_t1";

		
	public void getConnected()
	{
		try
		{
		Connection conn = DriverManager.getConnection(url, userName, password);
		if (conn != null)
			{
				System.out.println("Connected!");
			}
		}
		
		catch (SQLException e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	//The "main()" method is the method that starts your program. 
	//So for our final project, there will be ONLY ONE main() method for the entire
	//project. It starts the entire program rolling.
	
	//NOTE: The main() method MUST BE STATIC
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		//Since this is a static method, must create an instance of the above class "ConnectToSQL",
		//and then call it with the getConnected() method.
		ConnectToSQL connection = new ConnectToSQL();
			connection.getConnected();	
	}

}
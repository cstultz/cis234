package cis234a.nsort.controller;

import java.awt.List;

import cis234a.nsort.model.Report;
import cis234a.nsort.view.ReportView;
/**
 * The ReportController handles the logic for the test results reporting for all users.  
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class ReportController 
{
	@SuppressWarnings("unused")
	private AdminTestSetupController controller;
	
	private ReportView view;
	private Report model;
	
	private String[] x;
	@SuppressWarnings("unused")
	private Object[] tempObject;
	private List tempUserList;
	private List tempUserTestList;
	private String[] tableColumns;
	private Object[][] tableRows;
	
	/**
	 * Constructor for the class. Creates the listener for the ReportController.
	 * @param ReportViewold output representation of the test results reporting information.
	 * @param Report model will directly manage the data, logic and rules of the Test Results reporting.
	 */
	public ReportController(AdminTestSetupController controller,ReportView view, Report model)
	{
		this.controller = controller;
		this.model = model;
		this.view = view;
		
		getUserListData();
	}
	
	/**
	 * Asks model for the column Data
	 * @return x - String[]list of data
	 */
	public String[] getColumnData()
	{
		x = null;
		x = model.getColumnData();
		return x;
	}
	
	/**
	 * returns the data for the report table
	 * @param x user ID for query
	 * @param y test id for query
	 * @return object filled with results
	 */
	public Object[] queryColumnData(String x, int y) 
	{
		return tempObject = model.queryColumnData(x, y);
	}
	
	/**
	 * sets the user list to the frame.
	 */
	public void getUserListData()
	{
		tempUserList = new List();
		tempUserList = model.getUsers();
		view.setUserList(tempUserList);
	}
	
	/**
	 * sets the Test ID list to the frame
	 */
	public void getUserTestData()
	{
		tempUserTestList = new List();
		tempUserTestList = model.getUsersTestID(view.getUsers());
		view.setUserTestList(tempUserTestList);
	}
	
	/**
	 * sets the report table to the frame
	 */
	public void getReportTableData()
	{
		tableColumns = model.getColumnData();
		tableRows = model.queryColumnData(view.getUsers(), view.getTestID());
		view.setReportTable(tableColumns, tableRows);
		
	}
}
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
	private AdminTestSetupController controller;
	
	private ReportView view;
	private Report model;
	
	private String[] x;
	private Object[] tempObject;
	private List tempUserList;
	
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
	
	public Object[] queryColumnData(String x, int y) 
	{
		return tempObject = model.queryColumnData(x, y);
	}
	
	public List getUserListData()
	{
		tempUserList = new List();
		tempUserList = model.getUsers();
		return tempUserList;
	}
	
}


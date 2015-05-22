package cis234a.nsort.controller;

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
	private Report model;
	private String[] x;
	private Object[] tempObject;
	
	/**
	 * Constructor for the class. Creates the listener for the ReportController.
	 * @param ReportViewold output representation of the test results reporting information.
	 * @param Report model will directly manage the data, logic and rules of the Test Results reporting.
	 */
	public ReportController(ReportView view, Report model)
	{
		this.model = model;
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
	
}


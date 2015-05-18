package cis234a.nsort.controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
	private ReportView view;
	private Report model;
	
	/**
	 * Constructor for the class. Creates the listener for the ReportController.
	 * @param ReportView output representation of the test results reproting information.
	 * @param Report model will directly manage the data, logic and rules of the Test Results reporting.
	 */
	public ReportController(ReportView view, Report model)
	{
		this.view = view;
		this.model = model;
		this.view.addReportListener(new ReportListener());
		this.view.addUserSelectListener(new UserSelectListener());
		this.view.addUserComboBoxItemListener(new UserComboBoxListener());
		this.view.addUserTestComboBoxListener(new UserTestComboBoxListener());
	}
	
	/**
	 * creates and returns a list to populate the usersDDL in the ReportView
	 * @return userList - returns a List of Users from ReportModel
	 */
	public List getUser()
	{
		List userList = model.getUsers();
		return userList;
	}
	
	/**
	 * Listener Action to generate the ReportView with information for the selected user and testID
	 */
	class ReportListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			view.setReportTable((view.setColumns(model.getColumnData())), (view.setData(model.queryColumnData(view.setUser(), view.setTest()))));
		}
	}
	
	/**
	 * 
	 * Listener Action to generate the ReportView and the userTestDDL.
	 */
	class UserSelectListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			 view.setUserTestComboBox(model.getUsersTestID(view.setUser()));
		}
	}
	
	/**
	 *determines if a user has selected a correct value or not, and will 
	 *enable or disable the userTestButton per the users selection
	 *from the list
	 */
	class UserComboBoxListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent arg0) 
		{
			if (view.setUserComboBoxValue() != 0)
			{
				view.switchUserTestButton(true);
			}
			else
			{
				view.switchUserTestButton(false);
			}
		}
	}
	/**
	 *determines if a user has selected a correct value or not, and will 
	 *enable or disable the reportButton per the users selection
	 *from the list
	 */
	class UserTestComboBoxListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent arg0) 
		{
			if (view.setTestSelection() != 0)
			{
				view.switchReportButton(true);
			}
			else
			{
				view.switchReportButton(false);
			}
			
		}
	}
}

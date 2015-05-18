package nsort.controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import nsort.model.Report;
import nsort.view.ReportView;

public class ReportController 
{
	private ReportView theView;
	private Report theModel;
	//creates the listener for the ReportController
	public ReportController(ReportView theView, Report theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		this.theView.addReportListener(new ReportListener());
		this.theView.addUserSelectListener(new UserSelectListener());
		this.theView.addUserComboBoxItemListener(new UserComboBoxListener());
		this.theView.addUserTestComboBoxListener(new UserTestComboBoxListener());
	}
	
	/**
	 * creates and returns a list to populate the usersDDL in the ReportView
	 * @return userList - returns a List of Users from ReportModel
	 */
	public List getUser()
	{
		List userList = theModel.getUsers();
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
			theView.setReportTable((theView.setColumns(theModel.getColumnData())), (theView.setData(theModel.queryColumnData(theView.setUser(), theView.setTest()))));
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
			 theView.setUserTestComboBox(theModel.getUsersTestID(theView.setUser()));
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
			if (theView.setUserComboBoxValue() != 0)
			{
				theView.switchUserTestButton(true);
			}
			else
			{
				theView.switchUserTestButton(false);
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
			if (theView.setTestSelection() != 0)
			{
				theView.switchReportButton(true);
			}
			else
			{
				theView.switchReportButton(false);
			}
			
		}
	}
}

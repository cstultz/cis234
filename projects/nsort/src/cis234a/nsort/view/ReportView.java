package cis234a.nsort.view;

/**
 * The Report Class contains the components for the report view.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class ReportView extends JFrame 
{
	/**
	 * ReportView sets up all parameters for build and display of report panel
	 */
	//RankingSystemController controller;
	//final static boolean shouldFill = true;
	private JScrollPane scrollPane;
	private JPanel panel = new JPanel();
	private JComboBox<String> userComboBox;
	private JComboBox<String> userTestComboBox;
	private JButton reportButton;
	private JButton userTestButton;
	private JTable table;
	private Object[][] data;
	private String[] columns;
	private List usersDDL;
	private List usersTestDDL;
	private GridBagConstraints c = new GridBagConstraints();
	/**
	 * ReportView object received call from RankingSystemController to create panel for reports
	 * for use by the RankingSystem
	 * @param List x
	 */
	public ReportView(List x)
	{
		usersDDL = x;
		FrameCreation();
		PanelCreation(this);
		this.add(panel);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Creates the panel to hold the report info
	 * @param frame - JFrame frame
	 */
	private void PanelCreation(JFrame frame) 
	{
		/**
		 * decides to either display report table or display report selection information
		 */
		frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel.setLayout(new GridBagLayout());
		panel.setPreferredSize(new Dimension(400,400));
		createItems();
		setInitialVisibility();
	}

	/**
	 * 
	 */
	private void createItems() 
	{
		/**
		 * set up userComboBox
		 */
		userComboBox = new JComboBox<String>();
		setUsersComboBox();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;
		panel.add(userComboBox, c);

		/**
		 * set up userTestButton
		 */
		userTestButton = new JButton("Get User Tests");
		userTestButton.setEnabled(false);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.NORTH;
		panel.add(userTestButton, c);

		/**
		 * set up userTestComboBox
		 */
		userTestComboBox = new JComboBox<String>();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;
		panel.add(userTestComboBox, c);
		/**
		 * set up reportButton
		 */
		reportButton = new JButton("Run Report");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.NORTH;
		panel.add(reportButton, c);
	}
	
	/**
	 *Sets the initial visibility of items
	 *upon panel creation 
	 */
	private void setInitialVisibility() 
	{
		userComboBox.setVisible(true);
		userTestButton.setVisible(true);
		userTestComboBox.setVisible(false);
		reportButton.setVisible(false);
	}
	
	/**
	 * Sets the appropriate visible items for the 
	 * second part of information gathering
	 */
	private void setSecondVisibility()
	{
		userComboBox.setVisible(false);
		userTestButton.setVisible(false);
		userTestComboBox.setVisible(true);
		reportButton.setVisible(true);
		reportButton.setEnabled(false);
	}
	
	/**
	 * Sets all visible items for the ReportView
	 */
	private void setThirdVisibility()
	{
		userTestComboBox.setVisible(false);
		reportButton.setVisible(false);
	}
	
	/**	
	 * Creates the JFrame object 
	 */
	private void FrameCreation() 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}

	/**
	 * sets the data Object that is housed to display 
	 * @param objects objects from ReportController
	 * @return data objects sent to setReporttable
	 */
	public Object[][] setData(Object[] objects)
	{
		data = (Object[][]) objects;
		return data;
	}
	
	/**
	 * sets columns for setReportTable()
	 * @param x - String array returned the ReportController
	 * @return columns - String array sent to setReportTable
	 */
	public String[] setColumns(String[] x)
	{
		columns = x;
		return columns;
	}
	/**
	 * Sets and creates the JTable table to display results 
	 * @param stringList - input of columns for table
	 * @param objects - input of rows for the table
	 */
	public void setReportTable(String[] stringList , Object[][] objects)
	{
		table = new JTable(objects, stringList);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(200,50));
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		panel.add(scrollPane, c);
		setThirdVisibility();
		panelRefresh();
	}
	
	/**
	 * sets the UserComboBox to be used for selecting the User/Test Taker
	 */
	public void setUsersComboBox()
	{
		for (int i = 0; i < usersDDL.getItemCount(); i++)
		{
			Object temp  = usersDDL.getItem(i);
			userComboBox.addItem(temp.toString());
		}
	}
	
	/**
	 * Sets the selectedUser from the userCombobox
	 * @return selectedUser - String of selected user
	 */
	public String setUser()
	{
		String selectedUser = userComboBox.getSelectedItem().toString();
		return selectedUser;
	}
	
	/**
	 * Sets the selectedTests from the userTestComboBox
	 * @return selectedTest = 
	 */
	public int setTest()
	{
		int selectedTest = Integer.parseInt(userTestComboBox.getSelectedItem().toString());
		return selectedTest;
	}
	
	/**
	 * sets the userTestComboBox to be used for selecting the Test ID for that specific user
	 * @param x - List of TestIDs returned from the ReportModel
	 */
	public void setUserTestComboBox(List x)
	{
		usersTestDDL = x;
		userTestComboBox.removeAllItems();
		for (int i = 0; i < usersTestDDL.getItemCount(); i++)
		{
			Object temp  = usersTestDDL.getItem(i);
			userTestComboBox.addItem(temp.toString());
		}
		setSecondVisibility();
		panelRefresh();
	}
	
	/**
	 * recreates panel to update view
	 */
	private void panelRefresh() 
	{
		panel.revalidate();
		panel.repaint();		
	}

	/**
	 * sets the index value of the user's selection
	 * from the userComboBox
	 * @return userIndex - value of Index to test 
	 */
	public int setUserComboBoxValue()
	{
		int userIndex = 0;
		userIndex = userComboBox.getSelectedIndex();
		return userIndex;
	}
	
	/**
	 * sets the index value of the user's selection
	 * from the userTestComboBox
	 * @return testIndex - value of testIndex to test 
	 */
	public int setTestSelection()
	{
		int testIndex = 0;
		testIndex = userTestComboBox.getSelectedIndex();
		return testIndex;
	}
	
	/**
	 * listens for the ReportButton to be pressed to generate report
	 * @param listenForReportButton
	 */
	public void addReportListener(ActionListener listenForReportButton)
	{
		reportButton.addActionListener(listenForReportButton);
	}
	
	/**
	 * listener for using the usersDDL menu.
	 * @param listenForUserTestButton
	 */
	public void addUserSelectListener(ActionListener listenForUserTestButton)
	{
		userTestButton.addActionListener(listenForUserTestButton);
	}
	
	/**
	 * listener for enabling userTestButton when a user is selected
	 * @param listenforUserComboBox
	 */
	public void addUserComboBoxItemListener(ItemListener listenforUserComboBox)
	{
		userComboBox.addItemListener(listenforUserComboBox);
	}

	/**
	 * listener for enabling reportButton when a testID is selected
	 * @param listenforUserTestComboBox
	 */
	public void addUserTestComboBoxListener(ItemListener listenforUserTestComboBox)
	{
		userTestComboBox.addItemListener(listenforUserTestComboBox);
	}
	
	/**
	 * Enable or Disable userTestButton
	 * @param x - true / false value 
	 */
	public void switchUserTestButton(Boolean x) 
	{
		userTestButton.setEnabled(x);
	}
	
	/**
	 * Enable or Disable reportButton
	 * @param x - true / false value 
	 */
	public void switchReportButton(Boolean x) 
	{
		reportButton.setEnabled(x);
	}
}
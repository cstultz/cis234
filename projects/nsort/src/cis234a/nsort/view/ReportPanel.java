package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.w3c.dom.events.EventException;

import java.awt.GridBagConstraints;
import java.awt.List;
/**
 * The ReportPanel Class contains the components for the ReportFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
@SuppressWarnings("serial")
public class ReportPanel extends JPanel 
{
//	private static final Dimension DIM = new Dimension(600,600);
	private JComboBox<String> userComboBox;
	private JComboBox<String> userTestComboBox;
	private JButton reportButton;
	private JButton userTestButton;
	private JButton startOverButton;
	private JTable table;
	private Object[][] data;
	private String[] columns;
	private List usersDDL;
	private List usersTestDDL;
	private GridBagConstraints c = new GridBagConstraints();
	private JScrollPane scrollPane;
	@SuppressWarnings("unused")
	private String selectedUser;
	@SuppressWarnings("unused")
	private int selectedTest;
	@SuppressWarnings("unused")
	private int userIndex;
	
	/**
	 * Constructor for the ReportPanel. Must pass a parameter reference of the ReportController to the panel
	 * in order to communicate back to the ReportController.
	 * @param controller
	 */
	public ReportPanel()
	{
		setupPanel();
		setupLayout();
	}
	
	/**
	 * setup JPanel layout
	 */
	public void setupLayout()
	{
        // set border for the panel
		setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Report Panel"));
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		/**
		 * set up userComboBox
		 */
		userComboBox = new JComboBox<String>();
		//setUsersComboBox();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;
		add(userComboBox, c);

		/**
		 * set up userTestButton
		 */
		userTestButton = new JButton("Get User Tests");
		userTestButton.setEnabled(false);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.NORTH;
		add(userTestButton, c);

		/**
		 * set up userTestComboBox
		 */
		userTestComboBox = new JComboBox<String>();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;
		add(userTestComboBox, c);
		/**
		 * set up reportButton
		 */
		reportButton = new JButton("Run Report");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.NORTH;
		add(reportButton, c);
		
		/**
		 * set up startOverButton
		 */
		startOverButton = new JButton("Restart Reporting");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.BASELINE;
		add(startOverButton, c);
		
		setInitialVisibility();
	}
	
	/**
	 * listens for the ReportButton to be pressed to generate report
	 * @param listenForReportButton
	 */
	public void addReportListener(ActionListener al)
	{
		reportButton.addActionListener(al);
	}
	
	/**
	 * listener for using the usersDDL menu.
	 * @param listenForUserTestButton
	 */
	public void addUserSelectListener(ActionListener al)
	{
		userTestButton.addActionListener(al);
	}
	
	/**
	 * listener for enabling userTestButton when a user is selected
	 * @param listenforUserComboBox
	 */
	public void addUserComboBoxItemListener(ItemListener il)
	{
		userComboBox.addItemListener(il);
	}

	/**
	 * listener for enabling reportButton when a testID is selected
	 * @param listenforUserTestComboBox
	 */
	public void addUserTestComboBoxListener(ItemListener il)
	{
		userTestComboBox.addItemListener(il);
	}

	public void addStartOverButton(ActionListener al)
	{
		startOverButton.addActionListener(al);
	}
	
	/**
	 *Sets the initial visibility of items
	 *upon panel creation 
	 */
	public void setInitialVisibility() 
	{
		userComboBox.setVisible(true);
		userTestButton.setVisible(true);
		userTestComboBox.setVisible(false);
		reportButton.setVisible(false);
		startOverButton.setVisible(false);
	}
	
	/**
	 * Sets the appropriate visible items for the 
	 * second part of information gathering
	 */
	public void setSecondVisibility()
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
	public void setThirdVisibility()
	{
		userTestComboBox.setVisible(false);
		reportButton.setVisible(false);
		switchStartOverButton(true);
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
		scrollPane.setPreferredSize(new Dimension(300,300));
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scrollPane, c);
//		setThirdVisibility();
//		panelRefresh();
	}
		
	/**
	 * sets the UserComboBox to be used for selecting the User/Test Taker
	 */
	public void setUsersComboBox(List userList)
	{
		usersDDL = userList;
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
		return selectedUser = userComboBox.getSelectedItem().toString();
	}
	
	/**
	 * Sets the selectedTests from the userTestComboBox
	 * @return selectedTest = 
	 */
	public int setTest()
	{
		return selectedTest = Integer.parseInt(userTestComboBox.getSelectedItem().toString());
	}
	
	/**
	 * sets the userTestComboBox to be used for selecting the Test ID for that specific user
	 * @param x - List of TestIDs returned from the ReportModel
	 */
	public void setUserTestComboBox(List userTestList)
	{
		usersTestDDL = userTestList;
		userTestComboBox.removeAllItems();
		for (int i = 0; i < usersTestDDL.getItemCount(); i++)
		{
			Object temp  = usersTestDDL.getItem(i);
			userTestComboBox.addItem(temp.toString());
		}
	}
	
	/**
	 * recreates panel to update view
	 */
	public void panelRefresh() 
	{
		revalidate();
		repaint();	
	}

	/**
	 * sets the index value of the user's selection
	 * from the userComboBox
	 * @return userIndex - value of Index to test 
	 */
	public int setUserComboBoxValue()
	{
		return userIndex = userComboBox.getSelectedIndex();
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
	
	/**
	 * Enable or Disable startOverButton
	 * @param x true/false input
	 */
	public void switchStartOverButton(Boolean x)
	{
		startOverButton.setVisible(x);
	}
	/**
	 * gets the current user location
	 * @return int of current user
	 */
	public int getCurrentUserLoc()
	{
		int tempInt = userComboBox.getSelectedIndex();
		return tempInt;
	}
	
	/**
	 * clears all boxes and tables of data for restarting or reports
	 */
	public void startOver()
	{
		try
		{
			this.remove(scrollPane);
		}
		catch(EventException e)
		{
			
		}
		finally
		{
			if (userTestComboBox.isVisible())
			{
				userTestComboBox.removeAllItems();
			}		
		}
	}
}
package cis234a.nsort.model;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 * The AdminTestSetupModel class captures the behavior of the admin test setup independent of the user interface.   
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class AdminTestSetupModel {
	
	private User user;

	private ItemList existingItemsList;
	private ItemList testItemsList;
	
	//private int progressMeterSelectedState;
	
	private ProgressMeter progressMeter;
	
	private boolean adminTestSetupFrameState; 

	/**
	 * Constructor for the class. Set the admin test setup state false.
	 */
	public AdminTestSetupModel()
	{
		progressMeter = new ProgressMeter();
		setAdminTestSetupState(false);
	}

	/**
	 * Get the admin test setup state of the test
	 * @return true if test is finalized; false if not.
	 */
	public boolean getAdminTestSetupState() {
		return adminTestSetupFrameState;
	}

	/**
	 * set the state of the admin test setup frame determines if the frame is shown or hidden.
	 *  
	 * @param adminTestSetupState true hides the admin test setup frame; false shows the admin test setup frame 
	 */
	public void setAdminTestSetupState(boolean adminTestSetupState) 
	{
		this.adminTestSetupFrameState = adminTestSetupState;
	}
	
	/**
	 * populate the Existing Items List from the model for the view
	 * 
	 * @param JListModel the Default List Model from the view
	 * @return the Updated Default List Model object for the view
	 */
	public DefaultListModel<String> populateExistingItemsToTheDefaultListModel(DefaultListModel<String> JListModel)
	{
		for (int i = 0; i < existingItemsList.getSize(); i++)
		{
			Item item = existingItemsList.getItem(i);
			String value = item.getValue();
			JListModel.addElement(value);                     //add each item to the DefaultListModel view
		}
		return JListModel;
	}
	
	/**
	 * populate the Test Items List from the model for the view
	 * 
	 * @param JListModel the Default List Model from the view
	 * @return the Updated Default List Model object for the view
	 */
	public DefaultListModel<String> populateTestItemsToTheDefaultListModel(DefaultListModel<String> JListModel)
	{
		for (int i = 0; i < testItemsList.getSize(); i++)
		{
			Item item = testItemsList.getItem(i);
			String value = item.getValue();
			JListModel.addElement(value);                     //add each item to the DefaultListModel view
		}
		return JListModel;
	}

	/**
	 * remove an item from the test items list
	 * 
	 * @param JListModel Default List Model Test Items List in the view
	 * @param value the value of the item being removed
	 */
	public void removeItemFromTestItemList(DefaultListModel<String> JListModel, String value)
	{
        //nothing is saved to the database at this time until the admin clicks the 'Finish' button.
		JListModel.removeElement(value);     //update the view
		testItemsList.removeItem(value);     //update the model
	}
	
	/**
	 * add an item from the Existing Items List to the Test Items List
	 * 
	 * @param JListModel the DefaultListModel Existing Items List object to be populated
	 * @param value of the item being added
	 */
	public void addExistingItemToTestItemsList(DefaultListModel<String> JListModel, String value)
	{
		if (listIsUnique(JListModel, value))
		{
			JListModel.addElement(value);         //update the view
			
			Item item = new Item();               //create the new Item
			item.setValue(value);                 //set the value of the item
			
			testItemsList.addItem(item);          //update the model
		}
		else
		{
			String input = "";
			for (int i = 0; i < JListModel.size(); i++)
			{
					if (JListModel.getElementAt(i).equalsIgnoreCase(value))
					{
						input = JListModel.getElementAt(i);
								if (value.equalsIgnoreCase(input)) break;
					}
			}
			
			JOptionPane.showMessageDialog(null,"'" + input + "' item already exists in the Test Items List.","Duplicate item",JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * Checks to see if the Test Item List meets the minimum requirements for a test (test items list >= 2)
	 * 
	 * @param testItemsListModel the items on the test being checked
	 * @return true if minimum requirements of the test are met; false if not.
	 */
	public boolean checkItemsListMeetsMinimumRequirements(DefaultListModel<String> testItemsListModel)
	{
		if (testItemsListModel.size() >= 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Checks to see if the user input value already exists on the specified list
	 * 
	 * @param jListModel the DefaultListModel object to be checked
	 * @param value of the item being checked
	 * @return true if list is unique; false if not.
	 */
	public boolean listIsUnique(DefaultListModel<String> JListModel, String value)
	{
		for (int i = 0; i < JListModel.size(); i++)
		{
				if (JListModel.getElementAt(i).equalsIgnoreCase(value))
				{
					return false;
				}
		}
		return true;
	}
	
	/**
	 * save the Test Items List to the model
	 * 
	 * @param items being saved
	 */
	public void saveTestList(ItemList items)
	{
		testItemsList = items; 
	}
	
	/**
	 * set the Existing Items list in the model
	 * 
	 * @param items being set
	 */
	public void setExistingItemsList(ItemList items)
	{
		existingItemsList = items;
	}

	/**
	 * set the Test Items List in the model
	 * 
	 * @param items to be set
	 */
	public void setTestItemsList(ItemList items)
	{
		testItemsList = items;
	}
	
	/**
	 * set the User in the model
	 * 
	 * @param user to be set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * Set the user login in state to true
	 * 
	 * @param loginState true if logged in; false if not.
	 */
	public void setLoginState(boolean loginState) {
		user.setLoginState(loginState);
	}

	/**
	 * Get the login state of the user
	 * @return true if user is logged in; false if not.
	 */
	public boolean getLoggedInState() {
		return user.getLoginState();
	}
	
	/**
	 * get the user access role from the user in the model
	 * 
	 * @return the enum Role.UserAccessRole of the user
	 */
	public Role.UserAccessRole getUserAccessRole()
	{
		return user.getUserAccessRole();
	}
	
	/**
	 * set the user access role of the user in the model
	 * 
	 * @param Role.UserAccessRole of the user
	 */
	public void setUserAccessRole(Role.UserAccessRole userAccessRole)
	{
		user.setUserAccessRole(userAccessRole);
	}
	
	/**
	 * add a new item to the Existing Items List in the model
	 * 
	 * @param value of the item being added
	 */
	public void addNewItemToExistingItemsList(String value)
	{
		Item item = new Item();               //create the new Item
		item.setValue(value);                 //set the value of the item
		
		existingItemsList.addItem(item);      //update the model
	}
	
	/**
	 * get the Existing Items List item that matches the value  
	 * 
	 * @param value of the item to be matched
	 * @return the value of the item on the Existing Items List matching the value 
	 */
	public String getExistingItemsListModelMatch(String value)
	{
		return existingItemsList.getItem(value).getValue();
	}
	
	/**
	 * set the progress meter selected state in the model
	 * 
	 * @param progressMeterSelectedState 0 for false; 1 for true.
	 */
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		progressMeter.setSelectedState(progressMeterSelectedState);
	}	
	/**
	 * get the progress meter selected state
	 * 
	 * @return 0 for not selected (false); 1 for selected (true).
	 */
	public int getProgressMeterSelectedState()
	{
		//return progressMeterSelectedState;
		if (progressMeter.getSelectedState())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}
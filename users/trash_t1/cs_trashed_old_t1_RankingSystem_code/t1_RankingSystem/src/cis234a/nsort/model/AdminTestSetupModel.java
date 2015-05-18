package cis234a.nsort.model;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AdminTestSetupModel {
	
	private User user;

	private ItemList existingItemsList;
	private ItemList testItemsList;
	
	private int progressMeterSelectedState;
	
	private boolean adminTestSetupFrameState; 

	/**
	 * Constructor
	 */
	public AdminTestSetupModel()
	{
		setAdminTestSetupState(false);
	}

	/**
	 * Get the admin test setup state of the test
	 * @return true if test is finalized; false if not.
	 */
	public boolean getAdminTestSetupState() {
		return adminTestSetupFrameState;
	}

	public void setAdminTestSetupState(boolean adminTestSetupState) 
	{
		this.adminTestSetupFrameState = adminTestSetupState;
	}
	
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

	public void removeItemFromTestItemList(DefaultListModel<String> JListModel, String value)
	{
        //nothing is saved to the database at this time until the admin clicks the 'Finish' button.
		JListModel.removeElement(value);     //update the view
		testItemsList.removeItem(value);     //update the model
	}
	
	/**
	 * add an item from the Existing Items List to the Test Items List
	 * 
	 * @param JListModel the DefaultListModel object to be populated
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
	
	public void saveTestList(ItemList items)
	{
		testItemsList = items; 
	}
	
	public void setExistingItemsList(ItemList items)
	{
		existingItemsList = items;
	}

	public void setTestItemsList(ItemList items)
	{
		testItemsList = items;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * Set the user login in state to true
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
	
	public Role.UserAccessRole getUserAccessRole()
	{
		return user.getUserAccessRole();
	}
	
	public void setUserAccessRole(Role.UserAccessRole userAccessRole)
	{
		user.setUserAccessRole(userAccessRole);
	}
	
	public void addNewItemToExistingItemsList(String value)
	{
		Item item = new Item();               //create the new Item
		item.setValue(value);                 //set the value of the item
		
		existingItemsList.addItem(item);      //update the model
	}
	
	public String getExistingItemsListModelMatch(String value)
	{
		return existingItemsList.getItem(value).getValue();
	}
	
	public void setProgressMeterSelectedState(int progressMeterSelectedState)
	{
		this.progressMeterSelectedState = progressMeterSelectedState;
	}
	
	public int getProgressMeterSelectedState()
	{
		return progressMeterSelectedState;
	}
}
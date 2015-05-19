package cis234a.nsort.model;

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
	 * remove an item from the test items list
	 * 
	 * @param JListModel Default List Model Test Items List in the view
	 * @param value the value of the item being removed
	 */
	public void removeItemFromTestItemList(String value)
	{
		testItemsList.removeItem(value);     
	}
	
	/**
	 * add an item from the Existing Items List to the Test Items List
	 * 
	 * @param JListModel the DefaultListModel Existing Items List object to be populated
	 * @param value of the item being added
	 */
	public void addExistingItemToTestItemsList(String selectedValue)
	{
			Item item = new Item();               //create the new Item
			item.setValue(selectedValue);         //set the value of the item
			testItemsList.addItem(item);          //update the model
	}
	/**
	 * Checks to see if the Test Item List meets the minimum requirements for a test (test items list >= 2)
	 * 
	 * @param testItemsListModel the items on the test being checked
	 * @return true if minimum requirements of the test are met; false if not.
	 */
	public boolean checkItemsListMeetsMinimumRequirements()
	{
		if (testItemsList.getSize() >= 2)
		{
			return true;
		}
		else
		{
			return false;
		}
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
	
	/**
	 * check to see if the the selectedValue is currently already on the test items list 
	 * 
	 * @param selectedValue being checked for on the test items list
	 * @return true if item is found; false if not.
	 */
	public boolean checkTestItemsListMatch(String selectedValue)
	{
		if (testItemsList.getItem(selectedValue) != null)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * check to see if the the selectedValue is currently already on the existing items list 
	 * 
	 * @param selectedValue being checked for on the existing items list
	 * @return true if item is found; false if not.
	 */
	public boolean checkExistingItemsListMatch(String selectedValue)
	{
		if (existingItemsList.getItem(selectedValue) != null)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * get the existing items list
	 * 
	 * @return the ItemList existing items list
	 */
	public ItemList getExistingItemsList()
	{
		return existingItemsList;
	}

	/**
	 * get the test items list
	 * 
	 * @return the ItemList test items list
	 */
	public ItemList getTestItemsList()
	{
		return testItemsList;
	}
}
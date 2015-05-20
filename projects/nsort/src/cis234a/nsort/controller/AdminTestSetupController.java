package cis234a.nsort.controller;

import cis234a.nsort.model.*;
import cis234a.nsort.view.*;
/**
 * The AdminTestSetupController handles the logic for creating a user test, on/off state of the progress indicator during the user test, and
 * the access to the test results reporting for all users.
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class AdminTestSetupController {
	
	private RankingSystemController controller;
	
	private AdminTestSetupModel model;
	private AdminTestSetupView view;
	
	private SqlUser_234a_t1 sqlUser;

	/**
	 * Constructor for the class.
	 * @param RankingSystemController for communication back.
	 * @param AdminTestSetupModel will directly manage the data, logic and rules of the admin test setup.
	 * @param AdminTestSetupView output representation of the admin test setup information.
	 */
	public AdminTestSetupController(RankingSystemController controller, AdminTestSetupModel model, AdminTestSetupView view) {
		this.controller = controller;
		this.model = model;
		this.view = view;
		
		sqlUser = new SqlUser_234a_t1();
		
		updateView();   
	}

	/**
	 * update the view. populates the existing items list, the test items list, and updates the progress meter check box selected state.
	 */
	private void updateView() 
	{
		populateExistingItemsToTheDefaultListModel();
		populateTestItemsToTheDefaultListModel();
		updateProgressMeterCheckBoxSetSelected();
		view.updateAdminTestSetupFrame(model.getAdminTestSetupState());
	}
	
	/**
	 * populate the Items from the model to the Existing Items List in the view.
	 */
	public void populateExistingItemsToTheDefaultListModel()
	{
		view.setExistingItemsList(model.getExistingItemsList());
	}
	
	/**
	 * populate the Items from the model to the Test Items List in the view.
	 */
	public void populateTestItemsToTheDefaultListModel()
	{
		view.setTestItemsList(model.getTestItemsList());
	}

	/**
	 * remove an item from the Test Items list.
	 * 
	 * @param selectedValue of the item being removed
	 */
	public void removeItemFromTestItemList(String selectedValue)
	{
		model.removeItemFromTestItemList(selectedValue);
	}
	
	/**
	 * add an item from the Existing Items List to the Test Items List
	 * 
	 * @param selectedValue being added to the Test Items List from the Existing Items List
	 */
	public void addExistingItemToTestItemsList(String selectedValue)
	{
		if (! model.checkTestItemsListMatch(selectedValue))
		{
			Item item = new Item();                                     //create the new Item
			item.setValue(selectedValue);                               //set the value of the item
			
			model.addExistingItemToTestItemsList(selectedValue);        //update the model
			
			view.updateTestItemsList(selectedValue);                    //update the view
		}
		else
		{
			view.showDuplicateTestItemsMessage(selectedValue);
		}
	}
	
	/**
	 * save the Test Items List to the database and the model. logs out the administrator.
	 * 
	 * @param items Item List being saved
	 */
	public void saveTestItemsList(ItemList items)
	{
		sqlUser.saveTest(items);
		model.saveTestList(items);

		logoutAdmin();
	}
	
	/**
	 * log out the administrator. sets login state to false; set user access role to Unassigned.
	 */
	public void logoutAdmin()
	{
		model.setLoginState(false);
		model.setUserAccessRole(Role.UserAccessRole.Unassigned);
	}
	
	/**
	 * hide the admin test setup frame (admin logged off) and launch the login frame
	 */
	public void hideAdminTestSetup()
	{
		logoutAdmin();
		controller.launchLogin();
	}
	
	/**
	 * Checks to see if the Test Item List meets the minimum requirements for a test (test items list >= 2)
	 * 
	 * @param testItemsListModel the items on the test being checked
	 * @return true if minimum requirements of the test are met; false if not.
	 */
	public boolean checkItemsListMeetsMinimumRequirements()
	{
		return model.checkItemsListMeetsMinimumRequirements();
	}
	
	/**
	 * check to see if the new item value being added is an empty string
	 * 
	 * @param value of the item being added
	 * @return true if the value is empty; false if not.
	 */
	public boolean checkAddAnItemTextFieldIsEmpty(String value)
	{
		if (value.trim().equals("")) {return true;}                 
		else {return false;}                                       
	}
	
	/**
	 * check to see if the new item value being added is unique to the Existing Items List
	 *  
	 * @param existingItemsListModel being checked
	 * @param value of the item being added
	 * @return true if the value of the item is unique; false if not.
	 */
	public boolean checkAddAnItemTextFieldIsUnique(String selectedValue)
	{
		if (! model.checkExistingItemsListMatch(selectedValue)) {return true;}
		else {return false;}
	}
	
	/**
	 * add a new item to the Existing Items List in the database and the model. 
	 * 
	 * @param existingItemsListModel the value will be added to
	 * @param value of the item being added
	 */
	public void addNewItemToExistingItemsList(String newItemValue)
	{
		if (checkAddAnItemTextFieldIsEmpty(newItemValue))
		{
			view.showEmptyItemMessage();
		}
		else
		{
			if (checkAddAnItemTextFieldIsUnique(newItemValue))
			{
				model.addNewItemToExistingItemsList(newItemValue);
				sqlUser.addNewItem(newItemValue);
				view.updateExistingItemsList(newItemValue);
			}
			else
			{
				view.showExistingItemMatchMessage(model.getExistingItemsListModelMatch(newItemValue));
			}
		}
	}
	
	/**
	 * get the Existing Items List item value that matches the new item value being added
	 * 
	 * @param existingItemsListModel that contains the item value that matches
	 * @param value of the new item being added
	 * @return the value of the item match from the Existing Items List
	 */
	public String getExistingItemsListModelMatch(String value)
	{
		return model.getExistingItemsListModelMatch(value);
	}
	
	/**
	 * gets the persisted progress meter selected state from the database and updates the model and view.
	 */
	public void updateProgressMeterCheckBoxSetSelected()
	{
		boolean selectedState = sqlUser.getProgressMeterSelectedState();
		model.setProgressMeterSelectedState(selectedState);
		view.setProgressMeterSelectedState(selectedState);
	}
	
	/**
	 * sets the progress meter selected state in the database and the model.
	 * 
	 * @param progressMeterSelectedState
	 */
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		sqlUser.setProgressBarSelectedState(progressMeterSelectedState);
		model.setProgressMeterSelectedState(progressMeterSelectedState);
	}
	
	/**
	 * launch the test results reporting view
	 */
	public void launchReport()
	{
		Report model = new Report();
		ReportView view = new ReportFrame();
		@SuppressWarnings("unused")
		ReportController controller = new ReportController(view, model);
		//view.setVisible(true);
	}
}
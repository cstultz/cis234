package cis234a.nsort.controller;

import javax.swing.DefaultListModel;

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
		DefaultListModel<String> existingItemsListModel  = new DefaultListModel<String>();
		existingItemsListModel = model.populateExistingItemsToTheDefaultListModel(existingItemsListModel);
		view.setExistingItemsList(existingItemsListModel);
	}
	
	/**
	 * populate the Items from the model to the Test Items List in the view.
	 */
	public void populateTestItemsToTheDefaultListModel()
	{
		DefaultListModel<String> testItemsListModel  = new DefaultListModel<String>();
		testItemsListModel = model.populateTestItemsToTheDefaultListModel(testItemsListModel);
		view.setTestItemsList(testItemsListModel);
	}

	/**
	 * remove an item from the Test Items list.
	 * 
	 * @param JListModel the Test Items List
	 * @param value of the item being removed
	 */
	public void removeItemFromTestItemList(DefaultListModel<String> JListModel, String value)
	{
		model.removeItemFromTestItemList(JListModel, value);
	}
	
	/**
	 * add an item from the Existing Items List to the Test Items List
	 * 
	 * @param JListModel the Test Items List
	 * @param value being added from the Existing Items List
	 */
	public void addExistingItemToTestItemsList(DefaultListModel<String> JListModel, String value)
	{
		model.addExistingItemToTestItemsList(JListModel, value);
	}
	
	/**
	 * converts a default list model to an Item List object
	 * 
	 * @param JListModel default list model from the view
	 * @return Item List created
	 */
	public ItemList DefaultListModelToItemList(DefaultListModel<String> JListModel)
	{
		ItemList items = new ItemList();
		
		for (int i = 0; i < JListModel.size(); i++)
		{
			Item item = new Item();
			item.setValue(JListModel.getElementAt(i));
			items.addItem(item);
		}
		return items;
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
	public boolean checkItemsListMeetsMinimumRequirements(DefaultListModel<String> testItemsListModel)
	{
		return model.checkItemsListMeetsMinimumRequirements(testItemsListModel);
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
	public boolean checkAddAnItemTextFieldIsUnique(DefaultListModel<String> existingItemsListModel, String value)
	{
		if (model.listIsUnique(existingItemsListModel, value)) {return true;}
		else {return false;}
	}
	
	/**
	 * add a new item to the Existing Items List in the database and the model. 
	 * 
	 * @param existingItemsListModel the value will be added to
	 * @param value of the item being added
	 */
	public void addNewItemToExistingItemsList(DefaultListModel<String> existingItemsListModel, String value)
	{
		model.addNewItemToExistingItemsList(value);
		existingItemsListModel.addElement(value);
		sqlUser.addNewItem(value);
	}
	
	/**
	 * get the Existing Items List item value that matches the new item value being added
	 * 
	 * @param existingItemsListModel that contains the item value that matches
	 * @param value of the new item being added
	 * @return the value of the item match from the Existing Items List
	 */
	public String getExistingItemsListModelMatch(DefaultListModel<String> existingItemsListModel, String value)
	{
		return model.getExistingItemsListModelMatch(value);
	}
	
	/**
	 * gets the persisted progress meter selected state from the database and updates the model and view.
	 */
	public void updateProgressMeterCheckBoxSetSelected()
	{
		if (sqlUser.getProgressMeterSelectedState())
		{
			model.setProgressMeterSelectedState(1);
			view.setProgressMeterSelectedState(true);
		}
		else
		{
			model.setProgressMeterSelectedState(0);
			view.setProgressMeterSelectedState(false);
		}
	}
	
	/**
	 * sets the progress meter selected state in the database and the model.
	 * 
	 * @param progressMeterSelectedState
	 */
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		if(progressMeterSelectedState)
		{
			sqlUser.setProgressBarSelectedState(1);
			model.setProgressMeterSelectedState(1);
		}
		else
		{
			sqlUser.setProgressBarSelectedState(0);
			model.setProgressMeterSelectedState(1);
		}
		
	}
	
	/**
	 * launch the test results reporting view
	 */
	public void launchReport()
	{
		Report model = new Report();
		ReportView view = new ReportView(model.getUsers());
		@SuppressWarnings("unused")
		ReportController controlsler = new ReportController(view, model);
		view.setVisible(true);
	}
}
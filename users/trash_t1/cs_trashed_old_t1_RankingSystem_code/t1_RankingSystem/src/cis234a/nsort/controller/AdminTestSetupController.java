package cis234a.nsort.controller;

import javax.swing.DefaultListModel;
import cis234a.nsort.model.*;
import cis234a.nsort.view.*;

public class AdminTestSetupController {
	
	private RankingSystemController controller;
	
	private AdminTestSetupModel model;
	private AdminTestSetupView view;
	
	private SqlUser_234a_t1 sqlUser;

	public AdminTestSetupController(RankingSystemController controller, AdminTestSetupModel model, AdminTestSetupView view) {
		this.controller = controller;
		this.model = model;
		this.view = view;
		
		sqlUser = new SqlUser_234a_t1();
		
		updateView();   
	}

	private void updateView() 
	{
		populateExistingItemsToTheDefaultListModel();
		populateTestItemsToTheDefaultListModel();
		updateProgressMeterCheckBoxSetSelected();
		view.updateAdminTestSetupFrame(model.getAdminTestSetupState());
	}
	
	public void populateExistingItemsToTheDefaultListModel()
	{
		DefaultListModel<String> existingItemsListModel  = new DefaultListModel<String>();
		existingItemsListModel = model.populateExistingItemsToTheDefaultListModel(existingItemsListModel);
		view.setExistingItemsList(existingItemsListModel);
	}
	
	public void populateTestItemsToTheDefaultListModel()
	{
		DefaultListModel<String> testItemsListModel  = new DefaultListModel<String>();
		testItemsListModel = model.populateTestItemsToTheDefaultListModel(testItemsListModel);
		view.setTestItemsList(testItemsListModel);
	}

	/**
	 * remove an item from the Test Items list
	 * 
	 * @param JListModel the Test Items List object to be populated
	 * @param value of the item being removed
	 */
	public void removeItemFromTestItemList(DefaultListModel<String> JListModel, String value)
	{
		model.removeItemFromTestItemList(JListModel, value);
	}
	
	public void addExistingItemToTestItemsList(DefaultListModel<String> JListModel, String value)
	{
		model.addExistingItemToTestItemsList(JListModel, value);
	}
	
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
	
	public void saveTestItemsList(ItemList items)
	{
		sqlUser.saveTest(items);
		model.saveTestList(items);

		logoutAdmin();
	}
	
	public void logoutAdmin()
	{
		model.setLoginState(false);
		model.setUserAccessRole(Role.UserAccessRole.Unassigned);
	}
	
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
	
	public boolean checkAddAnItemTextFieldIsEmpty(String item)
	{
		if (item.trim().equals("")) {return true;}                 
		else {return false;}                                       
	}
	
	public boolean checkAddAnItemTextFieldIsUnique(DefaultListModel<String> existingItemsListModel, String value)
	{
		if (model.listIsUnique(existingItemsListModel, value)) {return true;}
		else {return false;}
	}
	
	public void addNewItemToExistingItemsList(DefaultListModel<String> existingItemsListModel, String value)
	{
		model.addNewItemToExistingItemsList(value);
		existingItemsListModel.addElement(value);
		sqlUser.addNewItem(value);
	}
	
	public String getExistingItemsListModelMatch(DefaultListModel<String> existingItemsListModel, String value)
	{
		return model.getExistingItemsListModelMatch(value);
	}
	
	public void updateProgressMeterCheckBoxSetSelected()
	{
		view.setProgressMeterSelectedState(sqlUser.getProgressMeterSelectedState());
	}
	
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		if(progressMeterSelectedState)
		{
			sqlUser.setProgressBarSelectedState(1);
		}
		else
		{
			sqlUser.setProgressBarSelectedState(0);
		}
		
	}
}

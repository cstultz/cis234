package cis234a.nsort.view;

import cis234a.nsort.model.*;
import cis234a.nsort.controller.*;
/**
 * The interface for a view of an AdminTestSetup model   
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public interface AdminTestSetupView {

	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param adminTestSetupFrameState true if the user is done with the view; false if not.
	 */
	public boolean updateAdminTestSetupFrame(boolean adminTestSetupFrameState);
	
	/**
	 * set the Existing Items List in the view
	 * 
	 * @param the Existing Items List Default List Model 
	 */
	public void setExistingItemsList(ItemList existingItemsList);
	
	/**
	 * set the Test Items List in the view
	 * 
	 * @param the Test Items List Default List Model
	 */
	public void setTestItemsList(ItemList testItemsList);

	/**
	 * update the test items list with the new item value
	 * 
	 * @param selectedValue of the item being added to the test items list.
	 */
	public void addItemToTestItemsList(String selectedValue);
	
	/**
	 * show the duplicate message of an item on the test items list to the user
	 * 
	 * @param selectedValue of the item already on the test items list.
	 */
	public void showDuplicateTestItemsMessage(String selectedValue);
	
	/**
	 * show the empty item message when user tries to add an empty string to the existing items list.
	 */
	public void showEmptyItemMessage();
	
	/**
	 * update the existing items list with the new item value
	 * 
	 * @param newItemValue of the item being added to the list.
	 */
	public void updateExistingItemsList(String newItemValue);
	
	/**
	 * show user the existing item match message
	 * 
	 * @param match value if the item already in the list
	 */
	public void showExistingItemMatchMessage(String match);
	
	/**
	 * set the progress meter selected state in the view
	 * 
	 * @param true if the progress meter is to be selected; false if not.
	 */
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState);
	
	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller to register with this view
	 */
	public void registerController(AdminTestSetupController controller);
	
	public void updateImage(byte[] data);
	
	public void removeItemFromExistingItemsList(String value);
	
	public void removeItemFromTestItemsList(String value);
	
	public boolean checkItemOnTestItemsList(String value);
	
	public void enableFinishButton(boolean setState);
}
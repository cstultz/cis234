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

	public void updateTestItemsList(String selectedValue);
	
	public void showDuplicateTestItemsMessage(String selectedValue);
	
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
	
}
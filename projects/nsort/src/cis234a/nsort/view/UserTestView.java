package cis234a.nsort.view;

import cis234a.nsort.controller.*;
/**
 * The interface for a view of an UserTest model.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/9/2015)
 */
public interface UserTestView {
	
	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param UserTestFrameState true if the user is done with the view; false if not.
	 */
	public boolean updateUserTestFrame(boolean UserTestFrameState);
	
	/**
	 * set the left item label object
	 */
	public void setLeftItemLabelValue(String itemLeftValue);
	
	/**
	 * set the right item label object
	 */
	public void setRightItemLabelValue(String itemRightValue);
	
	/**
	 * set the total questions on the user test
	 * 
	 * @param total questions on the test
	 */
	public void setTotalQuestions(int totalQuestions);
	
	/**
	 * update the progress meter selected state determines if the progress meter is shown or not shown
	 * 
	 * @param true if the progress meter is to be shown during the user test; false if not
	 */
	public void updateProgressMeterSelectedState (boolean progressMeterSelectedState);
	
	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller to register with this view
	 */
	public void registerController(UserTestController controller);
	
}
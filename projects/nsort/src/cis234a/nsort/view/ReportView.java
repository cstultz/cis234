package cis234a.nsort.view;

import java.awt.List;

import cis234a.nsort.controller.*;
/**
 * The interface for a view of an Report model   
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public interface ReportView {
	
	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param reportFrameState true if the user is done with the view; false if not.
	 */
	public boolean updateReportFrameState(boolean reportFrameState);
	
	/**
	 * test
	 * @param userList
	 */
	public void setUserList(List userList);
	
	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller to register with this view
	 */
		
	public void registerController(ReportController controller);
	
	
}
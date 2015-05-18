package cis234a.nsort.view;

import cis234a.nsort.controller.*;
/**
 * The interface for a view of an Registration model   
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public interface RegistrationView {
	
	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param registrationFrameState true if the user is done with the view; false if not.
	 */
	public boolean updateRegistrationFrameState(boolean registrationFrameState);
	
	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller to register with this view
	 */
	public void registerController(RegistrationController controller);
	
}
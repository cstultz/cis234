package cis234a.nsort.view;

import cis234a.nsort.controller.*;

public interface LoginView {
	
	public boolean updateLoginFrame(boolean loggedinState);
	
	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller to register with this view
	 */
	public void registerController(LoginController controller);
	
}
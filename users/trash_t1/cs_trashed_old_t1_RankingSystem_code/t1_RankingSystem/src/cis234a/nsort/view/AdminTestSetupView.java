package cis234a.nsort.view;

import javax.swing.DefaultListModel;

import cis234a.nsort.controller.*;

public interface AdminTestSetupView {
	
	public boolean updateAdminTestSetupFrame(boolean adminTestSetupFrameState);
	public String getExistingItemsListName();
	public String getTestItemsListName();
	public void setExistingItemsList(DefaultListModel<String> JListModel);
	public void setTestItemsList(DefaultListModel<String> JListModel);
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState);
	
	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller to register with this view
	 */
	public void registerController(AdminTestSetupController controller);
	
}
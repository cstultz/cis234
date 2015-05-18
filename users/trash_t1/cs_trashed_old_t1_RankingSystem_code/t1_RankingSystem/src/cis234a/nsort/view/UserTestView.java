package cis234a.nsort.view;

import javax.swing.JLabel;

import cis234a.nsort.controller.*;

public interface UserTestView {
	
	public boolean updateUserTestFrame(boolean UserTestFrameState);
	public JLabel getLeftItemLabel();
	public JLabel getRightItemLabel();
	public void setTotalQuestions(int questionsCount);
	public void updateProgressMeterSelectedState (boolean progressMeterSelectedState);
	
	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller to register with this view
	 */
	public void registerController(UserTestController controller);
	
}
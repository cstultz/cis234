package cis234a.nsort.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import cis234a.nsort.model.*;
import cis234a.nsort.controller.*;

/**
 * The UserTestFrame Class is the GUI of the user test for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class UserTestFrame extends JFrame implements UserTestView
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;
	private UserTestPanel userTestPanel;
	private UserTestController controller;
	
	/**
	 * Constructor for the UserTestFrame. Must pass a parameter reference of the UserTestController to the frame
	 * in order to communicate back to the UserTestController
	 * 
	 * @param controller a reference to the UserTestController
	 */
	public UserTestFrame()
	{
		super("Ranking System - UserTest"); 
		userTestPanel = createUserTestPanel();
		getContentPane().add(userTestPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(false);   
		setLocationRelativeTo(null);
	}
	
	/**
	 * create the user test panel
	 * 
	 * @return the user test panel
	 */
	private UserTestPanel createUserTestPanel()
	{
		UserTestPanel userTestPanel = new UserTestPanel();

		/**
		 * add an action listener to the I can't decide button
		 */
		userTestPanel.addIcantDecideButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//icantDecideRadioButton selected
				controller.recordAnswer(Answer.Value.CANT_DECIDE, userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel());
				
				if (controller.getNextQuestion(userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel()))
				{
					incrementProgressMeter();
				}
				else 
				{
					//next question is null and this is the end of the test
					TestComplete();
				}
			}
		});
		
		/**
		 * add an action listener to the right choice button
		 */
		userTestPanel.addRightChoiceButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//icantDecideRadioButton selected
				controller.recordAnswer(Answer.Value.RIGHT, userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel());
				
				if (controller.getNextQuestion(userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel()))
				{
					incrementProgressMeter();
				}
				else 
				{
					//next question is null and this is the end of the test
					TestComplete();
				}
			}
		});
		
		/**
		 * add an action listener to the left choice button
		 */
		userTestPanel.addLeftChoiceButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//icantDecideRadioButton selected
				controller.recordAnswer(Answer.Value.LEFT, userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel());
				
				if (controller.getNextQuestion(userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel()))
				{
					incrementProgressMeter();
				}
				else 
				{
					//next question is null and this is the end of the test
					TestComplete();
				}
			}
		});
		return userTestPanel;
	}
	
	/**
	 * test is complete. hide the user test frame. display the test complete message to the user. logout the user. launch the login frame. 
	 */
	public void TestComplete()
	{
		setVisible(false);
		JOptionPane.showMessageDialog(null,"You have completed the test! You will now be logged off and returned to the login screen.","User Test - Test Complete!",JOptionPane.WARNING_MESSAGE);
		controller.logoutUser();
		controller.launchLogin();
	}

	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param userTestFrameState true if the user is done with the view; false if not.
	 */
	@Override
	public boolean updateUserTestFrame(boolean userTestFrameState) {
		setVisible(!userTestFrameState);                      
		return userTestFrameState;
	}

	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller to register with this view
	 */
	@Override
	public void registerController(UserTestController controller) {
		this.controller = controller;
	}
	
	/**
	 * set the left item label with the value
	 * 
	 * @param value to set the left item label 
	 */
	public void setLeftItemLabel(String value)
	{
		userTestPanel.setLeftItemLabel(value);
	}

	/**
	 * set the right item label with the value
	 * 
	 * @param value to sdet the right item label
	 */
	public void setRightItemLabel(String value)
	{
		userTestPanel.setRightItemLabel(value);
	}
	
	/**
	 * get the left item label object
	 */
	@Override
	public JLabel getLeftItemLabel()
	{
		return userTestPanel.getLeftItemLabel();
	}

	/**
	 * get the right item label object
	 */
	@Override
	public JLabel getRightItemLabel() {
		return userTestPanel.getRightItemLabel();
	}
	
	/**
	 * set the total questions on the user test
	 * 
	 * @param total questions on the test
	 */
	public void setTotalQuestions(int totalQuestions)
	{
		userTestPanel.setTotalQuestions(totalQuestions);
	}
	
	/**
	 * update the progress meter selected state determines if the progress meter is shown or not shown
	 * 
	 * @param true if the progress meter is to be shown during the user test; false if not
	 */
	public void updateProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		userTestPanel.setProgressMeterSelectedState(progressMeterSelectedState);
	}
	
	/**
	 * increment the progress meter for the next question
	 */
	public void incrementProgressMeter()
	{
		userTestPanel.incrementProgressMeter();
	}
}
package cis234a.nsort.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	
	private UserTestPanel createUserTestPanel()
	{
		UserTestPanel userTestPanel = new UserTestPanel();

		userTestPanel.addIcantDecideRadioButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//icantDecideRadioButton selected
				controller.recordAnswer(userTestPanel.getICantDecideRadioButtonText(), userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel());
				
				if (controller.getNextQuestion(userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel()))
				{
					userTestPanel.clearRadioButtonGroupSelection();
					incrementProgressMeter();
				}
				else 
				{
					//next question is null and this is the end of the test
					TestComplete();
				}
			}
		});
		
		userTestPanel.addRightChoiceRadioButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//icantDecideRadioButton selected
				controller.recordAnswer(userTestPanel.getRightChoiceRadioButtonText(), userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel());
				
				if (controller.getNextQuestion(userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel()))
				{
					userTestPanel.clearRadioButtonGroupSelection();
					incrementProgressMeter();
				}
				else 
				{
					//next question is null and this is the end of the test
					TestComplete();
				}
			}
		});
		
		userTestPanel.addLeftChoiceRadioButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//icantDecideRadioButton selected
				controller.recordAnswer(userTestPanel.getLeftChoiceRadioButtonText(), userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel());
				
				if (controller.getNextQuestion(userTestPanel.getLeftItemLabel(), userTestPanel.getRightItemLabel()))
				{
					userTestPanel.clearRadioButtonGroupSelection();
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
	
	public void TestComplete()
	{
		setVisible(false);
		JOptionPane.showMessageDialog(null,"You have completed the test! You will now be logged off and returned to the login screen.","User Test - Test Complete!",JOptionPane.WARNING_MESSAGE);
		controller.logoutUser();
		controller.launchLoginFrame();
	}

	@Override
	public boolean updateUserTestFrame(boolean userTestFrameState) {
		setVisible(!userTestFrameState);                       //false (user has not yet completed the user test) shows the UserTestFrame; true (user is complete with the test) hides the UserTestFrame.
		return userTestFrameState;
	}

	@Override
	public void registerController(UserTestController controller) {
		this.controller = controller;
	}
	
	public void setLeftItemLabel(String value)
	{
		userTestPanel.setLeftItemLabel(value);
	}

	public void setRightItemLabel(String value)
	{
		userTestPanel.setRightItemLabel(value);
	}
	
	@Override
	public JLabel getLeftItemLabel()
	{
		return userTestPanel.getLeftItemLabel();
	}

	@Override
	public JLabel getRightItemLabel() {
		return userTestPanel.getRightItemLabel();
	}
	
	public void setTotalQuestions(int totalQuestions)
	{
		userTestPanel.setTotalQuestions(totalQuestions);
	}
	
	public void updateProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		userTestPanel.setProgressMeterSelectedState(progressMeterSelectedState);
	}
	
	public void incrementProgressMeter()
	{
		userTestPanel.incrementProgressMeter();
	}
}

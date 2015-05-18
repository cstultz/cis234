package cis234a.nsort.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import cis234a.nsort.controller.*;
/**
 * The RegistrationFrame Class is the GUI of the user registration for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/9/2015)
 */
public class RegistrationFrame extends JFrame implements RegistrationView
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;
	private RegistrationPanel registrationPanel;
	private RegistrationController controller;
	/**
	 * Constructor for the class.
	 */
	public RegistrationFrame()
	{
		super("Ranking System - Registration"); 
		registrationPanel = createRegistrationPanel();
		getContentPane().add(registrationPanel);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pack();
		setVisible(false);    
		setLocationRelativeTo(null);
	}
	
	private RegistrationPanel createRegistrationPanel()
	{
		RegistrationPanel registrationPanel = new RegistrationPanel();
		registrationPanel.addCheckAvailabilityButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//USERNAME CHECK AVAILABILITY - 'CHECK AVAILABILITY' BUTTON CLICKED
				
				if(registrationPanel.getUsername().trim().equals(""))
				{
					emptyUsernameMessage();
				}
				else if (!checkUsernameAvailability(registrationPanel))
				{
					usernameAlreadyTakenUsernameMessage(registrationPanel);
				}
				else if (checkUsernameAvailability(registrationPanel))
				{
					validUsernameMessage(registrationPanel);
				}
			}
		});
		
		registrationPanel.addSubmitButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//USER SUBMIT REGISTRATION - 'SUBMIT' BUTTON CLICKED
				
				resetErrorLabelVisibility(registrationPanel);
				
				boolean canProcess = noEmptyRegistrationTextFields(registrationPanel);
				
				if (!checkUsernameAvailability(registrationPanel)) 
				{
					usernameAlreadyTakenUsernameMessage(registrationPanel);
				}
				else if (registrationPanel.getUsername().trim().equals(""))
				{
					emptyUsernameMessage();
				}
				else //NO ERRORS - READY TO PROCESS
				{
					if (canProcess)
					{
						ArrayList<String> user = new ArrayList<String>();

						user.add(registrationPanel.getFirstName().trim());
						user.add(registrationPanel.getLastName().trim());
						user.add(registrationPanel.getEmail().trim());
						user.add(registrationPanel.getUsername().trim());
						user.add(registrationPanel.getPassword().trim());

						controller.saveUser(user);
						int userID = controller.getUserID(registrationPanel.getUsername());
						controller.saveUserAccess(userID);
						controller.hideRegistration();
						newUserWelcomeMessage(registrationPanel);
						controller.launchLogin();
					}
				}
			}
		});
		
		registrationPanel.addCancelButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.hideRegistration();
				updateRegistrationFrameState(true);
				cancelRegistrationMessage(registrationPanel);
				controller.launchLogin();
			}
		});

		return registrationPanel;
	}
	
	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param loggedInState true if the user is done with the view; false if not.
	 */
	@Override
	public boolean updateRegistrationFrameState(boolean registrationFrameState) {
		setVisible(!registrationFrameState);                      
		return registrationFrameState;
	}

	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller  the word list controller to register with this view
	 */
	@Override
	public void registerController(RegistrationController controller) {
		this.controller = controller;
	}
	
	private boolean checkUsernameAvailability(RegistrationPanel registrationPanel) {
		return controller.CheckUsernameAvailability(registrationPanel.getUsername().trim());
	}
	
	private void resetErrorLabelVisibility(RegistrationPanel registrationPanel) {
		registrationPanel.setFirstNameErrorLabelVisibility(false);
		registrationPanel.setLastNameErrorLabelVisibility(false);
		registrationPanel.setEmailErrorLabelVisibility(false);
		registrationPanel.setPasswordErrorLabelVisibility(false);
	}
	
	private void newUserWelcomeMessage(RegistrationPanel registrationPanel) {
		JOptionPane.showMessageDialog(null,"Welcome " + registrationPanel.getFirstName() + " " + registrationPanel.getLastName(),"New User Registration Success",JOptionPane.INFORMATION_MESSAGE);
	}

	private void cancelRegistrationMessage(RegistrationPanel registrationPanel) {
		JOptionPane.showMessageDialog(null,"User Registration has been cancelled. Returning to the login screen.", "New User Registration Cancelled",JOptionPane.WARNING_MESSAGE);
	}

	private void usernameAlreadyTakenUsernameMessage(RegistrationPanel registrationPanel) {
		JOptionPane.showMessageDialog(null,"'" + registrationPanel.getUsername().trim() + "' is already taken. The username must contain at least 1 character and can not match another username in the database.","Username  '" + registrationPanel.getUsername().trim() + "' Is Already Taken",JOptionPane.WARNING_MESSAGE);
	}
	private void validUsernameMessage(RegistrationPanel registrationPanel) {
		JOptionPane.showMessageDialog(null,"The username: '" + registrationPanel.getUsername().trim() + "' is available!","Username  '" + registrationPanel.getUsername().trim() + "' Is Available",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void emptyUsernameMessage() {
		JOptionPane.showMessageDialog(null,"The username must contain at least 1 character and can not match another username in the database.","Empty Username",JOptionPane.WARNING_MESSAGE);
	}
	
	private boolean noEmptyRegistrationTextFields(RegistrationPanel registrationPanel) 
	{
		boolean canProcess = true;
		
		if (registrationPanel.getFirstName().trim().equals("")) 
		{
			registrationPanel.setFirstNameErrorLabelVisibility(true);
			canProcess = false;
		}
		if (registrationPanel.getLastName().trim().equals("")) 
		{
			registrationPanel.setLastNameErrorLabelVisibility(true);
			canProcess = false;
		} 
		if (registrationPanel.getEmail().trim().equals("")) 
		{
			registrationPanel.setEmailErrorLabelVisibility(true);
			canProcess = false;
		} 
		if (registrationPanel.getPassword().trim().equals("")) 
		{
			registrationPanel.setPasswordErrorLabelVisibility(true);
			canProcess = false;
		}
		return canProcess;
	}
}
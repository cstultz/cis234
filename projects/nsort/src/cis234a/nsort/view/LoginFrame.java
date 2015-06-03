package cis234a.nsort.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cis234a.nsort.controller.*;

/**
 * The LoginFrame Class is the GUI of the user login for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements LoginView
{
	private LoginPanel loginPanel;
	private LoginController controller;
	
	/**
	 * Constructor for the class.
	 */
	public LoginFrame()
	{
		super("Ranking System - Login"); 
		loginPanel = createLoginPanel();
		getContentPane().add(loginPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(false);    
		setLocationRelativeTo(null);
	}
	
	private LoginPanel createLoginPanel()
	{
		loginPanel = new LoginPanel();
		loginPanel.addLoginButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//USER LOGIN ATTEMPT - 'SUBMIT' BUTTON CLICKED
				loginAttempt();
			}
		});
		
		loginPanel.addUsernameTextFieldKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				int key = e.getKeyCode();
				
				if (key == KeyEvent.VK_ENTER)
				{
					//USER LOGIN ATTEMPT - ENTER KEY PRESSED IN USERNAME TEXT FIELD
					loginPanel.loginButtonClick();
				}
			}
		});
		
		loginPanel.addRegisterButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//NEW USER REGISTRATION - 'REGISTER' BUTTON CLICKED
				loginPanel.setUsernameTextField("");
				setVisible(false);
				controller.launchRegistrationFrame();
			}
		});
		
		return loginPanel;
	}
	
	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param loggedInState true if the user is done with the view; false if not.
	 */
	@Override
	public boolean updateLoginFrameState(boolean loginFrameState) {
		setVisible(!loginFrameState);                      
		return loginFrameState;
	}

	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller  the word list controller to register with this view
	 */
	@Override
	public void registerController(LoginController controller) {
		this.controller = controller;
	}
	
	public void loginAttempt()
	{
		if (controller.loginAttempt(loginPanel.getUsernameTextField().trim())) 
		{
			controller.login();
			if(controller.checkUserAccessRoleForAdmin())
			{
				JOptionPane.showMessageDialog(null,"Welcome, " + controller.getFullUserName().trim() + "!","Login Successful - Administrator",JOptionPane.PLAIN_MESSAGE);
				controller.launchAdminTestSetup();
			}
			if (controller.checkUserAccessRoleForUser())
			{
				JOptionPane.showMessageDialog(null,"Welcome, " + controller.getFullUserName().trim() +
                        "\r\n\r\n This test compares items in groups of 2." +
                        "\r\n Each item on the test will be compared to every other item on the test." + 
                        "\r\n You must complete the test in order to save the results. " + 
                        "\r\n Click OK to start the test.","User Test - Welcome Message",JOptionPane.INFORMATION_MESSAGE);
				controller.launchUserTestFrame();
			}
		}
		else
		{
			if (loginPanel.getUsernameTextField().trim().equals(""))
			{
				emptyUsernameMessage();
			}
			else
			{
				invalidUsernameMessage();
				loginPanel.setUsernameTextField("");
			}
		}
	}
	
	public void invalidUsernameMessage()
	{
		JOptionPane.showMessageDialog(null,"'" + loginPanel.getUsernameTextField().trim() + "' is not a registered username.","Login Failed - Invalid Username",JOptionPane.WARNING_MESSAGE);
	}

	public void emptyUsernameMessage()
	{
		JOptionPane.showMessageDialog(null,"The username must contain at least 1 character and must match a username in the database.","Login Failed - Empty Username",JOptionPane.WARNING_MESSAGE);
	}
}

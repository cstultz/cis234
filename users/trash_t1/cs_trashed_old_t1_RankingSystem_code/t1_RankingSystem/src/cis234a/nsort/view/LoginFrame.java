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
public class LoginFrame extends JFrame implements LoginView
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;
	private LoginPanel loginPanel;
	private LoginController controller;
	
	/**
	 * Constructor for the LoginFrame. Must pass a parameter reference of the LoginController to the frame
	 * in order to communicate back to the LoginController
	 * 
	 * @param controller a reference to the LoginController
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
		LoginPanel loginPanel = new LoginPanel();
		loginPanel.addLoginButtonListener(new ActionListener()
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
		
		return loginPanel;
	}
	
	/**
	 * Update the current view as needed.
	 * 
	 * @param loggedInState true if user is logged in; false if not.
	 */
	@Override
	public boolean updateLoginFrame(boolean loggedInState) {
		setVisible(!loggedInState);                       //false (logged out) shows the login screen; true (logged in) hides the login screen.
		return loggedInState;
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
		if (controller.loginAttempt(loginPanel.getUsernameTextField())) 
		{
			controller.hideLogin();
			
			if(controller.checkUserAccessRoleForAdmin())
			{
				JOptionPane.showMessageDialog(null,"Welcome, '" + controller.getFullUserName() + "'","Login Successful - Administrator",JOptionPane.INFORMATION_MESSAGE);
				controller.launchAdminTestSetupFrame();
			}
			if (controller.checkUserAccessRoleForUser())
			{
				JOptionPane.showMessageDialog(null,"Welcome, " + controller.getFullUserName() +
                        "\r\n\r\n Next you will take a test whereby you will compare items in groups of 2." +
                        "\r\n Every item on the test will be compared to every other item on the test." + 
                        "\r\n Click OK to start the test.","User Test - Welcome Message",JOptionPane.INFORMATION_MESSAGE);
				controller.launchUserTestFrame();
			}
		}
		else
		{
			invalidUsername();
		}
	}
	
	public void invalidUsername()
	{
		JOptionPane.showMessageDialog(null,"'" + loginPanel.getUsernameTextField() + "' is not a valid username.","Login Failed - Invalid username",JOptionPane.WARNING_MESSAGE);
	}
}

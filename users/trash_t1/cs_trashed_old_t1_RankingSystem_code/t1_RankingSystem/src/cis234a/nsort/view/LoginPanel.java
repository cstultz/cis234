package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * The LoginPanel Class contains the components for the LoginFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class LoginPanel extends JPanel 
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension DIM = new Dimension(320,105);
	
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	private JButton loginButton;
	private SpringLayout loginPanelLayout;
	
	/**
	 * Constructor for the LoginPanel. Must pass a parameter reference of the LoginController to the panel
	 * in order to communicate back to the LoginController.
	 * @param controller
	 */
	public LoginPanel()
	{
		usernameLabel = new JLabel("Enter username:");
		usernameTextField = new JTextField("");
		loginButton = new JButton("Login");
		
		loginPanelLayout = new SpringLayout();

		setupPanel();
		setupLayout();
	}
	
	/**
	 * setup JPanel layout
	 */
	public void setupLayout()
	{
        // set border for the panel
		setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));

		loginPanelLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 14, SpringLayout.NORTH, this);
		loginPanelLayout.putConstraint(SpringLayout.EAST, usernameLabel, -10, SpringLayout.WEST, usernameTextField);
		loginPanelLayout.putConstraint(SpringLayout.EAST, usernameTextField, -10, SpringLayout.EAST, this);
		loginPanelLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 11, SpringLayout.NORTH, this);
		loginPanelLayout.putConstraint(SpringLayout.WEST, usernameTextField, 104, SpringLayout.WEST, this);
		loginPanelLayout.putConstraint(SpringLayout.WEST, loginButton, 96, SpringLayout.WEST, this);
		loginPanelLayout.putConstraint(SpringLayout.SOUTH, loginButton, -10, SpringLayout.SOUTH, this);
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		setPreferredSize(DIM);
		this.setLayout(loginPanelLayout);
		
		add(usernameLabel);
		add(usernameTextField);
		add(loginButton);
	}
	
	/**
	 * add the action listener to the loginButton
	 * @param al an ActionListener
	 */
	public void addLoginButtonListener(ActionListener al) 
	{
		loginButton.addActionListener(al);
	}
	
	public void addUsernameTextFieldKeyListener(KeyListener kl)
	{
		usernameTextField.addKeyListener(kl);
	}
	
	/**
	 * get the UsernameTextField returns the text in the field for username validation
	 * @return
	 */
	public String getUsernameTextField()
	{
		return usernameTextField.getText();
	}
	
	public void loginButtonClick()
	{
		loginButton.doClick();
	}
}

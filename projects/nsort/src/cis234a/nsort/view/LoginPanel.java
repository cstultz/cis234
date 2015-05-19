package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * The LoginPanel Class contains the components for the LoginFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
@SuppressWarnings("serial")
public class LoginPanel extends JPanel 
{
	private static final Dimension DIM = new Dimension(320, 104);
	
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	private JButton loginButton;
	private JButton registerButton;
	
	/**
	 * Constructor for the LoginPanel. Must pass a parameter reference of the LoginController to the panel
	 * in order to communicate back to the LoginController.
	 * @param controller
	 */
	public LoginPanel()
	{

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
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		setPreferredSize(DIM);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 40, 40, 40, 40};
		gridBagLayout.rowHeights = new int[]{60, 40, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		usernameLabel = new JLabel("Enter username:");
		
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 0;
		add(usernameLabel, gbc_usernameLabel);
		usernameTextField = new JTextField("");
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.anchor = GridBagConstraints.SOUTH;
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameTextField.gridwidth = 3;
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 0;
		add(usernameTextField, gbc_usernameTextField);
		
		loginButton = new JButton("Login");
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.SOUTH;
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 1;
		add(loginButton, gbc_loginButton);
		
		registerButton = new JButton("Register");
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.gridwidth = 2;
		gbc_btnRegister.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnRegister.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegister.gridx = 2;
		gbc_btnRegister.gridy = 1;
		add(registerButton, gbc_btnRegister);
	}
	
	/**
	 * add the action listener to the loginButton
	 * @param al an ActionListener
	 */
	public void addLoginButtonActionListener(ActionListener al) 
	{
		loginButton.addActionListener(al);
	}
	
	public void addUsernameTextFieldKeyListener(KeyListener kl)
	{
		usernameTextField.addKeyListener(kl);
	}
	
	public void addRegisterButtonActionListener(ActionListener al)
	{
		registerButton.addActionListener(al);
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
	
	public void setUsernameTextField(String value)
	{
		usernameTextField.setText(value);
	}
}

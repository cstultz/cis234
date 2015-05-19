package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

/**
 * The RegistrationPanel Class contains the components for the RegistrationFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/9/2015)
 */
@SuppressWarnings("serial")
public class RegistrationPanel extends JPanel 
{
	private static final Dimension DIM = new Dimension(463, 405);
	
	private JTextField firstNameTextField;
	private JLabel firstNameLabel;
	private JButton submitButton;
	private JLabel lastnameLabel;
	private JTextField lastNameTextField;
	private JLabel eMailLabel;
	private JTextField eMailTextField;
	private JLabel usernameLabel;
	private JTextField usernameTextField;
	private JLabel passwordLabel;
	private JButton checkAvailabilityButton;
	private JLabel firstNameErrorLabel;
	private JLabel lastNameErrorLabel;
	private JLabel eMailErrorLabel;
	private JLabel passwordErrorLabel;
	private JButton cancelButton;
	private JTextField passwordTextField;
	
	/**
	 * Constructor for the RegistrationPanel. Must pass a parameter reference of the RegistrationController to the panel
	 * in order to communicate back to the RegistrationController.
	 * @param controller
	 */
	public RegistrationPanel()
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
                BorderFactory.createEtchedBorder(), "Registration Panel"));
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		setPreferredSize(DIM);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{75, 40, 40, 40, 40, 40};
		gridBagLayout.rowHeights = new int[]{60, 60,60,60,60, 60};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		firstNameLabel = new JLabel("First Name:");
		
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 0;
		add(firstNameLabel, gbc_firstNameLabel);
		firstNameTextField = new JTextField("");
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameTextField.gridwidth = 2;
		gbc_firstNameTextField.gridx = 1;
		gbc_firstNameTextField.gridy = 0;
		
		firstNameErrorLabel = new JLabel("First Name cannot be empty.");
		firstNameErrorLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		firstNameErrorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_firstNameErrorLabel = new GridBagConstraints();
		gbc_firstNameErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_firstNameErrorLabel.gridwidth = 3;
		gbc_firstNameErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameErrorLabel.gridx = 3;
		gbc_firstNameErrorLabel.gridy = 0;
		add(firstNameErrorLabel, gbc_firstNameErrorLabel);
		
		lastnameLabel = new JLabel("Last Name:");
		GridBagConstraints gbc_lastnameLabel = new GridBagConstraints();
		gbc_lastnameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastnameLabel.gridx = 0;
		gbc_lastnameLabel.gridy = 1;
		add(lastnameLabel, gbc_lastnameLabel);
		
		lastNameTextField = new JTextField();
		GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
		gbc_lastNameTextField.gridwidth = 2;
		gbc_lastNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameTextField.gridx = 1;
		gbc_lastNameTextField.gridy = 1;
		lastNameTextField.setColumns(10);
		
		lastNameErrorLabel = new JLabel("Last Name cannot be empty.");
		lastNameErrorLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lastNameErrorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_lastNameErrorLabel = new GridBagConstraints();
		gbc_lastNameErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_lastNameErrorLabel.gridwidth = 3;
		gbc_lastNameErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameErrorLabel.gridx = 3;
		gbc_lastNameErrorLabel.gridy = 1;
		add(lastNameErrorLabel, gbc_lastNameErrorLabel);
		
		eMailLabel = new JLabel("eMail:");
		GridBagConstraints gbc_eMailLabel = new GridBagConstraints();
		gbc_eMailLabel.anchor = GridBagConstraints.EAST;
		gbc_eMailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_eMailLabel.gridx = 0;
		gbc_eMailLabel.gridy = 2;
		add(eMailLabel, gbc_eMailLabel);
		
		eMailTextField = new JTextField();
		GridBagConstraints gbc_eMailTextField = new GridBagConstraints();
		gbc_eMailTextField.gridwidth = 2;
		gbc_eMailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_eMailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_eMailTextField.gridx = 1;
		gbc_eMailTextField.gridy = 2;
		eMailTextField.setColumns(10);
		
		eMailErrorLabel = new JLabel("eMail cannot be empty.");
		eMailErrorLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		eMailErrorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_eMailErrorLabel = new GridBagConstraints();
		gbc_eMailErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_eMailErrorLabel.gridwidth = 3;
		gbc_eMailErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_eMailErrorLabel.gridx = 3;
		gbc_eMailErrorLabel.gridy = 2;
		add(eMailErrorLabel, gbc_eMailErrorLabel);
		
		usernameLabel = new JLabel("Username:");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.EAST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 3;
		add(usernameLabel, gbc_usernameLabel);
		
		usernameTextField = new JTextField();
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.gridwidth = 2;
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 3;
		usernameTextField.setColumns(10);
		
		checkAvailabilityButton = new JButton("Check Availability");
		GridBagConstraints gbc_checkAvailabilityButton = new GridBagConstraints();
		gbc_checkAvailabilityButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkAvailabilityButton.gridwidth = 2;
		gbc_checkAvailabilityButton.insets = new Insets(0, 0, 5, 5);
		gbc_checkAvailabilityButton.gridx = 3;
		gbc_checkAvailabilityButton.gridy = 3;
		
		passwordLabel = new JLabel("Password:");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 4;
		add(passwordLabel, gbc_passwordLabel);
		submitButton = new JButton("Submit");
		
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.insets = new Insets(0, 0, 0, 5);
		gbc_submitButton.gridx = 1;
		gbc_submitButton.gridy = 5;
		
		add(firstNameTextField, gbc_firstNameTextField);
		add(lastNameTextField, gbc_lastNameTextField);
		add(eMailTextField, gbc_eMailTextField);
		add(usernameTextField, gbc_usernameTextField);
		add(checkAvailabilityButton, gbc_checkAvailabilityButton);
		
		passwordTextField = new JTextField();
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.gridwidth = 2;
		gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTextField.gridx = 1;
		gbc_passwordTextField.gridy = 4;
		add(passwordTextField, gbc_passwordTextField);
		passwordTextField.setColumns(10);
		
		passwordErrorLabel = new JLabel("Password cannot be empty.");
		passwordErrorLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		passwordErrorLabel.setForeground(Color.red);
		GridBagConstraints gbc_passwordErrorLabel = new GridBagConstraints();
		gbc_passwordErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordErrorLabel.gridwidth = 3;
		gbc_passwordErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordErrorLabel.gridx = 3;
		gbc_passwordErrorLabel.gridy = 4;
		add(passwordErrorLabel, gbc_passwordErrorLabel);
		add(submitButton, gbc_submitButton);
		
		cancelButton = new JButton("Cancel");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = 5;
		add(cancelButton, gbc_cancelButton);
		
		firstNameErrorLabel.setVisible(false);
		lastNameErrorLabel.setVisible(false);
		eMailErrorLabel.setVisible(false);
		passwordErrorLabel.setVisible(false);
	}
	
	/**
	 * add the action listener to the registrationButton
	 * @param al an ActionListener
	 */
	public void addCheckAvailabilityButtonActionListener(ActionListener al) 
	{
		checkAvailabilityButton.addActionListener(al);
	}
	
	public void addSubmitButtonActionListener(ActionListener al)
	{
		submitButton.addActionListener(al);
	}
		
	public void addCancelButtonActionListener(ActionListener al)
	{
		cancelButton.addActionListener(al);
	}
		
	public void registrationButtonClick()
	{
		submitButton.doClick();
	}
	
	public String getUsername()
	{
		return usernameTextField.getText();
	}

	public String getFirstName()
	{
		return firstNameTextField.getText();
	}

	public String getLastName()
	{
		return lastNameTextField.getText();
	}

	public String getEmail()
	{
		return eMailTextField.getText();
	}

	public String getPassword()
	{
		return passwordTextField.getText();
	}
	
	public void setFirstNameErrorLabelVisibility(boolean visibleState)
	{
		firstNameErrorLabel.setVisible(visibleState);
	}

	public void setLastNameErrorLabelVisibility(boolean visibleState)
	{
		lastNameErrorLabel.setVisible(visibleState);
	}

	public void setEmailErrorLabelVisibility(boolean visibleState)
	{
		eMailErrorLabel.setVisible(visibleState);
	}

	public void setPasswordErrorLabelVisibility(boolean visibleState)
	{
		passwordErrorLabel.setVisible(visibleState);
	}
}

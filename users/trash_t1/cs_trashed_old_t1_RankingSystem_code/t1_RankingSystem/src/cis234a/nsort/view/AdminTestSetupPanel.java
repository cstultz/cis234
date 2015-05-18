package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
/**
 * The LoginPanel Class contains the components for the LoginFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class AdminTestSetupPanel extends JPanel
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Dimension DIM = new Dimension(775,469);
	
	private JLabel existingItemsLabel;
	private JList<String> existingItemsList;
	private DefaultListModel<String> existingItemsListModel;
	private JScrollPane existingItemsScrollPane;

	private JLabel addItemLabel;
	private JTextField addItemTextField;
	private JButton submitButton;
	
	private JLabel testItemsLabel;
	private JList<String> testItemsList;
	private DefaultListModel<String> testItemsListModel;
	private JScrollPane testItemsScrollPane;
	
	private JLabel adminTestSetupPageLabel;
	private JTextArea instructionsTextArea;
	
	private JButton finishButton;
	private JLabel lblOr;
	private JButton cancelButton;
	private JButton reportButton;
	private JCheckBox progressMeterCheckBox;
	
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbc_existingItemsLabel;
	private GridBagConstraints gbc_testItemsLabel;
	private GridBagConstraints gbc_existingItemsScrollPane;
	private GridBagConstraints gbc_testItemsScrollPane;
	private GridBagConstraints gbc_adminTestSetupPageLabel;
	private GridBagConstraints gbc_instructionsTextArea;
	private GridBagConstraints gbc_addItemLabel;
	private GridBagConstraints gbc_addItemTextField;
	private GridBagConstraints gbc_SubmitButton;
	private GridBagConstraints gbc_reportButton;
	private GridBagConstraints gbc_progressMeterCheckBox;
	private GridBagConstraints gbc_finishButton;
	private GridBagConstraints gbc_lblOr;
	private GridBagConstraints gbc_cancelButton;
	
	/**
	 * Constructor for the AdminTestSetupPanel. Must pass a parameter reference of the AdminTestSetupController to the panel
	 * in order to communicate back to the AdminTestSetupController.
	 */
	public AdminTestSetupPanel()
	{
		instructionsTextArea = new JTextArea();

		existingItemsListModel = new DefaultListModel<String>();

		existingItemsLabel = new JLabel("Existing Items");
		existingItemsScrollPane = new JScrollPane();
		existingItemsList = new JList<String>(existingItemsListModel);
		existingItemsScrollPane.setViewportView(existingItemsList);
		existingItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		testItemsListModel = new DefaultListModel<String>();

		testItemsLabel = new JLabel("Test Items");
		testItemsScrollPane = new JScrollPane();
		testItemsList = new JList<String>(testItemsListModel);
		testItemsScrollPane.setViewportView(testItemsList);
		testItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		adminTestSetupPageLabel = new JLabel("Administrator Test Setup");

		addItemLabel = new JLabel("Add an item");
		addItemTextField = new JTextField();
		submitButton = new JButton("Submit");
		reportButton = new JButton("Report");
		progressMeterCheckBox = new JCheckBox("Display progress indicator");
		finishButton = new JButton("Finish");
		finishButton.setEnabled(false);                                             
		lblOr = new JLabel("OR");
		cancelButton = new JButton("Cancel");
		
		gridBagLayout = new GridBagLayout();

		gbc_existingItemsLabel = new GridBagConstraints();
		gbc_testItemsLabel = new GridBagConstraints();
		gbc_existingItemsScrollPane = new GridBagConstraints();
		gbc_testItemsScrollPane = new GridBagConstraints();
		gbc_adminTestSetupPageLabel = new GridBagConstraints();
		gbc_instructionsTextArea = new GridBagConstraints();
		gbc_addItemLabel = new GridBagConstraints();
		gbc_addItemTextField = new GridBagConstraints();
		gbc_SubmitButton = new GridBagConstraints();
		gbc_reportButton = new GridBagConstraints();
		gbc_progressMeterCheckBox = new GridBagConstraints();
		gbc_finishButton = new GridBagConstraints();
		gbc_lblOr = new GridBagConstraints();
		gbc_cancelButton = new GridBagConstraints();

		setupLayout();
		setupPanel();
		getModelDataForView();
		addComponents();
	}
	
	/**
	 * setup JPanel layout
	 */
	public void setupLayout()
	{
		// set border for the panel
				setBorder(BorderFactory.createTitledBorder(
		                BorderFactory.createEtchedBorder(), "Admin Setup Panel"));

				adminTestSetupPageLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
				existingItemsLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				testItemsLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				addItemLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				instructionsTextArea.setFont(new Font("Arial", Font.PLAIN, 13));
				
				//Include the instructions to be dynamic to the labels of the components on the panel.

				instructionsTextArea.setBackground(UIManager.getColor("Panel.background"));
				instructionsTextArea.setEditable(false);

				instructionsTextArea.setText("INSTRUCTIONS:\r\n\u2022 To " + addItemLabel.getText() + " to the '" + existingItemsLabel.getText() + "' List:" +
		                "\r\n     1. Type a new item into the '" + addItemLabel.getText() + "' text field." + 
		                "\r\n     2. Click '" + submitButton.getText() + "'." + 
		                "\r\n\u2022 To add an item to the '" + testItemsLabel.getText() + "' List:" + 
		                "\r\n     1. Double-click an item in the '" + existingItemsLabel.getText() + "' List." + 
		                "\r\n\u2022 To remove an item from the '" + testItemsLabel.getText() + "' List:" + 
		                "\r\n     1. Double-click an item in the '" + testItemsLabel.getText() + "' List." + 
		                "\r\n\u2022 To discard the current changes to the '" + testItemsLabel.getText() + "' List:" + 
		                "\r\n     1. Click '" + cancelButton.getText() + "'." +
		                "\r\n\u2022 To keep the current changes to the '" + testItemsLabel.getText() + "' List:" +
		                "\r\n     1. Click '" + finishButton.getText() + "'." +
		                "\r\n\u2022 To view the '" + reportButton.getText() + "':" +
		                "\r\n     1. Click '" + reportButton.getText() + "'." +
		                "\r\n\u2022 To toggle the '" + progressMeterCheckBox.getText()  + "':" +
		                "\r\n     1. Click the '" + progressMeterCheckBox.getText() + "' check box:");
				
				lblOr.setHorizontalAlignment(SwingConstants.CENTER);

				gridBagLayout.columnWidths = new int[]{83, 105, 32, 65, 15, 65, 35, 333, 0};
				gridBagLayout.rowHeights = new int[]{49, 22, 27, 262, 39, 23, 23, 0};
				gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				setLayout(gridBagLayout);

				gbc_existingItemsLabel.anchor = GridBagConstraints.NORTH;
				gbc_existingItemsLabel.insets = new Insets(0, 0, 5, 5);
				gbc_existingItemsLabel.gridwidth = 2;
				gbc_existingItemsLabel.gridx = 0;
				gbc_existingItemsLabel.gridy = 1;

				gbc_testItemsLabel.anchor = GridBagConstraints.NORTHWEST;
				gbc_testItemsLabel.insets = new Insets(0, 0, 5, 5);
				gbc_testItemsLabel.gridwidth = 3;
				gbc_testItemsLabel.gridx = 3;
				gbc_testItemsLabel.gridy = 1;
				
				gbc_existingItemsScrollPane.fill = GridBagConstraints.BOTH;
				gbc_existingItemsScrollPane.insets = new Insets(0, 0, 5, 5);
				gbc_existingItemsScrollPane.gridheight = 2;
				gbc_existingItemsScrollPane.gridwidth = 2;
				gbc_existingItemsScrollPane.gridx = 0;
				gbc_existingItemsScrollPane.gridy = 2;

				gbc_testItemsScrollPane.fill = GridBagConstraints.BOTH;
				gbc_testItemsScrollPane.insets = new Insets(0, 0, 5, 5);
				gbc_testItemsScrollPane.gridheight = 2;
				gbc_testItemsScrollPane.gridwidth = 4;
				gbc_testItemsScrollPane.gridx = 2;
				gbc_testItemsScrollPane.gridy = 2;

				gbc_adminTestSetupPageLabel.anchor = GridBagConstraints.NORTH;
				gbc_adminTestSetupPageLabel.insets = new Insets(0, 0, 5, 0);
				gbc_adminTestSetupPageLabel.gridx = 7;
				gbc_adminTestSetupPageLabel.gridy = 2;
				
				gbc_instructionsTextArea.anchor = GridBagConstraints.NORTHWEST;
				gbc_instructionsTextArea.insets = new Insets(0, 0, 5, 0);
				gbc_instructionsTextArea.gridx = 7;
				gbc_instructionsTextArea.gridy = 3;
				
				gbc_addItemLabel.insets = new Insets(0, 0, 5, 5);
				gbc_addItemLabel.gridx = 0;
				gbc_addItemLabel.gridy = 4;
				
				gbc_addItemTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_addItemTextField.insets = new Insets(0, 0, 5, 5);
				gbc_addItemTextField.gridwidth = 4;
				gbc_addItemTextField.gridx = 1;
				gbc_addItemTextField.gridy = 4;
				
				gbc_SubmitButton.anchor = GridBagConstraints.WEST;
				gbc_SubmitButton.insets = new Insets(0, 0, 5, 5);
				gbc_SubmitButton.gridx = 5;
				gbc_SubmitButton.gridy = 4;
				
				gbc_reportButton.anchor = GridBagConstraints.WEST;
				gbc_reportButton.insets = new Insets(0, 0, 5, 0);
				gbc_reportButton.gridx = 7;
				gbc_reportButton.gridy = 4;
				
				gbc_progressMeterCheckBox.anchor = GridBagConstraints.WEST;
				gbc_progressMeterCheckBox.insets = new Insets(0, 0, 5, 0);
				gbc_progressMeterCheckBox.gridx = 7;
				gbc_progressMeterCheckBox.gridy = 5;
				
				gbc_finishButton.gridheight = 2;
				gbc_finishButton.insets = new Insets(0, 0, 0, 5);
				gbc_finishButton.gridx = 3;
				gbc_finishButton.gridy = 5;
				
				gbc_lblOr.gridheight = 2;
				gbc_lblOr.insets = new Insets(0, 0, 0, 5);
				gbc_lblOr.gridx = 4;
				gbc_lblOr.gridy = 5;
				
				gbc_cancelButton.gridheight = 2;
				gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
				gbc_cancelButton.gridx = 5;
				gbc_cancelButton.gridy = 5;
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		setPreferredSize(DIM);
	}
	
	/**
	 * make calls back to the controller to gather the data to setup the view
	 */
	public void getModelDataForView()
	{
		/**
		 * get the data to load the Existing Items List from the model
		 */
		//controller.loadItemsToList(existingItemsListModel, existingItemsLabel.getText());
		
		/**
		 * get the data to load the TestItemsList from the model
		 */
		//controller.loadItemsToList(testItemsListModel, testItemsLabel.getText());
		
 
		/**
		 * get the data from the model to set the selected state of the check box progress meter indicator on the AdminSetupPage
		 * based on the last persisted state of the check box from the ProgressMeter table in the database
		 */
		//progressMeterCheckBox.setSelected(controller.getProgressMeterSelectedState());
	}
	
	/**
	 * add the components of the panel with the GridBagContraints
	 */
	public void addComponents()
	{
		add(existingItemsLabel, gbc_existingItemsLabel);
		add(testItemsLabel, gbc_testItemsLabel);
		add(existingItemsScrollPane, gbc_existingItemsScrollPane);
		add(testItemsScrollPane, gbc_testItemsScrollPane);
		add(adminTestSetupPageLabel, gbc_adminTestSetupPageLabel);
		add(instructionsTextArea, gbc_instructionsTextArea);
		add(addItemLabel, gbc_addItemLabel);
		add(addItemTextField, gbc_addItemTextField);
		add(submitButton, gbc_SubmitButton);
		add(reportButton, gbc_reportButton);
		add(progressMeterCheckBox, gbc_progressMeterCheckBox);
		add(finishButton, gbc_finishButton);
		add(lblOr, gbc_lblOr);
		add(cancelButton, gbc_cancelButton);	
	}
	
	public String getExistingItemsListName()
	{
		return existingItemsLabel.getText();
	}
	
	public String getTestItemsListName()
	{
		return testItemsLabel.getText();
	}

	public void setExistingItemsList(DefaultListModel<String> JListModel)
	{
		for (int i = 0; i < JListModel.getSize(); i++)
		{
			existingItemsListModel.addElement(JListModel.elementAt(i));                     //add each item to the DefaultListModel view
		}
	}
	
	public void setTestItemsList(DefaultListModel<String> JListModel)
	{
		for (int i = 0; i < JListModel.getSize(); i++)
		{
			testItemsListModel.addElement(JListModel.elementAt(i));                     //add each item to the DefaultListModel view
		}
	}
	
	/**
	 * add the action listener to the test items list
	 * @param ml a MouseListener
	 */
	public void addTestItemsListMouseListener(MouseListener ml) 
	{
		testItemsList.addMouseListener(ml);
	}
	
	public DefaultListModel<String> getTestItemsListModel()
	{
		return testItemsListModel;
	}
	
	public String getTestItemsListSelectedValue()
	{
		return testItemsList.getSelectedValue();
	}

	public void addExistingItemsListMouseListener(MouseListener ml) 
	{
		existingItemsList.addMouseListener(ml);
	}
	
	public DefaultListModel<String> getExistingItemsListModel()
	{
		return existingItemsListModel;
	}
	
	public String getExistingItemsListSelectedValue()
	{
		return existingItemsList.getSelectedValue();
	}
	
	public void addFinishButtonActionListener(ActionListener al) 
	{
		finishButton.addActionListener(al);
	}
	
	/**
	 * The 'Finish' button is enabled when there are 2 or more items on the Test Items List. 
	 * 
	 * @param true enables the 'Finish' button; false disables the button.
	 */
	public void setFinishButtonEnabled(boolean finishButtonState)
	{
		finishButton.setEnabled(finishButtonState);
	}
	
	public void addSubmitButtonActionListener(ActionListener al) 
	{
		submitButton.addActionListener(al);
	}
	
	public String getAddAnItemTextField()
	{
		return addItemTextField.getText();
	}
	
	public void addAddAnItemTextFieldKeyListener(KeyListener kl)
	{
		addItemTextField.addKeyListener(kl);
	}
	
	public void submitButtonClick()
	{
		submitButton.doClick();
	}
	
	public void addCancelButtonActionListener(ActionListener al)
	{
		cancelButton.addActionListener(al);
	}
	
	public void clearAddAnItemTextField()
	{
		addItemTextField.setText("");
		addItemTextField.requestFocusInWindow();
	}
	
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		 progressMeterCheckBox.setSelected(progressMeterSelectedState);
	}
	
	public void addProgressMeterCheckBoxActionListener(ActionListener al)
	{
		progressMeterCheckBox.addActionListener(al);
	}
	public boolean getProgressMeterSelectedState()
	{
		return progressMeterCheckBox.isSelected();
	}
}

package nsort.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import nsort.controller.RankingSystemController;
//import nsort.controller.ReportController;

import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;

/**
 * The AdminTestSetupPanel Class is the panel that contains the components for the AdminTestSetup.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class AdminTestSetupPanel extends JPanel 
{
	/**
	 * default serialization UID
	 */
	private static final long serialVersionUID = 1L;

	private RankingSystemController controller;
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
	 * Constructor for the AdminTestSetupPanel. Must pass a parameter reference of the RankingSystemController to the panel
	 * in order to communicate back to the RankingSystemController.
	 * @param controller
	 */
	public AdminTestSetupPanel(RankingSystemController controller)
	{
		this.controller = controller;

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
		
		adminTestSetupPageLabel = new JLabel("Admin Test Setup");

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
		setupListeners();
	}
	
	/**
	 * setup JPanel Layout
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
	 * setup JPanel components
	 */
	public void setupPanel()
	{
		this.setSize(775,469);
	}
	
	/**
	 * make calls back to the controller to gather the data to setup the view
	 */
	public void getModelDataForView()
	{
		/**
		 * get the data to load the Existing Items List from the model
		 */
		controller.loadItemsToList(existingItemsListModel, existingItemsLabel.getText());
		
		/**
		 * get the data to load the TestItemsList from the model
		 */
		controller.loadItemsToList(testItemsListModel, testItemsLabel.getText());
		
 
		/**
		 * get the data from the model to set the selected state of the checkbox progress meter inidicator on the AdminSetupPage
		 * based on the last persisted state of the checkbox from the ProgressMeter table in the database
		 */
		progressMeterCheckBox.setSelected(controller.getProgressMeterSelectedState());
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
	
	/**
	 * setup JPanel listeners
	 */
	public void setupListeners()
	{
		/**
		 * click event for adding a new item to the existing items list
		 */
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{	
				//SUBMIT BUTTON CLICKED
				controller.addNewItemToExistingItemsList(existingItemsListModel, addItemTextField.getText());
			}
		});
		
		/**
		 * enter key clicked inside the add item text field calls a submit button click event
		 */
		addItemTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
			   int key = e.getKeyCode();
		        
		        if (key == KeyEvent.VK_ENTER) 
		        {	
		        	//ENTER KEY PRESSED IN ADD ITEM TEXT BOX
		        	submitButton.doClick();
		        }
			}
		});

		/**
		 * cancel button clicked discards changes to the test items list.  
		 * The previous test items list is preserved.
		 */
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//CANCEL BUTTON CLICKED
				controller.cancelTest();
			}
		});

		reportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//REPORT BUTTON CLICKED
				controller.startReport();
			}
		});
		/**
		 * admin saves the changes to the test items list
		 */
		finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//FINISH BUTTON CLICKED
				controller.saveTestList(controller.modelToList(testItemsListModel));
			}
		});

		/**
		 * user double clicks an existing item to add to the test items
		 */
		existingItemsList.addMouseListener(new MouseAdapter()
		{
			//EXISTING ITEMS LIST DOUBLE CLICKED
			public void mousePressed(MouseEvent event)
			{
				if (event.getClickCount() == 2) //double click
				{
					//ADDS EXISTING LIST ITEM TO TEST LIST ITEMS
					controller.addExistingItemToTestItemsList(testItemsListModel, existingItemsList.getSelectedValue());
					
					/**
					 * get the data from the model to check and see if the Test Item List meets the minimum requirements
					 * in order to determine the enable or disabled initialized state of the 'Finish' button on the AdminTestSetup page
					 */
					finishButton.setEnabled(controller.checkItemsListMeetsMinimumRequirements(testItemsListModel));

				}
			}
		});
		
		/**
		 * user double clicks a test item to remove the item from the test items list
		 */
		testItemsList.addMouseListener(new MouseAdapter()
		{
			//TEST ITEMS LIST DOUBLE CLICKED 
			public void mousePressed(MouseEvent event)
			{
				if (event.getClickCount() == 2)  //double click
				{
					/**
					 * remove the item from test items list model
					 */
					controller.removeItemFromTestItemList(testItemsListModel, testItemsList.getSelectedValue());
					
					/**
					 * get the data from the model to check and see if the Test Item List meets the minimum requirements
					 * in order to determine the enable or disabled initialized state of the 'Finish' button on the AdminTestSetup page
					 */
					finishButton.setEnabled(controller.checkItemsListMeetsMinimumRequirements(testItemsListModel));

				}
			}
		});
		
		/**
		 * launches the test results report
		 */
		
		/**
		 * when checkbox is selected or de-selected, send selected state change to the controller to update the model and the database
		 */
		progressMeterCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//CANCEL BUTTON CLICKED
				if (progressMeterCheckBox.isSelected() == false)
				{
					controller.progressMeterSelectedStateChanged(0);
				}
				else if (progressMeterCheckBox.isSelected() == true)
				{
					controller.progressMeterSelectedStateChanged(1);
				}
			}
		});
	}
}
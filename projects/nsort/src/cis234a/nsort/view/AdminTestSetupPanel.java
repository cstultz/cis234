package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import cis234a.nsort.model.*;
/**
 * The LoginPanel Class contains the components for the LoginFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
@SuppressWarnings("serial")
public class AdminTestSetupPanel extends JPanel
{

	private static final Dimension DIM = new Dimension(700, 404);
	
	private JLabel existingItemsLabel;
	protected JList<String> existingItemsList;
	protected DefaultListModel<String> existingItemsListModel;
	private JScrollPane existingItemsScrollPane;
	
	private JPopupMenu existingItemsListRightClickPopupMenu;
	private JMenuItem deleteMenuItem;

	private JLabel addItemLabel;
	private JTextField addItemTextField;
	private JButton submitButton;
	
	private JLabel testItemsLabel;
	protected JList<String> testItemsList;
	protected DefaultListModel<String> testItemsListModel;
	private JScrollPane testItemsScrollPane;
	
	private JButton finishButton;
	private JLabel lblOr;
	private JButton cancelButton;
	private JButton reportButton;
	private JCheckBox progressMeterCheckBox;
	
	private ImagePanel imagePanel;
	private JButton editButton;
	
	/**
	 * Constructor for the AdminTestSetupPanel.
	 */
	public AdminTestSetupPanel()
	{
		imagePanel = new ImagePanel();
		imagePanel.setSize(250, 264);
		imagePanel.setLocation(417, 32);

		editButton = new JButton("Edit");
		editButton.setEnabled(false);
		editButton.setBounds(498, 304, 89, 23);
		
		deleteMenuItem = new JMenuItem("Delete Item", new ImageIcon("resources/delete.jpg"));

		
		existingItemsListRightClickPopupMenu = new JPopupMenu("Existing Items List Right Click Menu");
		existingItemsListRightClickPopupMenu.setVisible(false);
		existingItemsListRightClickPopupMenu.add(deleteMenuItem); 
		existingItemsListRightClickPopupMenu.setBorder(new BevelBorder(BevelBorder.RAISED));
				
		existingItemsListModel = new DefaultListModel<String>();

		existingItemsLabel = new JLabel("Existing Items");
		existingItemsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		existingItemsLabel.setBounds(0, 25, 200, 22);
		existingItemsScrollPane = new JScrollPane();
		existingItemsScrollPane.setBounds(36, 53, 129, 243);
		existingItemsList = new JList<String>(existingItemsListModel);
		existingItemsScrollPane.setViewportView(existingItemsList);
		existingItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		testItemsListModel = new DefaultListModel<String>();

		testItemsLabel = new JLabel("Test Items");
		testItemsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testItemsLabel.setBounds(200, 25, 200, 22);
		testItemsScrollPane = new JScrollPane();
		testItemsScrollPane.setBounds(236, 53, 129, 243);
		testItemsList = new JList<String>(testItemsListModel);
		testItemsScrollPane.setViewportView(testItemsList);
		testItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		addItemLabel = new JLabel("Add an item");
		addItemLabel.setBounds(36, 309, 83, 17);
		addItemTextField = new JTextField();
		addItemTextField.setBounds(129, 308, 149, 20);
		submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Arial", Font.PLAIN, 12));
		submitButton.setBounds(288, 307, 77, 23);
		reportButton = new JButton("Test Results Reporting");
		reportButton.setFont(new Font("Arial", Font.PLAIN, 12));
		reportButton.setBounds(454, 360, 180, 34);
		progressMeterCheckBox = new JCheckBox("Display progress indicator during the User Test?");
		progressMeterCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		progressMeterCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
		progressMeterCheckBox.setBounds(54, 341, 292, 23);
		finishButton = new JButton("Finish");
		finishButton.setFont(new Font("Arial", Font.PLAIN, 12));
		finishButton.setBounds(105, 371, 77, 23);
		finishButton.setEnabled(false);                                             
		lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Arial", Font.PLAIN, 11));
		lblOr.setBounds(192, 376, 15, 14);
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
		cancelButton.setBounds(217, 371, 77, 23);

		setupLayout();
		setupPanel();
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
		existingItemsLabel.setFont(new Font("Arial", Font.BOLD, 16));
		testItemsLabel.setFont(new Font("Arial", Font.BOLD, 16));
		addItemLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		setPreferredSize(new Dimension(DIM));
	}
	
	/**
	 * add the components of the panel with the GridBagContraints
	 */
	public void addComponents()
	{
		setLayout(null);
		add(existingItemsLabel);
		add(testItemsLabel);
		add(existingItemsScrollPane);
		add(testItemsScrollPane);
		add(addItemLabel);
		add(addItemTextField);
		add(submitButton);
		add(reportButton);
		add(progressMeterCheckBox);
		add(finishButton);
		add(lblOr);
		add(cancelButton);
		add(imagePanel);
		add(editButton);
		add(existingItemsListRightClickPopupMenu);
	}

	/**
	 * set the Existing Items List in the view
	 * 
	 * @param JListModel Existing Items Default List Model
	 */
	public void setExistingItemsList(ItemList existingItemsList)
	{
		for (int i = 0; i < existingItemsList.getSize(); i++)
		{
			existingItemsListModel.addElement(existingItemsList.getItem(i).getValue());                     
		}
	}
	
	/**
	 * set the test Items List in the view
	 * 
	 * @param JListModel Test Items Default List Model
	 */
	public void setTestItemsList(ItemList testItemsList)
	{
		for (int i = 0; i < testItemsList.getSize(); i++)
		{
			testItemsListModel.addElement(testItemsList.getItem(i).getValue());                     
		}
	}
	
	/**
	 * add the mouse listener to the test items list 
	 * 
	 * @param ml a MouseListener
	 */
	public void addTestItemsListMouseListener(MouseListener ml) 
	{
		testItemsList.addMouseListener(ml);
	}
	
	/**
	 * get the Test Items List Default List Model
	 * 
	 * @return the Default List Model for the Test Items List
	 */
	public DefaultListModel<String> getTestItemsListModel()
	{
		return testItemsListModel;
	}
	
	/**
	 * get the Test Items List selected item value
	 * 
	 * @return the value of the selected item
	 */
	public String getTestItemsListSelectedValue()
	{
		return testItemsList.getSelectedValue();
	}

	/**
	 * add a mouse listener to the Existing Items List
	 * 
	 * @param ml event for when a user click in the Existing Items List 
	 */
	public void addExistingItemsListMouseListener(MouseListener ml) 
	{
		existingItemsList.addMouseListener(ml);
	}
	
	/**
	 * get the Existing Items List Default List Model 
	 * 
	 * @return the Default List Model of the Existing Items List
	 */
	public DefaultListModel<String> getExistingItemsListModel()
	{
		return existingItemsListModel;
	}
	
	/**
	 * get the Existing Items List selected item value
	 * 
	 * @return the value of the item selected
	 */
	public String getExistingItemsListSelectedValue()
	{
		return existingItemsList.getSelectedValue();
	}
	
	/**
	 * add a action listener to the 'Finish' button
	 * 
	 * @param al user has clicked the 'Finish' button
	 */
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
	
	/**
	 * add action listener to the 'Submit' button
	 * 
	 * @param al user has clicked the 'Submit' button
	 */
	public void addSubmitButtonActionListener(ActionListener al) 
	{
		submitButton.addActionListener(al);
	}
	
	/**
	 * get the text in the 'Add an Item' text field
	 * 
	 * @return the text in the 'Add an Item' text field
	 */
	public String getAddAnItemTextField()
	{
		return addItemTextField.getText();
	}
	
	/**
	 * add a key listener to the 'Add an Item' text field
	 * 
	 * @param kl user hits a key on the keyboard
	 */
	public void addAddAnItemTextFieldKeyListener(KeyListener kl)
	{
		addItemTextField.addKeyListener(kl);
	}
	
	/**
	 * simulates a 'Submit' button click
	 */
	public void submitButtonClick()
	{
		submitButton.doClick();
	}
	
	/**
	 * add an action listener to the 'Cancel' button
	 * 
	 * @param al user clicks the 'Cancel' button 
	 * 
	 */
	public void addCancelButtonActionListener(ActionListener al)
	{
		cancelButton.addActionListener(al);
	}
	
	/**
	 * clears the text in the 'Add an Item' text field
	 */
	public void clearAddAnItemTextField()
	{
		addItemTextField.setText("");
		addItemTextField.requestFocusInWindow();
	}
	
	/**
	 * set the progress meter check box selected state 
	 * 
	 * @param progressMeterSelectedState true if the check box is to be selected; false if not.
	 */
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		 progressMeterCheckBox.setSelected(progressMeterSelectedState);
	}
	
	/**
	 * add an action listener to the progress meter check box
	 * 
	 * @param al user has checked or unchecked the progress meter check box
	 */
	public void addProgressMeterCheckBoxActionListener(ActionListener al)
	{
		progressMeterCheckBox.addActionListener(al);
	}
	
	/**
	 * get the progress meter check box selected state
	 * 
	 * @return true if the check box is selected; false if not.
	 */
	public boolean getProgressMeterSelectedState()
	{
		return progressMeterCheckBox.isSelected();
	}
	
	/**
	 * add an action listener to the 'Test Results Reporting' button
	 * 
	 * @param al user has clicked the 'Test Results Reporting' button
	 */
	public void addReportButtonActionListener(ActionListener al)
	{
		reportButton.addActionListener(al);
	}
	
	/**
	 * remove an item from the test items list
	 * 
	 * @param selectedValue of item being removed
	 */
	public void removeItemFromTestItemList(String selectedValue)
	{
		testItemsListModel.removeElement(selectedValue);
	}
	
	/**
	 * add an existing items list item to the test items list
	 * 
	 * @param selectedValue of the item being added to the test
	 */
	public void addSelectedExistingItemTotestItemsList(String selectedValue)
	{
		testItemsListModel.addElement(selectedValue);
	}
	
	/**
	 * show the duplicate message of an item on the test items list to the user
	 * 
	 * @param selectedValue of the item already on the test items list.
	 */
	public void showDuplicateTestItemsMessage(String selectedValue)
	{
		String input = "";
		for (int i = 0; i < testItemsListModel.size(); i++)
		{
				if (testItemsListModel.getElementAt(i).equalsIgnoreCase(selectedValue))
				{
					input = testItemsListModel.getElementAt(i);
							if (selectedValue.equalsIgnoreCase(input))
							{
								JOptionPane.showMessageDialog(null,"'" + input + "' item already exists in the Test Items List.","Duplicate item",JOptionPane.WARNING_MESSAGE);
								break;
							}
				}
		}
	}
	
	/**
	 * add a new item value to the existing items list
	 * 
	 * @param newItemValue of the item being added.
	 */
	public void addNewItemToExistingItemsList(String newItemValue)
	{
		existingItemsListModel.addElement(newItemValue);
	}
	
	/**
	 * update the test items list with the new item value
	 * 
	 * @param selectedValue of the item being added to the test items list.
	 */
	public void updateTestItemsList(String selectedValue)
	{
		testItemsListModel.addElement(selectedValue);
	}

	public void clearTestItemsListSelection()
	{
		testItemsList.clearSelection();
	}

	public void clearExistingItemsListSelection()
	{
		existingItemsList.clearSelection();
	}

	/**
	 * add an action listener to the 'Edit' button
	 * 
	 * @param al user has clicked the 'Edit' button
	 */
	public void addEditButtonActionListener(ActionListener al)
	{
		editButton.addActionListener(al);
	}
	
	public boolean getEditButtonCurrentState()
	{
		return editButton.isEnabled();
	}
	
	public void setEditButtonCurrentState(boolean currentState)
	{
		editButton.setEnabled(currentState);
	}

	public void updateImage(byte[] data)
	{
		imagePanel.setImage(data);
	}
	
	public void updateItemImageToBlank()
	{
		imagePanel.updateItemImageToBlank();
	}
	
	public String existingItemListRightClickMenu(MouseEvent event)
	{
		JList<String> list = (JList<String>)event.getSource();
        int row = list.locationToIndex(event.getPoint());
        list.setSelectedIndex(row);

        existingItemsListRightClickPopupMenu.setVisible(true);
        existingItemsListRightClickPopupMenu.show(list, event.getPoint().x+5, event.getPoint().y+10);
        
        return list.getSelectedValue();
	}
	
	public void addExistingItemsListRightClickPopupMenuDeleteItemActionListener(ActionListener al)
	{
		deleteMenuItem.addActionListener(al);
	}
	
	public String getPopupMenuSelectedValue()
	{
		return existingItemsListRightClickPopupMenu.getSubElements().toString();
	}
}
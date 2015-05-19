package cis234a.nsort.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cis234a.nsort.controller.*;
import cis234a.nsort.model.*;

/**
 * The AdminTestSetupFrame Class is the GUI of the admin test setup for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
@SuppressWarnings("serial")
public class AdminTestSetupFrame extends JFrame implements AdminTestSetupView
{
	private AdminTestSetupPanel adminTestSetupPanel;
	private AdminTestSetupController controller;
	
	/**
	 * Constructor for the AdminTestSetupFrame. Must pass a parameter reference of the AdminTestSetupController to the frame
	 * in order to communicate back to the AdminTestSetupController
	 * 
	 * @param controller a reference to the AdminTestSetupController
	 */
	public AdminTestSetupFrame()
	{
		super("Ranking System - Administrator Test Setup"); 
		adminTestSetupPanel = createAdminTestSetupPanel();
		getContentPane().add(adminTestSetupPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(false);    
		setLocationRelativeTo(null);
	}
	
	/**
	 * create the admin test setup panel. override the listeners in order to communicate to the controller.
	 * 
	 * @return the admin test setup panel
	 */
	private AdminTestSetupPanel createAdminTestSetupPanel()
	{
		AdminTestSetupPanel adminTestSetupPanel = new AdminTestSetupPanel();
		
		/**
		 * listener for the Test Items List Mouse double click
		 */
		adminTestSetupPanel.addTestItemsListMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent event)
			{
				if (event.getClickCount() == 2)  //double click
				{
					String selectedValue = adminTestSetupPanel.getTestItemsListSelectedValue();
					controller.removeItemFromTestItemList(selectedValue);
					adminTestSetupPanel.removeItemFromTestItemList(selectedValue);
					adminTestSetupPanel.setFinishButtonEnabled(controller.checkItemsListMeetsMinimumRequirements());
				}
			}
		});
		
		/**
		 * listener for the Existing Items List mouse double click
		 */
		adminTestSetupPanel.addExistingItemsListMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent event)
			{
				if (event.getClickCount() == 2)  //double click
				{
					String selectedValue = adminTestSetupPanel.getExistingItemsListSelectedValue();
					controller.addExistingItemToTestItemsList(selectedValue);
					adminTestSetupPanel.setFinishButtonEnabled(controller.checkItemsListMeetsMinimumRequirements());
				}
			}
		});
		
		/**
		 * listener for the 'Submit' button click
		 */
		adminTestSetupPanel.addSubmitButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//'SUBMIT' BUTTON CLICKED - USER ATTEMPTS TO ADD A NEW ITEM TO THE EXISTING ITEMS LIST
				controller.addNewItemToExistingItemsList(adminTestSetupPanel.getAddAnItemTextField());
			}
		});
		
		/**
		 * listener for the 'Add an Item' Enter key pressed.
		 */
		adminTestSetupPanel.addAddAnItemTextFieldKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				int key = e.getKeyCode();
				
				if (key == KeyEvent.VK_ENTER)
				{
					//ENTER KEY PRESSED - USER ATTEMPTS TO ADD A NEW ITEM TO THE EXISTING ITEMS LIST
					adminTestSetupPanel.submitButtonClick();
				}
			}
		});
		
		/**
		 * listener for the 'Finish' button click
		 */
		adminTestSetupPanel.addFinishButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.saveTestItemsList(DefaultListModelToItemList(adminTestSetupPanel.getTestItemsListModel()));
				
				setVisible(false);

				JOptionPane.showMessageDialog(null,"New test has been saved successfully to the database. You will now be logged off and returned to the login screen.","New Test Saved",JOptionPane.PLAIN_MESSAGE);
				
				controller.hideAdminTestSetup();

			}
		});
		
		/**
		 * listener for the 'Cancel' button click
		 */
		adminTestSetupPanel.addCancelButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				setVisible(false);
				JOptionPane.showMessageDialog(null,"New test has been cancelled. Previous test has been preserved. You will now be logged off and returned to the login screen.","New Test Cancelled",JOptionPane.PLAIN_MESSAGE);
				controller.hideAdminTestSetup();
			}
		});
		
		/**
		 * listener for the progress meter check box being selected
		 */
		adminTestSetupPanel.addProgressMeterCheckBoxActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.setProgressMeterSelectedState(adminTestSetupPanel.getProgressMeterSelectedState());
			}
		});
		
		/**
		 * listener for the 'Test Results Reporting' button click
		 */
		adminTestSetupPanel.addReportButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//REPORT BUTTON CLICKED
				controller.launchReport();
			}
		});		
		
		return adminTestSetupPanel;
	}
	
	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param adminTestSetupFrameState true if the user is done with the view; false if not.
	 */
	@Override
	public boolean updateAdminTestSetupFrame(boolean adminTestSetupFrameState) {
		setVisible(!adminTestSetupFrameState);
		return adminTestSetupFrameState;
	}

	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller  the word list controller to register with this view
	 */
	@Override
	public void registerController(AdminTestSetupController controller) {
		this.controller = controller;
	}
	
	/**
	 * set the Existing Items List in the view
	 * 
	 * @param the Existing Items List Default List Model 
	 */
	public void setExistingItemsList(ItemList existingItemsList)
	{
		adminTestSetupPanel.setExistingItemsList(existingItemsList);
	}
	
	/**
	 * set the Test Items List in the view
	 * 
	 * @param the Test Items List Default List Model
	 */
	public void setTestItemsList(ItemList testItemsList)
	{
		adminTestSetupPanel.setTestItemsList(testItemsList);
	}
	
	/**
	 * set the progress meter selected state in the view
	 * 
	 * @param true if the progress meter is to be selected; false if not.
	 */
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		adminTestSetupPanel.setProgressMeterSelectedState(progressMeterSelectedState);
	}
	
	/**
	 * show the duplicate message of an item on the test items list to the user
	 * 
	 * @param selectedValue of the item already on the test items list.
	 */
	public void showDuplicateTestItemsMessage(String selectedValue)
	{
		adminTestSetupPanel.showDuplicateTestItemsMessage(selectedValue);
	}

	/**
	 * update the test items list in the panel
	 * 
	 * @param selectedValue of the item being added to the list.
	 */
	@Override
	public void updateTestItemsList(String selectedValue) 
	{
		adminTestSetupPanel.updateTestItemsList(selectedValue);
	}

	/**
	 * converts a default list model to an Item List object
	 * 
	 * @param JListModel default list model from the view
	 * @return Item List created
	 */
	public ItemList DefaultListModelToItemList(DefaultListModel<String> JListModel)
	{
		ItemList items = new ItemList();
		
		for (int i = 0; i < JListModel.size(); i++)
		{
			Item item = new Item();
			item.setValue(JListModel.getElementAt(i));
			items.addItem(item);
		}
		return items;
	}
	
	/**
	 * show the empty item message when user tries to add an empty string to the existing items list.
	 */
	public void showEmptyItemMessage()
	{
		JOptionPane.showMessageDialog(null,"The item must contain at least 1 character and can not match another item on the list.","Empty item",JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * update the existing items list with the new item value
	 * 
	 * @param newItemValue of the item being added to the list.
	 */
	public void updateExistingItemsList(String newItemValue)
	{
		adminTestSetupPanel.addNewItemToExistingItemsList(newItemValue);
	}
	
	/**
	 * show user the existing item match message
	 * 
	 * @param match value if the item already in the list
	 */
	public void showExistingItemMatchMessage(String match)
	{
		JOptionPane.showMessageDialog(null, "The item must contain at least 1 character and can not match another item on the list.", "'" + match + "' already exists on the list",JOptionPane.WARNING_MESSAGE);
	}
}

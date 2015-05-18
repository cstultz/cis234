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

/**
 * The AdminTestSetupFrame Class is the GUI of the admin test setup for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class AdminTestSetupFrame extends JFrame implements AdminTestSetupView
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;
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
	
	private AdminTestSetupPanel createAdminTestSetupPanel()
	{
		AdminTestSetupPanel adminTestSetupPanel = new AdminTestSetupPanel();
		
		adminTestSetupPanel.addTestItemsListMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent event)
			{
				if (event.getClickCount() == 2)  //double click
				{
					controller.removeItemFromTestItemList(adminTestSetupPanel.getTestItemsListModel(), adminTestSetupPanel.getTestItemsListSelectedValue());
					adminTestSetupPanel.setFinishButtonEnabled(controller.checkItemsListMeetsMinimumRequirements(adminTestSetupPanel.getTestItemsListModel()));
				}
			}
		});
		
		adminTestSetupPanel.addExistingItemsListMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent event)
			{
				if (event.getClickCount() == 2)  //double click
				{
					controller.addExistingItemToTestItemsList(adminTestSetupPanel.getTestItemsListModel(), adminTestSetupPanel.getExistingItemsListSelectedValue());
					adminTestSetupPanel.setFinishButtonEnabled(controller.checkItemsListMeetsMinimumRequirements(adminTestSetupPanel.getTestItemsListModel()));
				}
			}
		});
		
		adminTestSetupPanel.addSubmitButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				//'SUBMIT' BUTTON CLICKED - USER ATTEMPTS TO ADD A NEW ITEM TO THE EXISTING ITEMS LIST
				//entry can't be an empty string
				if (controller.checkAddAnItemTextFieldIsEmpty(adminTestSetupPanel.getAddAnItemTextField()))
				{
					JOptionPane.showMessageDialog(null,"The item must contain at least 1 character and can not match another item on the list.","Empty item",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					
					if (controller.checkAddAnItemTextFieldIsUnique(adminTestSetupPanel.getExistingItemsListModel(), adminTestSetupPanel.getAddAnItemTextField()))
					{
						controller.addNewItemToExistingItemsList(adminTestSetupPanel.getExistingItemsListModel(), adminTestSetupPanel.getAddAnItemTextField());
						adminTestSetupPanel.clearAddAnItemTextField();
					}
					else
					{ 
						String match = controller.getExistingItemsListModelMatch(adminTestSetupPanel.getExistingItemsListModel(), adminTestSetupPanel.getAddAnItemTextField());
						JOptionPane.showMessageDialog(null, "The item must contain at least 1 character and can not match another item on the list.", "'" + match + "' already exists on the list",JOptionPane.WARNING_MESSAGE);					
					}
					
					
				}
			}
		});
		
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
		
		adminTestSetupPanel.addFinishButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.saveTestItemsList(controller.DefaultListModelToItemList(adminTestSetupPanel.getTestItemsListModel()));
				
				setVisible(false);

				JOptionPane.showMessageDialog(null,"New test has been saved successfully to the database. You will now be logged off and returned to the login screen.","New Test Saved",JOptionPane.WARNING_MESSAGE);
				
				controller.hideAdminTestSetup();

			}
		});
		
		adminTestSetupPanel.addCancelButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				setVisible(false);
				JOptionPane.showMessageDialog(null,"New test has been cancelled. Previous test has been preserved. You will now be logged off and returned to the login screen.","New Test Cancelled",JOptionPane.WARNING_MESSAGE);
				controller.hideAdminTestSetup();
			}
		});
		
		adminTestSetupPanel.addProgressMeterCheckBoxActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.setProgressMeterSelectedState(adminTestSetupPanel.getProgressMeterSelectedState());
			}
		});
		
		
		return adminTestSetupPanel;
	}
	
	/**
	 * Update the current view as needed.
	 * 
	 * @param adminTestSetupFrameState false if 'Finish' or 'Cancel' button has not yet been click; true hides the AdminTestSetupFrame.
	 */
	@Override
	public boolean updateAdminTestSetupFrame(boolean adminTestSetupFrameState) {
		setVisible(!adminTestSetupFrameState);//false (user has not clicked the 'Finish' or 'Cancel' button) shows the AdminTestSetupFrame; true hides the Frame.
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
	
	public String getExistingItemsListName()
	{
		return adminTestSetupPanel.getExistingItemsListName();
	}
	
	public String getTestItemsListName()
	{
		return adminTestSetupPanel.getTestItemsListName();
	}
	
	public void setExistingItemsList(DefaultListModel<String> JListModel)
	{
		adminTestSetupPanel.setExistingItemsList(JListModel);
	}
	
	public void setTestItemsList(DefaultListModel<String> JListModel)
	{
		adminTestSetupPanel.setTestItemsList(JListModel);
	}
	
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		adminTestSetupPanel.setProgressMeterSelectedState(progressMeterSelectedState);
	}
}

package nsort.controller;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import nsort.model.*;
import nsort.model.Answer.Value;
import nsort.model.Role.UserAccessID;
import nsort.view.*;

/**
 * The RankingSystemController class is the main controller for the Ranking System application. 
 * Input will come from the view objects to the controller to encapsulate the 
 * business logic between the view and the model. 
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/17/2015)
 */
public class RankingSystemController {
	
	private SqlUser_234a_t1 sqlUser;

	private User user;
	private LoginFrame loginFrame;
	
	private AdminTestSetupFrame adminTestSetupFrame;
	private ItemList existingItemsList;
	private ItemList testItemsList;
	
	private ProgressMeter progressMeter;
	private UserTestFrame userTestFrame;
	private NTest userTest;

	private ReportView reportView;
	private Report reportModel;
	
	/**
	 * Constructor for the RankingSystemController
	 */
	public RankingSystemController()
	{
		sqlUser = SqlUser_234a_t1.instance();  //initialize the SqlUser_234a_t1 class object 
	}
	
	/**
	 * This method starts the application and presents the loginFrame to the user.
	 */
	public void start()
	{
		loginFrame = new LoginFrame(this);
		loginFrame.setVisible(true);
	}
	
	/**
	 * The logic for the user login. Upon successful login will determine if the user is an admin or a test taker.
	 * 
	 * @param username the value entered by the user during login
	 */
	public void login(String username)
	{
		if (sqlUser.validateUser(username))
		{
			
			user = sqlUser.getUser(username);
			
			int totalQuestions = setTotalQuestions();

			progressMeter = new ProgressMeter(totalQuestions, sqlUser.getProgressMeterSelectedState());

			Role role = new Role();

			if (sqlUser.isUserAccessAdmin(username))
			{
				
				role.setUserAccessID(UserAccessID.Admin);
				user.setRole(role);
				
				adminTestSetupFrame = new AdminTestSetupFrame(this);
				loginFrame.setVisible(false);
				adminTestSetupFrame.setVisible(true);
				
			}
			else
			{

				role.setUserAccessID(UserAccessID.User);
				user.setRole(role);
				
				loadItemsToTest();

				if (testItemsList.getSize() == 0)
				{
					JOptionPane.showMessageDialog(null,"Sorry, " + user.getFirstName() + " " + user.getLastName() +" there are currently zero items on the Test to be compared. Please contact the administrator.","No items exist on the current Test",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Welcome, " + user.getFirstName() + " " + user.getLastName() +
	                           "\r\n\r\n You are about to take a test whereby you will compare" + 
	                           "\r\n" + totalQuestions + " items in groups of 2. Every item in the test will be compared" + 
	                           "\r\n to every other item creating a total of " + (totalQuestions * (totalQuestions - 1) / 2) + " questions." + 
	                           "\r\n Click OK to start the test.","Ranking System - Welcome Message",JOptionPane.INFORMATION_MESSAGE);

					userTest = new NTest(testItemsList);

					userTestFrame = new UserTestFrame(this);
					loginFrame.setVisible(false);
					userTestFrame.setVisible(true);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"'" + username + "' is not a valid username.","Invalid username",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Populates the specific list (Existing Items or Test Items) with values pulled from the 
	 * specific database table (Item or TestItems) specified by the DefaultListModel and listName provided.
	 *  
	 * @param JListModel the DefaultListModel object to be populated
	 * @param listName the name of the DefaultListModel
	 */
	public void loadItemsToList(DefaultListModel<String> JListModel, String listName)
	{
		ItemList items = new ItemList();
		
		items = sqlUser.pullItems(listName);                  //pull the items from the database
		
		for (int i = 0; i < items.getSize(); i++)
		{
			Item item = items.getItem(i);
			String value = item.getValue();
			JListModel.addElement(value);                     //add each item to the DefaultListModel view
		}
		
		if (listName.equalsIgnoreCase("Existing Items"))
		{
			existingItemsList = items;                        //update the existing list in the model with the pull from the database
		}
		else if (listName.equalsIgnoreCase("Test Items"))
		{
			testItemsList = items;                            //update the test items list in the model with the pull from the database
		}
	}
	
	/**
	 * Populates the Test ItemsList with values pulled from the 
	 * TestItems database table. 
	 */
	public void loadItemsToTest()
	{
		testItemsList = sqlUser.pullItems("Test Items");                  //pull the Test Items from the database
	}

	/**
	 * remove an item from the Test Items list
	 * 
	 * @param JListModel the Test Items List object to be populated
	 * @param value of the item being removed
	 */
	public void removeItemFromTestItemList(DefaultListModel<String> JListModel, String value)
	{
		                                     //nothing is saved to the database at this time until the admin clicks the 'Finish' button.
		JListModel.removeElement(value);     //update the view
		testItemsList.removeItem(value);     //update the model
	}

	/**
	 * adds a new item to the Existing Items List and the Items table in the database. 
	 * 
	 * @param JListModel the Existing Items List in the view to be populated
	 * @param value of the item being added
	 */
	public void addNewItemToExistingItemsList(DefaultListModel<String> JListModel, String value)
	{
		//entry can't be an empty string
		if (value.equals(""))
		{
			JOptionPane.showMessageDialog(null,"The item must contain at least 1 character and can not match another item on the list.","Empty item",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			if (listIsUnique(JListModel, value))      //check to make sure the item is not already on the list.
			{
				Item item = new Item();               //create the new Item
				item.setValue(value);                 //set the value of the item
				
				existingItemsList.addItem(item);      //update the model
				JListModel.addElement(value);         //update the view
				sqlUser.addNewItem(item);             //update the database
			}
			else
			{
				String match = "";
				for (int i = 0; i < JListModel.size(); i++)
				{
						if (JListModel.getElementAt(i).equalsIgnoreCase(value))
						{
							match = JListModel.getElementAt(i);
						}
				}
				
				JOptionPane.showMessageDialog(null, "The item must contain at least 1 character and can not match another item on the list.", "'" + match + "' already exists on the list",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * Checks to see if the user input value already exists on the specified list
	 * 
	 * @param jListModel the DefaultListModel object to be checked
	 * @param value of the item being checked
	 * @return true if list is unique; false if not.
	 */
	public boolean listIsUnique(DefaultListModel<String> JListModel, String value)
	{
		for (int i = 0; i < JListModel.size(); i++)
		{
				if (JListModel.getElementAt(i).equalsIgnoreCase(value))
				{
					return false;
				}
		}
		return true;
	}

	/**
	 * add an item from the Existing Items List to the Test Items List
	 * 
	 * @param JListModel the DefaultListModel object to be populated
	 * @param value of the item being added
	 */
	public void addExistingItemToTestItemsList(DefaultListModel<String> JListModel, String value)
	{
		if (listIsUnique(JListModel, value))
		{
			JListModel.addElement(value);         //update the view
			
			Item item = new Item();               //create the new Item
			item.setValue(value);                 //set the value of the item
			
			testItemsList.addItem(item);          //update the model
		}
		else
		{
			String input = "";
			for (int i = 0; i < JListModel.size(); i++)
			{
					if (JListModel.getElementAt(i).equalsIgnoreCase(value))
					{
						input = JListModel.getElementAt(i);
								if (value.equalsIgnoreCase(input)) break;
					}
			}
			
			JOptionPane.showMessageDialog(null,"'" + input + "' item already exists in the Test Items List.","Duplicate item",JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Checks to see if the Test Item List meets the minimum requirements for a test (test items list >= 2)
	 * 
	 * @param testItemsListModel the items on the test being checked
	 * @return true if minimum requirements of the test are met; false if not.
	 */
	public boolean checkItemsListMeetsMinimumRequirements(DefaultListModel<String> testItemsListModel)
	{
		if (testItemsListModel.size() >= 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Creates an Item List of the DefaultListModel<String> from the specified view
	 *  
	 * @param JListModel the specific DefaultListModel from the view being referenced
	 * @return a new ItemList of values from the View's List Model
	 */
	public ItemList modelToList(DefaultListModel<String> JListModel)
	{
		ItemList newList = new ItemList();
		for (int i = 0; i < JListModel.size(); i++)
		{
			Item item = new Item();
			item.setValue(JListModel.getElementAt(i));
			newList.addItem(item);
		}
		return newList;
	}
	
	/**
	 * Saves the administrator newly created test, logs off the user and returns them to the login screen
	 * 
	 * @param itemList The list being saved
	 */
	public void saveTestList(ItemList itemList)
	{
		testItemsList = itemList;          //updated the model
		sqlUser.saveTest(itemList);        //updated the database

		adminTestSetupFrame.setVisible(false);

		JOptionPane.showMessageDialog(null,"New test has been saved successfully to the database. You will now be logged off and returned to the login screen.","New Test Saved",JOptionPane.WARNING_MESSAGE);
		
		loginFrame.setVisible(true);
	}
	
	/**
	 * Discards any changes made to the Test Items List and preserves the previous Test Items List. Returns the user to the login screen.
	 */
	public void cancelTest()
	{
		adminTestSetupFrame.setVisible(false);
		
		JOptionPane.showMessageDialog(null,"New test has been cancelled. Previous test has been preserved. You will now be logged off and returned to the login screen.","New Test Cancelled",JOptionPane.WARNING_MESSAGE);
		
		loginFrame.setVisible(true);
	}
	
	/**
	 * returns the existing items list from the model
	 * 
	 * @return the Existing Items List
	 */
	public ItemList getExistingItemsList()
	{
		return existingItemsList;
	}
	
	/**
	 * returns the Test Items List
	 * 
	 * @return the Test Items List
	 */
	public ItemList getTestItemsList()
	{
		return testItemsList;
	}
	
	/**
	 * Creates the report view for the test Results
	 */
	public void createReportView()
	{
		loginFrame.setVisible(false);
		adminTestSetupFrame.setVisible(true);
		//reportView = new ReportView(this);
		reportModel = new Report();
		reportView.setVisible(true);
	}
	
	/**
	 * creates the table for the report results set
	 */
	public void setReportTable()
	{
		try
		{
		reportView.setReportTable((reportView.setColumns(reportModel.getColumnData())),(reportView.setData(reportModel.getColumnData())));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"report error: " + e.getMessage(),"report error",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Queries the database and return the count of the items on the Test Items List
	 * 
	 * @return the count of test items on the Test Items List
	 */
	public int setTotalQuestions()
	{
		return sqlUser.pullTestItemsCount();
	}
	
	/**
	 * Updates the words on the User test to the first word on the test
	 *  
	 * @param itemLeft the first questions left item
	 * @param itemRight the first questions right item
	 */
	public void setupFirstQuestion(JLabel itemLeft, JLabel itemRight)
	{
		Question question;
		question = userTest.getCurrentQuestion();
		Item item = question.getItemLeft();
		itemLeft.setText(item.getValue());
		item = question.getItemRight();
		itemRight.setText(item.getValue());
	}

	/**
	 * sets the answer in the model chosen by the user taking the test 
	 * 
	 * @param value the radio button chosen on the test
	 * @param itemLeft the question's left item
	 * @param itemRight the question's right item
	 */
	public void recordAnswer(String value, JLabel itemLeft, JLabel itemRight)
	{
		//record answer to the question
		Question question = userTest.getCurrentQuestion();
		Answer answer = new Answer();
		if (value.equals("Choose left"))
		{
			answer.setValue(Value.LEFT);
			question.setAnswer(answer);
		}
		if (value.equals("Choose right"))
		{
			answer.setValue(Value.RIGHT);
			question.setAnswer(answer);
		}
		if (value.equals("I can't decide"))
		{
			answer.setValue(Value.CANT_DECIDE);
			question.setAnswer(answer);
		}
		
		//get the next question
		question = userTest.getNextQuestion();
		
		//determine if we have finished the test by the iterator returning a null value
		if (question == null)  //we are not passed the last item on the test and the test is complete
		{
			//if question is null then the test is over show the user the finished message
			//close the userTestFrame
			//return the user to the loginFrame
			
			calculateItemRankingsToTestItemsListItems();
			int userID = sqlUser.getUserID(user.getUsername());
			sqlUser.createUserNewTestSessionID(userID);
			int testSessionID = sqlUser.getTestSessionIDScopeIdentity();
			user.setTestSessionID(testSessionID);
			sqlUser.saveTestResults(testItemsList, user.getTestSessionID());

			userTestFrame.setVisible(false);

			JOptionPane.showMessageDialog(null,"You have completed the test! You will now be logged off and returned to the login screen.","User Test - Test Complete!",JOptionPane.WARNING_MESSAGE);
			
			loginFrame.setVisible(true);
		}
		else //test is still going as the question on the test is not null
		{
			//else set the labels for the next question on the test
			Item item = question.getItemLeft();
			itemLeft.setText(item.getValue());
			item = question.getItemRight();
			itemRight.setText(item.getValue());
			
			//Need to increment the ProgressBar here
			incrementProgressMeter();
		}
		
	}
	
	/**
	 * updates the progress meter in the view to the next question
	 */
	public void incrementProgressMeter()
	{
		userTestFrame.incrementProgressMeter();
	}
	
	/**
	 * queries the database ProgressMeter table to get the last selected state of the progress meter checkbox on the admin setup test page
	 * 
	 * @return true is the check box was selected; false if not
	 */
	public boolean getProgressMeterSelectedState()
	{
		return sqlUser.getProgressMeterSelectedState();
	}
	
	/**
	 * receives input from the view to update the model and the database to the new selected state of the progress meter checkbox on the admin test setup page  
	 * 
	 * @param selectedState true is the check box has been selected; false if not
	 */
	public void progressMeterSelectedStateChanged(int selectedState)
	{
		sqlUser.setProgressBarSelectedState(selectedState);   //database
		
		if (selectedState == 0)
		{
			progressMeter.setSelectedState(false);     //update the model 
		}
		else if (selectedState == 1)
		{
			progressMeter.setSelectedState(true);      //update the model 
		}
	}
	
	/**
	 * calculate the results from the test and turn the answers chosen and award each item accordingly
	 */
	public void calculateItemRankingsToTestItemsListItems()
	{
		// resetQuestionList iterator so we can go through the questions list again
		userTest.resetQuestionListIterator();
		userTest.getNextQuestion();
	 
		//look at the answer to the question
		Question question = userTest.getCurrentQuestion();
		
		while (question != null)
		{
			
			Answer answer = question.getAnswer();
			Item item;
			
			if (answer.getValue() == Value.LEFT)
			{
				item = question.getItemLeft();
				testItemsList.getItem(item.getValue()).addWin();
				item = question.getItemRight();
				testItemsList.getItem(item.getValue()).addLoss();
			}
			
			if (answer.getValue() == Value.RIGHT)
			{
				item = question.getItemLeft();
				testItemsList.getItem(item.getValue()).addLoss();
				item = question.getItemRight();
				testItemsList.getItem(item.getValue()).addWin();
			}
	
			if (answer.getValue() == Value.CANT_DECIDE)
			{
				item = question.getItemLeft();
				testItemsList.getItem(item.getValue()).addTie();
				item = question.getItemRight();
				testItemsList.getItem(item.getValue()).addTie();
			}
			
			//get next question
			question = userTest.getNextQuestion();
		}
	}
	
	public void startReport()
	{
		Report theModel = new Report();
		ReportView theView = new ReportView(theModel.getUsers());
		@SuppressWarnings("unused")
		
		ReportController theController = new ReportController(theView, theModel);
		
		theView.setVisible(true);
	}
}

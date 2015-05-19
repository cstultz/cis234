package cis234a.nsort.controller;

import javax.swing.JLabel;

import cis234a.nsort.model.*;
import cis234a.nsort.model.Answer.Value;
import cis234a.nsort.view.*;
/**
 * The UserTestController handles the logic of the user test.  
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class UserTestController {
	
	private RankingSystemController controller;
	
	private UserTestModel model;
	private UserTestView view;
	
	private SqlUser_234a_t1 sqlUser;

	/**
	 * Constructor for the class.
	 * @param RankingSystemController for communication back.
	 * @param UserTestModel will directly manage the data, logic and rules of the user test.
	 * @param UserTestView output representation of the user test information.
	 */
	public UserTestController(RankingSystemController controller, UserTestModel model, UserTestView view) {
		this.controller = controller;
		this.model = model;
		this.view = view;

		sqlUser = new SqlUser_234a_t1();
		
		updateView();   
	}

	/**
	 * updates the user test view. sets the first question on the test and sets the progress meter total questions and selected state.
	 */
	private void updateView() 
	{
		setupFirstQuestion();
		updateTotalQuestions();
		updateProgressMeterSelectedState();
		view.updateUserTestFrame(model.getUserTestFrameState());                        
	}
	
	/**
	 * send back a call to the controller to update the components in the view to the first question on the test.
	 */
	public void setupFirstQuestion()
	{
		model.setupFirstQuestion(view.getLeftItemLabel(), view.getRightItemLabel());
	}
	
	/**
	 * launch the login frame
	 */
	public void launchLogin()
	{
		controller.launchLogin();                                  

	}
	
	/**
	 * hides the user test frame
	 */
	public void hideUserTest()
	{
		view.updateUserTestFrame(model.getUserTestFrameState());
	}
	
	/**
	 * save the test items list to the model and the database. log out the user.
	 * 
	 * @param items
	 */
	public void saveTestItemsList(ItemList items)
	{
		model.setTestItemsList(items);
		sqlUser.saveTest(items);
		logoutUser();
	}
	
	/**
	 * logs out the user. sets the login state to false; sets the user access role to Unassigned.
	 */
	public void logoutUser()
	{
		model.setLoginState(false);
		model.setUserAccessRole(Role.UserAccessRole.Unassigned);
	}
	
	/**
	 * record an answer to a question on the test
	 * 
	 * @param UserSelection enum Value from the Answers class
	 * @param itemLeft JLabel object for the left item
	 * @param itemRight JLabel object for the right item
	 */
	public void recordAnswer(Answer.Value UserSelection, JLabel itemLeft, JLabel itemRight)
	{
		model.recordAnswer(UserSelection, itemLeft, itemRight);
	}
	
	/**
	 * get the next question on the test
	 * 
	 * @param itemLeft JLabel object of the left item
	 * @param itemRight JLabel object of the right item
	 * @return true if their is still another question on the test; false is all the questions on the test have been answered.
	 */
	public boolean getNextQuestion(JLabel itemLeft, JLabel itemRight)
	{
		
		Question question = model.getNextQuestion(); //get the next question from the model.
		
		if (question == null) //if we have surpassed the last question on the test 
		{
			calculateItemRankingsToTestItemsListItems();
			int userID = sqlUser.getUserID(model.getUsername());
			sqlUser.createUserNewTestSessionID(userID);
			int testSessionID = sqlUser.getTestSessionIDScopeIdentity();
			model.setTestSessionID(testSessionID);
			sqlUser.saveTestResults(model.getTestItemsList(), model.getTestSessionID());
			return false;
		}
		else //there is still more questions on the test
		{
			//set the labels for the next question on the test
			Item item = question.getItemLeft();
			itemLeft.setText(item.getValue());
			item = question.getItemRight();
			itemRight.setText(item.getValue());
			
			return true;
		}
	}
	
	/**
	 * converts the answer to a question into the win-loss-tie results for each item on the test. gets the next question on the test.
	 */
	public void calculateItemRankingsToTestItemsListItems()
	{
		// resetQuestionList iterator so we can go through the questions list again
		model.resetQuestionListIterator();
		model.getNextQuestion();
	 
		//look at the answer to the question
		Question question = model.getCurrentQuestion();
		
		while (question != null) //more question on the test exists
		{
			//all questions on the test have been answered. time to tally the results.						
			Answer answer = question.getAnswer();
			Item item;
			
			if (answer.getValue() == Value.LEFT)
			{
				item = question.getItemLeft();
				model.addWin(item);
				item = question.getItemRight();
				model.addLoss(item);
			}
			
			if (answer.getValue() == Value.RIGHT)
			{
				item = question.getItemLeft();
				model.addLoss(item);
				item = question.getItemRight();
				model.addWin(item);
			}
	
			if (answer.getValue() == Value.CANT_DECIDE)
			{
				item = question.getItemLeft();
				model.addTie(item);
				item = question.getItemRight();
				model.addTie(item);
			}
			
			//get next question
			question = model.getNextQuestion();
		}
	}
	
	/**
	 * updates the total questions on the test to the view from the model 
	 */
	public void updateTotalQuestions()
	{
		int totalQuestions = model.getQuestionsCount();
		view.setTotalQuestions(totalQuestions);
		model.setProgressMeterUpperBound(totalQuestions);
	}
	
	/**
	 * sets the progress meter selected state in the view from the database
	 */
	public void updateProgressMeterSelectedState()
	{
		model.setProgressMeterSelectedState(sqlUser.getProgressMeterSelectedState());
		view.updateProgressMeterSelectedState(model.getProgressMeterSelectedState());
	}
}

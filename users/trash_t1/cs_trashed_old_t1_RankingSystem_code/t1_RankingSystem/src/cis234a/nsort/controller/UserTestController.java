package cis234a.nsort.controller;

import javax.swing.JLabel;
import cis234a.nsort.model.*;
import cis234a.nsort.model.Answer.Value;
import cis234a.nsort.view.*;

public class UserTestController {
	
	private RankingSystemController controller;
	
	private UserTestModel model;
	private UserTestView view;
	
	private SqlUser_234a_t1 sqlUser;

	public UserTestController(RankingSystemController controller, UserTestModel model, UserTestView view) {
		this.controller = controller;
		this.model = model;
		this.view = view;

		sqlUser = new SqlUser_234a_t1();
		
		updateView();   
	}

	private void updateView() 
	{
		setupFirstQuestion();
		setTotalQuestions();
		setProgressMeterSelectedState();
		view.updateUserTestFrame(model.getUserTestFrameState());                        
	}
	
	/**
	 * send back a call to the controller to update the components in the view to the first question on the test.
	 */
	public void setupFirstQuestion()
	{
		model.setupFirstQuestion(view.getLeftItemLabel(), view.getRightItemLabel());
	}
	
	public void launchLoginFrame()
	{
		controller.launchLogin();                                  

	}
	
	public void hideUserTest()
	{
		view.updateUserTestFrame(model.getUserTestFrameState());
	}
	
	public void saveTestItemsList(ItemList items)
	{
		model.setTestItemsList(items);
		sqlUser.saveTest(items);
		logoutUser();
	}
	
	public void logoutUser()
	{
		model.setLoginState(false);
		model.setUserAccessRole(Role.UserAccessRole.Unassigned);
	}
	
	public void recordAnswer(String value, JLabel itemLeft, JLabel itemRight)
	{
		model.recordAnswer(value, itemLeft, itemRight);
	}
	
	public boolean getNextQuestion(JLabel itemLeft, JLabel itemRight)
	{
		
		Question question = model.getNextQuestion();
		
		if (question == null)
		{
			//if question is null then the test is over show the user the finished message
			//close the userTestFrame
			//return the user to the loginFrame
			
			calculateItemRankingsToTestItemsListItems();
			int userID = sqlUser.getUserID(model.getUsername());
			sqlUser.createUserNewTestSessionID(userID);
			int testSessionID = sqlUser.getTestSessionIDScopeIdentity();
			model.setTestSessionID(testSessionID);
			sqlUser.saveTestResults(model.getTestItemsList(), model.getTestSessionID());
			return false;
		}
		else //test is still going as the question on the test is not null
		{
			//else set the labels for the next question on the test
			Item item = question.getItemLeft();
			itemLeft.setText(item.getValue());
			item = question.getItemRight();
			itemRight.setText(item.getValue());
			
			//Need to increment the ProgressBar here
			//incrementProgressMeter();
			
			return true;
		}
	}
	
	/**
	 * calculate the results from the test and turn the answers chosen and award each item accordingly
	 */
	public void calculateItemRankingsToTestItemsListItems()
	{
		// resetQuestionList iterator so we can go through the questions list again
		model.resetQuestionListIterator();
		model.getNextQuestion();
	 
		//look at the answer to the question
		Question question = model.getCurrentQuestion();
		
		while (question != null)
		{
			
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
	
	public void setTotalQuestions()
	{
		view.setTotalQuestions(model.getQuestionsCount());
	}
	
	public void setProgressMeterSelectedState()
	{
		view.updateProgressMeterSelectedState(sqlUser.getProgressMeterSelectedState());
	}
}

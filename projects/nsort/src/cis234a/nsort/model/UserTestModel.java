package cis234a.nsort.model;

import cis234a.nsort.model.Answer.*;
/**
 * The UserTestModel class captures the behavior of the user test independent of the user interface.   
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class UserTestModel {
	
	private User user;
	private ItemList testItemsList;
	private NTest userTest;	
	private boolean userTestFrameState; 
	private ProgressMeter progressMeter;
	

	/**
	 * Constructor for the class. Sets the user test state to false.
	 */
	public UserTestModel()
	{
		progressMeter = new ProgressMeter();
		setUserTestState(false);   //user is not done taking the test
	}

	/**
	 * Get the admin test setup state of the test
	 * @return true if test is finalized; false if not.
	 */
	public boolean getUserTestFrameState() {
		return userTestFrameState;
	}

	/**
	 * set the user test state in the model
	 * 
	 * @param userTestFrameState true for when user is complete with the test; false for when the user is still taking the test.
	 */
	public void setUserTestState(boolean userTestFrameState) 
	{
		this.userTestFrameState = userTestFrameState;
	}
	
	/**
	 * set the user in the model
	 * 
	 * @param user to be set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * Set the user login in state to true
	 * @param login state of the user; true if user is logged in; false if not.
	 */
	public void setLoginState(boolean loginState) {
		user.setLoginState(loginState);
	}

	/**
	 * get the access role of the user
	 * 
	 * @return the Role.UserAccessRole {Admin, User, Unassigned} 
	 */
	public Role.UserAccessRole getUserAccessRole()
	{
		return user.getUserAccessRole();
	}
	
	/**
	 * set the access role of the user
	 * 
	 * @param the Role.UserAccessRole {Admin, User, Unassigned}
	 */
	public void setUserAccessRole(Role.UserAccessRole userAccessRole)
	{
		user.setUserAccessRole(userAccessRole);
	}
	
	/**
	 * set the Test Items List in the model
	 * 
	 * @param items to be set
	 */
	public void setTestItemsList(ItemList items)
	{
		testItemsList = items;
	}
	
	/**
	 * sets up the user test from the Test Items List in the model
	 */
	public void setUserTest()
	{
		userTest = new NTest(testItemsList);
	}
	
	/**
	 * sets up the first question on the test
	 * 
	 * @param JLabel for the left item of the question
	 * @param JLabel for the right item of the question
	 */
	public Question getFirstQuestion()
	{
		return userTest.getCurrentQuestion();
	}
	
	/**
	 * sets the answer in the model chosen by the user taking the test 
	 * 
	 * @param value the radio button chosen on the test
	 * @param itemLeft the question's left item
	 * @param itemRight the question's right item
	 */
	public void recordAnswer(Answer.Value userSelection)
	{
		//record answer to the question
		Question question = userTest.getCurrentQuestion();
		Answer answer = new Answer();
		if (userSelection == Answer.Value.LEFT)
		{
			answer.setValue(Value.LEFT);
			question.setAnswer(answer);
		}
		if (userSelection == Answer.Value.RIGHT)
		{
			answer.setValue(Value.RIGHT);
			question.setAnswer(answer);
		}
		if (userSelection == Answer.Value.CANT_DECIDE)
		{
			answer.setValue(Value.CANT_DECIDE);
			question.setAnswer(answer);
		}
	}
	
	/**
	 * get the next question on the test
	 * 
	 * @return the next question object
	 */
	public Question getNextQuestion()
	{
		return userTest.getNextQuestion();
	}
	
	/**
	 * get the username of the user in the model
	 * 
	 * @return the username of the user
	 */
	public String getUsername()
	{
		return user.getUsername();
	}
	
	public String getFullName()
	{
		return (user.getFirstName() + " " + user.getLastName());
	}
	
	/**
	 * set the unique test session ID for the test
	 * 
	 * @param testSessionID for the test
	 */
	public void setTestSessionID(int testSessionID)
	{
		user.setTestSessionID(testSessionID);
	}
	
	/**
	 * get the Test Items List from the model
	 * 
	 * @return the test Items List in the model
	 */
	public ItemList getTestItemsList()
	{
		return testItemsList;
	}
	
	/**
	 * get the unique test session ID for the test
	 * 
	 * @return the test session ID
	 */
	public int getTestSessionID()
	{
		return user.getTestSessionID();
	}
	
	/**
	 * reset the question list iterator so we can walk through the questions again.
	 */
	public void resetQuestionListIterator()
	{
		userTest.resetQuestionListIterator();
	}
	
	/**
	 * get the current question
	 * 
	 * @return the current question object
	 */
	public Question getCurrentQuestion()
	{
		return userTest.getCurrentQuestion();
	}
	
	/**
	 * add a win to an item
	 * 
	 * @param item that got a win
	 */
	public void addWin(Item item)
	{
		testItemsList.getItem(item.getValue()).addWin();
	}
	
	/**
	 * add a loss to an item
	 * 
	 * @param item that got a loss
	 */
	public void addLoss(Item item)
	{
		testItemsList.getItem(item.getValue()).addLoss();
	}

	/**
	 * add a tie to an item
	 * 
	 * @param item that got a tie
	 */
	public void addTie(Item item)
	{
		testItemsList.getItem(item.getValue()).addTie();
	}
	
	/**
	 * get the total number of questions that are on the test
	 * 
	 * @return the total questions on the test
	 */
	public int getQuestionsCount()
	{
		return userTest.numberOfQuestions();
	}
	
	/**
	 * get the progress meter selected state
	 * 
	 * @return true if the progress meter is to be visible; false if not.
	 */
	public boolean getProgressMeterSelectedState()
	{
		return progressMeter.getSelectedState();
	}
	
	/**
	 * set the progress meter selected state
	 * 
	 * @param selectedState of the progress meter
	 */
	public void setProgressMeterSelectedState(boolean selectedState)
	{
		progressMeter.setSelectedState(selectedState);
	}
	
	/**
	 * set the upper bound of the progress meter (total questions on the test)
	 * 
	 * @param totalQuestions on the test
	 */
	public void setProgressMeterUpperBound(int totalQuestions)
	{
		progressMeter.setUpperBound(totalQuestions);
	}
	
	/**
	 * get the progress meter upper bound (total questions on the test)
	 * 
	 * @return the upper bound (total questions on the test)
	 */
	public int getProgressMeterUpperBound()
	{
		return progressMeter.getUpperBound();
	}
}
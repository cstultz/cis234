package cis234a.nsort.model;

import javax.swing.JLabel;

import cis234a.nsort.model.Answer.*;

public class UserTestModel {
	
	private User user;
	
	private ProgressMeter progressMeter;
	private ItemList testItemsList;	
	private NTest userTest;	
	
	private boolean userTestFrameState; 

	/**
	 * Constructor
	 */
	public UserTestModel()
	{
		setUserTestState(false);   //user is not done taking the test
	}

	/**
	 * Get the admin test setup state of the test
	 * @return true if test is finalized; false if not.
	 */
	public boolean getUserTestFrameState() {
		return userTestFrameState;
	}

	public void setUserTestState(boolean userTestFrameState) 
	{
		this.userTestFrameState = userTestFrameState;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * Set the user login in state to true
	 */
	public void setLoginState(boolean loginState) {
		user.setLoginState(loginState);
	}

	/**
	 * Get the login state of the user
	 * @return true if user is logged in; false if not.
	 */
	public boolean getLoggedInState() {
		return user.getLoginState();
	}
	
	public Role.UserAccessRole getUserAccessRole()
	{
		return user.getUserAccessRole();
	}
	
	public void setUserAccessRole(Role.UserAccessRole userAccessRole)
	{
		user.setUserAccessRole(userAccessRole);
	}
	
	public void setTestItemsList(ItemList items)
	{
		testItemsList = items;
	}
	
	public void setUserTest()
	{
		userTest = new NTest(testItemsList);
	}
	
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
	}
	
	public Question getNextQuestion()
	{
		return userTest.getNextQuestion();
	}
	
	/**
	 * updates the progress meter in the view to the next question
	 */
	public void incrementProgressMeter()
	{
		//userTestFrame.incrementProgressMeter();
	}
	
	public String getUsername()
	{
		return user.getUsername();
	}
	
	public void setTestSessionID(int testSessionID)
	{
		user.setTestSessionID(testSessionID);
	}
	
	public ItemList getTestItemsList()
	{
		return testItemsList;
	}
	
	public int getTestSessionID()
	{
		return user.getTestSessionID();
	}
	
	public void resetQuestionListIterator()
	{
		userTest.resetQuestionListIterator();
	}
	
	public Question getCurrentQuestion()
	{
		return userTest.getCurrentQuestion();
	}
	
	public void addWin(Item item)
	{
		testItemsList.getItem(item.getValue()).addWin();
	}

	public void addLoss(Item item)
	{
		testItemsList.getItem(item.getValue()).addLoss();
	}

	public void addTie(Item item)
	{
		testItemsList.getItem(item.getValue()).addTie();
	}
	
	public int getQuestionsCount()
	{
		return userTest.numberOfQuestions();
	}
	
}
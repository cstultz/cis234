package cis234a.nsort.model;

/**
 * The NTest class represents the test taken by the user and holds the collection of questions for the test.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/17/2015)
 */
public class NTest {
	
	private QuestionList questions;	
	
	/**
	 * Constructor for the NTest class
	 * @param testItemsList the items on the Test Items List
	 */
	public NTest(ItemList testItemsList)
	{
		questions = new QuestionList(testItemsList);
	}
	
	/**
	 * the number of questions that are on the test
	 * 
	 * @return the number of questions on the test
	 */
	public int numberOfQuestions() {
		return questions.size(); 
	}

	/**
	 * add a question to the list of questions on the test
	 * 
	 * @param the question to be added to the list of questions on the test
	 */
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	/**
	 * get the current question on the test based on the position of the iterator 
	 * 
	 * @return the current question on the test
	 */
	public Question getCurrentQuestion()
	{
		return questions.getCurrentQuestion();
	}
	
	/**
	 * get the next question on the test based on the position of the iterator.
	 * 
	 * @return the next question on the test
	 */
	public Question getNextQuestion()
	{
		questions.nextQuestion();                     //iterate to the next question on the list
		return questions.getCurrentQuestion();        //return the next question in the list
	}
	
	/**
	 * reset the position of the iterator so we can traverse the questions on the test from the beginning
	 */
	public void resetQuestionListIterator()
	{
		questions.resetIterator();
	}
}
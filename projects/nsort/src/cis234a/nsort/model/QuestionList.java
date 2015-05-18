/**
 * 
 */
package cis234a.nsort.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * The QuestionList class represents a list of questions. This class contains an iterator to walk the collection.
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/17/2015)
 */
public class QuestionList
	{
	
	private List<Question> questions;	
	private ItemList testItemsList;
	private Question current = null;
	private int totalQuestions;
	Iterator<Question> iterator;
	
    /**
     * The Constructor for the QuestionList class. You must have a testItemsList in order to construct a QuestionList.
     * 
     * @param testItemsList the items on the test.
     */
	public QuestionList(ItemList testItemsList) 
	{ 
		this.testItemsList = testItemsList;
		int itemsCount = testItemsList.getSize();
		
		//the formula f(N) = N(N-1)/2
		totalQuestions = (itemsCount * (itemsCount - 1) / 2);
		questions = new ArrayList<Question>(totalQuestions);
		
		createQuestionsList();
		
		this.iterator = questions.iterator();      //initialize the iterator
		current = iterator.next();                //set the iterator to the first question of the list
	}
	
	/**
	 * Iterate to the next question in the QuestionList. Set current iterator to null when 
	 * the iterator has moved past the last question in the QuestionList
	 */
	public void nextQuestion() { 
		if ( iterator.hasNext() ) {
			current =  iterator.next();
		} else {
			current = null;
		}
	}
	
	/**
	 * get the iterator current question in the QuestionList
	 * 
	 * @return the current Question
	 */
	public Question getCurrentQuestion() {return current; }
		
    /**
     * get the size of the QuestionList
     * 	
     * @return the size of the QuestionList
     */
	public int size() {
		
		return questions.size();
	}
	
    /**
     * add a Question the the QuestionList
     * 
     * @param question to be added to the list
     */
	public void add(Question question) {
		
		questions.add(question);
	}
	
	/**
	 * Create the QuestionList from the testItemsList.
	 */
	public void createQuestionsList()
	{
		//mimics the formula f(N) = N(N-1)/2 using a set of nested loops 
		//to add all item compare questions to the QuestionList
		for (int i = 0; i < testItemsList.getSize(); i++)
		{
			for (int j = i + 1; j < testItemsList.getSize(); j++)
			{
				Item itemLeft = testItemsList.getItem(i);
				Item itemRight = testItemsList.getItem(j);
				
				Question question = new Question(itemLeft, itemRight);
				this.add(question);
			}
		}
	}
	

	/**
	 * reset the iterator back to its initialized position
	 */
	public void resetIterator()
	{
		this.iterator = questions.iterator(); 
	}
}
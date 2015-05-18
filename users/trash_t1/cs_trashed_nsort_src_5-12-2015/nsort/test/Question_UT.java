/**
 * 
 */
package nsort.test;

import static org.junit.Assert.*;
import nsort.model.Answer;
import nsort.model.Item;
import nsort.model.Question;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class Question_UT {

	
	/**
	 * Test method for {@link nsort.model.Question#Question(java.lang.String)}.
	 */
	@Test
	public void testQuestionInstantiation() {
		Item item1 = new Item();
		Item item2 = new Item();
		Question question = new Question(item1, item2);
		assertNotNull("Question instantiation failed.", question);
	}

	/**
	 * Test method for {@link nsort.model.Question#getAnswer()}.
	 */
	@Test
	public void testGetAnswerWithDefault() {
		Item item1 = new Item();
		Item item2 = new Item();
		Question question = new Question(item1, item2);
		Answer answer = question.getAnswer();
		
		assertNotNull("Answer answer is null", answer);
		
		assertEquals("Question answer default not expected UNANSWERED value",  Answer.Value.UNANSWERED,  answer.getValue());
	}

	/**
	 * Test method for {@link nsort.model.Question#setAnswer()}.
	 */
	@Test
	public void testSetValue() {
		Answer answer = new Answer();
		answer.setValue(Answer.Value.LEFT);

		Item item1 = new Item();
		Item item2 = new Item();
		Question question = new Question(item1, item2);
		question.setAnswer(answer);					// so now questions answer is LEFT
		 
		assertEquals("Question getAnswer did not return setAnswer'd value", question.getAnswer(), answer) ; 
	}

	/**
	 * Test method for {@link nsort.model.Question#getAnswer()}.
	 * This is the same as Set Value test, but with different data.  
	 * 
	 */
	@Test
	public void testGetValue() {
		Answer answer = new Answer();
		answer.setValue(Answer.Value.RIGHT);

		Item item1 = new Item();
		Item item2 = new Item();
		Question question = new Question(item1, item2);
		question.setAnswer(answer);					// so now questions answer is RIGHT
		 
		assertEquals("Question getAnswer did not return setAnswer'd value", question.getAnswer(), answer) ; 
	}

}

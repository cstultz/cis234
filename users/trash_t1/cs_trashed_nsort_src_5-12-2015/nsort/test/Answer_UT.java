package nsort.test;

import static org.junit.Assert.*;

import nsort.model.Answer;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class Answer_UT {

	@Test
	public void testAnswerInstantiation() {
		Answer answer = new Answer();
		assertNotNull("Answer instantiation failed.", answer);
		
	}

//	@Test
//	public void testIsAnsweredWithAnswerObjectDefaultValue1() {
//		Answer answer = new Answer();
//		assertEquals("Answer.isAnswered did not return false for expected initial Answer obj state", true, answer.isAnswered());
//	}

	@Test
	public void testgetAnswerWithAnswerObjectDefaultValue() {
		Answer answer = new Answer();
		assertEquals("Answer.isAnswered did not return expected getValue for initial Answer obj state", 
				     Answer.Value.UNANSWERED, answer.getValue());
	}

	@Test
	public void testAnswerSetGetValueUNANSWERED() {
		Answer answer = new Answer();
		answer.setValue(
				Answer.Value.UNANSWERED);
		assertEquals("Answer.getValue did not return expected value of UNANSWERED", 
				Answer.Value.UNANSWERED, answer.getValue());
	}

	@Test
	public void testAnswerSetGetValueLEFT() {
		Answer answer = new Answer();
		answer.setValue(
				Answer.Value.LEFT);
		assertEquals("Answer.getValue did not return expected value of LEFT", 
				Answer.Value.LEFT, answer.getValue());
	}

	@Test
	public void testAnswerSetGetValueRIGHT() {
		Answer answer = new Answer();
		answer.setValue(
				Answer.Value.RIGHT);
		assertEquals("Answer.getValue did not return expected value of RIGHT", 
				Answer.Value.RIGHT, answer.getValue());
	}

	@Test
	public void testAnswerSetGetValueCANT_DECIDE() {
		Answer answer = new Answer();
		answer.setValue(
				Answer.Value.CANT_DECIDE);
		assertEquals("Answer.getValue did not return expected value of CANT_DECIDE", 
				Answer.Value.CANT_DECIDE, answer.getValue());
	}
}

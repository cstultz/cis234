/**
 * 
 */
package nsort.test;


import nsort.model.Item;
import nsort.model.ItemList;
import nsort.model.Question;
import nsort.model.NTest;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class NTest_UT {

	// creates an array of zero questions
	@SuppressWarnings("unused")
	private NTest getNTestQtyZero(){
		ItemList items = new ItemList();
		return new NTest(items);
	}

	// creates an array of 1 question
	@SuppressWarnings("unused")
	private NTest getNTestQtyOne(){
		ItemList items = new ItemList();

		NTest nTest = new NTest(items);
		
		Item item1 = new Item();
		Item item2 = new Item();

		Question question =new Question(item1, item2);
		
		Item itemBananas 	= new Item();
		Item itemApples 	= new Item();
		
		itemBananas.setValue("Bananas");
		itemApples.setValue("Apples");
			
		question.setItemLeft(itemBananas);
		question.setItemRight(itemApples);

		nTest.addQuestion(question);
		
		return nTest;
	}
	
	// creates an array of 2 questions
	@SuppressWarnings("unused")
	private NTest getNTestQtyTwo(){
		ItemList items = new ItemList();

		NTest nTest = new NTest(items);

		Item item1 = new Item();
		Item item2 = new Item();

		Question question1 = new Question(item1, item2);
		Question question2 = new Question(item1, item2);
		
		Item itemBananas 	= new Item();
		Item itemApples 	= new Item();
		Item itemOranges 	= new Item();
		Item itemCherries 	= new Item();
		
		itemBananas.setValue("Bananas");
		itemApples.setValue("Apples");
		itemOranges.setValue("Oranges");
		itemCherries.setValue("Cherries");
			
		question1.setItemLeft(itemBananas);
		question1.setItemRight(itemApples);

		question2.setItemLeft(itemOranges);
		question2.setItemRight(itemCherries);
		
		nTest.addQuestion(question1);
		nTest.addQuestion(question2);
		
		return nTest;
	}

//	@Test
//	public void getNTestInstantiation1() {
//		ItemList items = new ItemList();
//		NTest nTest = new NTest(items);
//		assertNotNull("Instantiation failed.", nTest);
//	}

	
//	@Test
//	public void testTestGetQuestionsWithNoQuestions() {
//		ItemList items = new ItemList();
//		NTest nTest = new NTest(items);
//		assertEquals("number of questions input does not match numberOfQuestions call",0, 
//				nTest.numberOfQuestions() );
//	}


//	@Test
//	public void testWithQuestionsQtyZero() {
//		NTest nTest = getNTestQtyZero();
//		assertEquals("number of questions input does not match numberOfQuestions call",0, 
//				nTest.numberOfQuestions() );
//	}
//
//	@Test
//	public void testWithQuestionsQtyOne() {
//		NTest nTest = getNTestQtyOne();
//		assertEquals("number of questions input does not match numberOfQuestions call",1, 
//				nTest.numberOfQuestions());
//	}
//
//	@Test
//	public void testWithQuestionsQtyTwo() {
//		NTest nTest = getNTestQtyTwo();
//		assertEquals("number of questions input does not match numberOfQuestions call",2,   
//				nTest.numberOfQuestions() );
//	}
//
//	@Test
//	public void testNextWithQtyZero() {
//		NTest nTest = getNTestQtyZero();
//		
//		nTest.getCurrentQuestion();
//		
//		assertFalse("hasNext did not return expected value of false when called on new zero element question list", nTest.getCurrentQuestion() == null );
//	}
//
//	@Test
//	public void testNextWithQtyOne() {
//		NTest nTest = getNTestQtyOne();
//
//		nTest.getCurrentQuestion();
//			
//		assertNotNull("next() did return null when called with new qty 1 NTest", nTest.getNextQuestion());
//		
//		assertFalse("hasNext did not return expected value of false after pulling first element from question list", nTest.getCurrentQuestion() == null);
//
//	}
//
//	@Test
//	public void testNextWithQtyTwo() {
//		NTest nTest = getNTestQtyTwo();
//
//		Question question1 = nTest.getCurrentQuestion();
//			
//		assertNotNull("next() did return null when called with new qty 1 NTest", question1 );
//
//		Question question2 = nTest.getNextQuestion();
//
//		assertNotNull("second next() did return null when called with new qty 1 NTest", question2 );
//	
//	}
//
//	@Test
//	public void testQuestionDataWithQtyOne() {
//		NTest nTest = getNTestQtyOne();
//
//		Question question1 = nTest.getCurrentQuestion();
//		
//		assertEquals("Question.getItemLeft is not expected value of Bananas","Bananas", question1.getItemLeft().getValue() );
//		assertEquals("Question.getItemRight is not expected value of Apples","Apples", question1.getItemRight().getValue() );
//		assertEquals("Question.getAnswer is not expected value of UNANSWERED", Answer.Value.UNANSWERED, question1.getAnswer().getValue());
//	}
//
//	@Test
//	public void testQuestionDataWithQtyTwo() {
//		NTest nTest = getNTestQtyTwo();
//
//		Question question2 = nTest.getNextQuestion();
//			
//		assertEquals("Question.getItemLeft is not expected value of Oranges","Oranges",    question2.getItemLeft().getValue() );
//		assertEquals("Question.getItemRight is not expected value of Cherries","Cherries", question2.getItemRight().getValue() );
//		assertEquals("Question.getAnswer is not expected value of UNANSWERED", Answer.Value.UNANSWERED, question2.getAnswer().getValue());
//		
//	}
}
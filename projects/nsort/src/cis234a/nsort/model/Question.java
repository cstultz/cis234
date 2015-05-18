/**
 * 
 */
package cis234a.nsort.model;

import cis234a.nsort.model.Answer;
/**
 * The Question class captures the user response to a question. The user can choose 1 of 3 choices to a question
 * (choose left item, choose right item, or choose i can't decide)
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/17/2015)
 */
public class Question {

	private Item itemLeft;
	private Item itemRight;
	private Answer answer;

	/**
	 * The constructor for the Question class. Must know the left item and right item before constructing a question.
	 * 
	 * @param itemLeft the left item of the 2 item question
	 * @param itemRight right item of the 2 item question
	 */
	public Question(Item itemLeft, Item itemRight){
		this.itemLeft = itemLeft;
		this.itemRight = itemRight;
		this.answer = new Answer();  // so we have initial state of answer to be a valid answer state. 
	}
	
	/**
	 * get the left item of the 2 items in the question
	 * 
	 * @return the left item of the question
	 */
	public Item getItemLeft() {
		return itemLeft;
	}
	
	/**
	 * set the left item of the 2 items in the question
	 * 
	 * @param the left item to be set in the question
	 */
	public void setItemLeft(Item itemLeft) {
		this.itemLeft = itemLeft;
	}
	
	/**
	 * get the right item of the 2 item question
	 * 
	 * @return the right item of the question
	 */
	public Item getItemRight() {
		return itemRight;
	}
	
	/**
	 * set the right item of the 2 item question
	 * 
	 * @param the right item to be set in the question
	 */
	public void setItemRight(Item itemRight) {
		this.itemRight = itemRight;
	}
	
	/**
	 * get the answer to the question
	 * 
	 * @return the answer of the question
	 */
	public Answer getAnswer() {
		return answer;
	}
	/**
	 * set the answer of the question
	 * 
	 * @param the answer to the question
	 */
	public void setAnswer(Answer answer) {
		this.answer = answer;                   
		answer.isAnswer = true;                   //set the isAnswer to true when the answer is set.
	}
}

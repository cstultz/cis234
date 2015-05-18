package cis234a.nsort.model;

/**
 * The Answer class captures the answer to a question.  A question may be answered or unanswered.
 * If it is answered, it can have a value of Left, Right, or CantDecide.
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/21/2015)
 */
public class Answer {
	
	// possible answer values
	public enum Value { LEFT, RIGHT, CANT_DECIDE, UNANSWERED };
	
	boolean 	isAnswer;  		 // true if question is answered
	Value 		value;			 // only valid if question has been answered

	/**
	 * Constructor for the class.
	 */
	public Answer() { 
		setValue(Value.UNANSWERED); 
	}

	/**
	 * check to see of the question has been answered 
	 * 
	 * @return true if the question has been answered; false if not.
	 */
	public boolean isAnswered() {
		return value != Value.UNANSWERED;
	}

	/**
	 * get the value of the answer
	 * 
	 * @return the enum Value LEFT, RIGHT, CANT_DECIDE, UNANSWERED
	 */
	public Value getValue() {
		return this.value;
	}
	/**
	 * set the value of the answer
	 * 
	 * @param the enum Value LEFT, RIGHT, CANT_DECIDE, UNANSWERED
	 */	
	public void setValue(Value value) {
		this.value = value;
	}
}
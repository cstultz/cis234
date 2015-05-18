package cis234a.nsort.model;
import javax.swing.JProgressBar;

/**
 * The ProgressMeter class represents the lower, upper, and current bound of the progress indicator for the user test.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/17/2015)
 * 
 */public class ProgressMeter extends JProgressBar{

  	/**
	 * default serialization 
	 */
	private static final long serialVersionUID = 1L;
 	
	private int lowerBound;        //minimum value 
	private int upperBound;        //maximum value 
 	private int currentBound;      //current position
 	private boolean selectedState; //the on or off state set by the admin of the progress meter during the user test.
 	
 	public ProgressMeter (int upperBound, boolean setVisible) {
 		//Need to call the super class constructor bc of the extender. This way JProgressBar gets implemented
 		//and all the JProgressBar class elements are updated appropriately.
 		
 		this.lowerBound = 1;                   //set to first question static
 		this.upperBound = upperBound;          //total questions
 		this.selectedState = setVisible;       //initialize to the selectedState of the progress indicator
 		this.currentBound = 1;                 //set to first question static
 	}

	/**
	 * get the minimum value of the progress meter
	 * 
	 * @return the minimum value of the progress meter
	 */
 	public int getLowerBound() {
		return lowerBound;
	}

 	/**
 	 * set the minimum value of the progress meter 
 	 * 
 	 * @param minimum value of the progress meter
 	 */
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * get the maximum value of the progress meter
	 * 
	 * @return the maximum value of the progress meter
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * set the maximum value of the progress meter
	 * 
	 * @param the maximum value of the progress meter
	 */
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * get the current question position of the progress meter
	 * 
	 * @return the current question position of the progress meter  
	 */
	public int getCurrentBound() {
		return currentBound;
	}

	/**
	 * set the current question position of the progress meter 
	 * 
	 * @param the question position of the progress meter
	 */
	public void setCurrentBound(int currentBound) {
		this.currentBound = currentBound;
	}

	/**
	 * get the current state of the progress meter 
	 * 
	 * @return the current selected state of the progress meter
	 */
	public boolean getSelectedState() {
		return selectedState;
	}

	/**
	 * set the current state of the progress meter
	 * 
	 * @param setVisible true for shown; false for hidden 
	 */
	
	public void setSelectedState(boolean setVisible)
	{
		this.selectedState = setVisible;
	}
 } 
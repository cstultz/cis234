package cis234a.nsort.model;
import javax.swing.JProgressBar;

/**
 * The ProgressMeter captures the behavior of the progress meter independent of the user interface.
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
 	
 	private boolean selectedState; //the selectedState of the checkbox on the admin test setup
 	                               //determines the visible state of the progress meter during the user test.
 	/**
	 * Constructor for the class.
	 * 
	 */
 	public ProgressMeter () 
 	{
 		this.lowerBound = 1;                   //set to first question static
 		this.currentBound = 1;                 //set to first question static
 	}

	/**
	 * get the minimum value of the progress meter
	 * 
	 * @return the minimum value of the progress meter
	 */
 	public int getLowerBound() 
 	{
		return lowerBound;
	}

 	/**
 	 * set the minimum value of the progress meter 
 	 * 
 	 * @param minimum value of the progress meter
 	 */
	public void setLowerBound(int lowerBound) 
	{
		this.lowerBound = lowerBound;
	}

	/**
	 * get the maximum value of the progress meter
	 * 
	 * @return the maximum value of the progress meter
	 */
	public int getUpperBound() 
	{
		return upperBound;
	}

	/**
	 * set the maximum value of the progress meter
	 * 
	 * @param the maximum value of the progress meter
	 */
	public void setUpperBound(int upperBound) 
	{
		this.upperBound = upperBound;
	}

	/**
	 * get the current question position of the progress meter
	 * 
	 * @return the current question position of the progress meter  
	 */
	public int getCurrentBound() 
	{
		return currentBound;
	}

	/**
	 * set the current question position of the progress meter 
	 * 
	 * @param the question position of the progress meter
	 */
	public void setCurrentBound(int currentBound) 
	{
		this.currentBound = currentBound;
	}

	/**
	 * get the current state of the progress meter 
	 * 
	 * @return the current selected state of the progress meter
	 */
	public boolean getSelectedState() 
	{
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
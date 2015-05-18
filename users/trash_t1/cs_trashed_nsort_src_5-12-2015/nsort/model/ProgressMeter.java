package nsort.model;
import javax.swing.JProgressBar;

/**
 * The ProgressMeter class is the .....
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/17/2015)
 */public class ProgressMeter extends JProgressBar{

  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 	//Progress bar values. 'lowerBound' and 'upperBound' are for setting the progress bar. 
 	//'currentBound' is the current question number they're on.
	//selectedState is determines if the progress indicator is shown to the user during the test.
	private int lowerBound;        //set to first question static 

	private int upperBound;        //total questions
 	private int currentBound;  //initialize to first question dynamic
 	private boolean selectedState; //by default the progress meter is enabled
 	
 	
 	public ProgressMeter (int upperBound, boolean setVisible) {
 		//Need to call the super class constructor bc of the extender. This way JProgressBar gets implemented
 		//and all the JProgressBar class elements are updated appropriately.
 		
 		this.lowerBound = 1;                   //set to first question static
 		this.upperBound = upperBound;          //total questions
 		this.selectedState = setVisible;       //initialize to the selectedState of the progress indicator
 		this.currentBound = 1;                 //set to first question static
 	}
 	
	
	public int getLowerBound() {
		
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public int getCurrentBound() {
		return currentBound;
	}

	public void setCurrentBound(int currentBound) {
		this.currentBound = currentBound;
	}

	/**
	 * @return the visible
	 */
	public boolean getSelectedState() {
		return selectedState;
	}

	/**
	 * @param visible the visible to set
	 */
	
	public void setSelectedState(boolean setVisible)
	{
		this.selectedState = setVisible;
	}
 } 
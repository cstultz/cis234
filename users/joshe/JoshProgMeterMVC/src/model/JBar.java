package model;

import javax.swing.JProgressBar;


public class JBar extends JProgressBar{	
	
		//This Progress Bar is created in the "JbarJPanel" class which creates a JPanel,
		//and that JPanel is returned to the class that calls it. - josh eads 4/29/2015
	
	
		//Constructor for the Progress Bar. Takes 3 values:
		//lowerBound: lower bound of the Progress Bar.
		//upperBound: upper bound of the Progress bar.
		//visible: boolean whether to set the var as visible or not.
		public JBar(int lowerBound, int upperBound, boolean visible) {
			
		//Need to call the super class constructor bc this class "extends JProgressBar". This way JProgressBar gets implemented
		//and all the JProgressBar class elements are updated appropriately. - josh eads 4/29/2015		
		super (lowerBound, upperBound);
		
		//This method takes the boolean of the constructor, and uses JProgressBar class method "setVisible()"
		//to make the progress bar visible or not visible.
		this.setVisible(visible);				
	}
		
}


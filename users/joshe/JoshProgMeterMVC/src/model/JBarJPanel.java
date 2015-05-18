package model;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.Dimension;
import java.awt.FlowLayout;


/*
 * /*
 * NOTE: The below should be in the "RankingSystemController.java" class...
 * 
 * This method is used to return the number of Items in the ItemsList, which
 * can then be used to set the upperBound variable below, in the Constructor. - josh eads 4/29/2015
	 * Returns the number of items in the item list.
	 * This method is for the ProgressMeter class to set the size
	 * of the Progress Meter. - josh eads 4/29/2015
	 
	
	public int getTestItemsListSize () {
		int n = testItemsList.getSize();
		return n;
		}
	**/
 
public class JBarJPanel extends JPanel {

	//Variable to store the Progress Bar for this JPanel, using the Jbar class. - josh eads 4/29/2015
	private JBar myProgBar;
	
	//Creating a Dimension object. THIS OBJECT SETS THE SIZE OF THE PROGRESS BAR of the Jbar class.
	//Change the dimensions here to change the size of the Progress Bar as needed. - josh eads 4/29/2015
	private Dimension barSize = new Dimension (200, 30);
	/**
	 * Create the panel.
	 */
	public JBarJPanel(int progBarLower, int progBarUpper, boolean visibleBar) {
		
		//Call to the Super Class (JPanel) to create the JPanel. - josh eads 4/29/2015
		super();
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		
		//Create the Progress Bar thru the Jbar class. - josh eads 4/29/2015
		myProgBar = new JBar (progBarLower, progBarUpper, visibleBar);			
		
		//Set the size of the Progress Bar with the setPreferredSize() method (a method from the JProgressBar class),
		//and the Dimension object created above, "barSize", is used as the parameters for the size of 
		//the Progress Bar. - josh eads 4/29/2015
		myProgBar.setPreferredSize(barSize);
		
		//Add Progress Bar to the JPanel. - josh eads 4/29/2015
		add(myProgBar);

	}
	
	//This method is used to increment the Progress Bar up every time a question is answered. - josh eads 4/29/2015
	public void setBarValue (int x) {
		myProgBar.setValue(x);
	}
	}

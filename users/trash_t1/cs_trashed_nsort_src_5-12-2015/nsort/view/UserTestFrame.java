package nsort.view;

import javax.swing.JFrame;

import nsort.controller.RankingSystemController;

/**
 * The LoginFrame Class is the GUI of the user login for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class UserTestFrame extends JFrame
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;
	private UserTestPanel userTestPanel;
	
	/**
	 * Constructor for the UserTestFrame. Must pass a parameter reference of the RankingSystemController to the frame
	 * in order to communicate back to the RankingSystemController.
	 * @param controller
	 */
	public UserTestFrame(RankingSystemController controller)
	{
		super("Ranking System - User Test");
		userTestPanel = new UserTestPanel(controller);                   //pass the RankingSystemController down to the panel as well
		setupFrame();
	}
	
	/**
	 *Sets the content pane, size, and makes the frame visible. 
	 */
	private void setupFrame()
	{
		this.setContentPane(userTestPanel);
		this.setBounds(100, 100, 580, 378);
		this.setVisible(true);
	}
	
	/**
	 * 
	 */
	public void incrementProgressMeter()
	{
		userTestPanel.incrementProgressMeter();
	}
}

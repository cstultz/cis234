package nsort.view;

import javax.swing.JFrame;

import nsort.controller.RankingSystemController;

/**
 * The LoginFrame Class is the GUI of the user login for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class LoginFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginPanel basePanel;
	
	/**
	 * Constructor for the LoginFrame. Must pass a parameter reference of the RankingSystemController to the frame
	 * in order to communicate back to the RankingSystemController
	 * 
	 * @param controller a reference to the RankingSystemController
	 */
	public LoginFrame(RankingSystemController controller)
	{
		super("Ranking System - Login"); 
		basePanel = new LoginPanel(controller);                   //pass the RankingSystemController down to the panel as well
		setupFrame();
	}
	
	/**
	 *Sets the content pane, size, and makes the frame visible. 
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setBounds(100, 100, 330, 160);
		this.setVisible(true);
	}
}

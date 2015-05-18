package nsort.view;

import javax.swing.JFrame;

import nsort.controller.RankingSystemController;
/**
 * The AdminTestSetupFrame Class is the GUI Frame of the administrator test setup for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class AdminTestSetupFrame extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminTestSetupPanel basePanel;
	
	/**
	 * Constructor for the AdminTestSetupFrame. Must pass a parameter reference of the RankingSystemController to the frame
	 * in order to communicate back to the RankingSystemController
	 * 
	 * @param controller a reference to the RankingSystemController
	 */
	public AdminTestSetupFrame(RankingSystemController controller)
	{
		super("Ranking System - Admin Test Setup");
		basePanel = new AdminTestSetupPanel(controller);              //pass the RankingSystemController down to the panel as well 
		setupFrame();
	}
	
	/**
	 *Sets the content pane, size, and makes the frame visible. 
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setBounds(100, 100, 784, 564);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

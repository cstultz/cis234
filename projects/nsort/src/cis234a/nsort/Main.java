package cis234a.nsort;

import cis234a.nsort.controller.*;
/**
 * Main class for the Ranking System application following the MVC design pattern with interface implementation for the views.
 * The RankingSystemController is the parent controller for the 4 children controllers (Login, AdminTestSetup, UserTest, and Report). 
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class Main {
	
	private static RankingSystemController controller;
	
	/**
	 * Main starter method for entry point to the Ranking System application.
	 * 
	 * @param args unused at this time.
	 */
	public static void main(String[] args) 
	{
		
		controller = RankingSystemController.INSTANCE;
		
		javax.swing.SwingUtilities.invokeLater(new Runnable()                                        
		{
			public void run() {
				try 
				{
					controller.launchLogin();                                                       
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}

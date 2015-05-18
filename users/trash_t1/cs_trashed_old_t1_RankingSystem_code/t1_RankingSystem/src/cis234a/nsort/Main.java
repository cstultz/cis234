package cis234a.nsort;

import cis234a.nsort.controller.*;
/**
 * Main class for the Ranking System application following the MVC design pattern with interface implementation for the views. 
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/6/2015)
 */
public class Main {
	
	/**
	 * Main starter method for entry point to the Ranking System application.
	 * 
	 * @param args unused at this time.
	 */
	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()                                        
		{
			public void run() {
				try 
				{
					RankingSystemController controller = new RankingSystemController();             
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

package nsort;
import java.awt.EventQueue;
import nsort.controller.RankingSystemController;

/**
 * Main object for the Ranking System application following the MVC design pattern interface implementation. 
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/21/2015)
 */
public class Main {
	
	/**
	 * Main starter method for entry point for the Java Ranking System program
	 * @param args unused at this time.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					RankingSystemController controller = new RankingSystemController();
					controller.start(); 
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}

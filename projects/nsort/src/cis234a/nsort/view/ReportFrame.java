package cis234a.nsort.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cis234a.nsort.controller.*;

/**
 * The ReportFrame Class is the GUI of the user report for the Ranking System.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
@SuppressWarnings("serial")
public class ReportFrame extends JFrame implements ReportView
{
	private ReportPanel reportPanel;
	private ReportController controller;
	
	/**
	 * Constructor for the class.
	 */
	public ReportFrame()
	{
		super("Ranking System - Report"); 
		reportPanel = createReportPanel();
		getContentPane().add(reportPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(false);    
		setLocationRelativeTo(null);
	}
	
	private ReportPanel createReportPanel()
	{
		ReportPanel reportPanel = new ReportPanel();
		//Listeners go here		
		reportPanel.addReportListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				reportPanel.setReportTable((reportPanel.setColumns(controller.getColumnData())), (reportPanel.setData(controller.queryColumnData(reportPanel.setUser(), reportPanel.setTest()))));
			}
			
		});
		reportPanel.addReportListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		reportPanel.addReportListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return reportPanel;
	}
	
	/**
	 * Update the current view to be shown or hidden depending on the state of the frame. True indicates the user is done
	 * with the view and the view will be hidden. False indicates the user is still using the view and the view will be shown. 
	 * 
	 * @param loggedInState true if the user is done with the view; false if not.
	 */
	@Override
	public boolean updateReportFrameState(boolean reportFrameState) {
		setVisible(!reportFrameState);                      
		return reportFrameState;
	}

	/**
	 * Register the given controller with this view.
	 * Methods on the controller are invoked when events in the view occur that could change the model.
	 * 
	 * @param controller  the word list controller to register with this view
	 */
	@Override
	public void registerController(ReportController controller) {
		this.controller = controller;
	}
	//extra methods go here
}

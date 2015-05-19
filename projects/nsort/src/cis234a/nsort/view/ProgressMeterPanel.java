package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 * The LoginPanel Class contains the components for the LoginFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
@SuppressWarnings("serial")
public class ProgressMeterPanel extends JPanel 
{
	private static final Dimension DIM = new Dimension(320,80);
	
	private JProgressBar progressMeter;
	private JLabel progessMeterLabel;
	
	private int currentQuestion;
	private int totalQuestions;
	
	/**
	 * Constructor for the LoginPanel. Must pass a parameter reference of the LoginController to the panel
	 * in order to communicate back to the LoginController.
	 * @param controller
	 */
	public ProgressMeterPanel()
	{
		setupPanel();
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		setPreferredSize(DIM);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{300, 0};
		gridBagLayout.rowHeights = new int[]{14, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		progessMeterLabel = new JLabel("Question " + currentQuestion + " of " + totalQuestions);
		progessMeterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_progessMeterLabel = new GridBagConstraints();
		gbc_progessMeterLabel.insets = new Insets(0, 0, 5, 0);
		gbc_progessMeterLabel.gridx = 0;
		gbc_progessMeterLabel.gridy = 2;
		add(progessMeterLabel, gbc_progessMeterLabel);
		
		progressMeter = new JProgressBar();
		
		GridBagConstraints gbc_progressMeter = new GridBagConstraints();
		gbc_progressMeter.fill = GridBagConstraints.BOTH;
		gbc_progressMeter.gridx = 0;
		gbc_progressMeter.gridy = 1;
		add(progressMeter, gbc_progressMeter);
	}
	
	public void initializeQuestionsCounter(int totalQuestions)
	{
		this.totalQuestions = totalQuestions; 
		currentQuestion = 1;
		progessMeterLabel.setText("Question " + currentQuestion + " of " + this.totalQuestions);

		progressMeter.setValue(currentQuestion);
		progressMeter.setMinimum(currentQuestion);
		progressMeter.setMaximum(totalQuestions);
	}
	
	public void incrementProgressMeter()
	{
		currentQuestion ++;
		progessMeterLabel.setText("Question " + currentQuestion + " of " + totalQuestions);
		progressMeter.setValue(currentQuestion);
		
	}	
	
	public void setSelectedState(boolean progressMeterVisibleState)
	{
		setVisible(progressMeterVisibleState);
	}
}

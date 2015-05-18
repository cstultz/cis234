package nsort.view;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import nsort.controller.RankingSystemController;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * The LoginPanel Class contains the components for the LoginFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class ProgressMeterPanel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RankingSystemController controller;
	
	private JProgressBar progressMeter;
	private JLabel progessMeterLabel;
	
	private int currentQuestion;
	private int totalQuestions;

	/**
	 * ProgressMeterPanel object passing a reference to the RankingSystemController
	 * for use by the RankingSystem
	 * @param controller
	 */
	public ProgressMeterPanel(RankingSystemController controller)
	{
		this.controller = controller;

		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	public void setupLayout()
	{
		
	}
	
	public void setupPanel()
	{
		this.setSize(320,80);
		
        // set border for the panel
		setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Progress Meter Panel"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{300, 0};
		gridBagLayout.rowHeights = new int[]{14, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		initializeQuestionsCounter();
		
		progessMeterLabel = new JLabel("Question " + currentQuestion + " of " + totalQuestions);
		progessMeterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_progessMeterLabel = new GridBagConstraints();
		gbc_progessMeterLabel.anchor = GridBagConstraints.NORTH;
		gbc_progessMeterLabel.insets = new Insets(0, 0, 5, 0);
		gbc_progessMeterLabel.gridx = 0;
		gbc_progessMeterLabel.gridy = 0;
		add(progessMeterLabel, gbc_progessMeterLabel);
		
		progressMeter = new JProgressBar();
		
		progressMeter.setMinimum(currentQuestion);
		progressMeter.setMaximum(totalQuestions);
		progressMeter.setValue(currentQuestion);

		GridBagConstraints gbc_progressMeter = new GridBagConstraints();
		gbc_progressMeter.fill = GridBagConstraints.BOTH;
		gbc_progressMeter.gridx = 0;
		gbc_progressMeter.gridy = 1;
		add(progressMeter, gbc_progressMeter);
	}
	
	public void setupListeners()
	{
		
	}
	
	public void initializeQuestionsCounter()
	{
		//f(N) = N(N-1)/2
		int questionsCount =  controller.setTotalQuestions();
		totalQuestions = (questionsCount * (questionsCount-1))/2; 
		currentQuestion = 1;
	}
	
	public void incrementProgressMeter()
	{
		currentQuestion ++;
		progessMeterLabel.setText("Question " + currentQuestion + " of " + totalQuestions);
		progressMeter.setValue(currentQuestion);
		
	}	
}

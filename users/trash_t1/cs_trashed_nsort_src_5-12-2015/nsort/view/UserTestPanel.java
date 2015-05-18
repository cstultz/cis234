package nsort.view;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nsort.controller.RankingSystemController;

import java.awt.GridBagLayout;
import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UserTestPanelPanel Class is the JPanel that contains the components for use on the UserTest.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class UserTestPanel extends JPanel
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;

	private RankingSystemController controller;
	
	private ProgressMeterPanel progressMeterPanel;
	
	private ButtonGroup radioButtonGroup;
	private JRadioButton leftChoiceRadioButton;
	private JRadioButton IcantDecideRadioButton;
	private JRadioButton rightChoiceRadioButton;
	private JLabel leftItemLabel;
	private JLabel rightItemLabel;
	private final JLabel lblVs;
	private JLabel lblChooseEitherItem;
	
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbc_progressMeterPanel;
	private GridBagConstraints gbc_leftItemLabel;
	private GridBagConstraints gbc_lblVs;
	private GridBagConstraints gbc_rightItemLabel;
	private GridBagConstraints gbc_leftChoice;
	private GridBagConstraints gbc_IcantDecide;
	private GridBagConstraints gbc_rightChoice;
	private GridBagConstraints gbc_lblChooseEitherItem;
	
	
	/**
	 * Constructor for the UserTestPanel. Must pass a parameter reference of the RankingSystemController to the panel
	 * in order to communicate back to the RankingSystemController.
	 * @param controller
	 */
	public UserTestPanel(RankingSystemController controller)
	{
		this.controller = controller;
		
		radioButtonGroup = new ButtonGroup();
		
		progressMeterPanel = new ProgressMeterPanel(controller);
		leftItemLabel = new JLabel("Left Item");
		lblChooseEitherItem = new JLabel("Choose either item or \"I can't decide\"");
		rightItemLabel = new JLabel("Right Item");
		
		leftChoiceRadioButton = new JRadioButton("Choose left");
		IcantDecideRadioButton = new JRadioButton("I can't decide");
		rightChoiceRadioButton = new JRadioButton("Choose right");
		
		lblVs = new JLabel("Vs.");

		gridBagLayout = new GridBagLayout();
		gbc_progressMeterPanel = new GridBagConstraints();
		gbc_leftItemLabel = new GridBagConstraints();
		gbc_lblVs = new GridBagConstraints();
		gbc_rightItemLabel = new GridBagConstraints();
		gbc_leftChoice = new GridBagConstraints();
		gbc_IcantDecide = new GridBagConstraints();
		gbc_rightChoice = new GridBagConstraints();
		gbc_lblChooseEitherItem = new GridBagConstraints();

		
		setupPanel();
		setupLayout();
		addComponents();
		setupListeners();
		
		setupFirstQuestion();
	}
	
	/**
	 * setup JPanel layout
	 */
	public void setupLayout()
	{
		// set border for the panel
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "User Test Panel"));
		
		leftItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rightItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		leftChoiceRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		leftItemLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVs.setFont(new Font("Tahoma", Font.BOLD, 18));
		rightItemLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChooseEitherItem.setFont(new Font("Tahoma", Font.BOLD, 12));

		gridBagLayout.columnWidths = new int[]{178, 57, 40, 0, 39, 55, 181, 0};
		gridBagLayout.rowHeights = new int[]{38, 82, 100, 23, 33, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		gbc_progressMeterPanel.insets = new Insets(0, 0, 5, 0);
		gbc_progressMeterPanel.gridwidth = 7;
		gbc_progressMeterPanel.gridx = 0;
		gbc_progressMeterPanel.gridy = 1;
		
		gbc_leftItemLabel.fill = GridBagConstraints.BOTH;
		gbc_leftItemLabel.insets = new Insets(0, 0, 5, 5);
		gbc_leftItemLabel.gridwidth = 3;
		gbc_leftItemLabel.gridx = 0;
		gbc_leftItemLabel.gridy = 2;
		
		gbc_lblVs.insets = new Insets(0, 0, 5, 5);
		gbc_lblVs.gridx = 3;
		gbc_lblVs.gridy = 2;
		
		
		gbc_rightItemLabel.fill = GridBagConstraints.BOTH;
		gbc_rightItemLabel.insets = new Insets(0, 0, 5, 0);
		gbc_rightItemLabel.gridwidth = 3;
		gbc_rightItemLabel.gridx = 4;
		gbc_rightItemLabel.gridy = 2;
		
		gbc_leftChoice.gridwidth = 2;
		gbc_leftChoice.insets = new Insets(0, 0, 5, 5);
		gbc_leftChoice.gridx = 0;
		gbc_leftChoice.gridy = 3;
		
		gbc_IcantDecide.insets = new Insets(0, 0, 5, 5);
		gbc_IcantDecide.gridwidth = 3;
		gbc_IcantDecide.gridx = 2;
		gbc_IcantDecide.gridy = 3;
		
		gbc_rightChoice.gridwidth = 2;
		gbc_rightChoice.insets = new Insets(0, 0, 5, 0);
		gbc_rightChoice.gridx = 5;
		gbc_rightChoice.gridy = 3;
		
		gbc_lblChooseEitherItem.anchor = GridBagConstraints.NORTH;
		gbc_lblChooseEitherItem.gridwidth = 7;
		gbc_lblChooseEitherItem.gridx = 0;
		gbc_lblChooseEitherItem.gridy = 5;
	}
	
	/**
	 * setup JPanel
	 */
	public void setupPanel()
	{
		this.setSize(580, 374);
	}
	
	/**
	 * setup JPanel components with Grid Bag Layout Constraints
	 */
	public void addComponents()
	{
		add(leftItemLabel, gbc_leftItemLabel);
		add(lblVs, gbc_lblVs);
		add(rightItemLabel, gbc_rightItemLabel);
		add(lblChooseEitherItem, gbc_lblChooseEitherItem);
		
		radioButtonGroup.add(leftChoiceRadioButton);
		radioButtonGroup.add(IcantDecideRadioButton);
		radioButtonGroup.add(rightChoiceRadioButton);

		add(leftChoiceRadioButton, gbc_leftChoice);	
		add(IcantDecideRadioButton, gbc_IcantDecide);
		add(rightChoiceRadioButton, gbc_rightChoice);
		
		/**
		 * check to see if the ProgressMeterPanel should be enabled or disabled based on the last selected state
		 * of the checkbox on the AdminTestSetup panel
		 */
		if (getSelectedState())
		{
			add(progressMeterPanel, gbc_progressMeterPanel);
		}

	}
	
	/**
	 * setup JPanel component listeners
	 */
	public void setupListeners()
	{
		/**
		 * user selects the IcantDecideRadioButton
		 */
		IcantDecideRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//leftChoiceRadioButton selected
				controller.recordAnswer(IcantDecideRadioButton.getText(), leftItemLabel, rightItemLabel);
				radioButtonGroup.clearSelection();
			}
		});

		/**
		 * user selects the rightChoiceRadioButton
		 */
		rightChoiceRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//leftChoiceRadioButton selected
				controller.recordAnswer(rightChoiceRadioButton.getText(), leftItemLabel, rightItemLabel);
				radioButtonGroup.clearSelection();
			}
		});

		/**
		 * user selects the leftChoiceRadioButton
		 */
		leftChoiceRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//leftChoiceRadioButton selected
				controller.recordAnswer(leftChoiceRadioButton.getText(), leftItemLabel, rightItemLabel);
				radioButtonGroup.clearSelection();
			}
		});
	}
	
	/**
	 * send back a call to the controller to update the components in the view to the first question on the test.
	 */
	public void setupFirstQuestion()
	{
		controller.setupFirstQuestion(leftItemLabel, rightItemLabel);
	}
	
	/**
	 * increment the ProgressMeter on the ProgressMeterPanel once the user has made their selection to a question on the test
	 */
	public void incrementProgressMeter()
	{
		progressMeterPanel.incrementProgressMeter();
	}
	
	/**
	 * get the selected state of progressMeter checkbox from the controller to update the view for the user test 
	 * @return true if the checkbox was last checked on the admin JPanel; false if not.
	 */
	public boolean getSelectedState()
	{
		return controller.getProgressMeterSelectedState();
	}
}

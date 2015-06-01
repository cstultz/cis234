package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The UserTestPanel Class contains the components for the UserTestFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/7/2015)
 */
@SuppressWarnings("serial")
public class UserTestPanel extends JPanel 
{
	private static final Dimension DIM = new Dimension(580, 374);
	
	private ProgressMeterPanel progressMeterPanel;
	
	private ImagePanel imagePanelLeft;
	private ImagePanel imagePanelRight;
	
	private JButton leftChoiceButton;
	private JButton iCantDecideButton;
	private JButton rightChoiceButton;
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
	private GridBagConstraints gbc_imagePanelLeft;
	private GridBagConstraints gbc_imagePanelRight;
	
	/**
	 * Constructor for the UserTestPanel. Must pass a parameter reference of the UserTestController to the panel
	 * in order to communicate back to the UserTestController.
	 * @param controller
	 */
	public UserTestPanel()
	{
		imagePanelLeft = new ImagePanel();
		imagePanelRight = new ImagePanel();
		
		progressMeterPanel = new ProgressMeterPanel();
		leftItemLabel = new JLabel("Left Item");
		lblChooseEitherItem = new JLabel("Choose either item or \"I can't decide\"");
		rightItemLabel = new JLabel("Right Item");
		
		leftChoiceButton = new JButton("Choose left");
		iCantDecideButton = new JButton("I can't decide");
		rightChoiceButton = new JButton("Choose right");
		
		lblVs = new JLabel("Vs.");

		gridBagLayout = new GridBagLayout();
		gbc_progressMeterPanel = new GridBagConstraints();
		gbc_progressMeterPanel.anchor = GridBagConstraints.NORTH;
		gbc_progressMeterPanel.insets = new Insets(0, 0, 0, 5);
		gbc_leftItemLabel = new GridBagConstraints();
		gbc_lblVs = new GridBagConstraints();
		gbc_lblVs.gridwidth = 2;
		gbc_rightItemLabel = new GridBagConstraints();
		gbc_leftChoice = new GridBagConstraints();
		gbc_IcantDecide = new GridBagConstraints();
		gbc_IcantDecide.gridwidth = 2;
		gbc_rightChoice = new GridBagConstraints();
		gbc_lblChooseEitherItem = new GridBagConstraints();
		gbc_lblChooseEitherItem.insets = new Insets(0, 0, 5, 0);
		gbc_imagePanelLeft = new GridBagConstraints();
		gbc_imagePanelLeft.insets = new Insets(0, 0, 5, 5);
		gbc_imagePanelRight = new GridBagConstraints();
		gbc_imagePanelRight.insets = new Insets(0, 0, 5, 0);
		gbc_imagePanelLeft.gridheight = 3;
		gbc_imagePanelRight.gridheight = 3;

		setupPanel();
		setupLayout();
		addComponents();
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
		leftChoiceButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		leftItemLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVs.setFont(new Font("Tahoma", Font.BOLD, 18));
		rightItemLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChooseEitherItem.setFont(new Font("Tahoma", Font.BOLD, 12));

		gridBagLayout.columnWidths = new int[]{75, 75, 75, 75, 75, 75, 75, 75};
		gridBagLayout.rowHeights = new int[]{27, 75, 75, 75, 75, 75, 62, 62};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		gbc_progressMeterPanel.gridwidth = 6;
		gbc_progressMeterPanel.gridx = 1;
		gbc_progressMeterPanel.gridy = 7;
		
		gbc_imagePanelLeft.fill = GridBagConstraints.BOTH;
		gbc_imagePanelLeft.gridwidth = 3;
		gbc_imagePanelLeft.gridx = 0;
		gbc_imagePanelLeft.gridy = 1;
		
		gbc_imagePanelRight.fill = GridBagConstraints.BOTH;
		gbc_imagePanelRight.gridwidth = 3;
		gbc_imagePanelRight.gridx = 5;
		gbc_imagePanelRight.gridy = 1;
		
		gbc_leftItemLabel.fill = GridBagConstraints.BOTH;
		gbc_leftItemLabel.insets = new Insets(0, 0, 5, 5);
		gbc_leftItemLabel.gridwidth = 3;
		gbc_leftItemLabel.gridx = 0;
		gbc_leftItemLabel.gridy = 4;
		
		gbc_lblVs.insets = new Insets(0, 0, 5, 5);
		gbc_lblVs.gridx = 3;
		gbc_lblVs.gridy = 4;
		
		
		gbc_rightItemLabel.fill = GridBagConstraints.BOTH;
		gbc_rightItemLabel.insets = new Insets(0, 0, 5, 0);
		gbc_rightItemLabel.gridwidth = 3;
		gbc_rightItemLabel.gridx = 5;
		gbc_rightItemLabel.gridy = 4;
		
		gbc_leftChoice.gridwidth = 3;
		gbc_leftChoice.insets = new Insets(0, 0, 5, 5);
		gbc_leftChoice.gridx = 0;
		gbc_leftChoice.gridy = 5;
		
		gbc_IcantDecide.insets = new Insets(0, 0, 5, 5);
		gbc_IcantDecide.gridx = 3;
		gbc_IcantDecide.gridy = 5;
		
		gbc_rightChoice.gridwidth = 3;
		gbc_rightChoice.insets = new Insets(0, 0, 5, 0);
		gbc_rightChoice.gridx = 5;
		gbc_rightChoice.gridy = 5;
		
		gbc_lblChooseEitherItem.gridwidth = 8;
		gbc_lblChooseEitherItem.gridx = 0;
		gbc_lblChooseEitherItem.gridy = 6;
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		setPreferredSize(new Dimension(644, 588));
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
		
		add(leftChoiceButton, gbc_leftChoice);	
		add(iCantDecideButton, gbc_IcantDecide);
		add(rightChoiceButton, gbc_rightChoice);
		
		add(progressMeterPanel, gbc_progressMeterPanel);
		add(imagePanelLeft, gbc_imagePanelLeft);
		add(imagePanelRight, gbc_imagePanelRight);
	}
	
	/**
	 * increment the ProgressMeter on the ProgressMeterPanel once the user has made their selection to a question on the test
	 */
	public void incrementProgressMeter()
	{
		progressMeterPanel.incrementProgressMeter();
	}
	
	/**
	 * add an action listener to the 'I can't decide' button
	 * 
	 * @param al user clicked the button
	 */
	public void addIcantDecideButtonActionListener(ActionListener al)
	{
		iCantDecideButton.addActionListener(al);
	}

	/**
	 * add an action listener to the right choice button
	 * 
	 * @param al user clicked the button
	 */
	public void addRightChoiceButtonActionListener(ActionListener al)
	{
		rightChoiceButton.addActionListener(al);
	}

	/**
	 * add an action listener to the left choice button
	 * 
	 * @param al user clicked the button
	 */
	public void addLeftChoiceButtonActionListener(ActionListener al)
	{
		leftChoiceButton.addActionListener(al);
	}
	
	/**
	 * set the left item label with the value
	 * 
	 * @param value to set the left item label
	 */
	public void setLeftItemLabel(String value)
	{
		leftItemLabel.setText(value);
	}

	/**
	 * set the right item label with the value
	 * 
	 * @param value to set the right item label
	 */
	public void setRightItemLabel(String value)
	{
		rightItemLabel.setText(value);
	}
	
	/**
	 * set left item label
	 * 
	 * @param the left item label value
	 */
	public void setLeftItemLabelValue(String itemLeftValue)
	{
		leftItemLabel.setText(itemLeftValue);
	}

	/**
	 * set the right item image
	 * 
	 * @param the right item label image
	 */
	public void setRightItemImage(Image itemRightImage)
	{
		imagePanelRight.updateImage(itemRightImage);
	}
	
	/**
	 * set left item image
	 * 
	 * @param the left item label image
	 */
	public void setLeftItemImage(Image itemLeftImage)
	{
		imagePanelLeft.updateImage(itemLeftImage);
	}

	/**
	 * set the right item label
	 * 
	 * @param the right item label value
	 */
	public void setRightItemLabelValue(String itemRightValue)
	{
		rightItemLabel.setText(itemRightValue);
	}

	/**
	 * set the total question that will be on the test
	 * 
	 * @param totalQuestions on the user test
	 */
	public void setTotalQuestions(int totalQuestions)
	{
		progressMeterPanel.initializeQuestionsCounter(totalQuestions);
	}
	
	/**
	 * set the progress meter selected state
	 * 
	 * @param progressMeterSelectedState true if the progress meter panel is to be shown; false if not.
	 */
	public void setProgressMeterSelectedState(boolean progressMeterSelectedState)
	{
		progressMeterPanel.setSelectedState(progressMeterSelectedState);
	}
}

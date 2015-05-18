package nsort.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import nsort.controller.RankingSystemController;
/**
 * The LoginPanel Class contains the components for the LoginFrame.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/25/2015)
 */
public class LoginPanel extends JPanel 
{
	/**
	 * default serialization
	 */
	private static final long serialVersionUID = 1L;
	private RankingSystemController controller;
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	private JButton loginButton;
	private SpringLayout baseLayout;
	
	/**
	 * Constructor for the LoginPanel. Must pass a parameter reference of the RankingSystemController to the panel
	 * in order to communicate back to the RankingSystemController.
	 * @param controller
	 */
	public LoginPanel(RankingSystemController controller)
	{
		this.controller = controller;

		usernameLabel = new JLabel("Enter username:");
		usernameTextField = new JTextField("");
		loginButton = new JButton("Login");
		
		baseLayout = new SpringLayout();

		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * setup JPanel layout
	 */
	public void setupLayout()
	{
        // set border for the panel
		setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));

		baseLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 14, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, usernameLabel, -10, SpringLayout.WEST, usernameTextField);
		baseLayout.putConstraint(SpringLayout.EAST, usernameTextField, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 11, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, usernameTextField, 104, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, loginButton, 96, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, loginButton, -10, SpringLayout.SOUTH, this);
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		this.setSize(320,105);
		this.setLayout(baseLayout);
		
		add(usernameLabel);
		add(usernameTextField);
		add(loginButton);
	}
	
	/**
	 * setup JPanel component listeners
	 */
	public void setupListeners()
	{
		/**
		 * user attempt to login to the Ranking System. Pass the usernameTextField back up to the controller.
		 */
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//LOGIN BUTTON CLICKED
				
				controller.login(usernameTextField.getText());
				usernameTextField.setText("");
			}
		});

		/**
		 * user hits the enter key in the usernameTextField invokes the loginButton click event
		 */
		usernameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				int key = e.getKeyCode();
		        
		        if (key == KeyEvent.VK_ENTER) 
		        {	
		        	//ENTER KEY PRESSED IN USERNAME TEXT BOX
		        	loginButton.doClick();
		        }
			}
		});
	}
}

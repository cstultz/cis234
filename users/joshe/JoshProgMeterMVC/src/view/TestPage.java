package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
	 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.JBarJPanel;
	 
	/**
	 * This program demonstrates how to use JPanel in Swing.
	 * @author www.codejava.net
	 * http://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
	 */
	public class TestPage extends JFrame {
	     
	    private JTextField textField1 = new JTextField(20);
	    private JTextField textField2 = new JTextField(20);
	    private JButton buttonNextQuestion = new JButton("Next Question");
	    
	    private JBarJPanel myProgBar;
	    
	    private Integer progBarValue = 0;
	    
	       
	     public TestPage() {	    	
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        
	        myProgBar = new JBarJPanel (0, 10, true);
	        
	         
	        // create a new panel with GridBagLayout manager
	        JPanel newPanel = new JPanel(new GridBagLayout());
	         
	        GridBagConstraints constraints = new GridBagConstraints();
	        //JE 04/25/15: constraints.anchor = GridBagConstraints.WEST;
	        //JE 04/25/15: 'INSETS' SET THE padding of the components, starting from the top. 
	        constraints.insets = new Insets(10, 5, 10, 5);
	         
	        // add components to the panel
	        
	        ////FIRST COLUMN ////
	        	
	        constraints.gridwidth = 2;
	        constraints.anchor = GridBagConstraints.CENTER;
	        //constraints.fill = GridBagConstraints.HORIZONTAL;
	        constraints.ipady = 12;
	        constraints.gridx = 0;
	        constraints.gridy = 0;	         
	        newPanel.add(myProgBar, constraints);
	        
	        
	        //Need this to reset the gridwidth from 2 above to 1 here, 
	        //or the JFrame will collapse
	        constraints.gridwidth = 1;
	        
	        constraints.fill = GridBagConstraints.HORIZONTAL;
	        constraints.ipady = 150;
	        
	        //SECOND COLUMN
	        //constraints.anchor = GridBagConstraints.LINE_START;
	        
	        
	        constraints.gridx = 0;
	        constraints.gridy = 1;
	        newPanel.add(textField1, constraints);
	         
	         
	        constraints.gridx = 1;
	        constraints.gridy = 1;
	        newPanel.add(textField2, constraints);
	        
	        
	        constraints.ipady = 0;
	        
	        constraints.fill = GridBagConstraints.NONE;
	        constraints.gridx = 0;
	        constraints.gridy = 3;
	        constraints.gridwidth = 2;
	        constraints.anchor = GridBagConstraints.CENTER;
	        newPanel.add(buttonNextQuestion, constraints);
	        
	        //Event Handler to increment the progress bar up.
	        buttonNextQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 
					progBarValue = progBarValue +1;
					myProgBar.setBarValue(progBarValue);
				}
				
			});
	        
	  
	        // set border for the panel
	        newPanel.setBorder(BorderFactory.createTitledBorder(
	                BorderFactory.createEtchedBorder(), "Your Test"));
	         
	        // add the panel to this frame
	        add(newPanel);
	         
	        pack();
	        setLocationRelativeTo(null);
	    }
	     
	    public static void main(String[] args) {
	        // set look and feel to the system look and feel
	        try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	         
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new TestPage().setVisible(true);
	             }
	            
	        });
	        
	    }
	}


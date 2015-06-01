package cis234a.nsort.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
 public class ImagePanel extends JPanel{

    private static final Dimension DIM = new Dimension(250, 250);
    private Image graphic;
    private Image blank;
    private Image noImage;
    private JLabel imageLabel;
    private ClassLoader classLoader;
    private InputStream input;

    public ImagePanel() {
    	
		classLoader = Thread.currentThread().getContextClassLoader();

		try {     
    		input = classLoader.getResourceAsStream("no-image.png");
    		noImage = ImageIO.read(input);
    		input = classLoader.getResourceAsStream("blank.png");
    		blank = ImageIO.read(input);
    		graphic = blank;
		} catch (IOException ex) {

    		JOptionPane.showMessageDialog(null,ex.getMessage(),"Error Message",JOptionPane.WARNING_MESSAGE);
    	}  	
		
    	imageLabel = new JLabel(new ImageIcon(graphic));
    	
		setupLayout();
		setupPanel();
		addComponents();
    }
	
	/**
	 * setup JPanel layout
	 */
	public void setupLayout()
	{
		// set border for the panel
//		setBorder(BorderFactory.createTitledBorder(
//               BorderFactory.createEtchedBorder(), "Image Panel"));
	}
	
	/**
	 * set up JPanel components
	 */
	public void setupPanel()
	{
		setPreferredSize(new Dimension(DIM));
		
	}
	
	/**
	 * add the components of the panel with the GridBagContraints
	 */
	public void addComponents()
	{
		setLayout(null);
		
		add(imageLabel);
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(graphic, 15, 15, 220, 220, null);       
    }
    
    public void setImage(byte[] data)
	{
    	
    	if (data == null)
    	{
    		updateImage(noImage);
    	}
    	else
    	{
	    	graphic = Toolkit.getDefaultToolkit().createImage(data);
	    	updateImage(graphic);
    	}
    	
    	

	}
    
    public void updateItemImageToBlank()
    {
    	updateImage(blank);
    }
    
    public void updateImage(Image graphic)
    {
    	this.graphic = graphic;
    	imageLabel.setIcon(new ImageIcon(this.graphic));
    	setupLayout();
    	paintComponent(getGraphics());    }
}
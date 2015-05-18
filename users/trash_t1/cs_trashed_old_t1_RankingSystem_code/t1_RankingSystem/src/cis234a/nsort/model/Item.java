package cis234a.nsort.model;
/**
 * The Item class represents the items on a NTest. Each item on a test will be 
 * compared in groups of two to every other item on the test. During each compare 
 * the user will choose which item they like best or choose they can't decide 
 * and the item will be awarded a win, loss, or tie during. The results for each 
 * item will be recorded here.
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/21/2015)
 */
public class Item {
	
	private String value;
    private int wins = 0;
    private int losses = 0;
    private int ties = 0;
	
    /**
     * Constructor for the Item class
     */
	public Item() 
	{
		setValue("");  //initialize the Item to an empty string
	}
	
	/**
	 * get the value of the item
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * set the value of the item
	 * 
	 * @param the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
    /**
     * get the wins of the item. 
     * 
     * @return the wins total of the item.
     */
    public int getWins()
    {
        return wins;
    }

    /**
     * Set the wins of the item.
     * 
     * @param the wins of the item.
     */
    public void addWin()
    {
        wins ++;
    }

    /**
     * get the losses of the item. 
     * 
     * @return the losses total of the item.
     */
    public int getLosses()
    {
        return losses;
    }

    /**
     * Set the losses of the item.
     * 
     * @param the losses of the item.
     */
    public void addLoss()
    {
        losses ++;
    }

    /**
     * get the ties of the item. 
     * 
     * @return the ties total of the item.
     */
    public int getTies()
    {
        return ties;
    }

    /**
     * Set the ties of the item.
     * 
     * @param the ties of the item.
     */
    public void addTie()
    {
        ties ++;
    }

    /**
     * Check if an Item is equal to its value
     * 
     * @param the value of the item being checked
     * @return true if they are equal; false if they are not.
     */
    public Boolean equals(String value)
    {
    	if (this.value.equals(value))
    	{
    		return true;
    	}
    	else return false;
    }
    
}
package cis234a.nsort.model;

import java.util.ArrayList;
/**
 * This a ItemList class represents a list of items.
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/11/2015)
 */
public class ItemList
{
    private ArrayList<Item> items;

    /**
     * Constructor for the class.
     */
    public ItemList()
    {
        items = new ArrayList<Item>(); //initialize empty
    }

    /**
     * Add a new item to the nsort.test.
     * 
     * @param item to be added to the nsort.test.
     */
    public void addItem(Item item)
    {
        items.add(item);
    }

    /**
     * Remove an item from the nsort.test based on its value.
     * 
     * @return if the item was removed from the nsort.test.
     * @param value of the item to be removed from the nsort.test.
     */
    public boolean removeItem(String value)
    {
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getValue().equalsIgnoreCase(value))
            {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Get an item in the nsort.test based on its index.
     * 
     * @param index of the item in the nsort.test
     * @return the item in the nsort.test at the index position provided.
     */
    public Item getItem(int index)
    {
        if (index >= 0 && index <= items.size())
        {
        	return items.get(index);
        }
        else return null;
    }

    /**
     * Get an item in the nsort.test based on its value.
     * If it can't find the item with the value it returns null.
     * 
     * @param value of the item in the nsort.test
     * @return the item in the nsort.test with the value provided.
     * @override
     */
    public Item getItem(String value)
    {
        for (Item item : items)
        {
            if (item.getValue().trim().equalsIgnoreCase(value))
            {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Get the size of the nsort.test
     * 
     * @return the size of the nsort.test
     */
    public int getSize()
    {
        return items.size();
    }

	
}
import java.util.HashMap;
import java.util.Set;
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


public class Item
{
    public int weight;
    private String descr;
    private HashMap<String, Item> items;  
   
    public Item(String name) 
    {
        items = new HashMap<>();
    }
    
   
    public Item( int weight)
    {
        this.weight = weight;
    }
    
    public void setItem(String desc,Item item) 
    {
        items.put(desc, item);
    }   
}


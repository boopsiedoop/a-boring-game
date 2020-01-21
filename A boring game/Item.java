import java.util.HashMap;
import java.util.Set;
/**
 * Write a description of class Item here.
 *
 * @author me
 * @version 0
 */


public class Item
{
   private HashMap<String, Item> keys;
   int bigKey = 0;
   public void setItem(String name, Item item) 
    {
        keys.put(name, item);
    }
   
   public int bigKey(){
       if(keys.size() == 5){
           bigKey = 1;
       }
       return bigKey;
    }
     
    
   /* public int weight;
    private String descr;
    private HashMap<String, Item> items = new HashMap<>();  
    
    public void Item( int weight,String desc, Item item)
    {
        this.weight = weight;
        items.put(desc, item);
    }
    
    public void setItem(String desc,Item item) 
    {
        items.put(desc, item);
    } */  
}
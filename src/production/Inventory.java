package production;

import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author george
 */
public class Inventory implements Tick{
    public static HashMap<Integer,Item> database;
    public static HashMap<Integer,Integer> quantity;
    public static boolean initial=true;
    public static ArrayList<Item> Taskadd;
    public static ArrayList<Integer> TaskItemadd;
    public static ArrayList<Item> Taskremove;
    public static ArrayList<Integer> TaskItemremove;
    public static MockFloor Floor=new MockFloor();
    public static SimRandom rand; // method added by @author Tyler Foster to give randomness to quantities
    
    public static location[] loc=new location[MockFloor.getShelves().size()];
    /**
     * 
     * @author haoyang Wei
     */
    
    public static class location{
    	public static HashMap<Integer,Integer> quantity;
        public static Shelf shelf;
    	public location(Shelf shelf,HashMap<Integer,Integer> quantity){
    		this.shelf=shelf;
    		this.quantity=quantity;
    	}
    	public static void addquantity(location loc,int id,int quantity){
    		if(loc.quantity.containsKey(id)){
    			loc.quantity.put(id,loc.quantity.get(id)+quantity);
    		}
    		else{
    			loc.quantity.put(id, quantity);
    		}
    		
    	}
    	public static void removequantity(location loc,int id,int quantity){
    		loc.quantity.put(id,loc.quantity.get(id)-quantity);
    	}
    }
    public Inventory(){
        
    }
    
    /**
     * Inventory class that takes SimRandom variable as a constructor
     * @author Tyler Foster
     */
    public Inventory(SimRandom rand, MockFloor F){
    	Inventory.rand = rand;
    	Inventory.Floor = F;
    	ArrayList<Item> inputItems = obtainItems();
    	ArrayList<Integer> inputQuantities = obtainQuantities(rand);
    	Inventory.initialize(inputItems, inputQuantities);
    }
    
    /**
     * 
     * Extracts the list of items from CatItem's catalog
     * @author Tyler Foster
     */
    public static ArrayList<Item> obtainItems(){
    	ArrayList<Item> myItems = new ArrayList<Item>();
    	for (int k = 0; k < CatItem.catalog.length; k++) {
    		myItems.add(new Item(CatItem.catalog[k].id, CatItem.catalog[k].description));
    	}
    	return myItems;
    }
    
    /**
     * 
     * Creates a list of random quantities for every item of the catalog
     * @author Tyler Foster
     * 
     */
    public static ArrayList<Integer> obtainQuantities(SimRandom rand) {
    	ArrayList<Integer> myQuantities = new ArrayList<Integer>();
    	for (int k = 0; k < CatItem.catalog.length; k++) {
    		myQuantities.add(rand.nextInt(5));
    	}
    	return myQuantities;
    }

    /**
     * initial the database and add the items into database
     * @author haoyang wei
     */
    public static void initialize(ArrayList<Item> item,ArrayList<Integer> quantity){
    	for(int i=0;i<loc.length;i++){
    		loc[i]=new location(MockFloor.getShelves().get(i),new HashMap<Integer,Integer>());
    	}
        database=new HashMap<Integer,Item>();
        Inventory.quantity=new HashMap<Integer,Integer>();
       for(int i=0;i<item.size();i++){
           additems(item.get(i),quantity.get(i));
       }
    }/*
    *add item to the database, if exists we only need to add the quantity,then put the product onto shelf
    *@author haoyang wei
    */
    public static void additems(Item product,int quantity){
        if(database.containsKey(product.get_id_number())){
            Inventory.quantity.put(product.get_id_number(),Inventory.quantity.get(product.get_id_number())+quantity);
            putitemonshelf(product,quantity);
        }
        else{
            database.put(product.get_id_number(),product);
            Inventory.quantity.put(product.get_id_number(),quantity);
            putitemonshelf(product,quantity);
        }
    }
    public static void putitemonshelf(Item product,int quantity){
    	int temp=0;
        while(temp<loc.length){
        	Shelf i= MockFloor.getShelves().get(temp);
            if(i.addItem(product,quantity)){
            	
            	location.addquantity(loc[temp], product.get_id_number(), quantity);
                break;
            }
            else{
            	temp++;
}
            }
        if(temp==loc.length){
        	System.out.println("No shelf can store these items");
        
        }
                                    
    }
    /*remove item from shelf, first check whether we have enough in stock, if not we cannot remove it.
    @author haoyang wei
    */
    public static boolean removeitems(Item product,int quantity){
        if(!database.containsKey(product.get_id_number())){
            System.out.println("We don't have this product in stock");
            return false;
        }
        else{
            if(Inventory.quantity.get(product.get_id_number())<quantity){
                System.out.println("We don't have enough products in stock, please add more products or reduce the number of products to be shippped");
                return false;
            }
            else{
                removeitemfromshelf(product,quantity);
                Inventory.quantity.put(product.get_id_number(),Inventory.quantity.get(product.get_id_number())-quantity);
                if(Inventory.quantity.get(product.get_id_number())==0){
                    database.remove(product.get_id_number());
                    Inventory.quantity.remove(product.get_id_number());
                }
                return true;
            }
        }
    }
    public static void removeitemfromshelf(Item product,int quantity){
    	int temp=0;
        while(temp<loc.length){
        	Shelf i= MockFloor.getShelves().get(temp);
        	if(i.removeItem(product,quantity,false)){
        		location.removequantity(loc[temp],product.get_id_number(),quantity);
                break;
        	}
            else{
                temp++;
            }
        }
    }
    public static Shelf finditem(Item product){
        for(int i=0;i<loc.length;i++){
            if(loc[i].quantity.containsKey(product.get_id_number())){
                return loc[i].shelf;
            }
        }
        System.out.println("We cannot find that in stock");
        return null;
    }
    
    // NOTE: Commented out the for loop, this is not needed for the tick. @author Tyler Foster
    public void tick(int count){
        //for(int i=0;i<count;i++){
            if(initial==true){
            	if(Taskadd!=null){
                initialize(Taskadd,TaskItemadd);
                initial=false;}
            	else{
            		System.out.println("We need initial products in stock");
            	}
            }
            else{
                if(Taskadd!=null){
                    additems(Taskadd.get(0),TaskItemadd.get(0));
                }
                else{
                    System.out.println("We have no products to add into the stock");
                }
                if(Taskremove!=null){
                    removeitems(Taskremove.get(0),TaskItemremove.get(0));
                }
                else{
                    System.out.println("We have no products to be removed this time");
                }
                
            }
        //}
    }
    

    
}

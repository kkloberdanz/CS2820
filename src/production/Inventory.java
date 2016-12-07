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
    public static MockFloor Floor;
    public static ArrayList<Item> stock;
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
    		location.shelf=shelf;
    		location.quantity=quantity;
    	}
    	public static void addquantity(location loc,int id,int quantity){
    		if(location.quantity.containsKey(id)){
    			location.quantity.put(id,location.quantity.get(id)+quantity);
    		}
    		else{
    			location.quantity.put(id, quantity);
    		}
    		
    	}
    	public static void removequantity(location loc,int id,int quantity){
    		location.quantity.put(id,location.quantity.get(id)-quantity);
    	}
    }
    public Inventory(){
        
    }
    
    /**
     * Inventory class that takes SimRandom variable as a constructor
     * @author Tyler Foster
     */
    public Inventory(SimRandom randSource, MockFloor F){
    	Inventory.rand = randSource;
    	Inventory.Floor = F;
    	stock = new ArrayList<Item>();
    	for (int i = 0; i < CatItem.catalog.length; i++) {
    		int times = 1 + rand.nextInt(4);
    		//for (int j = 0; j < times; j++) {
    			Item n = new Item(CatItem.catalog[i].id, CatItem.catalog[i].description);
    			Object[] keyList = (MockFloor.getShelves().keySet().toArray());
    			Object randomKey = keyList[rand.nextInt(keyList.length)];
    			boolean valid = MockFloor.getShelves().get(randomKey).addItem(n, times);
    			System.out.println(times + " " + n.get_name() + "s were added to Shelf " + MockFloor.getShelves().get(randomKey).getID());
    		//}
    	}
    }
    
    /**
     * Restocks the requested item on a random shelf.
     * 
     * @author Tyler Foster
     * @param I
     */
    public static boolean restockItem(Item I) {
    	int times = 1 + rand.nextInt(4);
    	Object[] keyList = (MockFloor.getShelves().keySet().toArray());
    	Object randomKey = keyList[rand.nextInt(keyList.length)];
    	if (MockFloor.getShelves().get(randomKey).beingCarried == false) {
    		if (MockFloor.getShelves().get(randomKey).addItem(I, times)) {
    			System.out.println(times + " " + I.get_name() + "s have been restocked.");
    			return true;
    		}
    	}
    	return false;
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
           putitemonshelf(item.get(i),quantity.get(i));
       }
    }/*
    *add item to the database, if exists we only need to add the quantity,then put the product onto shelf
    *@author haoyang wei
    */

    public static void putitemonshelf(Item product,int quantity){
    	int count=0;
    	ArrayList<Integer> checkedshelf=new ArrayList<>();
        while(count<loc.length){
        	int temp=rand.nextInt(loc.length);
        	while(checkedshelf.contains(temp)){
        		temp=rand.nextInt(loc.length);
        	}
        	Shelf i= MockFloor.getShelves().get(temp);
            if(i.addItem(product,quantity)){
            	location.addquantity(loc[count], product.get_id_number(), quantity);
            	if(database.containsKey(product.get_id_number())){
            	Inventory.quantity.put(product.get_id_number(),Inventory.quantity.get(product.get_id_number())+quantity);
            	}
            	else{
            		database.put(product.get_id_number(),product);
                    Inventory.quantity.put(product.get_id_number(),quantity);
            	}
            	
            	break;
            }
            else{
                checkedshelf.add(temp);
            	count++;
}
            }
        if(count==loc.length){
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
                System.out.println("We don't have enough "+ product.get_name()+ " in stock, please add more products or reduce the number of products to be shippped");
                return false;
            }
            else{
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
            if(location.quantity.containsKey(product.get_id_number())){
                return location.shelf;
            }
        }
        System.out.println("We cannot find that in stock");
        return null;
    }
    
    public void tick(int count){
        putitemonshelf(obtainItems().get(rand.nextInt(obtainItems().size())),rand.nextInt(10));
        removeitems(obtainItems().get(rand.nextInt(obtainItems().size())),1);
        if(MockOrders.finish!=null){
        for(int i=0;i<MockOrders.finish.orderItems.size();i++){
        	Item temp=MockOrders.finish.orderItems.get(i);
        	Inventory.removeitems(temp,1);
        }
    }
    

    
}
}

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
    public static location[] loc;
    public static boolean initial=true;
    public static ArrayList<Item> Taskadd;
    public static ArrayList<Integer> TaskItemadd;
    public static ArrayList<Item> Taskremove;
    public static ArrayList<Integer> TaskItemremove;
    /**
     * 
     * @author haoyang Wei
     */
    
    public static class location{
    	HashMap<Integer,Integer> quantity;
        Shelf shelf;
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
     * initial the database and add the items into database
     * @author haoyang wei
     */
    public void initialize(ArrayList<Item> item,ArrayList<Integer> quantity){
    	loc=new location[shelves.length];
    	for(int i=0;i<loc.length;i++){
    		loc[i]=new location(shelves.get(i),new HashMap<Integer,Integer>());
    	}
        database=new HashMap<Integer,Item>();
        this.quantity=new HashMap<Integer,Integer>();
       for(int i=0;i<item.size;i++){
           additems(item.get(i),quantity.get(i));
       }
    }/*
    *add item to the database, if exists we only need to add the quantity,then put the product onto shelf
    *@author haoyang wei
    */
    public void additems(Item product,int quantity){
        if(database.containsKey(product.get_id_number())){
            this.quantity.put(product.get_id_number(),this.quantity.get(product.get_id_number())+quantity);
            putitemonshelf(product,quantity);
        }
        else{
            database.put(product.get_id_number(),product);
            this.quantity.put(product.get_id_number(),quantity);
            putitemonshelf(product,quantity);
        }
    }
    public void putitemonshelf(Item product,int quantity){
    	int temp=0;
        while(true){
        	Shelf i=shelves.get(temp);
            if(i.addItem(product,quantity)){
            	location.addquantity(loc[temp], product.id, quantity);
                break;
            }
            else{
                System.out.println("the shelf cannot store so many items");
                temp++;
            }
        }
                                    
    }
    /*remove item from shelf, first check whether we have enough in stock, if not we cannot remove it.
    @author haoyang wei
    */
    public boolean removeitems(Item product,int quantity){
        if(!database.containsKey(product.get_id_number())){
            System.out.println("We don't have this product in stock");
            return false;
        }
        else{
            if(this.quantity.get(product.get_id_number())<quantity){
                System.out.println("We don't have enough products in stock, please add more products or reduce the number of products to be shippped");
                return false;
            }
            else{
                removeitemfromshelf(product,quantity);
                this.quantity.put(product.get_id_number(),this.quantity.get(product.get_id_number())-quantity);
                if(this.quantity.get(product.get_id_number())==0){
                    database.remove(product.get_id_number());
                    this.quantity.remove(product.get_id_number());
                }
                return true;
            }
        }
    }
    public void removeitemfromshelf(Item product,int quantity){
    	int temp=0;
        while(true){
        	Shelf i=shelves.get(temp);
        	if(i.removeItem(product,quantity,false)){
        		location.removequantity(loc[temp],product.id,quantity);
                break;
        	}
            else{
                temp++;
            }
        }
    }
    public Shelf finditem(Item product){
        for(int i=0;i<loc.length;i++){
            if(loc[i].quantity.contains(product.id)){
                return loc[i].shelf;
            }
        }
        return null;
    }
    public void tick(int count){
        for(int i=0;i<count;i++){
            if(initial==true){
                initialize(Taskadd,TaskItemadd);
                initial=false;
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
        }
    }
    

    
}

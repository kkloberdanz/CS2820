package production;

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
    /**
     * 
     * @author haoyang Wei
     */
    
    public class location{
    	HashMap<Integer,Integer> quantity;
    	public location(Shelf shelf,HashMap<Integer,Integer> quantity){
    		this.shelf=shelf;
    		this.quantity=quantity
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
    public void initialize(Item[] item,int[] quantity){
    	loc=new location[shelves.length];
    	for(int i=0;i<loc.length;i++){
    		loc[i]=new location(shelves.get(i),new HashMap<Integer,Integer>())
    	}
        database=new HashMap<Integer,Item>();
        this.quantity=new HashMap<Integer,Integer>();
       for(int i=0;i<item.length;i++){
           additems(item[i],quantity[i]);
       }
    }/*
    *add item to the database, if exists we only need to add the quantity,then put the product onto shelf
    *@author haoyang wei
    */
    public void additems(Item product,int quantity){
        if(database.containsKey(product.get_id_number())){
            this.quantity.get(product.get_id_number())+=quantity;
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
                this.quantity.get(product.get_id_number())-=quantity;
                if(this.quantity.get(product.get_id_number())==0){
                    database.remove(product.get_id_number());
                    this.quantity.remove(product.get_id_number);
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
    

    
}

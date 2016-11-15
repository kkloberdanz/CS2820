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
public class Inventory implements Tick {
    Item item;
    int size;
    int weight;
    int quantity;
    HashMap<Integer,Inventory> database;
    Boolean reachcapacity=false;
    /**
     * constructor of Inventory
     * @author haoyang Wei
     */
    public Inventory(){
        this.item=null;
        this.size=0;
        this.weight=0;
        this.quantity=0;
    }
    public Inventory(Item item,int size,int weight,int quantity){
        this.item=item;
        this.size=size;
        this.weight=weight;
        this.quantity=quantity;
    }
    
    public void tick(int count) {
    	// TODO: finish tick
    }
    
    /**
     * initial the database and add the items into database
     * @author haoyang wei
     */
    public void initialize(Item[] item,int[] size,int[] weight,int[] quantity){
        database=new HashMap<Integer,Inventory>();
       for(int i=0;i<item.length;i++){
           Inventory initial=new Inventory(item[i],size[i],weight[i],quantity[i]);
           
           //This does not compile
           //additem(initial);
           //putitemonshelf(initial);
       }
    }/*
    *add item to the database, if exists we only need to add the quantity,then put the product onto shelf
    *@author haoyang wei
    */
    
    
    /* This code does not compile
    public void additem(Inventory product){
        if(database.containsKey(product.item.get_id_number())){
            database.get(product.item.get_id_number()).quantity+=item.quantity;
            putitemonshelf(product.item);
        }
        else{
            database.put(product.item.get_id_number(),product);
            putitemonshelf(product.item);
        }
    }
    public void putitemonshelf(Inventory product){
        addItem(product.item,product.quantity);
                                    
    }
    */
    
    /*remove item from shelf, first check whether we have enough in stock, if not we cannot remove it.
    @author haoyang wei
    */
    public void removeitem(Inventory product){
        if(!database.containsKey(product.item.get_id_number())){
            System.out.println("We don't have this product in stock");
        }
        /*// This does not compile
        else{
            if(database.get(product.item.get_id_number()).quantity<item.quantity){
                System.out.println("We don't have enough products in stock, please add more products or reduce the number of products to be shippped");
            }
            else{
                removeitemfromshelf(product);
                database.get(product.item.get_id_number()).quantity-=item.quantity;
                if(database.get(product.item.get_id_number()).quantity==0){
                    database.remove(product.item.get_id_number());
                }
            }
        }
        */
    }
    public void removeitemfromshelf(Inventory product){
        // This does not compile
    	//removeItem(product.item);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

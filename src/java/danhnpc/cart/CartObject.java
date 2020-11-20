/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author visug
 */
public class CartObject implements Serializable{
    Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String title){
        int quantity = 1;
        if(this.items == null){
            this.items = new HashMap<>();
        }
        if(this.items.containsKey(title)){
            quantity = this.items.get(title) + 1;
        }
        this.items.put(title, quantity);
    }
    
    public void removeItem(String title){
        if(this.items == null){
            return;
        }
        
        if(this.items.containsKey(title)){
            this.items.remove(title);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
    
    public void clearCart(){
        if(this.items == null){
            return;
        }
        this.items = null;
    }
    
}

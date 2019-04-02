package amazin.model;


import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lauramachado on 2019-03-18.
 */
public class ShoppingCart {

    private ArrayList<Item> items;
    private int itemCount;

    public ShoppingCart() {

        this.items = new ArrayList<>();
        itemCount = 0;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public int getItemCount(){
        return this.itemCount;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void updateItemCount(){
        int count = 0;
        for(Item item : items){
            count+=item.getQuantity();
        }

        this.itemCount = count;
    }

    public void incrementItemCount(){
        this.itemCount = this.itemCount + 1;
    }

    public void decrementItemCount(){
        this.itemCount = this.itemCount - 1;
    }

    public Item getItem(Long bookId){
        for(Item i : items){
            if(i.getBook().getId().equals(bookId)) return i;
        }

        return null;
    }

    public double getTotal(){

        double total = 0;

        for(Item item: items){
            total+=(item.getBook().getPrice()*item.getQuantity());
        }

        return total;
    }

    public void removeItem(Long bookId){
        items.remove(this.getItem(bookId));
        updateItemCount();
    }

    public int itemExists(Long bookId) {
        for(int i=0; i < items.size(); i++){
            if(items.get(i).getBook().getId().equals(bookId)){
                return i;
            }
        }

        return -1;
    }



}

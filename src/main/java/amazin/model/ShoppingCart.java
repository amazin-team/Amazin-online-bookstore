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

}

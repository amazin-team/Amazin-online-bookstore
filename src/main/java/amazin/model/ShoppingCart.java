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

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

}

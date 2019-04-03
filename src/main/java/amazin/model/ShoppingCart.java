package amazin.model;


import org.hibernate.annotations.*;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lauramachado on 2019-03-18.
 */
@Entity
@Indexed
@Table(name="carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @Column(name="items")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<Item> items){
        this.items = items;
    }

    public int getItemCount() {
        int count=0;
        for(Item i: items){
            count+= i.getQuantity();
        }

        return count;
    }

    public void addItem(Item item){
        this.items.add(item);
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
        Item item = this.getItem(bookId);
        items.remove(item);
    }

    public boolean itemExists(Long bookId) {
        for(int i=0; i < items.size(); i++){
            if(items.get(i).getBook().getId().equals(bookId)){
                return true;
            }
        }

        return false;
    }



}

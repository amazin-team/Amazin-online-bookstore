package amazin.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lauramachado on 2019-03-18.
 */

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;

    @Column(name="cartItems")
    private ArrayList<HashMap<String,Object>> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public ArrayList<HashMap<String,Object>> getItems() {
        return this.items;
    }

    public void addItem(HashMap<String,Object> item){
        this.items.add(item);
    }




}

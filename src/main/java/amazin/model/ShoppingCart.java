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
    private List<Map<String,Object>> items;

    public ShoppingCart() {
        this.items = new ArrayList<Map<String,Object>>();
    }

    public void addBook(Book book, int amount){
        HashMap<String,Object> item = new HashMap<String,Object>();
        item.put("bookId", book.getId());
        item.put("quantity", amount);

        this.items.add(item);
    }

    public List<Map<String,Object>> getBooks() {
        return this.items;
    }


}

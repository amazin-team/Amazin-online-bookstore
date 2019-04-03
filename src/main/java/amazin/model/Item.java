package amazin.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

/**
 * Created by lauramachado on 2019-03-19.
 */

@Entity
@Indexed
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Book book;

    @Column(name="quantity")
    private int quantity;

    public Item(){}

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public Book getBook(){
        return this.book;
    }

    public void setBook(Book b){
        this.book = b;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int amount){
        this.quantity = amount;
    }

    public double getSubtotal(){
        return this.book.getPrice()*this.quantity;
    }

    public void decrement(){
        this.quantity = this.quantity-1;
    }

    public void increment(){
        this.quantity = this.quantity+1;
    }
}


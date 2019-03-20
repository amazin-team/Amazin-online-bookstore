package amazin.model;

import javax.persistence.*;

/**
 * Created by lauramachado on 2019-03-19.
 */

public class Item {

    private Book book;
    private int quantity;

    public Item(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
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
}


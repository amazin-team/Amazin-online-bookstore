package amazin.service;

import amazin.model.Book;
import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.repository.BookRepository;
//import amazin.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lauramachado on 2019-03-18.
 */
@Service
public class ShoppingCartService {

    @Autowired
    BookRepository bookRepository;

    public ShoppingCart createCart(){
        ShoppingCart cart = new ShoppingCart();

        return cart;
    }

    public void addBook(ShoppingCart cart, long bookId){
        int amount = 1;
        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isPresent()){
            Item item = new Item(book.get(), amount);
            cart.addItem(item);
        }
    }

}

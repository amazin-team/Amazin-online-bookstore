package amazin.service;

import amazin.model.Book;
import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.model.User;
import amazin.repository.BookRepository;
//import amazin.repository.ShoppingCartRepository;
import amazin.repository.ItemRepository;
import amazin.repository.ShoppingCartRepository;
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

    @Autowired
    ShoppingCartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    public ShoppingCart createCart(User u){
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(u);
        cartRepository.save(cart);
        return cart;
    }

    public void addItem(ShoppingCart cart, long bookId){
        if(cart.itemExists(bookId)){
            Item i = cart.getItem(bookId);
            incrementItem(i);
        }else{
            Item item = createItem(bookId);
            if(item != null){
                cart.addItem(item);
            }
        }

        cartRepository.save(cart);

    }

    public void removeItem(ShoppingCart cart, long bookId){
            cart.removeItem(bookId);
            cartRepository.save(cart);
    }

    public Item createItem(long bookId){
        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isPresent()){
            int amount = 1;
            Item i = new Item();
            i.setBook(book.get());
            i.setQuantity(amount);
            itemRepository.save(i);

            return i;
        }

        return null;
    }

    public void incrementItem(Item i){
        if((i.getQuantity()+1) <= i.getBook().getInventory()){
            i.increment();
            itemRepository.save(i);
        }
    }

    public void decrementItem(Item i){
        if(i.getQuantity() > 1){
            i.decrement();
            itemRepository.save(i);
        }
    }

}

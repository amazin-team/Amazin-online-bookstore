package amazin.service;

import amazin.model.Book;
import amazin.model.ShoppingCart;
import amazin.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

/**
 * Created by lauramachado on 2019-03-18.
 */
public class ShoppingCartService {

    private final ShoppingCartRepository cartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository repository) {

        this.cartRepository = repository;
    }

    public ShoppingCart createCart(){
        ShoppingCart cart = new ShoppingCart();
        cartRepository.save(cart);

        return cart;
    }

    public void addBook(long cartId, Book book){
        int amount = 1;

        Optional<ShoppingCart> cartToUpdate = cartRepository.findById(cartId);
        ShoppingCart cart;

        if(!cartToUpdate.isPresent()){
            cart = this.createCart();
        } else {
            cart = cartToUpdate.get();
        }

        HashMap<String,Object> item = new HashMap<String,Object>();
        item.put("bookId", book.getId());
        item.put("quantity", amount);

        cart.addItem(item);
        cartRepository.save(cart);
    }

}

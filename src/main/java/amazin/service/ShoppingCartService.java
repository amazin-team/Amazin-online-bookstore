package amazin.service;

import amazin.model.Book;
import amazin.model.ShoppingCart;
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
    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart createCart(){
        ShoppingCart cart = new ShoppingCart();
        shoppingCartRepository.save(cart);

        return cart;
    }

    public void addBook(long bookId){
        int amount = 1;

        Iterable<ShoppingCart> cartToUpdate = shoppingCartRepository.findAll();

        if(cartToUpdate.spliterator().)
        ShoppingCart cart;

//        if(!cartToUpdate.isPresent()){
//            cart = this.createCart();
//        } else {
//            cart = cartToUpdate.get();
//        }

        HashMap<String,Object> item = new HashMap<String,Object>();
        item.put("bookId", bookId);
        item.put("quantity", amount);

        //cart.addItem(item);
        //shoppingCartRepository.save(cart);
    }

    public double calculateTotal(ArrayList<HashMap<String,Object>> items){

        double total = 0;

        for(Map item: items){
            Book b = (Book) item.get("book");
            int quantity = (int) item.get("quantity");
            total+=(b.getPrice()*quantity);
        }

        return total;
    }

}

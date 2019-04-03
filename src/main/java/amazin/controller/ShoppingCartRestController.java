package amazin.controller;

import amazin.model.Book;
import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.model.User;
import amazin.repository.BookRepository;
import amazin.repository.ShoppingCartRepository;
import amazin.repository.UserRepository;
import amazin.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import java.util.Optional;

/**
 * Created by lauramachado on 2019-04-01.
 */

@RestController
@SessionAttributes("cart")
public class ShoppingCartRestController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;


    @PostMapping("/cart/decrement/{bookId}")
    public JSONObject decrementItem(@PathVariable("bookId") Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername());
        ShoppingCart cart = cartRepository.findByUser(user);

        Optional<Book> book = bookRepository.findById(bookId);

        Item item = null;
        if(book.isPresent()){
            item = cart.getItem(bookId);
            shoppingCartService.decrementItem(item);
        }

        JSONObject obj = new JSONObject();
        obj.put("item", item);
        obj.put("total", cart.getTotal());
        obj.put("itemCount", cart.getItemCount());

        return obj;
    }

    @PostMapping("/cart/increment/{bookId}")
    public JSONObject incrementItem(@PathVariable("bookId") Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername());
        ShoppingCart cart = cartRepository.findByUser(user);

        Optional<Book> book = bookRepository.findById(bookId);

        Item item = null;
        if(book.isPresent()){
            item = cart.getItem(bookId);
            shoppingCartService.incrementItem(item);
        }

        JSONObject obj = new JSONObject();
        obj.put("item", item);
        obj.put("total", cart.getTotal());
        obj.put("itemCount", cart.getItemCount());

        return obj;
    }

    @PostMapping("/cart/addToCart/{bookId}")
    public JSONObject addToCart(@PathVariable("bookId") Long bookId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername());
        ShoppingCart cart = cartRepository.findByUser(user);

        if(cart == null){
            cart = shoppingCartService.createCart(user);
        }

        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isPresent()){
            shoppingCartService.addItem(cart, bookId);
        }

        JSONObject obj = new JSONObject();
        obj.put("itemCount", cart.getItemCount());

        return obj;
    }
}

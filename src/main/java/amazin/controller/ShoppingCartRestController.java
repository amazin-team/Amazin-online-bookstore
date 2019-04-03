package amazin.controller;

import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.model.User;
import amazin.repository.ShoppingCartRepository;
import amazin.repository.UserRepository;
import amazin.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

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



    @PostMapping("/cart/decrement/{bookId}")
    public JSONObject decrementItem(@PathVariable("bookId") Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername());
        ShoppingCart cart = cartRepository.findByUser(user);

        Item item = cart.getItem(bookId);
        shoppingCartService.decrementItem(cart, item);

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

        Item item = cart.getItem(bookId);
        shoppingCartService.incrementItem(cart, item);

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

        shoppingCartService.addItem(cart, bookId);

        JSONObject obj = new JSONObject();
        obj.put("itemCount", cart.getItemCount());

        return obj;
    }
}

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.*;

/**
 * Created by lauramachado on 2019-03-18.
 */
@Controller
@SessionAttributes("cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    public static final String VIEW_SHOPPING_CART = "shopping-cart";

    @GetMapping("/cart/delete/{bookId}")
    public String deleteCartItem(@PathVariable("bookId") Long bookId, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername());
        ShoppingCart cart = cartRepository.findByUser(user);
        shoppingCartService.removeItem(cart, bookId);


        model.addAttribute("cart", cart);
        return VIEW_SHOPPING_CART;
    }

    @GetMapping("/cart")
    public String viewCart(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername());
        ShoppingCart cart = cartRepository.findByUser(user);

        model.addAttribute("cart", cart);
        return VIEW_SHOPPING_CART;
    }

}

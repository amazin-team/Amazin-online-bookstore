package amazin.controller;

import amazin.model.Book;
import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.repository.BookRepository;
import amazin.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Array;
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
    BookRepository bookRepository;

    public static final String VIEW_SHOPPING_CART = "shopping-cart";

    @PostMapping("/updateCartItem")
    public void updateCartItem(@ModelAttribute @Valid Book book, Model model) {

    }


    @PostMapping("/removeItemFromCart")
    public void deleteCartItem(){}

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model){
        ArrayList<Item> items = new ArrayList<>();
        int itemCount = 0;
        if (session.getAttribute("cart") != null) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            items = cart.getItems();
            session.setAttribute("cart", cart);
        }

        double total = shoppingCartService.calculateTotal(items);
        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return VIEW_SHOPPING_CART;
    }

}

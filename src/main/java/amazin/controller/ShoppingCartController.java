package amazin.controller;

import amazin.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

/**
 * Created by lauramachado on 2019-03-18.
 */
@Controller
@SessionAttributes("cart")
public class ShoppingCartController {

    @PostMapping("/addToCart")
    public void addToCart(@ModelAttribute @Valid Book book,  Model model){

    }

    @PostMapping("/updateCartItem")
    public void updateCartItem(@ModelAttribute @Valid Book book, Model model) {

    }


    @PostMapping("/removeItemFromCart")
    public void 

    @GetMapping("/cart")
    public void viewCart(Model model){

    }
}

package amazin.controller;

import amazin.model.Book;
import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by lauramachado on 2019-04-01.
 */

@RestController
@SessionAttributes("cart")
public class ShoppingCartRestController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/cart/decrement/{bookId}")
    public Item decrementItem(@PathVariable("bookId") Long bookId, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Item item = cart.getItem(bookId);

        if(item.getQuantity() > 1){
            item.decrement();
        }

        return item;
    }

    @PostMapping("/cart/increment/{bookId}")
    public Item incrementItem(@PathVariable("bookId") Long bookId, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Item item = cart.getItem(bookId);

        item.increment();

        return item;
    }

    @RequestMapping("/cart/addToCart/{bookId}")
    public ShoppingCart addToCart(@PathVariable("bookId") Long bookId, HttpSession session){
        ShoppingCart cart;

        if (session.getAttribute("cart") == null) {
            cart = shoppingCartService.createCart();
            shoppingCartService.addBook(cart, bookId);
            session.setAttribute("cart", cart);
        } else {
            cart = (ShoppingCart) session.getAttribute("cart");

            int index = shoppingCartService.itemExists(cart, bookId);

            if (index == -1) {
                shoppingCartService.addBook(cart, bookId);
            } else {
                Item i = cart.getItems().get(index);
                shoppingCartService.incrementItem(i, 1);

            }

            cart.updateItemCount();
            session.setAttribute("cart", cart);
        }

        return cart;
    }
}

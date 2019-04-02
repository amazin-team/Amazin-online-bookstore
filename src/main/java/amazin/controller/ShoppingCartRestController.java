package amazin.controller;

import amazin.model.Book;
import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

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
    public JSONObject decrementItem(@PathVariable("bookId") Long bookId, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Item item = cart.getItem(bookId);

        if(item.getQuantity() > 1){
            item.decrement();
            cart.decrementItemCount();
        }

        JSONObject obj = new JSONObject();
        obj.put("item", item);
        obj.put("total", cart.getTotal());
        obj.put("itemCount", cart.getItemCount());

        return obj;
    }

    @PostMapping("/cart/increment/{bookId}")
    public JSONObject incrementItem(@PathVariable("bookId") Long bookId, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Item item = cart.getItem(bookId);

        item.increment();
        cart.incrementItemCount();

        JSONObject obj = new JSONObject();
        obj.put("item", item);
        obj.put("total", cart.getTotal());
        obj.put("itemCount", cart.getItemCount());

        return obj;
    }

    @RequestMapping("/cart/addToCart/{bookId}")
    public ShoppingCart addToCart(@PathVariable("bookId") Long bookId, HttpSession session){
        ShoppingCart cart;

        if (session.getAttribute("cart") == null) {
            cart = shoppingCartService.createCart();
            shoppingCartService.addBook(cart, bookId);
        } else {
            cart = (ShoppingCart) session.getAttribute("cart");

            int index = cart.itemExists(bookId);

            if (index == -1) {
                shoppingCartService.addBook(cart, bookId);
            } else {
                Item i = cart.getItems().get(index);
                i.increment();
            }

        }

        session.setAttribute("cart", cart);
        cart.incrementItemCount();
        return cart;
    }
}

package amazin.controller;

import amazin.model.Book;
import amazin.model.ShoppingCart;
import amazin.repository.BookRepository;
import amazin.repository.ShoppingCartRepository;
import amazin.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    BookRepository bookRepository;

    public static final String VIEW_SHOPPING_CART = "shopping-cart";


    @PostMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long bookId,  Model model){
        int amount = 1;
        shoppingCartService.addBook(bookId);

        return "index";

    }

    @PostMapping("/updateCartItem")
    public void updateCartItem(@ModelAttribute @Valid Book book, Model model) {

    }


    @PostMapping("/removeItemFromCart")
    public void deleteCartItem(){}

    @GetMapping("/cart")
    public String viewCart(Model model){
//        Optional<ShoppingCart> c = cartRepository.findById(cartId);
//        List<Map<String, Object>> items = null;
//
//        //ShoppingCart cart = cartRepository.findByUserId();
//
//        if(c.isPresent()){
//            ShoppingCart cart = c.get();
//            items = cart.getItems();
//        }
//
//        model.addAttribute("items", items);

        ArrayList<HashMap<String,Object>> items = new ArrayList<>();

        for(Book b: bookRepository.findAll()){
            HashMap<String,Object> item = new HashMap<>();
            item.put("book", b);
            item.put("quantity", 2);
            item.put("subtotal", 2*b.getPrice());
            items.add(item);
        }

        double total = shoppingCartService.calculateTotal(items);

        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return VIEW_SHOPPING_CART;
    }

}

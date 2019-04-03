package amazin.controller;

import amazin.model.User;
import amazin.repository.UserRepository;
import amazin.service.SecurityServiceImpl;
import amazin.model.ShoppingCart;
import amazin.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import amazin.repository.BookRepository;

@Controller
public class ApplicationController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityServiceImpl securityService;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public static final String VIEW_ADMIN = "admin";


    @GetMapping("/")
    public String index(Model model) {
        User currentUser = userRepository.findByEmail(securityService.findLoggedInEmail());
        ShoppingCart userCart = shoppingCartRepository.findByUser(currentUser);

        model.addAttribute(BookController.MODEL_ATTRIBUTE_BOOK, bookRepository.findAll());
        model.addAttribute(UserController.MODEL_ATTRIBUTE_USER, currentUser);
        model.addAttribute("cart", userCart);

        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute(BookController.MODEL_ATTRIBUTE_BOOK, bookRepository.findAll());
        return "admin";
    }
}

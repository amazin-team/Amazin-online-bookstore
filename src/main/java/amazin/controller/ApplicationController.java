package amazin.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import amazin.service.BookService;
import amazin.model.User;
import amazin.repository.UserRepository;
import amazin.service.SecurityServiceImpl;
import amazin.model.ShoppingCart;
import amazin.repository.ShoppingCartRepository;
import amazin.model.Book;

@Controller
public class ApplicationController {

    @Autowired
    BookService bookService;
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

        model.addAttribute(BookController.MODEL_ATTRIBUTE_BOOK, bookService.getAll());
        model.addAttribute(UserController.MODEL_ATTRIBUTE_USER, currentUser);
        model.addAttribute("cart", userCart);
        Set<Book> recommendedBooks = bookService.getAllRecommendedBooks(currentUser);

        if (recommendedBooks.size() > 0)
            model.addAttribute(BookController.MODEL_ATTRIBUTE_RECOMMENDATIONS,
                               recommendedBooks);

        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute(BookController.MODEL_ATTRIBUTE_BOOK, bookService.getAll());
        return "admin";
    }
}

package amazin.controller;

import amazin.model.ShoppingCart;
import amazin.model.User;
import amazin.repository.ShoppingCartRepository;
import amazin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import amazin.repository.BookRepository;

import java.util.Iterator;
import java.util.Optional;

@Controller
public class ApplicationController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());

        model.addAttribute("cart", shoppingCartRepository.findByUser(user));
        model.addAttribute(BookController.MODEL_ATTRIBUTE_BOOK, bookRepository.findAll());
        return "index";
    }
}

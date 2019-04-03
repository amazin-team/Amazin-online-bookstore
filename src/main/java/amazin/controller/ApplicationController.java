package amazin.controller;

import amazin.model.User;
import amazin.repository.UserRepository;
import amazin.service.SecurityServiceImpl;
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

    @GetMapping("/")
    public String index(Model model) {
        User currentUser = userRepository.findByEmail(securityService.findLoggedInEmail());

        model.addAttribute(BookController.MODEL_ATTRIBUTE_BOOK, bookRepository.findAll());
        model.addAttribute(UserController.MODEL_ATTRIBUTE_USER, currentUser);

        return "index";
    }
}

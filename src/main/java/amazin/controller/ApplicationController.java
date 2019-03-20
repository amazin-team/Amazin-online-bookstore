package amazin.controller;

import amazin.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(BookController.MODEL_ATTRIBUTE_BOOK, bookRepository.findAll());
        return "index";
    }
}

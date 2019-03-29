package amazin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import amazin.repository.BookRepository;

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

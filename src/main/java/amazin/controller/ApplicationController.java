package amazin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import amazin.repository.BookRepository;

@Controller
public class ApplicationController {

    @Autowired
    BookRepository repository;

    @GetMapping({ "/" })
    public String index(Model model) {
        model.addAttribute("books", repository.findAll());
        return "index";
    }

}

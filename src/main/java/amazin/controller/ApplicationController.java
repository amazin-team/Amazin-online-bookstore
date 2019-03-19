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

    @GetMapping({ "/" })
    public String index(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @GetMapping({"/login"})
    public String login(){
        return "login";
    }

    @GetMapping({"/register"})
         public String register(){
        return "registration";
    }

    @GetMapping({"/admin/register"})
    public String registerAdmin(){
        return "registration-admin";
    }

    @GetMapping({"/forgot-password"})
    public String forgotPassword(){
        return "forgot-password";
    }

}

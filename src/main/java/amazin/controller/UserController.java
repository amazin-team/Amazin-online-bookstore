package amazin.controller;

import amazin.model.Book;
import amazin.model.User;
import amazin.service.SecurityService;
import amazin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "registration";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User userForm, BindingResult result) {
        /** TODO */

        return "redirect:/";
    }

    @GetMapping("/admin/register")
    public String registerAdmin(){
        return "registration-admin";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(@Valid @ModelAttribute User userForm, BindingResult result) {
        /** TODO has extra token as opposed to normal register */

        return "redirect:/";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(){
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@Valid @ModelAttribute User userForm, BindingResult result) {
        /** TODO */

        return "redirect:/";
    }
}

package amazin.controller;

import amazin.model.Book;
import amazin.model.User;
import amazin.service.SecurityService;
import amazin.service.SecurityServiceImpl;
import amazin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User userForm, BindingResult result) {
        /** TODO */
        logger.debug(userForm.getFirstName());
        logger.debug(userForm.getLastName());
        logger.debug(userForm.getEmail());
        logger.debug(userForm.getPassword());
        logger.debug(userForm.getPasswordConfirmation());

        return "redirect:/admin/register";
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

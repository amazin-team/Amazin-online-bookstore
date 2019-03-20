package amazin.controller;

import amazin.model.User;
import amazin.service.SecurityService;
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
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Invalid username or password.");

        if (logout != null)
            model.addAttribute("message", "Successfully logged out.");

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        /** TODO properly set errors back to user from validation, validate that password = password confirmation */

        userService.save(userForm);
        securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirmation());

        return "redirect:/";
    }

    @GetMapping("/admin/register")
    public String registerAdmin(Model model){
        model.addAttribute("user", new User());

        return "registration-admin";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(@Valid @ModelAttribute User userForm, BindingResult result, Model model) {
        /** TODO has extra token as opposed to normal register */

        return "redirect:/";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@Valid @ModelAttribute User userForm, BindingResult result, Model model) {
        /** TODO */

        return "redirect:/";
    }
}

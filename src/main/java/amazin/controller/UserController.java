package amazin.controller;

import amazin.model.User;
import amazin.service.SecurityService;
import amazin.service.UserService;
import amazin.validator.UserValidator;
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
    @Autowired
    private UserValidator userValidator;

    public static final String VIEW_LOGIN = "login";
    public static final String VIEW_REGISTER = "registration";
    public static final String VIEW_REGISTER_ADMIN = "registration-admin";
    public static final String VIEW_FORGOT_PASSWORD = "forgot-password";

    public static final String REQUEST_MAPPING_INDEX = "/";

    public static final String MODEL_ATTRIBUTE_USER = "user";
    public static final String MODEL_ATTRIBUTE_ERROR = "error";
    public static final String MODEL_ATTRIBUTE_MESSAGE = "message";

    public static final String ERROR_LOGIN_INVALID = "Invalid username or password.";
    public static final String MESSAGE_LOGOUT_SUCCESS = "Successfully logged out.";

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute(MODEL_ATTRIBUTE_ERROR, ERROR_LOGIN_INVALID);

        if (logout != null)
            model.addAttribute(MODEL_ATTRIBUTE_MESSAGE, MESSAGE_LOGOUT_SUCCESS);

        return VIEW_LOGIN;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute(MODEL_ATTRIBUTE_USER, new User());

        return VIEW_REGISTER;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User userForm, BindingResult result, Model model) {
        userValidator.validate(userForm, result);
        if (result.hasErrors()) {
            return VIEW_REGISTER;
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirmation());

        return getRedirectViewPath(REQUEST_MAPPING_INDEX);
    }

    @GetMapping("/admin/register")
    public String registerAdmin(Model model){
        model.addAttribute(MODEL_ATTRIBUTE_USER, new User());

        return VIEW_REGISTER_ADMIN;
    }

    @PostMapping("/admin/register")
    public String registerAdmin(@Valid @ModelAttribute User userForm, BindingResult result, Model model) {
        /** TODO has extra token as opposed to normal register and needs to set ROLE_ADMIN role in addition to ROLE_USER */

        return getRedirectViewPath(REQUEST_MAPPING_INDEX);
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return VIEW_FORGOT_PASSWORD;
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@Valid @ModelAttribute User userForm, BindingResult result, Model model) {
        /** TODO */

        return getRedirectViewPath(REQUEST_MAPPING_INDEX);
    }

    private String getRedirectViewPath(String requestMapping) {
        return "redirect:" + requestMapping;
    }
}

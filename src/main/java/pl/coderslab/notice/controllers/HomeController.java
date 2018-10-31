package pl.coderslab.notice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.notice.entity.User;
import pl.coderslab.notice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }


    /* layout */

    @GetMapping("/layout")
    public String home() {
        return "layout";
    }


    /* Main */

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    /* add an admin */

    @GetMapping("/addAdmin")
    public String showFormAdmin(Model model) {
        model.addAttribute("user", new User());
        return "/addAdmin";
    }

    @PostMapping("/addAdmin")

    public String perform(@ModelAttribute @Valid User user, String role, BindingResult result) {
        if (result.hasErrors()) {
            return "/addAdmin";
        }
        userService.saveUser(user, "ROLE_ADMIN");
        return "redirect:/login";

    }

    /* add User */

    @GetMapping("/addUser")

    public String showFormUser(Model model) {
        model.addAttribute("user", new User());
        return "/addUser";
    }

    @PostMapping("/addUser")

    public String perform(@ModelAttribute @Valid User user, BindingResult bindingResult, String role) {
        if (bindingResult.hasErrors()) {
            return "user/addUser";
        }
        userService.saveUser(user, "ROLE_USER");
        return "redirect:/login";

    }


    /* login */

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /* check role on login */

    @GetMapping("/checkrole")

    public String loginPage(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.isUserInRole("ADMIN")) {
            return "redirect:/welcomeAd";
        } else if (httpServletRequest.isUserInRole("USER")) {
            return "redirect:/welcome";
        }
        return "redirect:/";
    }


    /* logout */

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }



    /* welcome admin */

    @GetMapping("/welcomeAd")
    public String welcomeAdmin() {
        return "admin/welcomeAdmin";
    }

    /* welcome user */

    @GetMapping("/welcome")
    public String welcomeUser() {
        return "user/welcome";
    }

}

package pl.coderslab.notice.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index() {
        return "layoutAdmin";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/checkrole")

    public String loginPage(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.isUserInRole("ADMIN")) {
            return "redirect:/welcomeAd";
        } else if (httpServletRequest.isUserInRole("USER")) {
            return "redirect:/welcome";
        }
        return "redirect:/";
    }


    @GetMapping("/welcome")
    public String welcomeUser() {
        return "user/welcome";
    }

    @GetMapping("/welcomeAd")
    public String welcomeAdmin() {
        return "admin/welcomeAdmin";
    }
}

package pl.coderslab.notice.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.notice.entity.User;
import pl.coderslab.notice.repository.UserRepository;
import pl.coderslab.notice.service.CurrentUser;
import pl.coderslab.notice.service.UserService;

import javax.validation.Valid;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    //addadmin

    @GetMapping("/add")
    public String showFormUser(Model model) {
        model.addAttribute("users", new User());
        return "admin/addAdmin";
    }

    @PostMapping("/add")

    public String perform(@ModelAttribute @Valid User user, String role, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addAdmin";
        }
        userService.saveUser(user, "ROLE_ADMIN");
        return "redirect:/login";

    }

    //redirectpage

    @GetMapping("/welcomeAd")
    public String redirectAdmin() {
        return "admin/welcomeAdmin";
    }


//showAdmin


    @GetMapping("/showAdmin")

    public String showUser(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        model.addAttribute("user", entityUser);
        return "admin/showAdmin";

    }

    //updateAdmin

    @GetMapping("/update")
    public String showFormUser(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        model.addAttribute("user", user);
        return "admin/updateAdmin";
    }

    @PostMapping("/update")
    public String performUpdate(@ModelAttribute @Valid User user,  BindingResult result) {
        if (result.hasErrors()) {
            return "admin/updateAdmin";
        }
        userRepository.save(user);

        return "redirect:/welcomeAd";
    }



    //deleteAdmin
    @GetMapping("/delete")
    public String delete( @AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        userRepository.delete(user);
        return "redirect:/";
    }

}

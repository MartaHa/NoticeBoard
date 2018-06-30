package pl.coderslab.notice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.notice.entity.User;
import pl.coderslab.notice.repository.UserRepository;
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
    @GetMapping("/addAnAdmin")
    public String showFormUser(Model model) {
        model.addAttribute("users", new User());
        return "admin/addAdmin";
    }

    @PostMapping("/addAnAdmin")

    public String perform(@ModelAttribute @Valid User user, String role, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addAdmin";
        }
        userService.saveUser(user, "ROLE_ADMIN");
        return "redirect:/login";

    }

    //redirectpage

    @GetMapping("/welcomeAd")
    public String redirectAdmin(){
        return "admin/welcomeAdmin";
    }

}

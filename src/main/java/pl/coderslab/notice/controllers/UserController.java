package pl.coderslab.notice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.notice.entity.Notice;
import pl.coderslab.notice.entity.User;
import pl.coderslab.notice.repository.NoticeRepository;
import pl.coderslab.notice.repository.UserRepository;
import pl.coderslab.notice.service.CurrentUser;
import pl.coderslab.notice.service.UserService;

import javax.validation.Valid;
import javax.validation.Validator;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    Validator validator;

    private final UserRepository userRepository;
    private final UserService userService;


    public UserController(UserRepository userRepository, UserService userService, NoticeRepository noticeRepository) {
        this.userRepository = userRepository;
        this.userService = userService;

    }

    //addUser
    @GetMapping("/add")
    public String showFormUser(Model model) {
        model.addAttribute("users", new User());
        return "user/addUser";
    }

    @PostMapping("/add")

    public String perform(@ModelAttribute @Valid User user, BindingResult bindingResult, String role) {
        if (bindingResult.hasErrors()) {
            return "user/addUser";
        }
        userService.saveUser(user, "ROLE_USER");
        return "redirect:/login";

    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "this is user id " + entityUser.getId();
    }


    //updateUser
    @GetMapping("/update")
    public String showFormUser(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        long userId = user.getId();
        userRepository.findById(userId).ifPresent(o -> model.addAttribute("user", o));
        return "user/updateUser";
    }

    @PostMapping("/update")
    public String performUpdate(@ModelAttribute User user) {
        userRepository.save(user);
        ;
        return "/welcome";
    }


    @GetMapping("/showUser")

    public String showUser(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        model.addAttribute("user", entityUser);
        return "/user/showUser";

    }


    //deleteUser
    @GetMapping("/delete")
    public String delete(@AuthenticationPrincipal CurrentUser customUser) {
        User thisUser = customUser.getUser();
        long userId = thisUser.getId();
        userRepository.delete(thisUser);
        return "redirect:/";
    }
}
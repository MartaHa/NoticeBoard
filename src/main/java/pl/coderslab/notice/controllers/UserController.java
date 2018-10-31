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

    /* show Current User */

    @GetMapping("/admin")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "this is user id " + entityUser.getId();
    }


    /* update User */

    @GetMapping("/update")
    public String showFormUser(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        model.addAttribute("user", customUser.getUser());
        return "user/updateUser";
    }

    @PostMapping("/update")
    public String performUpdate(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/updateUser";
        }
        userRepository.save(user);
        ;
        return "redirect:/welcome";
    }


    /* show User */

    @GetMapping("/showUser")

    public String showUser(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        model.addAttribute("user", entityUser);
        return "/user/showUser";

    }


    /* delete User */

    @GetMapping("/delete")
    public String delete(@AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        userRepository.delete(user);
        return "redirect:/";
    }


    /* show All users list */


    @GetMapping("/showUsersList")
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/showUsers";
    }
}


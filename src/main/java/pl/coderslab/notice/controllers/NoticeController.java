package pl.coderslab.notice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.notice.entity.Notice;
import pl.coderslab.notice.entity.User;
import pl.coderslab.notice.repository.NoticeRepository;
import pl.coderslab.notice.service.CurrentUser;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {


    @Autowired
    Validator validator;

    private final NoticeRepository noticeRepository;


    public NoticeController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    //addNotice
    @GetMapping("/addNotice")
    public String addNotice(Model model) {
        model.addAttribute("notice", new Notice());
        return "notice/addNotice";
    }


    @PostMapping("/addNotice")

    public String performNotice(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute @Valid Notice notice, BindingResult result) {
        if (result.hasErrors()) {
            return "ride/addRide";
        }
        User entityUser = customUser.getUser();
        notice.setUser(entityUser);
        noticeRepository.save(notice);
        return "redirect:/notice/showAll";

    }



    //showAll

    @GetMapping("/showAll")
    public String showAll(Model model) {
        model.addAttribute("notices", noticeRepository.findAll());
        return "notice/showAll";
    }

    //showOne

    @GetMapping("/showNotice/{id}")

    public String showOne(Model model, @PathVariable Long id) {
        model.addAttribute("notice", noticeRepository.getOne(id));
        return "notice/showNotice";

    }

    //updateNotice

    @GetMapping("/update/{id}")
    public String showForm(Model model, @PathVariable long id) {
        Notice n  = noticeRepository.findOne(id);
        model.addAttribute("notice", n);
        return "notice/updateNotice";
    }


    @PostMapping("/update")

    public String performUpdate(@ModelAttribute Notice notice) {
        noticeRepository.save(notice);
        return "redirect:/notice/showAll";

    }

//deletenotice

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {

        noticeRepository.delete(noticeRepository.findOne(id));
        return "redirect:/welcome";
    }

}

package pl.coderslab.notice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.notice.entity.Comment;
import pl.coderslab.notice.entity.Notice;
import pl.coderslab.notice.repository.CommentRepository;
import pl.coderslab.notice.repository.NoticeRepository;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    Validator validator;

    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;

    public CommentController(CommentRepository commentRepository, NoticeRepository noticeRepository) {
        this.commentRepository = commentRepository;
        this.noticeRepository = noticeRepository;
    }

    //addComment


//    @RequestMapping("/addComment/{id}")
//    public String showFormUser(Model model, @PathVariable long id){
//        model.addAttribute("comment", new Comment());;
//        return "comment/commentform";
//    }

    @PostMapping("/addComment/{id}")
    public String addComment(@ModelAttribute @Valid Comment comment, BindingResult result, @PathVariable long id) {
        if (result.hasErrors()) {
            return "category/addCategory";
        }
        commentRepository.save(comment);
        Notice n = noticeRepository.getOne(id);
        List <Comment> c = n.getComment();
        c.add(comment);
        noticeRepository.save(n);

        return "redirect:/notice/showAll";
    }
}

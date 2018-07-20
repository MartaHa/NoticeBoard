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


    @RequestMapping("/add/{id}")
    public String showFormUser(Model model, @PathVariable long id) {
        Notice thisNotice = noticeRepository.getOne(id);
        Comment newComment = new Comment();
        newComment.setNotice(thisNotice);
        model.addAttribute("comment", newComment);
        return "comment/addComment";
    }

    @PostMapping("/add")
    public String addComment(@ModelAttribute @Valid Comment comment, BindingResult result) {
        if (result.hasErrors()) {
            return "comment/addComment";
        }
        commentRepository.save(comment);
        return "redirect:/notice/showAll";
    }

    //showAllComments

    @GetMapping("/showComments/{id}")
    public String showAll(Model model, @PathVariable long id) {
        Notice notice = noticeRepository.getOne(id);
        List<Comment> allComments = notice.getComments();
        if (allComments.isEmpty()) {
            return "comment/noComments";
        } else {
            model.addAttribute("comments", commentRepository.findAll());
            return "comment/showComments";

        }


    }
}

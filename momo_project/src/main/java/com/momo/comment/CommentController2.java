package com.momo.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController2 {
    @Autowired 

    private final CommentService02 commentService02;    

    public CommentController2(CommentService02 commentService02) {

        this.commentService02 = commentService02;

    }

@RequestMapping(value = "/addComment02") // 2
public String homePage(Model model) { 
    model.addAttribute("comment2", new Comment()); // 3
    return "comment/makeComment"; // 4
}

@RequestMapping(value = "/comment2")
public String getPagePerson(Model model) {
    model.addAttribute("comments", commentService02.getAllComments()); // 5
    return "comment/result02"; 
}

@RequestMapping(value = "/comment2", method = RequestMethod.POST) // 6
public String addPagePerson(@ModelAttribute Comment2 comment2, Model model) { // 7
    commentService02.createComment(comment2); 
    model.addAttribute("comments", commentService02.getAllComments());
    return "comment/result02";
}

@RequestMapping(value = "/comment2/delete/{replyNum}")
public String deletePagePerson(@PathVariable Long replyNum) { // 8
    commentService02.deleteComment(replyNum); 
    return "redirect:/comment2"; // 9
}

}

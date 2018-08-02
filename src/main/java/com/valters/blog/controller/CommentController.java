package com.valters.blog.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.valters.blog.exception.ResourceNotFoundException;
import com.valters.blog.model.Comment;
import com.valters.blog.model.Post;
import com.valters.blog.repository.CommentRepository;

@Controller
public class CommentController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	CommentRepository commentRepository;

	@PostMapping(value = "/comment-create")
	public String commentCreateProccess(@Valid Comment comment, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "posts/" + comment.getPost().getId();

		} else {
		
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		String currentPrincipalName = authentication.getName();

    		comment.setAuthor(currentPrincipalName);
			
			commentRepository.save(comment);
			return "redirect:/posts/" + comment.getPost().getId();
		}
	}
	
	@GetMapping(value = "/admin/comment-delete")
	public String commentDeleteProccess(@RequestParam(name = "commentId") Long commentId) {

		
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		commentRepository.delete(comment);
		
		return "redirect:/posts/" + comment.getPost().getId();
	}
	
}

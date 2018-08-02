package com.valters.blog.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.valters.blog.exception.ResourceNotFoundException;
import com.valters.blog.model.Post;
import com.valters.blog.repository.PostRepository;

@Controller

public class PostController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	PostRepository postRepository;

	@GetMapping("/posts/{postId}")
	public String postPageById(@PathVariable(value = "postId") Long postId, Model model) {
		
		logger.info("Retrieving single post by id");
		model.addAttribute("post",
				postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId)));

		logger.info("Opening single post page");
		return "post";
	}

	@GetMapping("/admin/post-create")
	public String postCreatePage(Model model) {

		model.addAttribute("post", new Post());
		
		logger.info("Opening post creation page for admin");
		return "admin/post-create";
	}

	@PostMapping("/admin/post-create")
	public String postCreateProccess(@Valid Post post,
            BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			
			logger.error("Error in post creation, staying on same page");
            return "admin/post-create";
            
        } else {
        	
        	logger.info("Retrieving current user username");
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		String currentPrincipalName = authentication.getName();

    		logger.info("Setting posts author to the currently logged in user username");
    		post.setAuthor(currentPrincipalName);
        	
    		logger.info("Saving created post to database");
        	postRepository.save(post);
        	
        	logger.info("Redirecting to index page");
            return "redirect:/";
        }
		
	}

	@GetMapping(value = "/admin/post-edit")
	public String postEditPage(@RequestParam(name = "postId") Long postId, Model model) {

		logger.info("Retrieving single post by id");
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		model.addAttribute(post);

		logger.info("Opening post edit page for admin");
		return "admin/post-edit";
	}

	@PostMapping(value = "/admin/post-edit")
	public String postEditProccess(@Valid Post post,
            BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			
			logger.error("Error in post editing, staying on same page");
            return "admin/post-edit";
            
        } else {
		
        	logger.info("Retrieving current user username");
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
	
			logger.info("Setting posts author to the currently logged in user username");
			post.setAuthor(currentPrincipalName);
			
			logger.info("Saving created post to database");
			postRepository.save(post);
			
			logger.info("Redirecting to index page");
			return "redirect:/";
        }
		
	}

	@GetMapping(value = "/admin/post-delete")
	public String postDeleteProccess(@RequestParam(name = "postId") Long postId) {

		logger.info("Retrieving single post by id");
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		logger.info("Deleting post from the database");
		postRepository.delete(post);

		logger.info("Redirecting to index page");
		return "redirect:/";
	}

}

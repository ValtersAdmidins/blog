package com.valters.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.valters.blog.exception.ResourceNotFoundException;
import com.valters.blog.repository.PostRepository;

@Controller
public class HomeController {
	
	@Autowired
	PostRepository postRepository;
	
    @RequestMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "index";
    }
    
    @RequestMapping("/posts/{postId}")
	public String postPageById(@PathVariable(value = "postId") Long postId,
			Model model) {
		model.addAttribute("post",
				postRepository.findById(postId).orElseThrow(
						() -> new ResourceNotFoundException("Post", "id",
								postId)));
		
		return "post";
	}

}
package com.valters.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.valters.blog.exception.ResourceNotFoundException;
import com.valters.blog.repository.PostRepository;

@Controller
public class HomeController {
	
	@Autowired
	PostRepository postRepository;
	
    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("posts", postRepository.findAllByDate());
        return "index";
    }

}
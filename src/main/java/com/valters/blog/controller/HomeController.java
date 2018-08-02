package com.valters.blog.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.valters.blog.repository.PostRepository;

@Controller
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	PostRepository postRepository;
	
    @GetMapping("/")
    public String indexPage(Model model) {
    	
    	logger.info("Retrieving all posts sorted by date");
        model.addAttribute("posts", postRepository.findAllByDate());
        
        logger.info("Opening index page");
        return "index";
    }

}
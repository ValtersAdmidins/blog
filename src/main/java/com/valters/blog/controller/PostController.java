package com.valters.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.valters.blog.exception.ResourceNotFoundException;
import com.valters.blog.model.Post;
import com.valters.blog.repository.PostRepository;

@Controller

public class PostController {

	@Autowired
	PostRepository postRepository;

	@GetMapping("/posts/{postId}")
	public String postPageById(@PathVariable(value = "postId") Long postId, Model model) {
		model.addAttribute("post",
				postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId)));

		return "post";
	}

	@GetMapping("/admin/post-create")
	public String postCreatePage(Model model) {

		model.addAttribute("post", new Post());
		return "admin/post-create";
	}

	@PostMapping("/admin/post-create")
	public String postCreateProccess(Post post) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		post.setCurrentDate();
		post.setAuthor(currentPrincipalName);

		postRepository.save(post);
		return "redirect:/";
	}

	@GetMapping(value = "/admin/post-edit")
	public String postEditPage(@RequestParam(name = "postId") Long postId, Model model) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		model.addAttribute(post);

		return "admin/post-edit";
	}

	@PostMapping(value = "/admin/post-edit")
	public String postEditProccess(Post post) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		post.setCurrentDate();
		post.setAuthor(currentPrincipalName);
		
		postRepository.save(post);
		return "redirect:/";
	}

	@GetMapping(value = "/admin/post-delete")
	public String postDeleteProccess(@RequestParam(name = "postId") Long postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		postRepository.delete(post);

		return "redirect:/";
	}

}

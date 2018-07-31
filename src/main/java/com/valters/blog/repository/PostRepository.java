package com.valters.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valters.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}

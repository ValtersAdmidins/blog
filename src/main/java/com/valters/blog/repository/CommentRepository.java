package com.valters.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.valters.blog.model.Comment;
import com.valters.blog.model.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query(value = "SELECT * FROM comment ORDER BY published_on DESC;", nativeQuery = true)
	public List<Comment> findAllByDate();
}

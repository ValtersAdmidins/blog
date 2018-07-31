package com.valters.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.valters.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	@Query(value = "SELECT * FROM post ORDER BY published_on DESC;", nativeQuery = true)
	public List<Post> findAllByDate();
}

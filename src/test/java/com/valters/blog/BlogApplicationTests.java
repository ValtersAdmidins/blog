package com.valters.blog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.valters.blog.model.Post;
import com.valters.blog.repository.PostRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void givenPostRepository_whenSaveAndRetreiveEntity_thenOK() {
		
		Post postEntity = postRepository.save(new Post("Test"));
		Optional<Post> retrievedPostEntity = postRepository.findById(postEntity.getId());
		
		assertNotNull(retrievedPostEntity.get());
		assertEquals(postEntity.getTitle(), retrievedPostEntity.get().getTitle());
	}

}

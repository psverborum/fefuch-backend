package com.verborum.fefuchbackend.Controller;

import com.verborum.fefuchbackend.Entity.Post;
import com.verborum.fefuchbackend.Repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/post")
public class PostController {

	private final PostRepository postRepository;

	public PostController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<Post> addPost(@RequestBody Post post) {
		postRepository.save(post);

		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public @ResponseBody ResponseEntity<Iterable<Post>> getAllPosts() {
		return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public @ResponseBody ResponseEntity<Post> getOne(@PathVariable Long id) {
		Optional<Post> optPost = postRepository.findById(id);

		return optPost.map(post -> new ResponseEntity<>(post, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT));
	}

}

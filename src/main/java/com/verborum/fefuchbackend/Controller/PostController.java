package com.verborum.fefuchbackend.Controller;

import com.verborum.fefuchbackend.Entity.Post;
import com.verborum.fefuchbackend.Repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(path = "/post")
public class PostController {

	private final PostRepository postRepository;

	public PostController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<Post> addPost(@RequestBody String text) {
		Post post = new Post();
		postRepository.save(post);

		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public @ResponseBody ResponseEntity<Iterable<Post>> getAllPosts() {
		return new ResponseEntity<>(postRepository.findAll(), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public @ResponseBody ResponseEntity<Post> getOne(@PathVariable Long id) {
		Optional<Post> optPost = postRepository.findById(id);

		return optPost.map(post -> new ResponseEntity<>(post, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
	}

	@SuppressWarnings("ConstantConditions")
	protected String fetchClientIpAddress() {
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
				.getRequest();
		String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
		if (ip.equals("0:0:0:0:0:0:0:1"))
			ip = "127.0.0.1";
		Assert.isTrue(ip.chars().filter($ -> $ == '.').count() == 3, "Illegal IP: " + ip);
		return ip;
	}

}

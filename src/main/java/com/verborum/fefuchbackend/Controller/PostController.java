package com.verborum.fefuchbackend.Controller;

import com.verborum.fefuchbackend.Entity.Post;
import com.verborum.fefuchbackend.Entity.Thread;
import com.verborum.fefuchbackend.Entity.User;
import com.verborum.fefuchbackend.Repository.PostRepository;
import com.verborum.fefuchbackend.Repository.ThreadRepository;
import com.verborum.fefuchbackend.Repository.UserRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
@RequestMapping(path = "/post")
public class PostController implements BaseController {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final ThreadRepository threadRepository;

	public PostController(PostRepository postRepository, UserRepository userRepository, ThreadRepository threadRepository) {
		this.postRepository   = postRepository;
		this.userRepository   = userRepository;
		this.threadRepository = threadRepository;
	}

	@PostMapping(path = "/add")
	public @ResponseBody
	ResponseEntity<Object> addPost(@RequestBody String request) {
		JSONParser jsonParser = new JSONParser(request);

		LinkedHashMap<String, Object> json;
		try {
			json = jsonParser.parseObject();
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		String text = (String) json.get("text");
		Long threadId = ((BigInteger) json.get("thread")).longValue();

		Optional<Thread> optThread = threadRepository.findById(threadId);

		if (optThread.isEmpty() || text == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		Thread thread = optThread.get();
		Post post = new Post().setText(text).setThread(thread);

		String ip = fetchClientIpAddress();
		User user = userRepository.findUserByIp(ip);

		if (user == null) {
			user = new User().setIp(ip);

			post.setUser(user);

			userRepository.save(user);
		} else {
			post.setUser(user);
		}

		if (user.isBanned()) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
		}

		postRepository.save(post);

		return ResponseEntity.status(HttpStatus.OK).body(null);
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

}

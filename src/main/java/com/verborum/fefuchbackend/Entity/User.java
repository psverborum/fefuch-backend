package com.verborum.fefuchbackend.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String ip;

	@Column(nullable = false)
	private boolean banned = false;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	@OneToMany(mappedBy = "user")
	private List<Thread> threads;

	public Long getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public User setIp(String ip) {
		this.ip = ip;

		return this;
	}

	public boolean isBanned() {
		return banned;
	}

	public User setBanned(boolean banned) {
		this.banned = banned;

		return this;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public User setPosts(List<Post> posts) {
		this.posts = posts;

		return this;
	}

	public User addPost(Post post) {
		posts.add(post);

		return this;
	}

	public User removePost(Post post) {
		posts.remove(post);

		return this;
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public User setThreads(List<Thread> threads) {
		this.threads = threads;

		return this;
	}

	public User addThread(Thread thread) {
		threads.add(thread);

		return this;
	}

	public User removeThread(Thread thread) {
		threads.remove(thread);

		return this;
	}

}

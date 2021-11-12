package com.verborum.fefuchbackend.Entity;

import javax.persistence.*;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id", insertable=false, updatable=false)
	@Column(nullable = false)
	private User user;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private boolean enabled;

	@ManyToOne
	@JoinColumn(name = "id", insertable=false, updatable=false)
	@Column(nullable = false)
	private Thread thread;

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Post setUser(User user) {
		this.user = user;

		return this;
	}

	public String getText() {
		return enabled ? text : "<<<пост удалён>>>";
	}

	public Post setText(String text) {
		this.text = text;

		return this;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Post setEnabled(boolean enabled) {
		this.enabled = enabled;

		return this;
	}

	public Thread getThread() {
		return thread;
	}

	public Post setThread(Thread thread) {
		this.thread = thread;

		return this;
	}
}

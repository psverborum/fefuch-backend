package com.verborum.fefuchbackend.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Thread {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
	private User user;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private boolean enabled;

    @OneToMany(mappedBy = "thread")
    private List<Post> posts;

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Thread setUser(User user) {
		this.user = user;

		return this;
	}

	public String getText() {
		return enabled ? text : "<<<пост удалён>>>";
	}

	public Thread setText(String text) {
		this.text = text;

		return this;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Thread setEnabled(boolean enabled) {
		this.enabled = enabled;

        return this;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Thread setPosts(List<Post> posts) {
        this.posts = posts;

        return this;
    }

    public Thread addPost(Post post) {
        posts.add(post);

        return this;
    }

    public Thread removePost(Post post) {
        posts.remove(post);

        return this;
    }

}

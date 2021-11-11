package com.verborum.fefuchbackend.Entity;

import javax.persistence.*;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String text;

	private String sender;

	public Post(String sender, String text) {
		this.sender = sender;
		this.text = text;
	}

	public Post() {
		sender = null;
		text = null;
	}

	@Override
	public String toString() {
		return String.format("{\"id\": %d, \"sender\": %s, \"text\": %s}", id, sender, text);
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Post setText(String text) {
		this.text = text;

		return this;
	}

	public String getSender() {
		return sender;
	}

	public Post setSender(String sender) {
		this.sender = sender;

		return this;
	}

}

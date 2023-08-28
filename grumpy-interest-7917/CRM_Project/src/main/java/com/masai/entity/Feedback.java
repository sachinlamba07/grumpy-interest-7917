package com.masai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 300, nullable = false)
	private String message;
	
	@Column(nullable = true)
	private int rating;
	
	@ManyToOne
	@JoinColumn(name = "issue_id")
	private Issues feedIssue;

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(String message, int rating, Issues feedIssue) {
		super();
		this.message = message;
		this.rating = rating;
		this.feedIssue = feedIssue;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the feedIssue
	 */
	public Issues getFeedIssue() {
		return feedIssue;
	}

	/**
	 * @param feedIssue the feedIssue to set
	 */
	public void setFeedIssue(Issues feedIssue) {
		this.feedIssue = feedIssue;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", message=" + message + ", rating=" + rating + ", feedIssue=" + feedIssue + "]";
	}

	
	
	
	
}

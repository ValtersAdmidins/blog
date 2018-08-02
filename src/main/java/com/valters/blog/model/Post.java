package com.valters.blog.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5, message = "*Your title must have at least 5 characters")
	@Size(max = 255, message = "*Your title must not exceed 255 characters")
    @NotEmpty(message = "*Please provide a title")
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String short_content;
	
	@Column(columnDefinition = "TEXT")
	private String full_content;
	
	private String author;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date published_on;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	@OrderBy("published_on DESC")
    private Collection<Comment> comments;
	
	public Post() {

	}
	
	public Post(String test) {
		this.title = test + " title";
		this.short_content = test + " short content";
		this.full_content = test + " full content";
		this.author = test + " author";
		this.published_on = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShort_content() {
		return short_content;
	}

	public void setShort_content(String short_content) {
		this.short_content = short_content;
	}

	public String getFull_content() {
		return full_content;
	}

	public void setFull_content(String full_content) {
		this.full_content = full_content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublished_on() {
		return published_on;
	}

	public void setPublished_on(Date published_on) {
		this.published_on = published_on;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", short_content=" + short_content + ", full_content="
				+ full_content + ", author=" + author + ", published_on=" + published_on + ", comments=" + comments
				+ "]";
	}
	
}

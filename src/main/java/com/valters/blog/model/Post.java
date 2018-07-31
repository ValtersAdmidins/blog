package com.valters.blog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String short_content;
	private String full_content;
	private String author;
	private Date published_on;
	
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
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", short_content=" + short_content + ", full_content="
				+ full_content + ", author=" + author + ", published_on=" + published_on + "]";
	}
	
}

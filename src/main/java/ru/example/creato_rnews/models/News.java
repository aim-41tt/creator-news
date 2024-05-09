package ru.example.creato_rnews.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	
	private String topic;
	
	private String description;

	@OneToOne
	private ContentNews contentNews;

	public News() {
	}

	/**
	 * @param id
	 * @param title
	 * @param topic
	 * @param description
	 * @param contentNews
	 */
	public News(Long id, String title,String topic, String description, ContentNews contentNews) {
		this.id = id;
		this.title = title;
		this.topic = topic;
		this.description = description;
		this.contentNews = contentNews;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param team the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the contentNews
	 */
	public ContentNews getContentNews() {
		return contentNews;
	}

	/**
	 * @param contentNews the contentNews to set
	 */
	public void setContentNews(ContentNews contentNews) {
		this.contentNews = contentNews;
	}



}

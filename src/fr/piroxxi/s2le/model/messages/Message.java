package fr.piroxxi.s2le.model.messages;

import java.util.Date;

import fr.piroxxi.s2le.model.BasicEntity;

public class Message extends BasicEntity {
	private Date date;
	private String id;
	private String title;
	private String content;
	private boolean readen = false;

	public Message() {

	}

	public Message(String title, String content) {
		this.date = new Date();

		/*
		 * TODO(raphael) :p la on a un leger probleme :p j'ai pas d'uuid coté
		 * client. :/
		 */
		this.id = "" + date.getTime();

		this.title = title;
		this.content = content;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the readen
	 */
	public boolean isReaden() {
		return readen;
	}

	/**
	 * @param readen
	 *            the readen to set
	 */
	public void setReaden(boolean readen) {
		this.readen = readen;
	}

}

package com.github.desyncr.fmsweb.models;

import java.util.Date;

public class Thread {
	public String author;
	public String forum;
	public String title;
	public String textOutline;
	public Date createdAt;
	public Integer commentsCount;
	public Thread(String title, String textOutline, Date date) {
		this.title = title;
		this.textOutline = textOutline;
		this.author = "unknown";
		this.forum = "freenet";
		this.createdAt = date;
		this.commentsCount = 4;
	}
}

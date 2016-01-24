package com.example.photoviewer;

import java.io.Serializable;

public class gallary implements Serializable {
	String owner, pub_date, title, imageurl;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "gallary [owner=" + owner + ", pub_date=" + pub_date
				+ ", title=" + title + ", imageurl=" + imageurl + "]";
	}
}

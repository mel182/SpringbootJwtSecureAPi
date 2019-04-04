package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;

import javax.persistence.Column;

public class Post {

    private String title;

    private String description;

    private Category category;

    private Long publishedDate;

    private long lastUpdate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

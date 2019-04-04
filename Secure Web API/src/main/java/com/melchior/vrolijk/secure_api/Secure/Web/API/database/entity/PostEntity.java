package com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;

import javax.persistence.*;

@Entity
@Table(name="POST")
public class PostEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CATEGORY")
    private Category category;

    @Column(name="PUBLISHED_DATE")
    private Long publishedDate;

    @Column(name="LAST_UPDATE")
    private long lastUpdate;

    @Column(name="CREATOR")
    private long creator;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Long getpublishedDate() {
        return publishedDate;
    }

    public void setpublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }
}

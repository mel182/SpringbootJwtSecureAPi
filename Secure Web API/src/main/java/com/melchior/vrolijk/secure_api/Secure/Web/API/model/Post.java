package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This is the post model
 * @author Melchior Vrolijk
 */
@SuppressWarnings("ALL")
@ApiModel
public class Post {

    //region Model properties
    @ApiModelProperty(readOnly = true)
    private Long id;

    @ApiModelProperty(required = true)
    private String title;

    @ApiModelProperty(required = true)
    private String description;

    @ApiModelProperty(value = "Post categories: 'EDUCATIONAL','COMMERCIAL','TECHNOLOGY','SPORT'",required = true)
    private String category;

    @ApiModelProperty(readOnly = true)
    private Long creator;
    //endregion

    //region Post ID getter and setter
    /**
     * Get post ID
     * @return The post ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Set post ID
     * @param id The post ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    //endregion

    //region Post title getter and setter
    /**
     * Get post title
     * @return The post title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set post title
     * @param title The post title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    //endregion

    //region Post description getter and setter
    /**
     * Get post description
     * @return The post description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set post description
     * @param description The post description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    //region Post category getter and setter
    /**
     * Get post category
     * @return The post category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the post category
     * @param category The post category option
     */
    public void setCategory(String category) {
        this.category = category;
    }
    //endregion

    //region Post creator ID getter and setter
    /**
     * Get post creator ID
     * @return The post creator ID
     */
    public Long getCreator() {
        return this.creator;
    }

    /**
     * Set post creator ID
     * @param creator The post creator ID
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
    //endregion
}

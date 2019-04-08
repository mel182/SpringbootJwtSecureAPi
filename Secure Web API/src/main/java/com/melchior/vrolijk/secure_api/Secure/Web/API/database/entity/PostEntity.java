package com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel
@Entity
@Table(name="POST")
/***
 * <p>This is the post entity database model.</p>
 * <p>Note: all properties are read-only when exposes to clients</p>
 *
 * @author Melchior Vrolijk
 * @date 2019/4/8
 */
public class PostEntity {


    //region Model properties
    @ApiModelProperty(readOnly = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(readOnly = true)
    @Column(name="TITLE")
    private String title;

    @ApiModelProperty(readOnly = true)
    @Column(name="DESCRIPTION")
    private String description;

    @ApiModelProperty(readOnly = true)
    @Column(name="CATEGORY")
    private Category category;

    @ApiModelProperty(readOnly = true)
    @Column(name="PUBLISHED_DATE")
    private Long publishedDate;

    @ApiModelProperty(readOnly = true)
    @Column(name="LAST_UPDATE")
    private long lastUpdate;

    @ApiModelProperty(readOnly = true)
    @Column(name="CREATOR")
    private long creator;
    //endregion

    //region Entity ID getter and setter
    /**
     * Get entity ID
     * @return Entity ID
     */
    public long getId() {
        return id;
    }

    /**
     * Set entity ID
     * @param id The entiry ID
     */
    public void setId(long id) {
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
    public Category getCategory() {
        return category;
    }

    /**
     * Set post category
     * @param category The post category option
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    //endregion

    //region Get published date getter and setter
    /**
     * Get post published date
     * @return post published timestamp
     */
    public Long getpublishedDate() {
        return publishedDate;
    }

    /**
     * Set post published date
     * @param publishedDate The published date timestamp
     */
    public void setpublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }
    //endregion

    //region Last update getter and setter
    /**
     * Get last update date timestamp
     * @return The last update timestamp
     */
    public long getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Set last update timestamp
     * @param lastUpdate The last update timestamp
     */
    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    //endregion

    //region Post creator ID getter and setter
    /**
     * Get creator user ID
     * @return The creator ID
     */
    public long getCreator() {
        return creator;
    }

    /**
     * Set post creator ID
     * @param creator The creator ID
     */
    public void setCreator(long creator) {
        this.creator = creator;
    }
    //endregion
}

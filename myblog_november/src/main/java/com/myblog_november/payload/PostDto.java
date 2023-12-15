package com.myblog_november.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//@Data
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min=2, message = "Post title should have atleast 2 characters")
    private String title;

    @NotEmpty
    @Size(min=10, message = "Post title should have atleast 2 characters")
    private String description;

    @NotEmpty
    private String content;

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

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

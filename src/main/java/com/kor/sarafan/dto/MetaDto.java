package com.kor.sarafan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.select.Elements;

@Data
@AllArgsConstructor
public class MetaDto {
    private String title;
    private String description;
    private String cover;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}

package com.dikulous.ric.myapplication.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Map;

/**
 * Created by ric on 7/05/16.
 */
@Entity
public class KeyWordEntity {
    @Id Long id;
    @Index String keyWord;
    @Index String category;
    @Index String tone;
    @Index Long createdAt;
    Map<String, Long> dateCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Long> getDateCount() {
        return dateCount;
    }

    public void setDateCount(Map<String, Long> dateCount) {
        this.dateCount = dateCount;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }
}

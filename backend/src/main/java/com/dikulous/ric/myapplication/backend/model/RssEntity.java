package com.dikulous.ric.myapplication.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by ric on 7/05/16.
 */
@Entity
public class RssEntity {
    @Id Long id;
    @Index String url;
    @Index String countryCode;
    @Index String category;
    @Index Long scheduledRead;
    @Index Long readFrequency;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getScheduledRead() {
        return scheduledRead;
    }

    public void setScheduledRead(Long scheduledRead) {
        this.scheduledRead = scheduledRead;
    }

    public Long getReadFrequency() {
        return readFrequency;
    }

    public void setReadFrequency(Long readFrequency) {
        this.readFrequency = readFrequency;
    }
}

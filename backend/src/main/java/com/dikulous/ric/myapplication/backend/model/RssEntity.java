package com.dikulous.ric.myapplication.backend.model;

/**
 * Created by ric on 7/05/16.
 */
public class RssEntity {
    String url;
    String countryCode;
    String category;
    Long scheduledRead;
    Long readFrequency;

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

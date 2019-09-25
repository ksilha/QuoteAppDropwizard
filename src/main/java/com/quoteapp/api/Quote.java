package com.quoteapp.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Quote {
    private static int counter = 0;
    
    @XmlElement(nillable = true)
    private int id;
    private Date dateCreated;
    private String quoteText;
    private String author;

    public Quote() {
        this.id = counter++;
        this.dateCreated = Calendar.getInstance().getTime();
    }
    
    public Quote(final String quoteText, final String author) {
        this.id = counter++;
        this.quoteText = quoteText;
        this.author = author;
        this.dateCreated = Calendar.getInstance().getTime();
    }

    @JsonProperty
    public int getId() {
        return id;
    }
    
    public void setId(Integer id) {
        if (id != null)
            this.id = id;
    }
    
    @JsonProperty
    public Date getDateCreated() {
        return dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    @JsonProperty
    public String getQuoteText() {
        return quoteText;
    }
    
    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }
    
    @JsonProperty
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
}

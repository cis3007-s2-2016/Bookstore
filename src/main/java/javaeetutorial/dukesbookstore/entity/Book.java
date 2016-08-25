/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * <p>Entity class for bookstore example.</p>
 */
@Entity
@Table(name="Books")
public class Book implements Serializable {

    private static final long serialVersionUID = -4146681491856848089L;
    @Id
    @NotNull
    private String ISBN;
    
    //Refractor out to author entity
    private String surname;
    private String firstname;
    
    private String title;
    private Float costPrice;
    private Float retailPrice;
    private Integer pubYear;
    private String publisher;
    private String description;
    private Integer stockLevel;
    private String genre; 
    private String format;

    public Book() {
    }

    public Book(String ISBN, String surname, String firstname,
            String title, Float costPrice, Float retailPrice, Integer publishedYear,
            String description, Integer stockLevel, String publisher,
            String genre, String format) {
        
        this.ISBN = ISBN;
        this.surname = surname;
        this.firstname = firstname;
        this.title = title;
        this.publisher = publisher;
        this.costPrice = costPrice;
        this.retailPrice = retailPrice;
        this.pubYear = publishedYear;
        this.description = description;
        this.stockLevel = stockLevel;
        this.genre = genre;
        this.format = format;
    }

    public Book(String ISBN) {
        this.ISBN = ISBN;
        /**
         @TODO Do magical book information scrape 
         */
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ISBN != null ? ISBN.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        return this.ISBN != null || this.ISBN == null 
                && other.ISBN == null || this.ISBN.equals(other.ISBN);
    }

    @Override
    public String toString() {
        return "bookstore.entities.Book[ bookId=" + ISBN + " ]";
    }
    
    //Getter and setters
    // ===============================================================
        public String getISBN() {
        return ISBN;
    }

    public void setISBN(String bookId) {
        this.ISBN = bookId;
    }
    /**
     * @deprecated author will be moved to it's own entity
     * @return the surname of the book author
     */
    public String getSurname() {
        return surname;
    }
    /**
     * @deprecated author will be moved to it's own entity
     * @param surname sets the surname of the book author
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * @deprecated author will be moved to it's own entity
     * @return 
     */
    public String getFirstname() {
        return firstname;
    }
    /**
     * @deprecated author will be moved to it's own entity
     * @param firstname 
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Float getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Float price) {
        this.costPrice = price;
    }
    public void setRetailPrice(Float price)
    {
        this.retailPrice = price;
    }
    public Float getRetailPrice()
    {
        return retailPrice;
    }
    public Integer getPublishedYear() {
        return pubYear;
    }
    public void setPublishedYear(Integer calendarYear) {
        this.pubYear = calendarYear;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getStockLevel() {
        return stockLevel;
    }
    public void setStockLevel(Integer inventory) {
        this.stockLevel = inventory;
    }   
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }   
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
}

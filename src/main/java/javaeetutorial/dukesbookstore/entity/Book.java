/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * <p>
 * Entity class for bookstore example.</p>
 */
@Entity
@Table(name = "books")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
	@NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.ISBN = :ISBN"),
	@NamedQuery(name = "Book.findByDescription", query = "SELECT b FROM Book b WHERE b.description = :description"),
	@NamedQuery(name = "Book.findByFormat", query = "SELECT b FROM Book b WHERE b.format = :format"),
	@NamedQuery(name = "Book.findByGenre", query = "SELECT b FROM Book b WHERE b.genre = :genre"),
	@NamedQuery(name = "Book.findByPubyear", query = "SELECT b FROM Book b WHERE b.pubYear = :pubYear"),
	@NamedQuery(name = "Book.findByPublisher", query = "SELECT b FROM Book b WHERE b.publisher = :publisher"),
	@NamedQuery(name = "Book.findByStocklevel", query = "SELECT b FROM Book b WHERE b.stockLevel = :stockLevel"),
	@NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
	@NamedQuery(name = "Book.findTitlebyIsbn", query = "SELECT b.title FROM Book b WHERE b.ISBN = :ISBN")})

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
//    private static final long serialVersionUID = -4146681491856848089L;
	@Id
	@NotNull
	private String ISBN;

	@Basic(optional = false)
	private String title;

	@Basic(optional = false)
	@Column(name = "COST_PRICE", nullable = false, precision = 7, scale = 2)    // Creates the database field with this size.
	private BigDecimal costPrice;

	@Basic(optional = false)
	@Column(name = "RETAIL_PRICE", nullable = false, precision = 7, scale = 2)    // Creates the database field with this size.
	private BigDecimal retailPrice;
	
	
	private Date pubYear;
	
	
	private String publisher;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date created;
	
	@Basic(optional = false)
	private Integer stockLevel;
	
	@Basic(optional = false)
	private String genre;
	
	@Basic(optional = false)
	private String format;
	
	@Lob
	@Basic(optional = false)
	private byte[] thumbnail;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Author.class)
	private List<Author> bookAuthors;

	//Getter and setters
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String bookId) {
		this.ISBN = bookId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal price) {
		this.costPrice = price;
	}

	public void setRetailPrice(BigDecimal price) {
		this.retailPrice = price;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public Date getPublishedYear() {
		return pubYear;
	}

	public void setPublishedYear(Date calendarYear) {
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

	public Date getPubYear() {
		return pubYear;
	}

	public void setPubYear(Date pubYear) {
		this.pubYear = pubYear;
	}

	@XmlTransient
	public List<Author> getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(List<Author> bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

	public Book() {
	}

	public Book(String ISBN,
			String title, BigDecimal costPrice, BigDecimal retailPrice, Date publishedYear,
			String description, Integer stockLevel, String publisher,
			String genre, String format, List<Author> bookAuthors, byte[] thumbnail) {

		this.ISBN = ISBN;
		this.title = title;
		this.publisher = publisher;
		this.costPrice = costPrice;
		this.retailPrice = retailPrice;
		this.pubYear = publishedYear;
		this.description = description;
		this.stockLevel = stockLevel;
		this.genre = genre;
		this.format = format;
		this.bookAuthors = bookAuthors;
		this.setThumbnail(thumbnail);

	}

	public Book(String ISBN) {
		this.ISBN = ISBN;
		/**
		 * @TODO Do magical book information scrape
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
		//return "bookstore.entities.Book[ bookId=" + ISBN + " ]";
		return title + ", ISBN: " + ISBN;

	}
}

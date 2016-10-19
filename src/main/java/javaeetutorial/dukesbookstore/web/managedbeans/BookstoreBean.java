/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 * <p>
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import com.sun.java.swing.plaf.gtk.resources.gtk;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.CatalogManager;
import javaeetutorial.dukesbookstore.entity.Book;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * <p>
 * Backing bean for the <code>/bookstore.xhtml</code> page.</p>
 */
@Named("shop")
@SessionScoped
public class BookstoreBean implements Serializable {

	private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedBeans.BookStoreBean");
	@EJB
	CatalogManager catalog;
	private String genre;
	private Book selectedBook;

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public static Logger getLogger() {
		return logger;
	}

	public CatalogManager getCatalog() {
		return catalog;
	}

	public void setCatalog(CatalogManager catalog) {
		this.catalog = catalog;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public BookstoreBean() {
		setGenre("Fiction");
	}

	public List<Book> getBooks() {
		try {
			return getCatalog().getBooksInGenre(genre);
		} catch (Exception e) {
			getLogger().severe("Bookstore.beans: getBooks:  NO BOOKS: " + e+ e.getStackTrace());
			return new ArrayList<Book>();
		}
	}

	public String viewBook(String isbn) {
		try {
			setSelectedBook(getCatalog().findBook(isbn));
			return "/shop/book?faces-redirect=true&includeViewParams=true&title=" + getSelectedBook().getTitle();
		} catch (Exception e) {
			//todo: return booknotfound.xhtml
			getLogger().info("Book not found:  " + isbn);
			return "/shop/category";
		}

	}
	
	public String viewBook(String isbn, String genre) {
		setGenre(genre);
		return viewBook(isbn);
	}
	
	public List<String> getGenres(){
		try {
			return getCatalog().getGenres();
		} catch (Exception e) {
			getLogger().info("NO GENRES: " + e);
			return new ArrayList<>();
		}
	}
	
	public List<Book> getNewestBooksInGenre(String genre){
		
		try {
			return getCatalog().getNewestBooksInGenre(genre, 6);
		} catch (Exception e){
			getLogger().info("BookstoreBean: getBooksInGenre: No Books in " + genre);
			return new ArrayList<>();
			
		}
	}
	
	public String viewGenre(String genre){
		setGenre(genre);
		return "/shop/category?faces-redirect=true&includeViewParams=true&genre=" + genre;

	}
}

package javaeetutorial.dukesbookstore.ejb;

import javaeetutorial.dukesbookstore.entity.Author;
import javaeetutorial.dukesbookstore.entity.Book;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

/**
 * Created by matt on 15/10/2016.
 */
@Stateless
public class CatalogManagerBean implements CatalogManager {
	@PersistenceContext(unitName = "bookstorePU")
	private EntityManager entityManager;

	@Override
	public boolean bookExists(String isbn) {
		return findBook(simplifyISBN(isbn)) != null;
	}

	@Override
	public Book findBook(String isbn) {
		try {
			return entityManager.find(Book.class, simplifyISBN(isbn));
		} catch (Exception e) {
			throw new RuntimeException("Book not found!");
		}
	}


	@Override
	public Book createBook(String isbn, String title, double costPrice, double retailPrice, Date publishedYear, String description, Integer stockLevel, String publisher, String genre, String format, List<Author> bookAuthors, byte[] thumbnail) {
		//todo: validate given data against business rules
		if (simplifyISBN(isbn).length() != 13) {
			throw new RuntimeException("Invalid ISBN. Must be 13 digits.");
		}
		Book book = new Book(simplifyISBN(isbn), title, costPrice, retailPrice, publishedYear, description, stockLevel, publisher, genre, format, bookAuthors, thumbnail);
		entityManager.persist(book);
		return book;
	}


	@Override
	public Author createAuthor(String firstnames, String lastname) {
		//todo: validate given data against business rules
		Author author = new Author(firstnames.toUpperCase(), lastname.toUpperCase());
		entityManager.persist(author);
		return author;
	}

	@Override
	public Author createOrFindAuthor(String firstnames, String lastname) {
		TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a WHERE a.givenNames=:firstnames AND a.surname=:lastname", Author.class);
		query.setParameter("firstnames", firstnames.toUpperCase());
		query.setParameter("lastname", lastname.toUpperCase());
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return this.createAuthor(firstnames, lastname);
		}
	}

	private String simplifyISBN(String isbn) {
		return isbn.trim().replaceAll("-", "");
	}
}

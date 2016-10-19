package javaeetutorial.dukesbookstore.ejb;

import javaeetutorial.dukesbookstore.entity.Author;
import javaeetutorial.dukesbookstore.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
		return entityManager.find(Book.class, simplifyISBN(isbn));
	}

	@Override
	public List<Book> getBooksInGenre(String genre) {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.genre = :genre", Book.class);
		query.setParameter("genre", genre);
		return query.getResultList();
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
	
	

	

	@Override
	public List<String> getGenres() {
		TypedQuery<String> query = entityManager.createQuery("SELECT DISTINCT(b.genre) FROM Book b", String.class);
		return query.getResultList();
	}
	
	
	private String simplifyISBN(String isbn) {
		return isbn.trim().replaceAll("-", "");
	}

	@Override
	public List<Book> getNewestBooksInGenre(String genre, int count) {
		TypedQuery<Book> query;
		query = entityManager.createQuery("SELECT b FROM Book b WHERE b.genre = :genre ORDER BY b.created DESC", Book.class).setMaxResults(count);
		query.setParameter("genre", genre);
		return query.getResultList();
	}

	public List<Book> findBooks(List<String> isbns) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}

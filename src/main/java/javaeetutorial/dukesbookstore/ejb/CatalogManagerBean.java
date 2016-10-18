package javaeetutorial.dukesbookstore.ejb;

import javaeetutorial.dukesbookstore.entity.Author;
import javaeetutorial.dukesbookstore.entity.Book;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

/**
 * Created by matt on 15/10/2016.
 */
@Stateless
public class CatalogManagerBean implements CatalogManager {
    @PersistenceContext(unitName="bookstorePU")
    private EntityManager entityManager;

    @Override
    public boolean bookExists(String isbn){
        return findBook(simplifyISBN(isbn)) != null;
    }

    @Override
    public Book findBook(String isbn){
        try {
            return entityManager.find(Book.class, simplifyISBN(isbn));
        } catch (Exception e){
            throw new RuntimeException("Book not found!");
        }
    }


    @Override
    public Book createBook(String isbn, String title, double costPrice, double retailPrice, Date publishedYear, String description, Integer stockLevel, String publisher, String genre, String format, List<Author> bookAuthors, byte[] thumbnail) {
        //todo: validate given data against business rules
        if (simplifyISBN(isbn).length()!=13) {
            throw new RuntimeException("Invalid ISBN. Must be 13 digits.");
        }
        Book book = new Book(simplifyISBN(isbn), title, costPrice, retailPrice, publishedYear, description, stockLevel, publisher, genre, format, bookAuthors, thumbnail);
        entityManager.persist(book);
        return book;
    }


    @Override
    public Author createAuthor(String firstnames, String lastname){
        //todo: validate given data against business rules
        Author author = new Author(firstnames, lastname);
        entityManager.persist(author);
        return author;
    }

    private String simplifyISBN(String isbn){
        return isbn.trim().replaceAll("-", "");
    }
}

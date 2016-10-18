package javaeetutorial.dukesbookstore.ejb;

import javaeetutorial.dukesbookstore.entity.Author;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.entity.Member;

import javax.ejb.Remote;
import java.sql.Date;
import java.util.List;

/**
 * Created by matt on 15/10/2016.
 */
@Remote
public interface CatalogManager {

    public boolean bookExists(String isbn);
    public Book findBook(String isbn);
    public Book createBook(
            String isbn,
            String title,
            double costPrice,
            double retailPrice,
            Date publishedYear,
            String description,
            Integer stockLevel,
            String publisher,
            String genre,
            String format,
            List< Author > bookAuthors,
            byte[] thumbnail
    );

    public Author createAuthor(String firstnames, String lastname);

    public Author createOrFindAuthor(String firstnames, String lastname);
}




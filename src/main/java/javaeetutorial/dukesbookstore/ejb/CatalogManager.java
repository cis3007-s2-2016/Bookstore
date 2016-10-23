package javaeetutorial.dukesbookstore.ejb;

import java.math.BigDecimal;
import javaeetutorial.dukesbookstore.entity.Author;
import javaeetutorial.dukesbookstore.entity.Book;
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
			BigDecimal costPrice,
			BigDecimal retailPrice,
			Date publishedYear,
			String description,
			Integer stockLevel,
			String publisher,
			String genre,
			String format,
			List< Author> bookAuthors,
			byte[] thumbnail
	) throws RuntimeException;

	public Author createAuthor(String firstnames, String lastname);

	public Author createOrFindAuthor(String firstnames, String lastname);

	List<Book> getBooksInGenre(String genre);

	List<String> getGenres();

	public List<Book> getNewestBooks(int count);

	public List<Book> getNewestBooks(int count, int index);

	public List<Book> getNewestBooksInGenre(String genre, int count);

	public void changeStockCount(String isbn, int qty);

	public void decrementStockCount(String isbn);

	public void returnBooks(String isbn, int qty);

	public List<Book> booksWithStockLowerThan(int count);
	
	public void buyBooks(Book book, int qty);

}

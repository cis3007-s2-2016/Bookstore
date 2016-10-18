package javaeetutorial.dukesbookstore.ejb;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.Author;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.exception.BookNotFoundException;
import javaeetutorial.dukesbookstore.exception.BooksNotFoundException;
import javaeetutorial.dukesbookstore.exception.OrderException;
import javaeetutorial.dukesbookstore.web.managedbeans.ShoppingCart;
import javaeetutorial.dukesbookstore.web.managedbeans.ShoppingCartItem;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.internal.oxm.schema.model.Restriction;
import org.eclipse.persistence.jpa.jpql.parser.LikeExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * <p>Stateful session bean for the bookstore example.</p>
 */
@Stateful
public class BookRequestBean {

    @PersistenceContext(unitName="bookstorePU")
    private EntityManager entityManager;
    private static final Logger logger =
            Logger.getLogger("dukesbookstore.ejb.BookRequestBean");

    public BookRequestBean() throws Exception {
    }

    public void createBook(String isbn, String title, double costPrice, double retailPrice, Date publishedYear,
                           String description, Integer stockLevel, String publisher,
                           String genre, String format, List<Author> bookAuthors, byte[] thumbnail) {
        try {
            Book book = new Book(isbn, title,
                    costPrice, retailPrice, publishedYear,
                    description, stockLevel, publisher,
                    genre, format, bookAuthors, thumbnail);
            
            logger.log(Level.INFO, "Created book {0}", isbn);
            entityManager.persist(book);
            logger.log(Level.INFO, "Persisted book {0}", isbn);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public List<Book> getBooks() throws BooksNotFoundException {
        try {
            return (List<Book>) entityManager.createNamedQuery("findBooks").getResultList();
        } catch (Exception ex) {
            throw new BooksNotFoundException(
                    "Could not get books: " + ex.getMessage());
        }
    }

    public Book getBook(String bookId) throws BookNotFoundException {
        Book requestedBook = entityManager.find(Book.class, bookId);

        if (requestedBook == null) {
            throw new BookNotFoundException("Couldn't find book: " + bookId);
        }

        return requestedBook;
    }

    public void buyBooks(ShoppingCart cart) throws OrderException {
        Collection<ShoppingCartItem> items = cart.getItems();
        Iterator<ShoppingCartItem> i = items.iterator();

        try {
            while (i.hasNext()) {
                ShoppingCartItem sci = (ShoppingCartItem) i.next();
                Book bd = (Book) sci.getItem();
                String id = bd.getISBN();
                int quantity = sci.getQuantity();
                buyBook(id, quantity);
            }
        } catch (OrderException ex) {
            throw new OrderException("Commit failed: " + ex.getMessage());
        }
    }

    public void buyBook(String bookId, int quantity)
            throws OrderException {
        try {
            Book requestedBook = entityManager.find(Book.class, bookId);

            if (requestedBook != null) {
                int inventory = requestedBook.getStockLevel();

                if ((inventory - quantity) >= 0) {
                    int newInventory = inventory - quantity;
                    requestedBook.setStockLevel(newInventory);
                } else {
                    throw new OrderException(
                            "Not enough of " + bookId
                            + " in stock to complete order.");
                }
            }
        } catch (OrderException ex) {
            throw new OrderException(
                    "Couldn't purchase book: " + bookId + ex.getMessage());
        }
    }

    public void updateInventory(ShoppingCart cart) throws OrderException {
        try {
            buyBooks(cart);
        } catch (OrderException ex) {
            throw new OrderException("Inventory update failed: " + ex.getMessage());
        }
    }
    
    public List<Book> getBooksWithTitleLike(String title)
    {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.title LIKE :title", Book.class);
        query.setParameter("title", "%" + title + "%");
        System.out.println(query.toString());
        
        return query.getResultList();
    }
}


package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.BookRequestBean;
import javaeetutorial.dukesbookstore.ejb.CatalogManager;
import javaeetutorial.dukesbookstore.entity.Book;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 * <p>
 * Backing bean for the <code>/bookstore.xhtml</code> page.</p>
 */
@Named("shop")
@SessionScoped
public class BookstoreBean implements Serializable 
{
    private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedBeans.BookStoreBean");
    
    @EJB
    CatalogManager catalog;
    @EJB
    BookRequestBean bookRequestBean;
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

        protected String searchString;
    
    private List<Book> bookList = new ArrayList<>();
    
    
   
    public List<Book> getBookList()
    {
        return this.bookList;
    }
    
    public void setSearchString(String searchString)
    {
        this.searchString = searchString;
    }
    
    public String getSearchString()
    {
        return this.searchString;
    }
    
    public void searchStringValueChanged(AjaxBehaviorEvent event){
        System.out.println("Event: " + event.toString());
        searchByTitle(this.searchString);
    }
    
    private String searchByTitle(String bookTitle)
    {
        if(bookTitle == null)
        {
            System.out.println("null book title");
        }
        this.bookList = bookRequestBean.getBooksWithTitleLike(bookTitle);
        return null; // go nowhere
    }
    
}
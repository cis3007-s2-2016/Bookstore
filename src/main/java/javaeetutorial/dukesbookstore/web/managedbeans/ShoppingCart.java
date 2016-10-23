package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.CatalogManager;
import javaeetutorial.dukesbookstore.entity.Book;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import org.omnifaces.util.Ajax;
import javax.inject.Named;

@Named("cart")
@SessionScoped
public class ShoppingCart implements Serializable {

	@EJB
	CatalogManager catalogManager;
	private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedbeans.ShoppingCart");
	private static final long serialVersionUID = -2181710426297811604L;

	private ArrayList<CartItem> shoppingCart;

	public ArrayList<CartItem> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ArrayList<CartItem> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public ShoppingCart() {
		clearCart();
	}


	public void clearCart() {
		setShoppingCart(new ArrayList<>());

	}

	public void add(String isbn) {
		try {
			Book book = catalogManager.findBook(isbn);
			if (bookAlreadyInCart(book)) {
				increaseQuantity(book);
			} else {
				getShoppingCart().add(new CartItem(book));
			}
		} catch (Exception e) {
			logger.info("Could not find isbn to add book to cart. ISBN: " + isbn);
			logger.info((Supplier<String>) e);
			throw new RuntimeException("Supplied ISBN does not match any in database");
		}


		Ajax.data("cartCount", numberOfItems());
		Ajax.oncomplete("updateCartCountBadge()");

	}

	public int numberOfItems() {
		int total = 0;
		for (CartItem item : getShoppingCart()) {
			total += item.getQuantity();
		}
		return total;
	}

	public BigDecimal totalPrice() {
		BigDecimal total = new BigDecimal(0);
		for (CartItem item : getShoppingCart()) {
			total = item.getBook().getRetailPrice().multiply(new BigDecimal(item.getQuantity())).add(total);
		}
		return total;
	}

	public void remove(String isbn) {

		CartItem removeMe = null;
		for (CartItem item : getShoppingCart()) {
			if (isbn.equals(item.getBook().getISBN())) {
				removeMe = item;
			}
		}
		if (removeMe != null) {
			catalogManager.returnBooks(isbn, removeMe.getQuantity());
			getShoppingCart().remove(removeMe);
		}
	}
	
	public ArrayList<Book> getBooks(){
		ArrayList<Book> books = new ArrayList<>();
		for (CartItem item : this.getShoppingCart()){
			books.add(item.getBook());
		}
		return books;
	}

	private void increaseQuantity(Book book) {
		for (CartItem item : getShoppingCart()) {
			if (book.getISBN().equals(item.getBook().getISBN())) {
				item.increase();
				return;
			}
		}
	}

	private boolean bookAlreadyInCart(Book book) {
		for (CartItem item : getShoppingCart()) {
			if (book.getISBN().equals(item.getBook().getISBN())) {
				return true;
			}
		}
		return false;
	}
	

}

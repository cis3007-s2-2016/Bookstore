package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
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

		
		//reserve books for purchase
		catalogManager.decrementStockCount(isbn);
		
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

	public double totalPrice() {
		double total = 0;
		for (CartItem item : getShoppingCart()) {
			total = item.getQuantity() * item.getBook().getRetailPrice();
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

	public String changeQty() {
		this.setShoppingCart(shoppingCart);
		return "/bookshowcart?faces-redirect=true";
	}

	@PreDestroy
	private void putCartItemsBack() {
		for (CartItem item : getShoppingCart()) {
			catalogManager.returnBooks(item.getBook().getISBN(), item.getQuantity());
		}
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

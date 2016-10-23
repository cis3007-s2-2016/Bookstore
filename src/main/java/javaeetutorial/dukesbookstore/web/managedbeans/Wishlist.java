/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.CatalogManager;
import javaeetutorial.dukesbookstore.ejb.WishlistManager;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.entity.WishlistItem;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.omnifaces.util.Ajax;

/**
 *
 * @author matt
 */
@Named(value = "wishlist")
@SessionScoped
public class Wishlist implements Serializable {

	@EJB
	WishlistManager wishlistManager;
	@EJB
	CatalogManager catalogManager;
	@Inject
	MemberSessionBean memberSession;
	private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedbeans.ShoppingCart");
	private static final long serialVersionUID = -2181710426297811604L;
	private ArrayList<String> wishlist;

	public ArrayList<String> getWishlist() {
		return wishlist;
	}

	public void setWishlist(ArrayList<String> wishlist) {
		this.wishlist = wishlist;
	}

	public WishlistManager getWishlistManager() {
		return wishlistManager;
	}

	public void setWishlistManager(WishlistManager wishlistManager) {
		this.wishlistManager = wishlistManager;
	}

	public MemberSessionBean getMemberSession() {
		return memberSession;
	}

	public void setMemberSession(MemberSessionBean memberSession) {
		this.memberSession = memberSession;
	}

	/**
	 * Creates a new instance of Wishlist
	 */
	public Wishlist() {
	}

	@PostConstruct
	private void init() {
		setWishlist(new ArrayList<>());
	}

	public void updateWishlists() {
		List<WishlistItem> items = getWishlistManager().getAll(getMemberSession().getUser());
		for (WishlistItem item : items) {
			getWishlist().add(item.getBook().getISBN());
		}
	}

	public void toggle(String isbn) {
		if (getWishlist().contains(isbn)) {
			logger.severe("Wishlist:  Wishlist contains book");
			remove(isbn);
		} else {
			logger.severe("Wishlist:  Wishlis doesn't contain book");
			add(isbn);
		}
	}

	public void add(String isbn) {
		logger.severe("Wishlist: enetered add(Book book)");
		getWishlist().add(isbn);
		logger.severe("Wishlist:  added book to array of isbns");
		try {
			getWishlistManager().add(isbn, getMemberSession().getUser());
		} catch (Exception e) {
			logger.severe("Wishlist: failed to add wishlist item to database");
		}
	}

	public void remove(String isbn) {
		getWishlist().remove(isbn);
		try {
			getWishlistManager().remove(isbn, getMemberSession().getUser());
		} catch (Exception e) {
			logger.severe("Wishlist: failed to remove wishlist item from database");
		}
	}

	public ArrayList<Book> getBooks() {
		ArrayList<Book> result = new ArrayList<>();
		for (String isbn : getWishlist()) {
			try {
				Book book = catalogManager.findBook(isbn);
				if (book != null) {
					result.add(book);
				}
			} catch (Exception e) {
				logger.severe("Wishlist: error when pulling books from database.. skipping. " + e);
				continue;
			}

		}
		return result;
	}

}

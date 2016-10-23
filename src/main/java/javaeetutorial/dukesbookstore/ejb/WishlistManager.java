/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.WishlistItem;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author matt
 */
@Stateful
public class WishlistManager {

	@PersistenceContext(unitName = "bookstorePU")
	private EntityManager entityManager;
	private static final Logger logger = Logger.getLogger("dukesbookstore.ejb.BookRequestBean");

	public void add(String isbn, Member user) {
		Book book = entityManager.find(Book.class, isbn);
		WishlistItem item = new WishlistItem();
		item.setBook(book);
		item.setMember(user);
		entityManager.persist(item);

	}

	public void remove(String isbn, Member user) {
		Book book = entityManager.find(Book.class, isbn);
		TypedQuery<WishlistItem> query = entityManager.createQuery("SELECT w FROM WishlistItem w WHERE w.user = :user AND w.book = :book", WishlistItem.class);
		query.setParameter("user", user);
		query.setParameter("book", book);

		entityManager.remove(query.getSingleResult());
	}

	public List<WishlistItem> getAll(Member user) {
		TypedQuery<WishlistItem> query = entityManager.createQuery("SELECT w FROM WishlistItem w WHERE w.user = :user", WishlistItem.class);
		query.setParameter("user", user);
		try {
			return query.getResultList();

		} catch (Exception e) {
			logger.severe("WishlistManager: " + e);
		}
		return new ArrayList<>();

	}
}

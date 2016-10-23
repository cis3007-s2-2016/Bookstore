/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.PurchasedItem;
import javaeetutorial.dukesbookstore.entity.SaleNew;
import javaeetutorial.dukesbookstore.web.managedbeans.CartItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author matt
 */
@Stateless
public class SalesManagerBean implements SalesManager {

	@PersistenceContext(unitName = "bookstorePU")
	private EntityManager entityManager;
	private static final Logger logger = Logger.getLogger("dukesbookstore.ejb.SalesManagerBean");

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void newSale(Member user, ArrayList<CartItem> cartItems) {
		logger.severe("SalesManager: Creating new SaleNew");
		SaleNew sale = new SaleNew();

		if (user == null) {
			logger.severe("SalesManager: Trying to create new SaleNew:  User is null!");
		}

		if (cartItems == null) {
			logger.severe("SalesManager: Trying to create new SaleNew:  Cart is null!");
		}
		if (cartItems.size() == 0) {
			logger.severe("SalesManager: Trying to create new SaleNew:  Cart is empty!");
		}

		try {
			logger.severe("SalesMangager: persisting sale...");
			entityManager.persist(sale);
			logger.severe("SalesMangager: sale persisted.");

			sale.setPurchaser(user);
			sale.setPostagePrice(new BigDecimal(0));
			sale.setAddressShipped(shippingAddressString(user));

			for (CartItem item : cartItems) {
				logger.severe("SalesManager: creating new PurchasedItem...");
				PurchasedItem purchase = new PurchasedItem();
				logger.severe("SalesManager: PurchasedItem created. Adding book....");
				purchase.setItem(item.getBook());
				logger.severe("SalesManager: Book added. Adding qty...");

				purchase.setQuantity(item.getQuantity());
				logger.severe("SalesManager: Qty added");

				purchase.setPriceEach(item.getBook().getRetailPrice());
				logger.severe("SalesManager:  attempting to add PurchasedItem to SaleNew...");
				purchase.setSale(sale);
				logger.severe("SalesManager: Added purchasedItem to SaleNew");

				entityManager.persist(purchase);
			}

		} catch (Exception e) {
			logger.severe("SalesManager: Failed to create new SaleNew:  " + e);
			throw e;
		}

		try {

		} catch (Exception e) {
			logger.severe("SalesManager: Failed persist SaleNew:  " + e);
			throw e;
		}
	}

	public String shippingAddressString(Member user) {
		StringBuilder address = new StringBuilder();
		if (!user.getShippingAddressLine1().isEmpty()) {
			address.append(user.getShippingAddressLine1()).append("\n");
		}
		if (!user.getShippingAddressLine2().isEmpty()) {
			address.append(user.getShippingAddressLine2()).append("\n");
		}
		address.append(user.getShippingCity()).append("\n");
		address.append(user.getShippingState()).append(" ");
		address.append(user.getShippingPostcode());
		return address.toString();
	}

	@Override
	public List<PurchasedItem> getPurchasedItems(SaleNew sale) {
		TypedQuery<PurchasedItem> query;
		query = entityManager.createQuery("SELECT p FROM PurchasedItem p WHERE p.sale = :sale", PurchasedItem.class);
		query.setParameter("sale", sale);
		return query.getResultList();
	}

	@Override
	public List<SaleNew> getPurchases(Member user) {
		TypedQuery<SaleNew> query;
		query = entityManager.createQuery("SELECT s FROM SaleNew s WHERE s.purchaser = :user ORDER BY s.purchaseDate DESC", SaleNew.class);
		query.setParameter("user", user);
		return query.getResultList();
	}

}

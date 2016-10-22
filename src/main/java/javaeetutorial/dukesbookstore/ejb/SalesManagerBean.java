/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.PurchasedItem;
import javaeetutorial.dukesbookstore.entity.SaleNew;
import javaeetutorial.dukesbookstore.web.managedbeans.CartItem;
import javaeetutorial.dukesbookstore.web.managedbeans.ShoppingCart;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		logger.fine("SalesManager: Creating new SaleNew");
		SaleNew sale = new SaleNew();

		if (user == null) {
			logger.fine("SalesManager: Trying to create new SaleNew:  User is null!");
		}
		
		if (cartItems == null) {
			logger.fine("SalesManager: Trying to create new SaleNew:  Cart is null!");
		}
		if (cartItems.size() == 0) {
			logger.fine("SalesManager: Trying to create new SaleNew:  Cart is empty!");
		}

		try {
			sale.setPurchaser(user);
			sale.setPostagePrice(new BigDecimal(0));
			sale.setAddressShipped(user.shippingAddressString());
			sale.setPurchasedItems(new ArrayList<>());

			for (CartItem item : cartItems) {
				logger.fine("SalesManager: creating new PurchasedItem...");
				PurchasedItem purchase = new PurchasedItem();
				logger.fine("SalesManager: PurchasedItem created. Adding book....");
				purchase.setItem(item.getBook());
				logger.fine("SalesManager: Book added. Adding qty...");

				purchase.setQuantity(item.getQuantity());
				logger.fine("SalesManager: Qty added");

				purchase.setPriceEach(item.getBook().getRetailPrice());
				logger.fine("SalesManager:  attempting to add PurchasedItem to SaleNew...");
				sale.add(purchase);
				logger.fine("SalesManager: Added purchasedItem to SaleNew");
			}

		} catch (Exception e) {
			logger.warning("SalesManager: Failed to create new SaleNew:  " + e);
			throw e;
		}

		try {
			entityManager.persist(sale);
		} catch (Exception e) {
			logger.warning("SalesManager: Failed persist SaleNew:  " + e);
			throw e;
		}
	}

}

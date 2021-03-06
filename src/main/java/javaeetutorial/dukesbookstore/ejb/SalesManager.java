/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.ArrayList;
import java.util.List;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.PurchasedItem;
import javaeetutorial.dukesbookstore.entity.SaleNew;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javaeetutorial.dukesbookstore.web.managedbeans.CartItem;
import javaeetutorial.dukesbookstore.web.managedbeans.ShoppingCart;
import javax.ejb.Remote;
import javax.persistence.EntityManager;

/**
 *
 * @author matt
 */
@Remote
public interface SalesManager {

    public void newSale(Member user, ArrayList<CartItem> cart);

    public List<PurchasedItem> getPurchasedItems(SaleNew sale);

    public List<SaleNew> getPurchases(Member user);

    public String shippingAddressString(Member user);

    public EntityManager getEntityManager();

    public void setEntityManager(EntityManager entityManager);

	public List<SaleUsed> getItemsWon(Member user);

	public void markAsPaymentSent(SaleUsed selectedSale);
	
	public void buyFixedPrice(SaleUsed sale, Member user);
	
}

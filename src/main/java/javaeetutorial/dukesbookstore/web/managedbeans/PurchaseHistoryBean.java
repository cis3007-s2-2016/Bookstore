/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.SalesManager;
import javaeetutorial.dukesbookstore.entity.PurchasedItem;
import javaeetutorial.dukesbookstore.entity.SaleNew;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author matt
 */
@Named(value = "purchaseHistory")
@Dependent
public class PurchaseHistoryBean {

	@EJB
	SalesManager salesManager;
	@Inject
	MemberSessionBean sessionBean;
		private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedbeans.ShoppingCart");

	
	
	public List<SaleNew> getPurchases(){
		return salesManager.getPurchases(sessionBean.getUser());
	}
	public List<PurchasedItem> getPurchasedItems(SaleNew sale){
		return salesManager.getPurchasedItems(sale);
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 *			TO DO:   Make this work. List is empty
	 * 
	 * 
	 * 
	 * 
	 * @param sale
	 * @return 
	 */
	public BigDecimal totalSalePrice(SaleNew sale){
		BigDecimal total = new BigDecimal(0);
		List<PurchasedItem> purchases = getPurchasedItems(sale);
		for (PurchasedItem item : purchases){
			total = item.getPriceEach().multiply(new BigDecimal(item.getQuantity())).add(total);
		}
		return total;
	}
	
	
	
	
	/**
	 * Creates a new instance of PurchaseHistoryBean
	 */
	public PurchaseHistoryBean() {
	}
	
	
	
	
}

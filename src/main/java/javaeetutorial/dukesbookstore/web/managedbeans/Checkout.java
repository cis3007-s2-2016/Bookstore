/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.CatalogManager;
import javaeetutorial.dukesbookstore.ejb.SalesManager;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.omnifaces.util.Ajax;

/**
 *
 * @author matt
 */
@Named(value = "checkout")
@SessionScoped
public class Checkout implements Serializable {
	
	@EJB
	private SalesManager salesManager;
	
	@EJB
	private CatalogManager catalogManager;
	
	@Inject
	private ShoppingCart cart;
	
	@Inject
	private MemberSessionBean memberSession;
	
	@Inject
	private ItemsWonBean winBean;
	
	private String cardnumber;
	private String expiryMonth;
	private String expiryYear;
	private String csv;
	private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedbeans.ShoppingCart");
	
	public SalesManager getSalesManager() {
		return salesManager;
	}
	
	public void setSalesManager(SalesManager salesManager) {
		this.salesManager = salesManager;
	}
	
	public String getCardnumber() {
		return cardnumber;
	}
	
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	public String getExpiryMonth() {
		return expiryMonth;
	}
	
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	
	public String getExpiryYear() {
		return expiryYear;
	}
	
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	
	public String getCsv() {
		return csv;
	}
	
	public void setCsv(String csv) {
		this.csv = csv;
	}
	
	public ShoppingCart getCart() {
		return cart;
	}
	
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	
	public MemberSessionBean getMemberSession() {
		return memberSession;
	}
	
	public void setMemberSession(MemberSessionBean memberSession) {
		this.memberSession = memberSession;
	}

	/**
	 * Creates a new instance of Checkout
	 */
	public Checkout() {
	}
	
	public void pay() {
		
		if (makeTransaction()) {
			destroyPrivateData();
		} else {
			//todo: handle error from 3rd party payment processing
		}
		decrementStock();
		getCart().clearCart();
		Ajax.data("PaymentSuccess", "true");
		Ajax.data("cartCount", 0);
		Ajax.oncomplete("paymentsuccess()");
	}
	
	public void paySeller(){
		try {
			sendPaymentToSeller();
		} catch(Exception e){
			//todo: handle error from 3rd party payment processing
		}
		destroyPrivateData();
		winBean.getSelectedSale().setPaid(true);
		getSalesManager().markAsPaymentSent(winBean.getSelectedSale());
		Ajax.data("PaymentSuccess", "true");
		Ajax.oncomplete("auctionpaymentsuccess()");
	}

	public Checkout(SalesManager salesManager, ShoppingCart cart, MemberSessionBean memberSession, String cardnumber, String expiryMonth, String expiryYear, String csv) {
		this.salesManager = salesManager;
		this.cart = cart;
		this.memberSession = memberSession;
		this.cardnumber = cardnumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.csv = csv;
	}
	
	private void decrementStock(){
		for (CartItem item : getCart().getShoppingCart()){
			catalogManager.buyBooks(item.getBook(), item.getQuantity());
		}
	}
	
	private boolean makeTransaction() {
		try {
			//Send credit card data to third party payment API
		} catch (Exception e) {
			return false;
		}
		
		try {
			getSalesManager().newSale(getMemberSession().getUser(), getCart().getShoppingCart());
		} catch (Exception e) {
			logger.severe("Failed to create sale record!!");
		}
		
		return true;
	}
	private void sendPaymentToSeller(){
		// Make transaction and send to seller
		return;
	}
	
	private void destroyPrivateData() {
		setCardnumber(null);
		setCsv(null);
		setExpiryMonth(null);
		setExpiryYear(null);
	}
}

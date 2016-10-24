/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.CatalogManager;
import javaeetutorial.dukesbookstore.ejb.SalesManager;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author matt
 */
@Named(value = "itemsWonBean")
@SessionScoped
public class ItemsWonBean implements Serializable {

	
	
	@EJB
	SalesManager salesManager;
	@Inject
	MemberSessionBean memberSession;
	private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedbeans.ItemsWonBean");
	private static final long serialVersionUID = -2181710426297811604L;
	
	
	private SaleUsed selectedSale;

	
	
	

	public SaleUsed getSelectedSale() {
		return selectedSale;
	}

	public void setSelectedSale(SaleUsed selectedSale) {
		this.selectedSale = selectedSale;
	}
	
	
	
	
	public List<SaleUsed> getItemsWon(){
		try{
		return salesManager.getItemsWon(memberSession.getUser());
		} catch(Exception e){
			return new ArrayList<>();
		}
	}
	
	public String payFor(SaleUsed sale){
		setSelectedSale(sale);
		return "pay-for-item";
	}
	
	public BigDecimal totalPriceSelectedItem(){
		return getSelectedSale().getPostage().add(getSelectedSale().getAmount());
	}
}

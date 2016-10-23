/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import javaeetutorial.dukesbookstore.ejb.MemberSaleManager;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.BookRequestBean;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.exception.BookNotFoundException;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;


@Named("newMemberSale")
@ViewScoped
public class NewSaleBean implements Serializable{
    
    Logger logger = Logger.getLogger(NewSaleBean.class.getTypeName());
    
    @Inject
    MemberSaleManager memberSaleManager;
    
    @Inject
    BookRequestBean bookRequest;
    @Inject
    MemberSessionBean memberSession;
    
    private SaleUsed memberSale;
	private boolean myBookIsntListed = false;

	public boolean getMyBookIsntListed() {
		return myBookIsntListed;
	}

	public void setMyBookIsntListed(boolean myBookIsntListed) {
		this.myBookIsntListed = myBookIsntListed;
	}
	
    private String bookISBN = "";

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }
    
    

    public NewSaleBean() {
    }
    
    @PostConstruct
    public void postConstruct()
    {
        memberSale = new SaleUsed();
        memberSale.setSelleridId(memberSession.getUser());
        memberSale.setPostage(BigDecimal.ZERO);
        memberSale.setReserveprice(BigDecimal.ZERO);
        memberSale.setSaleprice(BigDecimal.ZERO);
        memberSale.setStartprice(BigDecimal.ZERO);
        memberSale.setAmount(BigDecimal.ONE);
        memberSale.setCommission(BigDecimal.valueOf(0.05));
    }
    
    
    
    public SaleUsed getMemberSale() {
        return this.memberSale;
    }
    
    public void onAjaxSubmit(AjaxBehaviorEvent event)
    {
        logger.info("Submitting new user sale");
        memberSaleManager.persist(memberSale);
    }
    
    public void debugOnAjax(AjaxBehaviorEvent event)
    {
        logger.info("Yes ajax was actually called.");
    }
    
    public void updateBookFromBookISBN(AjaxBehaviorEvent event) throws BookNotFoundException
    {
        Book toSell = bookRequest.getBook(bookISBN);
        memberSale.setIsbn(toSell);
        
        logger.info("Updated book based on book isbn");
    }

}

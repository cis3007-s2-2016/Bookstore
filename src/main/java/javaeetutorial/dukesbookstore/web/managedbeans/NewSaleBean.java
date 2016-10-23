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
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;


@Named("newMemberSale")
@ViewScoped
public class NewSaleBean implements Serializable{
    
    Logger logger = Logger.getLogger(NewSaleBean.class.getTypeName());
    
    @Inject
    MemberSaleManager memberSaleManager;
    
    
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
        
        logger.info("MEMBER SALE IS: " + memberSale.toString());
    }
    
    
    
    public SaleUsed getMemberSale() {
        return this.memberSale;
    }

    
    
    public void onAjaxSubmit(AjaxBehaviorEvent event)
    {
        logger.info("AJAX EVENT IN NEW SALE BEAN");
        memberSaleManager.persist(memberSale);
    }

}

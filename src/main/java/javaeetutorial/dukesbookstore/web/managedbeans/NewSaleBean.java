/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import javaeetutorial.dukesbookstore.ejb.MemberSaleManager;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.EJB;


@Named("newMemberSale")
@ViewScoped
public class NewSaleBean implements Serializable{
    
    Logger logger = Logger.getLogger(NewSaleBean.class.getTypeName());
    
    @Inject
    MemberSaleManager memberSaleManager;
    
    private final SaleUsed memberSale = new SaleUsed();
    
    public NewSaleBean()
    {
        memberSale.setSaletype("auction");
        memberSale.setSaleprice(BigDecimal.ZERO);
        memberSale.setReserveprice(BigDecimal.ZERO);
        memberSale.setDuration(0);
        memberSale.setPaid(false);
        memberSale.setStartprice(BigDecimal.ZERO);
        memberSale.setPostage(BigDecimal.ZERO);
        
        logger.info(memberSale.toString());
    }
    

    public SaleUsed getMemberSale() {
        return this.memberSale;
    }

    
    public void submit(Member member)
    {
        if(null == memberSale)
        {
            return;
        }
        memberSale.setSelleridId(member);
        memberSaleManager.persist(memberSale);
    }
    
    

}

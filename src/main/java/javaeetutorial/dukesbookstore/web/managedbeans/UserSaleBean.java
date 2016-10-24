/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import javaeetutorial.dukesbookstore.ejb.MemberSaleManager;
import javaeetutorial.dukesbookstore.entity.AuctionBid;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javax.enterprise.context.Dependent;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kyle.Lewer
 */
@Named("userSale")
@SessionScoped
public class UserSaleBean extends AbstractBean implements Serializable{
    
    @Inject
    MemberSaleManager saleManager;
    @Inject
    MemberSessionBean memberSession;
    
    private SaleUsed selectedSale;
    
    private double bidValue = 0.0;
    private boolean bidFailed = false;
    public double getBidValue() {
        return bidValue;
    }

    public void setBidValue(double bidValue) {
        this.bidValue = bidValue;
    }
    
    public SaleUsed getSelectedSale() {
        return selectedSale;
    }

    public void setSelectedSale(SaleUsed selectedSale) {
        this.selectedSale = selectedSale;
    }

    public String checkout(SaleUsed selectedSale){
        if(selectedSale.getSaletype().equals("auction"))
        {
            AuctionBid b = new AuctionBid();
            b.setMemberidId(memberSession.getUser());
            b.setSaleidId(selectedSale);
            b.setBidvalue((float) bidValue);
            if(saleManager.createBid(b))
            {
                return cancelPurchase();
            }
            else bidFailed = true;
            return null;
        }
        return cancelPurchase();
    }
    
    public String cancelPurchase()
    {
        this.bidValue = 0.0;
        this.selectedSale = null;
        return "/auctions/index.xhtml";
    }
    
    public String getNiceConditionName()
    {
        String condition = selectedSale.getItemcondition();
        
        if(null == condition)
        {
            return "Not Specified";
        }
        
        if(condition.equalsIgnoreCase("new")) return "New";
        if(condition.equalsIgnoreCase("good")) return "Good";
        if(condition.equalsIgnoreCase("minor")) return "Minor Damage";
        if(condition.equalsIgnoreCase("poor")) return "Poor Condition";
        
        return "Not Specified";
    }
    
    public BigDecimal getLatestBid()
    {
        if(selectedSale.getSaletype().equals("auction"))
        {
            if(selectedSale.getSaleprice().equals(BigDecimal.valueOf(0.0)))
            {
                return selectedSale.getStartprice();
            }
        }
        
        return selectedSale.getSaleprice();
        
    }
    
    public BigDecimal getCurrentTotal()
    {
        
        if(selectedSale.getSaletype().equals("auction"))
        {
            if(BigDecimal.valueOf(bidValue).subtract(getLatestBid()).doubleValue()>0)
            {
                return selectedSale.getPostage().add(BigDecimal.valueOf(bidValue));
            }
        }
        return selectedSale.getSaleprice().add(selectedSale.getPostage());
        
    }
    

}

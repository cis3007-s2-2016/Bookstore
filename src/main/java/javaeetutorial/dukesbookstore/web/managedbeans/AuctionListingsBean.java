/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.MemberSaleManager;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
/**
 *
 * @author matt
 */
@Named(value = "auctionListingsBean")
@ViewScoped
public class AuctionListingsBean extends AbstractBean{
    Logger logger = Logger.getLogger(AuctionListingsBean.class.getTypeName());
        
    @Inject
    MemberSaleManager saleManager;
    
    @Inject
    UserSaleBean saleBean;
    /**
     * Creates a new instance of AuctionListingsBean
     */

    private boolean filterFixedSaleOnly = false;
    private boolean filterAuctionsOnly = false;

    public boolean isFilterFixedSaleOnly() {
        return filterFixedSaleOnly;
    }

    public void setFilterFixedSaleOnly(boolean filterFixedSaleOnly) {
        this.filterFixedSaleOnly = filterFixedSaleOnly;
    }

    public boolean isFilterAuctionsOnly() {
        return filterAuctionsOnly;
    }

    public void setFilterAuctionsOnly(boolean filterAuctionsOnly) {
        this.filterAuctionsOnly = filterAuctionsOnly;
    }
        
    public AuctionListingsBean() {
    }


    public List<SaleUsed> getActiveMemberSales()
    {
        
        return saleManager.getActiveSales();
    }
    
    public String bidOrBuy()
    {
            logger.info("bid or buy ....................................");
            return "/auctions/bid-or-buy.xhtml";
    }

        
	
}

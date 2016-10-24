/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.AuctionBid;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javaeetutorial.dukesbookstore.entity.SaleUsed_;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kyle.Lewer
 */
@Named("memberSaleManager")
@Stateless
public class MemberSaleManager{
    Logger logger = Logger.getLogger(MemberSaleManager.class.getTypeName());
    @PersistenceContext(unitName = "bookstorePU")
    EntityManager entityManager;
    
    public void persist(SaleUsed memberSale)
    {
        
        entityManager.persist(memberSale);
    }
    
    public List<SaleUsed> getAllSales()
    {
        TypedQuery<SaleUsed> query;
        query = entityManager.createQuery("SELECT ms FROM SaleUsed ms", SaleUsed.class);
        return query.getResultList();
    }
    
    public List<SaleUsed> getActiveSales()
    {
        TypedQuery<SaleUsed> query;
        query = entityManager.createQuery("SELECT ms FROM SaleUsed ms WHERE ms.paid != true", SaleUsed.class);
        return query.getResultList();
    }
    
    public List<SaleUsed> getActiveBy(Member member)
    {
        TypedQuery<SaleUsed> query;
        query = entityManager.createQuery("SELECT ms FROM SaleUsed ms WHERE ms.selleridId = :member AND ms.paid != true", SaleUsed.class);
        query.setParameter("member", member);
        return query.getResultList();
    }
    
    public List<SaleUsed> getCompleteBy(Member member)
    {
        TypedQuery<SaleUsed> query;
        query = entityManager.createQuery("SELECT ms FROM SaleUsed ms WHERE ms.selleridId = :member AND ms.paid != true", SaleUsed.class);
        query.setParameter("member", member);
        return query.getResultList();
    }
    
    public List<SaleUsed> getLostBy(Member member)
    {
        return null;
    }
    
    public boolean saleExists(long id)
    {
        if(null == entityManager.find(SaleUsed.class, id))
        {
            return false;
        }
        return true;
                
    }
    
    public boolean createBid(AuctionBid bid)
    {
//        TypedQuery<SaleUsed> query;
//        query = entityManager.createQuery("SELECT COUNT(ms) FROM SaleUsed ms WHERE ms.startprice < :bidval AND ms.saleprice < :bidval", Integer.class);
//        query.setParameter("bidval", bid.getBidvalue());
//        query.setParameter("bidval", bid.getBidvalue());
//        if()
//        return false;
        BigDecimal bidValue = BigDecimal.valueOf(bid.getBidvalue());
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        Root<SaleUsed> sale = cq.from(SaleUsed.class);
        Path<BigDecimal> startPrice = sale.get(SaleUsed_.startprice);
        Path<BigDecimal> salePrice = sale.get(SaleUsed_.saleprice);
        cq.select(qb.count(sale))
            .where(qb.and( qb.gt(startPrice, bidValue), qb.gt(salePrice, bidValue)));
        
        if(entityManager.createQuery(cq).getSingleResult() > 0)
        {
            return false;
        }
        
        SaleUsed theSale = entityManager.find(SaleUsed.class, bid.getSaleidId().getId());
        theSale.setSaleprice(bidValue);
        theSale.setBuyeridId(bid.getMemberidId());
        entityManager.merge(theSale);
        entityManager.persist(bid);
        return true;
    }
    
    public void completeSale(SaleUsed sale)
    {
        logger.info("Completing auction");
        sale.setComplete(true);
        entityManager.merge(sale);
    }
    
    public List<SaleUsed> getCompleteSalesBy(Member m)
    {
        TypedQuery<SaleUsed> query;
        query = entityManager.createQuery("SELECT s FROM SaleUsed s WHERE s.complete = TRUE", SaleUsed.class);
        return query.getResultList();
    }
}

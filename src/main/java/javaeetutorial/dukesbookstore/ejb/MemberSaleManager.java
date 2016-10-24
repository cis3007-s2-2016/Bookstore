/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
}

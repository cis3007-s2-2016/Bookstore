/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.io.Serializable;
import java.util.List;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.SellerReview;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Kyle.Lewer
 */
@Stateless
public class MemberReviewManagerBean {
    
    @PersistenceContext
    EntityManager em;
    
    public List<SellerReview> getReviewsSellerHas(Member m)
    {
        TypedQuery<SellerReview> query = (TypedQuery<SellerReview>) em.createQuery(
                "SELECT sr FROM SellerReview sr WHERE sr.reviewed = :member" 
        );
        
        query.setParameter("member", m);
        return query.getResultList();
    }
    
    /**
     *  will throw exception if rev already exists
     * @param sellerRev the book review to persist for the first time 
     */
    public void saveNewReview(SellerReview sellerRev)
    {
        em.persist(sellerRev);
    }
    
    public SellerReview updateReview(SellerReview sellerRev)
    {
        return em.merge(sellerRev);
    }
}

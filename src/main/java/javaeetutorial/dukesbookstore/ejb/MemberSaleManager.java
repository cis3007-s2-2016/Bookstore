/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.io.Serializable;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}

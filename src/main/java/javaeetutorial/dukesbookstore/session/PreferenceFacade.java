/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.session;

import javaeetutorial.dukesbookstore.entity.AuctionNotificationRequest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amanda hugnkiss
 */
@Stateless
public class PreferenceFacade extends AbstractFacade<AuctionNotificationRequest> {

    @PersistenceContext(unitName = "bookstorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreferenceFacade() {
        super(AuctionNotificationRequest.class);
    }
    
}

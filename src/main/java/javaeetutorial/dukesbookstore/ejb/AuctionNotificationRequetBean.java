/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.AuctionNotificationRequest;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kyle.Lewer
 */
@Stateful
public class AuctionNotificationRequetBean {
    @PersistenceContext
    EntityManager em;
    private static final Logger logger 
            = Logger.getLogger("dukesbookstore.ejb.PreferenceRequestBean");
    
    public AuctionNotificationRequetBean() throws Exception {
        
    }
    
    public void createPreference(String isbn, long memberId){
        AuctionNotificationRequest notify = new AuctionNotificationRequest();
        notify.setIsbn(isbn);
        notify.setMemberId(memberId);
        
        em.persist(notify);
    }
    
    public List<AuctionNotificationRequest> getMemberPreferences(Member mem) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}

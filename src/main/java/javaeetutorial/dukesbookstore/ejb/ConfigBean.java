/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.dukesbookstore.ejb;

import javaeetutorial.dukesbookstore.entity.Member;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * <p>Singleton bean that initializes the book database for the bookstore
 * example.</p>
 */
@Singleton 
@Startup
public class ConfigBean {

    @EJB
    private AuctionNotificationRequetBean preferenceRequest;
    @PersistenceContext(unitName = "bookstorePU")
    private EntityManager em;
    


    @PostConstruct
    public void createData() {
        
		/**
		 *	Add two members into database for testing:
		 *	
		 * 
		 *		admin: admin
		 *		user@dukes.com: password
		 * 
		 *	
		 * 
		 * 
		 */
        Member admin = new Member("admin", "admin", "admin");
        admin.setFirstName("Administrator");
        this.getEm().persist(admin);
        
        Member customer = new Member("user@dukes.com", "password", "customer");
        customer.setFirstName("John");
        customer.setSurname("Doe");
        customer.setShippingAddressLine1("Unit 3");
        customer.setShippingAddressLine2("123 Fake Street");
        customer.setShippingCity("Fakeville");
        customer.setShippingState("QLD");
        customer.setShippingPostcode("4000");
        customer.setBillingAddressLine1("100 North Road");
        customer.setBillingCity("Darwin");
        customer.setBillingState("Northern Territory");
        customer.setBillingPostcode("7654");
        this.getEm().persist(customer);
        
//        preferenceRequest.createPreference("pref.notify.auction.complete",
//                "If a user has this preference they will recieve a notification when auctions they are involved in are complete");
//        preferenceRequest.createPreference("pref.notify.auction.outbid",
//                "If a user has this preference they will be notified when they have been outbid");
//        preferenceRequest.createPreference("pref.notify.wishlist.auction",
//                "If a user has this preference they will be notified when an item on their wishlist is listed for auction");
//        preferenceRequest.createPreference("pref.notify.wishlist.sale",
//                "If a user has this preference they will be notified when an item on their wishlist is listed for sale");
//        preferenceRequest.createPreference("pref.notify.auction.expire",
//                "If a user has this preference they will be notified when their auction(s) expires");
//        preferenceRequest.createPreference("pref.notify.interest.newitem.avaliable",
//                "If a user has this preference they will be notified when a new item exists in a genre they are interested");
//        //Notification methods
//        preferenceRequest.createPreference("pref.notify.method.email",
//                "If a user has this preference they will be notified by email");
        
    }

    public ConfigBean() throws Exception{

    }

    public EntityManager getEm() {
        return em;
    }


//        Notification Preferences

        

    public void setEm(EntityManager em) {
        this.em = em;
    }
}

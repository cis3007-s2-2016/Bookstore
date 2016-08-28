/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.dukesbookstore.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * <p>Singleton bean that initializes the book database for the bookstore
 * example.</p>
 */
@Singleton 
@Startup
public class ConfigBean {

    @EJB
    private BookRequestBean bookRequest;
    @EJB
    private PreferenceRequestBean preferenceRequest;

    @PostConstruct
    public void createData() {
//        request.createBook("201", "Duke", "",
//                "My Early Years: Growing Up on *7",
//                30.75, false, 2005, "What a cool book.", 20);
//        request.createBook("202", "Jeeves", "",
//                "Web Servers for Fun and Profit", 40.75, true,
//                2010, "What a cool book.", 20);
//        request.createBook("203", "Masterson", "Webster",
//                "Web Components for Web Developers",
//                27.75, false, 2010, "What a cool book.", 20);
//        request.createBook("205", "Novation", "Kevin",
//                "From Oak to Java: The Revolution of a Language",
//                10.75, true, 2008, "What a cool book.", 20);
//        request.createBook("206", "Thrilled", "Ben",
//                "The Green Project: Programming for Consumer Devices",
//                30.00, true, 2008, "What a cool book.", 20);
//        request.createBook("207", "Coding", "Happy",
//                "Java Intermediate Bytecodes", 30.95, true,
//                2010, "What a cool book.", 20);

        //Notification Preferences
        preferenceRequest.createPreference("pref.notify.auction.complete",
                "If a user has this preference they will recieve a notification when auctions they are involved in are complete");
        preferenceRequest.createPreference("pref.notify.auction.outbid",
                "If a user has this preference they will be notified when they have been outbid");
        preferenceRequest.createPreference("pref.notify.wishlist.auction", 
                "If a user has this preference they will be notified when an item on their wishlist is listed for auction");
        preferenceRequest.createPreference("pref.notify.wishlist.sale", 
                "If a user has this preference they will be notified when an item on their wishlist is listed for sale");
        preferenceRequest.createPreference("pref.notify.auction.expire",
                "If a user has this preference they will be notified when their auction(s) expires");
        preferenceRequest.createPreference("pref.notify.interest.newitem.avaliable",
                "If a user has this preference they will be notified when a new item exists in a genre they are interested");
        //Notification methods
        preferenceRequest.createPreference("pref.notify.method.email", 
                "If a user has this preference they will be notified by email");
        
    }
}

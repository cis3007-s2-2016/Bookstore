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

    @PersistenceContext(unitName = "bookstorePU")
    EntityManager em;
    
    

    @PostConstruct
    public void createData() {
        Member admin = new Member("admin", "admin", "admin");
        admin.setFirstName("Adminstrator");
        em.persist(admin);
        
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
        em.persist(customer);
    }
}

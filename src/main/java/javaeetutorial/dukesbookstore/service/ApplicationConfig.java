/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Kyle.Lewer
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(javaeetutorial.dukesbookstore.service.AuctionBidFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.AuctionFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.AuthorFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.BookFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.BookReviewFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.BookWantedFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.FixedSaleFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.FixedSaleItemFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.InterestFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.MemberFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.PaymentFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.PreferenceFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.SaleFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.SaleItemFacadeREST.class);
        resources.add(javaeetutorial.dukesbookstore.service.SellerReviewFacadeREST.class);
    }
    
}

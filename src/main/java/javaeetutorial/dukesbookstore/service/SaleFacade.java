///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package javaeetutorial.dukesbookstore.service;
//
//import javaeetutorial.dukesbookstore.entity.Sale;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
///**
// *
// * @author amanda hugnkiss
// */
//@Stateless
//public class SaleFacade extends AbstractFacade<Sale> {
//
//    @PersistenceContext(unitName = "bookstorePU")
//    private EntityManager em;
//
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
//
//    public SaleFacade() {
//        super(Sale.class);
//    }
//    
//}

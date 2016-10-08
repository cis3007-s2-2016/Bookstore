///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package javaeetutorial.dukesbookstore.web.managedbeans;
//
//import java.io.Serializable;
//import javaeetutorial.dukesbookstore.entity.Book;
//import javaeetutorial.dukesbookstore.entity.Sale;
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;
//
///**
// *
// * @author amanda hugnkiss
// * 
// * backing bean used by: 
// * addFixedSale.xhtml
// * 
// */
//@Named("newSaleBean")
//@SessionScoped
//public class NewSaleBean extends AbstractBean implements Serializable{
//
//    /**
//     * Creates a new instance of NewSaleBean
//     * @param ISBN
//     */
//
////    private Book ISBN = new Book();
////    
////    private Book book = new Book();
////
////    public NewSaleBean() {
////    }
//
//    public synchronized void add(Book ISBN) {
//        NewSaleItem newSale = new NewSaleItem(ISBN);
//    }
////    private Book book = new Book();
//    private Sale newSale;
//    private String ISBN;
//
//    public Sale getNewSale() {
//        return newSale;
//    }
//
//    public void setNewSale(Sale newSale) {
//        this.newSale = newSale;
//    }
//
//    public String getISBN() {
//        return ISBN;
//    }
//
//    public void setISBN(String ISBN) {
//        this.ISBN = ISBN;
//    }
//     
//    public synchronized void add(String ISBN) {
//        Book book = new Book();
//        book.setISBN(ISBN);
//        newSale = new Sale(book);
//    }
//
//}

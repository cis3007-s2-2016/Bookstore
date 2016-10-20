/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import javaeetutorial.dukesbookstore.entity.Book;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author matt
 */
@Named(value = "cartItem")
@Dependent
public class CartItem {

	private Book book;
	private int quantity;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	public CartItem() {
		this.setQuantity(0);
	}
	public CartItem(Book book){
		this.setQuantity(1);
		setBook(book);
	}
	public void increase(){
		setQuantity(getQuantity() + 1);
	}
	
}

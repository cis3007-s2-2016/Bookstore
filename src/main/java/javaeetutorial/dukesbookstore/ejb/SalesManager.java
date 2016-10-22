/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.ArrayList;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.web.managedbeans.CartItem;
import javaeetutorial.dukesbookstore.web.managedbeans.ShoppingCart;
import javax.ejb.Remote;

/**
 *
 * @author matt
 */
@Remote
public interface SalesManager {

	public void newSale(Member user, ArrayList<CartItem> cart);
	
	
}

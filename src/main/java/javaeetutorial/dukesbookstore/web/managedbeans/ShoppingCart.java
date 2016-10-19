
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.Book;
import javax.enterprise.context.SessionScoped;
import org.omnifaces.util.Ajax;
import javax.inject.Named;


@Named("cart")
@SessionScoped
public class ShoppingCart implements Serializable {

    private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedbeans.ShoppingCart");
	private ArrayList<String> shoppingCart;

	public ArrayList<String> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ArrayList<String> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public ShoppingCart() {
		clearCart();
	}

	private void clearCart() {
		setShoppingCart(new ArrayList<>());
		
	}

	public void add(String isbn){
		getShoppingCart().add(isbn);
		Ajax.data("cartCount", numberOfItems());
		Ajax.oncomplete("updateCartCountBadge()");
	}
	
	public int numberOfItems(){
		return getShoppingCart().size();
	}
	
	
	
	
}

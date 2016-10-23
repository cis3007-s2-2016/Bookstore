/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;
import java.util.List;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.Remote;
/**
 *
 * @author matt
 */
@Remote
public interface MemberManager {
    
	public long createAdmin(String email, String password1, String password2, String firstname, String lastname);
    
	public long createCustomer(
    	String email,
    	String password1,
    	String password2,
    	String firstname,
    	String lastname,
    	String shippingLine1,
    	String shippingLine2,
    	String shippingCity,
    	String shippingState,
    	String shippingPostcode,
    	String billingLine1,
    	String billingLine2,
    	String billingCity,
    	String billingState,
    	String billingPostcode,
		boolean receiveNewsletter
    );
    
    public Member find(String email);
    
    /**
     *
     * @return list of members who are bookstore staff members
     */
    public List<Member> getStaff();
    public List<Member> getCustomers();
	
	public void updateUser(Member user);
	
	public void changePassword(Member user, String password);

    public void persist(Member user);
}

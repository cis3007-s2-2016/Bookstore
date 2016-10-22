/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.List;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author matt
 */
@Stateless
public class MemberManagerBean implements MemberManager {
    
    @PersistenceContext(unitName="bookstorePU")
    private EntityManager entityManager;  
    
    @Override
    public long createAdmin(String email, String password1, String password2, String firstname, String lastname){
        if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")){
            throw new RuntimeException("Email  is invalid.");
        }
        if (!password1.matches("^[!-~]{6,250}$") ){
            throw new RuntimeException("Password must contain at least 6 characters.");
        }
        if (!password1.equals(password2)){
            throw new RuntimeException("Passwords do no match.");
        }
        Member member = new Member(email, password1, "admin");
        member.setFirstName(firstname);
        member.setSurname(lastname);
        entityManager.persist(member);
        return member.getId();
    }

    @Override
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
		boolean receiveNewsletters ){
        
        if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")){
            throw new RuntimeException("Email  is invalid.");
        }
        if (!password1.matches("^[!-~]{6,250}$") ){
            throw new RuntimeException("Password must contain at least 6 characters.");
        }
        if (!password1.equals(password2)){
            throw new RuntimeException("Passwords do no match.");
        }
        Member member = new Member(email, password1, "customer");
        member.setFirstName(firstname);
        member.setSurname(lastname);
        member.setShippingAddressLine1(shippingLine1);
        member.setShippingAddressLine2(shippingLine2);
        member.setShippingCity(shippingCity);
        member.setShippingState(shippingState);
        member.setShippingPostcode(shippingPostcode);
        member.setBillingAddressLine1(billingLine1);
        member.setBillingAddressLine2(billingLine2);
        member.setBillingCity(billingCity);
        member.setBillingState(billingState);
        member.setBillingPostcode(billingPostcode);
		member.setReceiveNewletter(receiveNewsletters);
        
        entityManager.persist(member);
        return member.getId();
    }
    
    @Override
    public Member find(String email){
        TypedQuery<Member> query = entityManager.createQuery("SELECT u FROM Member u WHERE u.email=:email", Member.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
    
    
    public List<Member> getStaff()
    {
      String adminPermissionGroup = "admin";
      TypedQuery<Member> query = entityManager.createQuery("SELECT m FROM Member m WHERE m.permissionGroup = :permissionGroup", Member.class);
      query.setParameter("permissionGroup", adminPermissionGroup);
      return query.getResultList();
    }
    public MemberManagerBean(){

    }

	@Override
	public void persist(Member user) {
		entityManager.persist(user);
	}
    
}

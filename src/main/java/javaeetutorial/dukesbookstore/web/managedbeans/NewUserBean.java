/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javaeetutorial.dukesbookstore.ejb.MemberManager;

/**
 *
 * @author matt
 */
@Named
@RequestScoped
public class NewUserBean extends AbstractBean implements Serializable{

    @EJB
    private MemberManager memberManager;
    
    
    /**
     * Creates a new instance of NewUserBean
     */
    public NewUserBean() {
    }
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String password2;
    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingPostcode;
    private String shippingCity;
    private String shippingState;
    private boolean billingSameAsShippingAddress;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingCity;
    private String billingState;
    private String billingPostcode;


    public MemberManager getMemberManager() {
        return memberManager;
    }

    public void setMemberManager(MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getShippingAddressLine1() {
        return shippingAddressLine1;
    }

    public void setShippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1;
    }

    public String getShippingAddressLine2() {
        return shippingAddressLine2;
    }

    public void setShippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public boolean isBillingSameAsShippingAddress() {
        return billingSameAsShippingAddress;
    }

    public void setBillingSameAsShippingAddress(boolean billingSameAsShippingAddress) {
        this.billingSameAsShippingAddress = billingSameAsShippingAddress;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingPostcode() {
        return billingPostcode;
    }

    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode = billingPostcode;
    }
    
    
    
    public String createCustomer(){
        try {
        long memberId = memberManager.createCustomer(
                this.getEmail(), 
                this.getPassword(), 
                this.getPassword2(),
                this.getFirstname(),
                this.getLastname(),
                this.getShippingAddressLine1(),
                this.getShippingAddressLine2(),
                this.getShippingCity(),
                this.getShippingState(),
                this.getShippingPostcode(),
                this.getBillingAddressLine1(),
                this.getBillingAddressLine2(),
                this.getBillingCity(),
                this.getBillingState(),
                this.getBillingPostcode()
                );
        } catch (Exception e){
            //todo return error page
        }
        return ("index");
    }
    
    public String createAdmin(){
        try{
            long memberID = memberManager.createAdmin(this.getEmail(), this.getPassword(), this.getPassword2(), this.getFirstname(), this.getLastname());
        } catch (Exception e){
            //todo return error page
        }
        return ("admin/sales-report");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author matt
 */
@Named
@SessionScoped
public class MemberSessionBean implements Serializable{

    @PersistenceContext(unitName="bookstorePU")
    private EntityManager entityManager;
    
    private Member user;
    private String username;
    private String password;

    
    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/bookreceipt";
    }
    
    public boolean isAdmin(){
        if (this.getUser() == null){
            return false;
        } 
        return this.getUser().getPermissionGroup().equalsIgnoreCase("admin");
    }
    public boolean isCustomer(){
        if (this.getUser() == null){
            return false;
        }
        return this.getUser().getPermissionGroup().equalsIgnoreCase("customer");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import javaeetutorial.dukesbookstore.ejb.MemberManager;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
    
    @EJB
    MemberManager memberManager;
    
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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MemberManager getMemberManager() {
        return memberManager;
    }

    public void setMemberManager(MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    
    
    public MemberSessionBean(){
    }
    
    public String firstname(){
        if (this.getUser() != null){
            return this.getUser().getFirstName();
        }
        else{
            return "no user";
        }
    }
    
    
    public String login(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String nextPage;

        try {
            request.login(
                this.getUsername(),
                this.getPassword());

            //System.out.println("Succesfully logged in user: " + this.getUsername());

            this.setUser(this.getMemberManager().find(getUsername()));
            this.getAuthenticatedUser();

            if (isAdmin()) {
                nextPage = "/admin/add-new-book";
            } else {
                nextPage = "/index";
            }
        } catch (Exception e) {
            //System.out.println("Failed to authenticate user: " + this.getUsername() +" with password: " + this.getPassword() + "  :  " + e.getLocalizedMessage());
            nextPage = "/login.xhtml?error=true";
        }

        return nextPage;
    }
    
    
    public String logout() {
        try{
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = (HttpSession) request.getSession(false);
        
            request.logout();
            session.invalidate();
        
            this.setUser(null);
        } catch (Exception e){
            //System.out.println("failed to logout user: " + e.getLocalizedMessage());
            return "/index";
        }
        return "/bookreceipt";
    }
    
    @Produces
    @com.forest.qualifiers.LoggedIn
    public Member getAuthenticatedUser() {
        return user;
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
 
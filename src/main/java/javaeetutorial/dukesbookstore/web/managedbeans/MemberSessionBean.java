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

    @EJB
    MemberManager memberManager;

    private String username;
    private String password;



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


    public MemberSessionBean(){
    }
    

    
    public String login(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String nextPage;

        try {
            request.login(
                this.getUsername(),
                this.getPassword());



            request.getSession().setAttribute("user", this.user());

            //if (isAdmin()) {
            //    nextPage = "/admin/add-new-book";
            //} else {
                nextPage = "/index";
            //}
        } catch (Exception e) {
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

        } catch (Exception e){
            return "/index";
        }
        return "/index";
    }

    
    public boolean isAdmin(){
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            return request.isUserInRole("admin");
        } catch (Exception e){
            return false;
        }
    }
    public boolean isCustomer(){
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            return request.isUserInRole("customer");
        } catch (Exception e){
            return false;
        }
    }

    public Member user(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            return (Member) request.getAttribute("user");
        } catch (Exception e) {
            throw new RuntimeException("No current user logged in");
        }
    }

    public String firstname(){
        try {
            return this.user().getFirstName();
        } catch (Exception e){
            return e.getMessage();
        }
    }

}
 
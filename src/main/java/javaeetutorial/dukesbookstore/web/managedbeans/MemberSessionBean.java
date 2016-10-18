/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javaeetutorial.dukesbookstore.ejb.MemberManager;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author matt
 */
@Named("memberSession")
@SessionScoped
public class MemberSessionBean implements Serializable{

    @EJB
    MemberManager memberManager;

    private String username;
    private String password;
    private Member user;
    private int loginAttemptCount;


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

    private Member getUser() {
        return user;
    }

    private void setUser(Member user) {
        this.user = user;
    }

    private int getLoginAttemptCount() {
        return loginAttemptCount;
    }

    private void setLoginAttemptCount(int loginAttemptCount) {
        this.loginAttemptCount = loginAttemptCount;
    }

    public MemberSessionBean(){
        setLoginAttemptCount(0);
    }



    

    
    public String login(){
        try {
            this.setUser(memberManager.find(this.getUsername()));
            String password = this.hashedPassword(this.getPassword());
            System.out.println(this.getPassword() + ",  " + password + "     " + getUser().getPassword());
            if (!password.equals(this.getUser().getPassword())) {
                throw new RuntimeException("Incorrect password");
            }
            this.setLoginAttemptCount(0);
            System.out.println(this.getUser().getFirstName() + " logged in.");


            if (isAdmin()){
                System.out.println("Authenticated user is Admin. Returning admin panel");
                return "/admin/activity-summary";
            }
            if (isCustomer()){
                System.out.println("Authenticated user is Customer. Returning customer dashboard");
                return "/dashboard";
            }


        } catch (Exception e){
            this.setUser(null);
            this.setUsername(null);
            this.setPassword(null);
            this.setLoginAttemptCount(this.getLoginAttemptCount() + 1);
            System.out.println("Incorrect password:\n");

            try {
                if (this.getLoginAttemptCount() > 5){
                    Thread.sleep(10000);
                    /* todo: lock account */
                }
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                System.out.print(e1.getMessage());
                return "/login.xhtml?faces-redirect=true&error=true";
            }
        }
        return "/login.xhtml?faces-redirect=true&error=true";

    }
    
    
    public void logout() {
        try{
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.invalidateSession();
            context.redirect(context.getRequestContextPath() + "/index.xhtml");
        } catch (Exception e){
            System.out.println("Failed to log out! " + e.getMessage());
        }
    }

    
    public boolean isAdmin() {
        return this.getUser() != null && this.getUser().getPermissionGroup().equalsIgnoreCase("admin");
    }

    public boolean isCustomer(){
        return this.getUser() != null && this.getUser().getPermissionGroup().equalsIgnoreCase("customer");
    }

    public boolean hasPermissionGroup(String groupName){
        if (groupName.equalsIgnoreCase("admin")){
            return this.isAdmin();
        }
        if (groupName.equalsIgnoreCase("customer")){
            return this.isCustomer();
        }
        return false;
    }

    public Member user(){
        return this.getUser();
    }

    public String getFirstname(){
        if (this.getUser() == null){
            return null;
        }
        return this.user().getFirstName();
    }

    public boolean isLoggedIn() {
        return this.getUser() != null;
    }

    private String hashedPassword(String password){
        byte byteData[];
        try {

            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byteData = md.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Failed to encrypt password!");
            return "";
        }


        StringBuilder hexString = new StringBuilder();
        for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);
        }
        return hexString.toString();

    }


}
 
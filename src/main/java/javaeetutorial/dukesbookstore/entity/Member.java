/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author kyle.lewer
 */
@Entity
@Table(name="members")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String email;
    private Date joinDate;
    private String billingAddress;
    private String deliveryAddress;
    
    @ManyToMany
    private List<Interest> userInterests;
    
    @ManyToMany
    private List<Preference> userPreferences;
    
    @ManyToMany(mappedBy = "groupMembers")
    private List<PrivilageGroup> groups;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getUsername()
    {
        return username;
    }

    public List<PrivilageGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PrivilageGroup> groups) {
        this.groups = groups;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<Preference> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<Preference> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public List<Interest> getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(List<Interest> userInterests) {
        this.userInterests = userInterests;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member)) {
            return false;
        }
        Member other = (Member) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "javaeetutorial.dukesbookstore.entity.Member[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javaeetutorial.dukesbookstore.ejb.MemberManager;
import javaeetutorial.dukesbookstore.ejb.MemberManagerBean;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kyle.lewer
 */
@Named("staff")
@SessionScoped
public class StaffBean extends AbstractBean {
    
    @EJB
    MemberManager memberManager;
    
    public List<Member> getStaffMembers()
    { 
        return memberManager.getStaff();
    }
    
}

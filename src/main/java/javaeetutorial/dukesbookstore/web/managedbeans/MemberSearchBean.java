/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javaeetutorial.dukesbookstore.ejb.MemberManager;
import javaeetutorial.dukesbookstore.ejb.MemberManagerBean;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kyle.Lewer
 */
@Named("memberSearchBean")
@ViewScoped
public class MemberSearchBean implements Serializable {
    
    @EJB
    MemberManager memberManager;
   
    
    public MemberSearchBean(){}
    
    private String memberSearchString = "";
    
    public String getMemberSearchString() {
        return memberSearchString;
    }

    public void setMemberSearchString(String memberSearchString) {
        this.memberSearchString = memberSearchString;
    }
    
    public List<Member> getUsersWithNameLike()
    {
        Predicate<Member> nameLike = (Member m) ->
                (m.getFirstName().contains(memberSearchString) || m.getSurname().contains(memberSearchString));
      
        return memberManager.getCustomers().stream().filter(nameLike).collect(Collectors.toList());
    }
    
    
}

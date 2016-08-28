/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.List;
import javaeetutorial.dukesbookstore.entity.Interest;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kyle.Lewer
 */
@Stateful
public class InterestRequestBean {
    @PersistenceContext
    EntityManager em;
    
    public void createData(String genre){
        Interest interest = new Interest();
        interest.setGenre(genre);
        
        em.persist(interest);
    }
    
    public List<Interest> getMemberInterests(Member mem){
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

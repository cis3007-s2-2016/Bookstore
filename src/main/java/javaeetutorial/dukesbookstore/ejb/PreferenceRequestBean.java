/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.Preference;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kyle.Lewer
 */
@Stateful
public class PreferenceRequestBean {
    @PersistenceContext
    EntityManager em;
    private static final Logger logger 
            = Logger.getLogger("dukesbookstore.ejb.PreferenceRequestBean");
    
    public PreferenceRequestBean() throws Exception {
        
    }
    
    public void createPreference(String preference, String description){
        Preference pref = new Preference();
        pref.setPreference(preference);
        pref.setPreferenceDescription(description);
        
        em.persist(pref);
    }
    
    public List<Preference> getMemberPreferences(Member mem) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kyle.Lewer
 */
@Entity
@XmlRootElement
public class Preference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    String preference;
    String preferenceDescription;

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getPreferenceDescription() {
        return preferenceDescription;
    }

    public void setPreferenceDescription(String preferenceDescription) {
        this.preferenceDescription = preferenceDescription;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preference != null ? preference.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preference)) {
            return false;
        }
        Preference other = (Preference) object;
        if ((this.preference == null && other.preference != null) || (this.preference != null && !this.preference.equals(other.preference))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaeetutorial.dukesbookstore.entity.Preference[ preference=" + preference + " ]";
    }
    
}

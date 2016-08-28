/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Kyle.Lewer
 */
@Entity
public class Interest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String genre;
    
    public void setGenre(String genre) { this.genre = genre; }
    public String getGenre() { return genre; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genre != null ? genre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the genre fields are not set
        if (!(object instanceof Interest)) {
            return false;
        }
        Interest other = (Interest) object;
        if ((this.genre == null && other.genre != null) || (this.genre != null && !this.genre.equals(other.genre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaeetutorial.dukesbookstore.entity.Interest[ id=" + genre + " ]";
    }
    
}

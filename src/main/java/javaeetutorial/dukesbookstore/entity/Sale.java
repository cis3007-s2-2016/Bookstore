/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kyle.Lewer
 */
@Entity
@Table(name="sales")
@XmlRootElement
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @ManyToOne
    private Member buyer;
    
    @ManyToOne
    private Member seller;
    
    @OneToOne
    private Payment payment;
    
    private Date dateListed;

    public Date getDateListed() {
        return dateListed;
    }

    public void setDateListed(Date dateListed) {
        this.dateListed = dateListed;
    }
    public Float getPostage() {
        return postage;
    }

    public void setPostage(Float postage) {
        this.postage = postage;
    }
    private Float postage;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaeetutorial.dukesbookstore.entity.MemberSale[ id=" + id + " ]";
    }
    
}

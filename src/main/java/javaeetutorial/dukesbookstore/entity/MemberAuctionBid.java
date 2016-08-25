/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Kyle.Lewer
 */
@Entity
public class MemberAuctionBid implements Serializable, Comparable<MemberAuctionBid> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   
    private Float amount;
    private Float bidValue;


    @ManyToOne
    private MemberAuction auction;
    
    public Float getSalePrice() {
        return bidValue;
    }

    public void setSalePrice(Float salePrice) {
        this.bidValue = salePrice;
    }
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public MemberAuction getAuction() {
        return auction;
    }

    public void setAuction(MemberAuction auction) {
        this.auction = auction;
    }
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
        if (!(object instanceof MemberAuctionBid)) {
            return false;
        }
        MemberAuctionBid other = (MemberAuctionBid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaeetutorial.dukesbookstore.entity.Bid[ id=" + id + " ]";
    }

    @Override
    public int compareTo(MemberAuctionBid otherBid) {
        // Ideally we only want to compare bids on the same auction
        if(otherBid.auction.equals(this.auction)){
            //descending order
            return Float.compare(this.amount, otherBid.amount);
        }
        else
        {
            System.err.println("Only bids of the same auction should be compared");
            return 0;
        }
    }
    
}

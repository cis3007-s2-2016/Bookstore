/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kyle.Lewer
 * 
 */

@Entity
@Table(name="auction_bids")
@XmlRootElement
 public class AuctionBid implements Serializable { //, Comparable<AuctionBid> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Sale saleId;
    
    @ManyToOne
    private Member memberId;
    
    private LocalDateTime bidTime;
    
    private Float bidValue;


//    @ManyToOne
//    private Auction auction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sale getSaleId() {
        return saleId;
    }

    public void setSaleId(Sale saleId) {
        this.saleId = saleId;
    }

    public Member getMemberId() {
        return memberId;
    }

    public void setMemberId(Member memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }

    public Float getBidValue() {
        return bidValue;
    }

    public void setBidValue(Float bidValue) {
        this.bidValue = bidValue;
    }
   
//    public Auction getAuction() {
//        return auction;
//    }
//
//    public void setAuction(Auction auction) {
//        this.auction = auction;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuctionBid)) {
            return false;
        }
        AuctionBid other = (AuctionBid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
 
    //@Override
    public String toString(LocalDateTime bidTime) {
        SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy HH:mm:ss" );
        return formatter.format( bidTime ) ; 
        //return "javaeetutorial.dukesbookstore.entity.Bid[ id=" + id + " ]";
    }
    //@Override
    public String toString(Long id) {
        return "javaeetutorial.dukesbookstore.entity.Bid[ id=" + id + " ]";
    }
    
//    @Override
//    public int compareTo(AuctionBid otherBid) {
//        // Ideally we only want to compare bids on the same auction
//        if(otherBid.auction.equals(this.auction)){
//            //descending order
//            return Float.compare(this.amount, otherBid.amount);
//        }
//        else
//        {
//            System.err.println("Only bids of the same auction should be compared");
//            return 0;
//        }
//    }
    
}

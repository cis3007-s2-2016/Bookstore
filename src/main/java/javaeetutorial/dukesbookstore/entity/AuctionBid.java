/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amanda hugnkiss
 */
@Entity
@Table(name = "auction_bid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuctionBid.findAll", query = "SELECT a FROM AuctionBid a"),
    @NamedQuery(name = "AuctionBid.findById", query = "SELECT a FROM AuctionBid a WHERE a.id = :id"),
    @NamedQuery(name = "AuctionBid.findByBidvalue", query = "SELECT a FROM AuctionBid a WHERE a.bidvalue = :bidvalue")})
public class AuctionBid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Lob
    @Column(name = "BIDTIME")
    private byte[] bidtime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BIDVALUE")
    private Float bidvalue;
    @JoinColumn(name = "MEMBERID_ID", referencedColumnName = "ID")
    @ManyToOne
    private Member memberidId;
    @JoinColumn(name = "SALEID_ID", referencedColumnName = "ID")
    @ManyToOne
    private SaleUsed saleidId;

    public AuctionBid() {
    }

    public AuctionBid(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBidtime() {
        return bidtime;
    }

    public void setBidtime(byte[] bidtime) {
        this.bidtime = bidtime;
    }

    public Float getBidvalue() {
        return bidvalue;
    }

    public void setBidvalue(Float bidvalue) {
        this.bidvalue = bidvalue;
    }

    public Member getMemberidId() {
        return memberidId;
    }

    public void setMemberidId(Member memberidId) {
        this.memberidId = memberidId;
    }

    public SaleUsed getSaleidId() {
        return saleidId;
    }

    public void setSaleidId(SaleUsed saleidId) {
        this.saleidId = saleidId;
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
        if (!(object instanceof AuctionBid)) {
            return false;
        }
        AuctionBid other = (AuctionBid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.AuctionBid[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.util.List;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kyle.Lewer
 */
@Entity
@Table(name="auctions")
@XmlRootElement
public class Auction extends Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private String auctionStatus;
    private Float reserve;
    private List<SaleItem> saleItems;


    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Float getReserve() {
        return reserve;
    }

    public void setReserve(Float reserve) {
        this.reserve = reserve;
    }

    @Override
    public String toString() {
        return "javaeetutorial.dukesbookstore.entity.Auction[ id=" + id + " ]";
    }
    
}

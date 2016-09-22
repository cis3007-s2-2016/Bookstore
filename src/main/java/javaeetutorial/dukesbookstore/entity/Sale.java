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
    protected Long      id;
    @ManyToOne
    private Book        ISBN;
    private String      saleType; //auction or sale
    private String      itemCondition;
    private int         postage;
    private int         startPrice;
    private int         salePrice;
    private int         reservePrice;
    private int         duration;
    @ManyToOne
    private Member      buyerId;
    @ManyToOne
    private Member      sellerId;
    @OneToOne
    private Payment     paymentId;    
    private LocalDateTime        dateListed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getISBN() {
        return ISBN;
    }

    public void setISBN(Book ISBN) {
        this.ISBN = ISBN;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public int getPostage() {
        return postage;
    }

    public void setPostage(int postage) {
        this.postage = postage;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Member getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Member buyerId) {
        this.buyerId = buyerId;
    }

    public Member getSellerId() {
        return sellerId;
    }

    public void setSellerId(Member sellerId) {
        this.sellerId = sellerId;
    }

    public Payment getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Payment paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getLocalDateTimeListed() {
        return dateListed;
    }

    public void setLocalDateTimeListed(LocalDateTime dateListed) {
        this.dateListed = dateListed;
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
    //@Override
    public String toString(LocalDateTime dateListed) {
        SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy HH:mm:ss" );
        return formatter.format( dateListed ) ;   
    }
}

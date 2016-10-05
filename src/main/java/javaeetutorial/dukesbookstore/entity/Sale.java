/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.faces.convert.Converter;

/**
 *
 * @author amanda hugnkiss
 */
@Entity
@Table(name = "sale")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s"),
    @NamedQuery(name = "Sale.findById", query = "SELECT s FROM Sale s WHERE s.id = :id"),
    @NamedQuery(name = "Sale.findByDatelisted", query = "SELECT s FROM Sale s WHERE s.datelisted = :datelisted"),
    @NamedQuery(name = "Sale.findByDuration", query = "SELECT s FROM Sale s WHERE s.duration = :duration"),
    @NamedQuery(name = "Sale.findByItemcondition", query = "SELECT s FROM Sale s WHERE s.itemcondition = :itemcondition"),
    @NamedQuery(name = "Sale.findByPostage", query = "SELECT s FROM Sale s WHERE s.postage = :postage"),
    @NamedQuery(name = "Sale.findByReserveprice", query = "SELECT s FROM Sale s WHERE s.reserveprice = :reserveprice"),
    @NamedQuery(name = "Sale.findBySaleprice", query = "SELECT s FROM Sale s WHERE s.saleprice = :saleprice"),
    @NamedQuery(name = "Sale.findBySaletype", query = "SELECT s FROM Sale s WHERE s.saletype = :saletype"),
    @NamedQuery(name = "Sale.findByStartprice", query = "SELECT s FROM Sale s WHERE s.startprice = :startprice")})

public class Sale implements Serializable, Converter {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "DATELISTED", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datelisted;
    
    @Column(name = "DURATION")
    private Integer duration;
    
    @Size(max = 255)
    @Column(name = "ITEMCONDITION")
    private String itemcondition;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "POSTAGE")
    private Float postage;
    
    @Column(name = "RESERVEPRICE")
    private Float reserveprice;
    
    @Column(name = "SALEPRICE")
    private Float saleprice;
    
    @Size(max = 255)
    @Column(name = "SALETYPE")
    private String saletype;
    
    @Column(name = "STARTPRICE")
    private Float startprice;
    
    @OneToMany(mappedBy = "saleidId")
    private Collection<AuctionBid> auctionBidsCollection;
    
    @JoinColumn(name = "BUYERID_ID", referencedColumnName = "ID")
    @ManyToOne
    private Member buyeridId;
    
    @JoinColumn(name = "ISBN_ISBN", referencedColumnName = "ISBN")
    @ManyToOne
    private Book isbnIsbn;
    
    @JoinColumn(name = "PAYMENTID_ID", referencedColumnName = "ID")
    @ManyToOne
    private Payment paymentidId;
    
    @JoinColumn(name = "SELLERID_ID", referencedColumnName = "ID")
    @ManyToOne
    private Member selleridId;


    public Sale() {
    }

    public Sale(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatelisted() {
        return datelisted;
    }

    public void setDatelisted(Date datelisted) {
        this.datelisted = datelisted;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getItemcondition() {
        return itemcondition;
    }

    public void setItemcondition(String itemcondition) {
        this.itemcondition = itemcondition;
    }

    public Float getPostage() {
        return postage;
    }

    public void setPostage(Float postage) {
        this.postage = postage;
    }

    public Float getReserveprice() {
        return reserveprice;
    }

    public void setReserveprice(Float reserveprice) {
        this.reserveprice = reserveprice;
    }

    public Float getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(Float saleprice) {
        this.saleprice = saleprice;
    }

    public String getSaletype() {
        return saletype;
    }

    public void setSaletype(String saletype) {
        this.saletype = saletype;
    }

    public Float getStartprice() {
        return startprice;
    }

    public void setStartprice(Float startprice) {
        this.startprice = startprice;
    }

    @XmlTransient
    public Collection<AuctionBid> getAuctionBidsCollection() {
        return auctionBidsCollection;
    }

    public void setAuctionBidsCollection(Collection<AuctionBid> auctionBidsCollection) {
        this.auctionBidsCollection = auctionBidsCollection;
    }

    public Member getBuyeridId() {
        return buyeridId;
    }

    public void setBuyeridId(Member buyeridId) {
        this.buyeridId = buyeridId;
    }

    public Book getIsbnIsbn() {
        return isbnIsbn;
    }

    public void setIsbnIsbn(Book isbnIsbn) {
        this.isbnIsbn = isbnIsbn;
    }

    public Payment getPaymentidId() {
        return paymentidId;
    }

    public void setPaymentidId(Payment paymentidId) {
        this.paymentidId = paymentidId;
    }

    public Member getSelleridId() {
        return selleridId;
    }

    public void setSelleridId(Member selleridId) {
        this.selleridId = selleridId;
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
    //@Override
    public Object getAsObject(FacesContext facesContext, 
      UIComponent component, String value) {
      StringBuilder ISBN = new StringBuilder();
      ISBN.append(value);

      Book bookISBN = new Book(ISBN.toString()); 
      return bookISBN;
   }
    @Override
    public String toString() {
        //return "jpa.entities.Sale[ id=" + id + " ]";
        return "" + isbnIsbn;
    }
    @Override
    public String getAsString(FacesContext facesContext,
        UIComponent component, Object value) {
        return value.toString();
    }  
}

///*
// * 
// * author: amanda naismith
// * 
// */
//package javaeetutorial.dukesbookstore.entity;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.Date;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;
//
///**
// *
// * @author Kyle.Lewer
// */
//@FacesConverter("Sale")
////@ManagedBean(name = "newSaleBean", eager = true)
////@SessionScoped
//@Entity
//@Table(name = "sales")
//@XmlRootElement
//public class Sale implements Serializable, Converter {
//
//    private static final long serialVersionUID = 1L;
//    
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    protected Long id;
//    
//    @ManyToOne
//    private Book ISBN;
//    
//    private String saleType; //auction or sale
//    private String itemCondition;
//    private float postage;
//    private float startPrice;
//    private float salePrice;
//    private float reservePrice;
//    private int duration;
//    @ManyToOne
//    private Member buyerId;
//    @ManyToOne
//    private Member sellerId;    
//    @OneToOne
//    private Payment paymentId;
//    private Timestamp dateListed;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Book getISBN() {
//        return ISBN;
//    }
//
//    public void setISBN(Book ISBN) {
//        this.ISBN = ISBN;
//    }
//
//    public String getSaleType() {
//        return saleType;
//    }
//
//    public void setSaleType(String saleType) {
//        this.saleType = saleType;
//    }
//
//    public String getItemCondition() {
//        return itemCondition;
//    }
//
//    public void setItemCondition(String itemCondition) {
//        this.itemCondition = itemCondition;
//    }
//
//    public float getPostage() {
//        return postage;
//    }
//
//    public void setPostage(float postage) {
//        this.postage = postage;
//    }
//
//    public float getStartPrice() {
//        return startPrice;
//    }
//
//    public void setStartPrice(float startPrice) {
//        this.startPrice = startPrice;
//    }
//
//    public float getSalePrice() {
//        return salePrice;
//    }
//
//    public void setSalePrice(float salePrice) {
//        this.salePrice = salePrice;
//    }
//
//    public float getReservePrice() {
//        return reservePrice;
//    }
//
//    public void setReservePrice(float reservePrice) {
//        this.reservePrice = reservePrice;
//    }
//
//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }
//
//    public Member getBuyerId() {
//        return buyerId;
//    }
//
//    public void setBuyerId(Member buyerId) {
//        this.buyerId = buyerId;
//    }
//
//    public Member getSellerId() {        
//        return sellerId;
//    }
//
//    public void setSellerId(Member sellerId) {
//        this.sellerId = sellerId;
//    }
//
//    public Payment getPaymentId() {
//        return paymentId;
//    }
//
//    public void setPaymentId(Payment paymentId) {
//        this.paymentId = paymentId;
//    }
//
//    public Timestamp getDateListed() {
//        return dateListed;
//    }
//
//    public void setDateListed(Timestamp dateListed) {
//        this.dateListed = dateListed;
//    }
//
//    public Sale() {
//    }
//
//    public Sale(Book ISBN) {
//        this.setISBN(ISBN);
//    }
//    
//    public Sale(Book ISBN, String saleType, String itemCondition, int postage) {//, int salePrice) {
//        this.setISBN(ISBN);
//        this.setSaleType(saleType);
//        this.setItemCondition(itemCondition);
//        this.setPostage(postage);
//        this.setSalePrice(salePrice);
//        this.setSellerId(sellerId);
//        //ZoneId zone = ZoneId.of("Australia/Brisbane");
//        //dateListed = LocalDateTime.now(zone);
//        //this.setDateListed(LocalDateTime.now(zone));
//        this.setDateListed(new Timestamp(new Date().getTime()));
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Sale)) {
//            return false;
//        }
//        Sale other = (Sale) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//    
//    @Override
//    public Object getAsObject(FacesContext facesContext, 
//      UIComponent component, String value) {
//      StringBuilder ISBN = new StringBuilder();
//      ISBN.append(value);
//
//      Book bookISBN = new Book(ISBN.toString()); 
//      return bookISBN;
//   }
//
//   @Override
//   public String getAsString(FacesContext facesContext,
//      UIComponent component, Object value) {
//         return value.toString();
//   }    
//    
//    @Override
//    public String toString() {
//        return "javaeetutorial.dukesbookstore.entity.MemberSale[ id=" + id + " ]";
//    }
//
//    //@Override
//    public String toString(LocalDateTime dateListed) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
//        return formatter.format(dateListed);
//    }
//}
